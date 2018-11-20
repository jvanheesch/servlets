package com.github.jvanheesch.stack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "InfoServlet", urlPatterns = "/info/*", asyncSupported = true)
public class InfoServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOGGER.info("InfoServlet - start.");
        PrintWriter writer = response.getWriter();
        writer.write("InfoServlet - start. \n");
        writer.write(String.format("ContextPath: %s \n", request.getContextPath()));
        writer.write(String.format("ServletPath: %s \n", request.getServletPath()));
        writer.write(String.format("PathInfo: %s \n", request.getPathInfo()));
        writer.write("InfoServlet - end. \n");
        LOGGER.info("InfoServlet - end.");
    }
}
