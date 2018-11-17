package com.github.jvanheesch.stack;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * See {@link javax.servlet.RequestDispatcher#forward(ServletRequest, ServletResponse)}:
 *
 * <code>forward</code> should be called before the response has been
 * committed to the client (before response body output has been flushed).
 * If the response already has been committed, this method throws an
 * <code>IllegalStateException</code>. Uncommitted output in the response
 * buffer is automatically cleared before the forward.
 */
@WebServlet(name = "SomeForwardingServlet", urlPatterns = "/someForwardingServlet", asyncSupported = true)
public class SomeForwardingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.getWriter().write("SomeForwardingServlet - start. \n");
        request.getRequestDispatcher("/someServlet").forward(request, response);
        response.getWriter().write("SomeForwardingServlet - end. \n");
    }
}
