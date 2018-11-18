package com.github.jvanheesch.stack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SomeServlet", urlPatterns = "/someServlet", asyncSupported = true)
public class SomeServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOGGER.info("SomeServlet - start.");
        response.getWriter().write("SomeServlet - start. \n");
        response.getWriter().write("SomeServlet - end. \n");
        LOGGER.info("SomeServlet - end.");
    }
}
