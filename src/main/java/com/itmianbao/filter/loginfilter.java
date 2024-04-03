package com.itmianbao.filter;

import com.alibaba.fastjson.JSONObject;
import com.itmianbao.common.JwtUtils;
import com.itmianbao.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Response;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@Slf4j
@CrossOrigin(origins = "http://localhost:3001")
/*@WebFilter(urlPatterns = "/*")*/
public class loginfilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        // 获取请求方法
        String method = req.getMethod();

        // 如果是预检请求（OPTIONS 请求），直接放行
        if ("OPTIONS".equals(method)) {
            chain.doFilter(request, response);
            return;
        }

        // 获取请求参数url
        String url = req.getRequestURL().toString();
        log.info("请求的url: {}", url);

        // 判断请求url中是否包含 login，如果包含，说明是登录操作，放行
        if (url.contains("login")) {
            log.info("登录操作,放行");
            chain.doFilter(request, response);
            return;
        }

        // 获取请求头中的令牌
        String jwt = req.getHeader("Token");
        log.info("Received Token Header: " + jwt);

        // 判断令牌是否存在，如果不存在返回错误结果
        if (!StringUtils.hasLength(jwt)) {
            log.info("请求头 Token 为空,返回未登录信息");
            Result error = Result.error("not_login");
            // 手动转换对象为 JSON
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }

        // 解析 Token，如果解析失败也返回错误
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            e.printStackTrace();  // jwt解析失败
            log.info("解析失败返回错误信息");
            Result error = Result.error("not_login");
            // 手动转换对象为 JSON
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }

        // 放行
        log.info("放行！！！");
        chain.doFilter(request, response);
    }

}
