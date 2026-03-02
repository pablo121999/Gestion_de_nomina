package com.pablo.nomina.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class TokenFilter extends OncePerRequestFilter {

    private static final String TOKEN = "123456";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
                                    throws ServletException, IOException {

        String path = request.getRequestURI();

        // 🔓 Permitir archivos estáticos sin token
        if (path.equals("/") ||
            path.equals("/index.html") ||
            path.equals("/empleado-form.html") ||
            path.equals("/nomina.html") ||
            path.equals("/nomina-form.html") ||
            path.startsWith("/css") ||
            path.startsWith("/js") ||
            path.startsWith("/images")) {

            filterChain.doFilter(request, response);
            return;
        }

        String header = request.getHeader("Authorization");

        if (header == null || !header.equals("Bearer " + TOKEN)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(
                        "admin",
                        null,
                        Collections.emptyList()
                );

        SecurityContextHolder.getContext().setAuthentication(auth);

        filterChain.doFilter(request, response);
    }
}