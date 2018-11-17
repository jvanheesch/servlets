package com.github.jvanheesch.async;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * An example of a blocking servlet.
 */
@WebServlet(name = "SleepyServlet", urlPatterns = "/sleepy")
public class SleepyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        long timeoutInMs = Long.parseLong(request.getParameter("timeoutInMs"));
        try {
            Thread.sleep(timeoutInMs);
        } catch (InterruptedException e) {
            throw new ServletException("Something went wrong...", e);
        }
        response.setContentType("text/html");
        response.getWriter().println("God I love a good night's sleep.");
    }
}
