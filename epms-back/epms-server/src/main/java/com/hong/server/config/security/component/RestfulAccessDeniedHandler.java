package com.hong.server.config.security.component;

import com.hong.server.pojo.RespBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Description: 当访问接口没有权限时，自定义返回结果
 * @Author: 黄海宏
 * @Create: 2023-01-09 19:57
 **/
@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        /*httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");*/
        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();
        RespBean bean = RespBean.error("权限不足，请联系管理");
        bean.setCode(401);
        pw.write(new ObjectMapper().writeValueAsString(bean));
        pw.flush();
        pw.close();
    }
}
