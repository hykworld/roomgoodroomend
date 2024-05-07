package com.room.good.security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

@Log4j2
public class CustomAccessDeniedHandler implements AccessDeniedHandler {// 예외처리

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response, AccessDeniedException accessException)
            throws IOException, ServletException {

        log.error("Access Denied Handler");

        log.error("Redirect....");
        response.sendRedirect("/loginshop");
        //response.sendRedirect("/error"); // error페이지로 이동

    }

}
