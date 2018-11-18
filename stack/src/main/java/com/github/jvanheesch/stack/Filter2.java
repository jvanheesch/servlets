package com.github.jvanheesch.stack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

public class Filter2 implements Filter {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOGGER.info("Filter2 - start.");
        response.getWriter().write("Filter2 - start. \n");
        chain.doFilter(request, response);
        response.getWriter().write("Filter2 - end. \n");
        LOGGER.info("Filter2 - end.");
    }
}
