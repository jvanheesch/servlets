package com.github.jvanheesch;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SessionInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (PrintWriter writer = resp.getWriter()) {
            writer.println(String.format("Your session id is %s.", req.getSession().getId()));
            writer.println("In Chome, you can verify this by checking your JSESSIONID cookie at chrome://settings/cookies/detail?site=localhost.");
        }
    }
}
