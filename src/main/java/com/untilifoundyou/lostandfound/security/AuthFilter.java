package com.untilifoundyou.lostandfound.security;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class AuthFilter implements Filter{

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        String path = req.getRequestURI();
    
        System.out.println("Request to: " + path);
        System.out.println("Session ID: " + (session != null ? session.getId() : "No session"));
        System.out.println("Logged-in user: " + (session != null ? session.getAttribute("loggedInUser") : "None"));
    
        if (!path.startsWith("/auth") && (session == null || session.getAttribute("loggedInUser") == null)) {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            return;
        }
    
        chain.doFilter(request, response);
    }
    
}