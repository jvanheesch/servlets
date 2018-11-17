package com.github.jvanheesch.stack;

import javax.servlet.*;
import java.io.IOException;

public class Filter2 implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        response.getWriter().write("Filter2 - start. \n");
        chain.doFilter(request, response);
        response.getWriter().write("Filter2 - end. \n");
    }
}
