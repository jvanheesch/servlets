package com.github.jvanheesch.stack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.AsyncContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SomeAsyncServlet", urlPatterns = "/someAsyncServlet", asyncSupported = true)
public class SomeAsyncServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOGGER.info("SomeAsyncServlet - start.");
        AsyncContext asyncContext = request.startAsync(request, response);
        asyncContext.dispatch("/someServlet");
        LOGGER.info("SomeAsyncServlet - end.");
    }
}
