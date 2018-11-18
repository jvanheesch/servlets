package com.github.jvanheesch.stack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.AsyncContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SomeAsyncServletWhichUnwraps", urlPatterns = "/someAsyncServletWhichUnwraps", asyncSupported = true)
public class SomeAsyncServletWhichUnwraps extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOGGER.info("SomeAsyncServletWhichUnwraps - start.");
        response.getWriter().write("SomeAsyncServletWhichUnwraps - start. \n");

        while (request instanceof HttpServletRequestWrapper) {
            request = (HttpServletRequest) ((HttpServletRequestWrapper) request).getRequest();
        }

        AsyncContext asyncContext = request.startAsync(request, response);
        asyncContext.dispatch("/someServlet");

        response.getWriter().write("SomeAsyncServletWhichUnwraps - end. \n");
        LOGGER.info("SomeAsyncServletWhichUnwraps - end.");
    }
}
