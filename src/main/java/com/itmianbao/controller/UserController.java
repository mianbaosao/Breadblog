package com.itmianbao.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.itmianbao.common.JwtUtils;
import com.itmianbao.pojo.Result;
import com.itmianbao.pojo.User;
import com.itmianbao.service.UserService;
import com.itmianbao.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 用户
 */
@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:3001")
@RequestMapping("/user")
public class   UserController {

    public static int userId;
    @Autowired
    private UserService userService;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    private RedisUtil  redisUtil;

    /**
     * 短信验证码的发送
     */
    @GetMapping("/phoneCode")
    public Result sendSms() {
        try {
            // 创建 SendMsg 实例
            SendMsg sendMsg = new SendMsg();
            // 获取短信客户端
            com.aliyun.dysmsapi20170525.Client client = sendMsg.createClient();
            // 生成随机的四位数字
            String randomCode = String.valueOf(new Random().nextInt(9000) + 1000);
            System.out.print("此时手机验证码为:"+" "+randomCode);
            // 设置发送短信的参数
            redisUtil.set("phoneCode",randomCode,120);
            com.aliyun.dysmsapi20170525.models.SendSmsRequest sendSmsRequest = new com.aliyun.dysmsapi20170525.models.SendSmsRequest()
                    .setSignName("BreadBlog")
                    .setTemplateCode("SMS_465387823")
                    .setPhoneNumbers("13140303042")
                    .setTemplateParam("{\"code\":\"" + randomCode + "\"}");
            com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
            // 发送短信
            client.sendSmsWithOptions(sendSmsRequest, runtime);
            return Result.success(randomCode);
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
            return Result.error("获取失败");
        }

    }

    /**
     * 验证码图片的发送
     */
    @GetMapping("/checkCode")
    public void getCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // 设置响应内容类型为PNG图像，并禁用缓存
            response.setContentType("image/png");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // 增强缓存控制策略
            response.setDateHeader("Expires", 0);

            Object[] objs = CodeUtil.createImage();
            String mm = (String) objs[0];
            System.out.println("Generated Code: " + mm);
            redisUtil.set("checkCode",mm,120);
            HttpSession session = request.getSession(true); // 确保获取到session对象
            session.removeAttribute("imgcode");
            session.setAttribute("imgcode", mm);
            BufferedImage image = (BufferedImage) objs[1];

            OutputStream outputStream = response.getOutputStream();
            ImageIO.write(image, "png", outputStream);

            // 关闭OutputStream以释放资源（尽管在Servlet容器中通常会自动关闭）
            outputStream.flush();
            outputStream.close();

        } catch (IOException e) {
            log.error("Failed to generate or write captcha image: {}", e.getMessage()); // 使用日志记录器记录异常信息
            throw new IOException("无法生成或写出验证码图片", e); // 重新抛出异常以便框架处理
        }
    }
    /**
     * 根据用户ID删除用户
     *
     * @param id 用户ID
     * @return 返回操作结果
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteuser(@PathVariable Integer id) {
        userService.deleteuser(id);
        return Result.success();
    }

    /**
     * 用户登录
     *
     * @param user 用户信息
     * @return 返回登录结果，成功返回JWT令牌，失败返回错误信息
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        log.info("员工登录 {}", user);
        User e = userService.login(user);
        String submittedCode = user.getCode();
        System.out.println("程序保存为"+":"+redisUtil.get("checkCode"));
        System.out.println("用户输入为"+":"+submittedCode);
        if(!redisUtil.get("checkCode").equals(submittedCode)){
            return  Result.error("验证码错误");
        }
        // 登录成功生成令牌，返回令牌
        if (e != null) {
            if(e.getAccount().equals("1623666966")){
                StpAdminUtil.login(e.getId());
                System.out.println("管理员登录成功");
            }else{
                StpUserUtil.login(e.getId());
                System.out.println("用户登陆成功");
            }
            Map<String, Object> claims = new HashMap<>();
            // 生成JWT令牌之前，将用户名添加到claims中
            claims.put("id", e.getId());
            claims.put("password", e.getPassword());
            claims.put("account", e.getAccount());
            claims.put("username", e.getUsername());
            claims.put("cover", e.getCover());
            claims.put("token",StpAdminUtil.getTokenInfo());

            userId=e.getId();
            // 生成JWT令牌，并将其赋值给jwt
            String jwt = JwtUtils.generateJwt(claims); // jwt包含了当前登录的员工信息
            System.out.println(StpAdminUtil.getTokenInfo().tokenValue);
            return Result.success(jwt);
        }
        // 登陆失败，返回错误信息
        return Result.error("用户名或密码错误");
    }
    /**
     * 手机号验证码登录
     * @param phone
     * @return 直接返回jwt令牌此时已经通过验证了
     */
    @GetMapping("/phoneLogin/{phone}")
    public Result phoneLogin(@PathVariable String phone){
        log.info("手机验证码登录{}",phone);
        User e=userService.phoneLogin(phone);
        // 登录成功生成令牌，返回令牌
        if (e != null) {
            Map<String, Object> claims = new HashMap<>();
            // 生成JWT令牌之前，将用户名添加到claims中
            claims.put("id", e.getId());
            claims.put("password", e.getPassword());
            claims.put("account", e.getAccount());
            claims.put("username", e.getUsername());
            claims.put("cover", e.getCover());
            if(e.getAccount().equals("1623666966")){
                StpAdminUtil.login(e.getId());
                System.out.println("管理员登录成功");
            }else{
                StpUserUtil.login(e.getId());
                System.out.println("用户登陆成功");
            }
            // 生成JWT令牌，并将其赋值给jwt
            String jwt = JwtUtils.generateJwt(claims); // jwt包含了当前登录的员工信息
            return Result.success(jwt);
        }
        return Result.error("获取失败");
    }

    /**
     * 更新用户信息
     *
     * @param user 用户信息
     * @return 返回操作结果
     */
    @PutMapping("/updateuser")
    public Result updateuser(@RequestBody User user, @PathVariable String phoneCode) {
        userService.updateuser(user);
        return Result.success();
    }

    /**
     * 根据用户ID查询用户信息
     *
     * @param id 用户ID
     * @return 返回用户信息
     */
    @GetMapping("/showuser/{id}")
    public Result list(@PathVariable Integer id) {
        List<User> userList = userService.list5(id);
        return Result.success(userList);
    }

    /**
     * 查询所有用户信息
     *
     * @return 返回所有用户信息
     */
    @GetMapping("/showalluser")
    public Result showall() {
        List<User> userList = userService.showuser();
        return Result.success(userList);
    }

}
