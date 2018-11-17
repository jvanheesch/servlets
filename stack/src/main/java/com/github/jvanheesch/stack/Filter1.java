package com.github.jvanheesch.stack;

import javax.servlet.*;
import java.io.IOException;

public class Filter1 implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        response.getWriter().write("Filter1 - start. \n");
        chain.doFilter(request, response);
        response.getWriter().write("Filter1 - end. \n");
    }
}
