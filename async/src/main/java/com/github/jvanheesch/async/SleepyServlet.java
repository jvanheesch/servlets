package com.github.jvanheesch.async;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SleepyServlet", urlPatterns = "/sleepy", asyncSupported = true)
public class SleepyServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Begin doGet, threadID: {}.", Thread.currentThread().getId());

        long timeoutInMs = Long.parseLong(request.getParameter("timeoutInMs"));
        try {
            Thread.sleep(timeoutInMs);
            response.setContentType("text/html");
            response.getWriter().println("God I love a good night's sleep.");
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException("Something went wrong...", e);
        }

        LOGGER.info("End doGet, threadID: {}.", Thread.currentThread().getId());
    }
}
