package com.itmianbao.controller;

import com.itmianbao.pojo.BlogData;
import com.itmianbao.pojo.Result;
import com.itmianbao.service.BlogDataservice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;

import static com.itmianbao.controller.UserController.userId;

/**
 * 博客数据控制器
 */
@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:3001")
public class BlogDataController {

    @Autowired
    private BlogDataservice blogDataservice;

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    /**
     * 文章点赞
     */
    @PutMapping("/like/{blogId}/{userId}")
    public Result likeBlog(@PathVariable("blogId")int blogId,@PathVariable("userId")int userId){
        return Result.success(blogDataservice.likeBlog(blogId,userId));
    }

    /**
     * 展示所有博客
     *
     * @return 返回博客列表
     */
    @GetMapping("/showblog")

    public Result list() {
        log.info("查询全部博客");
        // 先从Redis中获取数据
        List<BlogData> blogListFromRedis = (List<BlogData>) redisTemplate.opsForValue().get("blogList");

        List<BlogData> blogListFromDB = blogDataservice.list4();
        blogListFromDB.forEach(blogData ->{
            this.isBlogLiked(blogData);
        });
        return Result.success(blogListFromDB);
    }

    /**
     * 插入博客
     *
     * @param blogData 博客数据
     * @return 返回操作结果
     */
    @PostMapping("/insert")
    public Result save(@RequestBody BlogData blogData) {
        blogDataservice.save(blogData);
        return Result.success();
    }

    /**
     * 删除博客
     *
     * @param blogId 博客ID
     * @return 返回操作结果
     */
    @DeleteMapping("/deleteblog/{blogId}")
    public Result deleteuser(@PathVariable int blogId) {
        blogDataservice.deleteblog(blogId);
        return Result.success();
    }

    /**
     * 根据博客ID获取博客数据
     *
     * @param blogId 博客ID
     * @return 返回博客数据
     */
    @GetMapping("/showdata/{blogId}")
    public Result callbackdata(@PathVariable Integer blogId) {
        BlogData blogData = blogDataservice.getBolgId(blogId);
        //检查是否被点赞了
        isBlogLiked(blogData);

        return Result.success(blogData);
    }

    private void isBlogLiked(BlogData blogData) {
        int userId2 = UserController.userId;
        System.out.println("用户id为:"+userId2);
        int p=0;
        //判断当前用户是否已经点赞
        String key="blog:liked:"+blogData.getBlogId();
        Boolean isMember = stringRedisTemplate.opsForSet().isMember(key,String.valueOf(userId2));
        if(isMember==false){
             p=0;
        }else{
            p=1;
        }
        blogData.setIsLike(p);
    }


    /**
     * 更新博客数据
     *
     * @param blogId   博客ID
     * @param blogData 博客数据
     * @return 返回操作结果
     */
    @PutMapping("/updateblog/{blogId}")
    public Result update(@PathVariable Integer blogId, @RequestBody BlogData blogData) {
        blogDataservice.updateblog(blogId, blogData);
        return Result.success();
    }

    /**
     * 根据分类ID获取博客数据
     *
     * @param categoryId 分类ID
     * @return 返回博客列表
     */
    @GetMapping("/showspecialdata/{categoryId}")
    public Result specialdata(@PathVariable Integer categoryId) {
        List<BlogData> deptList = blogDataservice.getcategoryId(categoryId);
        return Result.success(deptList);
    }

    /**
     * 回收站
     *
     * @return 返回回收站博客列表
     */
    @GetMapping("/showrubbish")
    public Result list2() {
        List<BlogData> deptList = blogDataservice.list6();
        return Result.success(deptList);
    }

    /**
     * 博客管理点击删除回收到回收站
     *
     * @param blogId 博客ID
     * @return 返回操作结果
     */
    @PutMapping("/recoverblog/{blogId}")
    public Result update2(@PathVariable Integer blogId) {
        blogDataservice.recoverblog(blogId);
        return Result.success();
    }

    /**
     * 回收站点击回收返回博客管理
     *
     * @param blogId 博客ID
     * @return 返回操作结果
     */
    @PutMapping("/restoreblog/{blogId}")
    public Result update3(@PathVariable Integer blogId) {
        blogDataservice.restoreblog(blogId);
        return Result.success();
    }

    /**
     * 增加浏览次数
     *
     * @param blogId 博客ID
     * @return 返回操作结果
     */
    @PutMapping("/updatebrowse/{blogId}")
    public Result update(@PathVariable Integer blogId) {
        blogDataservice.updatebrowse(blogId);
        return Result.success();
    }
}
