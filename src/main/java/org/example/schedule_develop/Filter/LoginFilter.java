package org.example.schedule_develop.Filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

public class LoginFilter implements Filter {
    private static final String[] WHITE_LIST = {"/", "/users", "/users/login"};


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String requestURI = httpRequest.getRequestURI();

        // 화이트리스트에 포함되지 않은 경우, 로그인 여부 확인
        if (!isWhiteList(requestURI)) {
            HttpSession session = httpRequest.getSession(false);
            if (session == null || session.getAttribute("LOGIN_USER") == null) {
                // 로그인 하지 않은 경우 에러 반환
                httpResponse.sendError(401, "로그인 해주세요.");
                return;
            }
        }
        chain.doFilter(request, response);
    }

    /**
     * whitelist의 경우 인증 체크 X
     */
    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }

}


