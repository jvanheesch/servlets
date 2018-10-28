package com.github.jvanheesch;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RequestReturnedServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (PrintWriter writer = resp.getWriter()) {
            // when doing a post with postman, the response
            // contains a header content-length with size == IOUtils.BUFFER_SIZE.
            writer.write(new String(IOUtils.read(req.getInputStream())));
        }
    }
}
