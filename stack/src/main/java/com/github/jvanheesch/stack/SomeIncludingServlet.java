package com.github.jvanheesch.stack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * See {@link javax.servlet.RequestDispatcher#include(ServletRequest, ServletResponse)}:
 */
@WebServlet(name = "SomeIncludingServlet", urlPatterns = "/someIncludingServlet", asyncSupported = true)
public class SomeIncludingServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOGGER.info("SomeIncludingServlet - start.");
        response.getWriter().write("SomeIncludingServlet - start. \n");
        request.getRequestDispatcher("/someServlet").include(request, response);
        response.getWriter().write("SomeIncludingServlet - end. \n");
        LOGGER.info("SomeIncludingServlet - end.");
    }
}
