package com.github.jvanheesch.async;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class LogPostBodyFilter extends HttpFilter {
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * IMPORTANT NOTE: Since WE create the baos, is, bufferedReader, it is OUR responsibility to close these.
     * We close them right after chain.doFilter(), which WON'T WORK IN ASYNC mode
     * (which is fine, given that @WebFilter's asyncSupported defaults to false).
     */
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOGGER.info("Begin doFilter, threadID: {}.", Thread.currentThread().getId());

        if (request.getMethod().equals("POST")) {
            // log "lazily", i.e.: log to baos while input is being read, log baos afterwards.
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ServletInputStream is = new InterceptorServletInputStream(request.getInputStream(), baos);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            try (
                    ByteArrayOutputStream baosx = new ByteArrayOutputStream();
            ) {
                HttpServletRequest wrappedRequest = new HttpServletRequestWrapper(request) {
                    @Override
                    public BufferedReader getReader() throws IOException {
                        return bufferedReader;
                    }

                    @Override
                    public ServletInputStream getInputStream() throws IOException {
                        return is;
                    }
                };

                chain.doFilter(wrappedRequest, response);
                String postBody = new String(baos.toByteArray());
                LOGGER.info("PostBody: {}.", postBody);
            }
        } else {
            chain.doFilter(request, response);
        }

        LOGGER.info("End doFilter, threadID: {}.", Thread.currentThread().getId());
    }
}
