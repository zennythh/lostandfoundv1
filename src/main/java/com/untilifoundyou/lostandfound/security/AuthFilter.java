package com.untilifoundyou.lostandfound.security;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Collections;

@Component
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);
        String path = req.getRequestURI();

        System.out.println("AuthFilter triggered for: " + path);

        if (session != null) {
            String loggedInUser = (String) session.getAttribute("loggedInUser");
            System.out.println("Session ID: " + session.getId());
            System.out.println("Logged-in user: " + loggedInUser);

            if (loggedInUser != null) {
                // ✅ Set authentication in Spring Security
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        loggedInUser, null, Collections.singletonList(new SimpleGrantedAuthority("USER")));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } else {
            System.out.println("No active session");
        }

        if (!path.startsWith("/auth") && (session == null || session.getAttribute("loggedInUser") == null)) {
            System.out.println("Unauthorized request to: " + path);
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            return;
        }

        chain.doFilter(request, response);
    }
}
