package com.example.demo.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Administrator on 2017/6/19 0019.
 */
public class Encoding implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoder=filterConfig.getInitParameter("encoding");
    }
    String encoder=null;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletResponse.setCharacterEncoding("utf-8");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
