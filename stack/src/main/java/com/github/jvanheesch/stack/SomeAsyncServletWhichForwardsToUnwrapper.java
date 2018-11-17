package com.github.jvanheesch.stack;

import javax.servlet.AsyncContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SomeAsyncServletWhichForwardsToUnwrapper", urlPatterns = "/someAsyncServletWhichForwardsToUnwrapper", asyncSupported = true)
public class SomeAsyncServletWhichForwardsToUnwrapper extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AsyncContext asyncContext = request.startAsync(request, response);
        asyncContext.dispatch("/someAsyncServletWhichUnwraps");
    }
}
