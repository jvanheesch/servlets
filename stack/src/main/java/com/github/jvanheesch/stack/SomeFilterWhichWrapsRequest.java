package com.github.jvanheesch.stack;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This filter supports async (asyncSupported defaults to false for @WebFilter, but Spring's FilterRegistrationBean defaults this to true...).
 * A filter should make no assumptions about the remaining components in the filter chain.
 * Therefore, if a filter supports async, two different situations can occur:
 * 1. The chain is sync, and the requestWrapper object can be "released" after filterChain returns.
 * 2. The chain is async, and the requestWrapper object can - in general - not yet be "released" after filterChain returns.
 * The latter happens when the requestWrapper object is used by the async process.
 */
public class SomeFilterWhichWrapsRequest extends HttpFilter {
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        response.getWriter().write("SomeFilterWhichWrapsRequest - start. \n");
        MyHttpServletRequestWrapper requestWrapper = new MyHttpServletRequestWrapper(request);
        chain.doFilter(requestWrapper, response);
        response.getWriter().write("SomeFilterWhichWrapsRequest: requestWrapper.getAsyncContext().hasOriginalRequestAndResponse(): " + requestWrapper.getAsyncContext().hasOriginalRequestAndResponse() + "\n");
        response.getWriter().write("SomeFilterWhichWrapsRequest - end. \n");
    }

    private static class MyHttpServletRequestWrapper extends HttpServletRequestWrapper {
        MyHttpServletRequestWrapper(HttpServletRequest request) {
            super(request);
        }
    }
}
