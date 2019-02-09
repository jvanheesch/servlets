package com.github.jvanheesch.async;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.AsyncContext;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = AsyncFilter.URL_PATTERN_WITH_WILDCARD, asyncSupported = true)
public class AsyncFilter extends HttpFilter {
    public static final String URL_PATTERN = "/async";
    public static final String URL_PATTERN_WITH_WILDCARD = URL_PATTERN + "/*";
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOGGER.info("Begin doFilter, threadID: {}.", Thread.currentThread().getId());

        String requestPath = request.getServletPath();

        if (!requestPath.startsWith(URL_PATTERN)) {
            throw new IllegalStateException("This filter can't handle url " + requestPath);
        }

        String destPath = requestPath.replace(URL_PATTERN, "");

        AsyncContext asyncContext = request.startAsync();
        asyncContext.dispatch(destPath);

        LOGGER.info("End doFilter, threadID: {}.", Thread.currentThread().getId());
    }
}
