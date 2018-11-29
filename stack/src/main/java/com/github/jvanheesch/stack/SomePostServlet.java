package com.github.jvanheesch.stack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SomePostServlet", urlPatterns = "/somePostServlet", asyncSupported = true)
public class SomePostServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOGGER.info("SomePostServlet - start.");
        byte[] read = IOUtils.read(request.getInputStream());

        String param1 = request.getParameter("param1");
        String param2 = request.getParameter("param2");

        System.out.println(new String(read));
        System.out.println(param1);
        System.out.println(param2);

        response.getWriter().write("SomePostServlet - start. \n");
        response.getWriter().write("SomePostServlet - end. \n");
        LOGGER.info("SomePostServlet - end.");
    }
}
