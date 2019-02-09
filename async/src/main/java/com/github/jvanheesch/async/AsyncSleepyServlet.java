package com.github.jvanheesch.async;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.AsyncContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * An example of an ASYNC servlet.
 * The container is still blocking, however, due to the fact that server.tomcat.max-threads=1.
 * The real work now happens in {@link AsyncContext#start(Runnable)}.
 * See https://stackoverflow.com/questions/10073392/whats-the-purpose-of-asynccontext-start-in-servlet-3-0/
 * and https://dzone.com/articles/limited-usefulness.
 */
@WebServlet(name = "AsyncSleepyServlet", urlPatterns = "/asyncSleepy", asyncSupported = true)
public class AsyncSleepyServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Begin doGet, threadID: {}.", Thread.currentThread().getId());
        AsyncContext asyncContext = request.startAsync();
        asyncContext.start(() -> {
            LOGGER.info("Begin sleep, threadID: {}.", Thread.currentThread().getId());
            long timeoutInMs = Long.parseLong(request.getParameter("timeoutInMs"));
            try {
                Thread.sleep(timeoutInMs);
                response.setContentType("text/html");
                response.getWriter().println("God I love a good night's sleep.");
                LOGGER.info("End sleep");
                asyncContext.complete();
            } catch (InterruptedException | IOException e) {
                throw new RuntimeException("Something went wrong...", e);
            }
        });
        LOGGER.info("End doGet");
    }
}
