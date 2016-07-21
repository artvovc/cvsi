package com.winify.cvsi.server.security;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends UsernamePasswordAuthenticationFilter {
    private TokenUtils tokenUtils;
    private UserDetailsServiceImpl userDetailsService;
    private Logger log = Logger.getLogger(JwtFilter.class);

    @Autowired
    public JwtFilter(UserDetailsServiceImpl userDetailsService, TokenUtils tokenUtils) {
        this.userDetailsService = userDetailsService;
        this.tokenUtils = tokenUtils;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        tokenUtils = WebApplicationContextUtils
                .getRequiredWebApplicationContext(this.getServletContext())
                .getBean(TokenUtils.class);
        userDetailsService = WebApplicationContextUtils
                .getRequiredWebApplicationContext(this.getServletContext())
                .getBean(UserDetailsServiceImpl.class);


        HttpServletResponse resp = (HttpServletResponse) res;
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
        resp.setHeader("Access-Control-Max-Age", "3600");
        resp.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, " + "X-Auth-Token");


        HttpServletRequest httpRequest = (HttpServletRequest) req;
        String authToken = httpRequest.getHeader("X-Auth-Token");
        String username = this.tokenUtils.getUsernameFromToken(authToken);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if (this.tokenUtils.validateToken(authToken, userDetails)) {
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        chain.doFilter(req, res);
    }
}
