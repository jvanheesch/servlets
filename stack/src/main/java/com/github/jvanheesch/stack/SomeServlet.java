package com.github.jvanheesch.stack;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SomeServlet", urlPatterns = "/someServlet", asyncSupported = true)
public class SomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().write("SomeServlet - start. \n");
        response.getWriter().write("SomeServlet - end. \n");
    }
}
