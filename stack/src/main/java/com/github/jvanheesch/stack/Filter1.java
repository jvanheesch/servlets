package com.github.jvanheesch.stack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class Filter1 implements Filter {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOGGER.info("Filter1 - start.");
        String path = ((HttpServletRequest) request).getRequestURI();
        if (path.startsWith("/favicon")) {
            chain.doFilter(request, response); // Just continue chain.
        } else {
            response.getWriter().write("Filter1 - start. \n");
            chain.doFilter(request, response);
            response.getWriter().write("Filter1 - end. \n");
        }
        LOGGER.info("Filter1 - end.");
    }
}
