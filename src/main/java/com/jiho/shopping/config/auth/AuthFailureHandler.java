package com.jiho.shopping.config.auth;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@Component
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res, AuthenticationException exception)
        throws IOException, ServletException{
        String errMsg;
        if(exception instanceof BadCredentialsException){
            errMsg = "아이디 또는 비밀번호가 잘못되었습니다";
        } else if (exception instanceof InternalAuthenticationServiceException){
            errMsg = "존재하지 않은 계정 입니다";
        } else if (exception instanceof AuthenticationCredentialsNotFoundException){
            errMsg = "인증 요청이 거부되었습니다";
        } else {
            errMsg = "알 수 없는 오류로 로그인에 실패하였습니다";
        }
        errMsg = URLEncoder.encode(errMsg,"UTF-8");
        setDefaultFailureUrl("/signin?error=true&exception="+errMsg);

        super.onAuthenticationFailure(req,res,exception);

    }

}
