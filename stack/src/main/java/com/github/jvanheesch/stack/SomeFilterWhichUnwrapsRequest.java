package com.github.jvanheesch.stack;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("OverlyStrongTypeCast")
public class SomeFilterWhichUnwrapsRequest extends HttpFilter {
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        response.getWriter().write("SomeFilterWhichUnwrapsRequest - start. \n");

        while (request instanceof HttpServletRequestWrapper) {
            request = (HttpServletRequest) ((HttpServletRequestWrapper) request).getRequest();
        }

        chain.doFilter(request, response);
        response.getWriter().write("SomeFilterWhichUnwrapsRequest - end. \n");
    }
}
