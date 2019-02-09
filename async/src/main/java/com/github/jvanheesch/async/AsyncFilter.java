package com.github.jvanheesch.async;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

        AsyncContext asyncContext = request.startAsync(request, response);

        asyncContext.addListener(new LoggingAsyncListener());

        asyncContext.dispatch(destPath);

        LOGGER.info("End doFilter, threadID: {}.", Thread.currentThread().getId());
    }

    private static class LoggingAsyncListener implements AsyncListener {
        @Override
        public void onComplete(AsyncEvent event) throws IOException {
            LOGGER.info("onComplete");
        }

        @Override
        public void onTimeout(AsyncEvent event) throws IOException {
            LOGGER.info("onTimeout");
        }

        @Override
        public void onError(AsyncEvent event) throws IOException {
            LOGGER.info("onError");
        }

        @Override
        public void onStartAsync(AsyncEvent event) throws IOException {
            LOGGER.info("onStartAsync");
        }
    }
}
