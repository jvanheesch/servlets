package com.github.jvanheesch.async;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

// example: curl http://localhost:8080/post -d '{"abc": "def"}' -H "Content-Type: application/json"
@WebServlet(name = "ReturnPostBodyServlet", urlPatterns = "/post", asyncSupported = true)
public class ReturnPostBodyServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            String postBody = request.getReader().lines().collect(Collectors.joining("\n"));
            LOGGER.info("PostBody: {}.", postBody);
            response.setContentType("text/html");
            response.getWriter().println(postBody);
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong...", e);
        }
    }
}