package com.github.jvanheesch;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class ResponseLoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            PrintWriter printWriter = new PrintWriter(byteArrayOutputStream) {
                @Override
                public void close() {
                    super.close();

                    System.out.println("Closed printWriter, stracktrace: ");
                    new Exception().printStackTrace(System.out);
                }
            };

            chain.doFilter(request, new HttpServletResponseWrapper((HttpServletResponse) response) {
                @Override
                public ServletOutputStream getOutputStream() {
                    throw new RuntimeException("Not sure when this is called - if this is needed, it should probably be changed to return byteArrayOutputStream.");
                }

                @Override
                public PrintWriter getWriter() {
                    // should probably always return same writer.
                    return printWriter;
                }
            });

            String responseAsString = byteArrayOutputStream.toString();
            System.out.println("<responseAsString>");
            System.out.println(responseAsString);
            System.out.println("</responseAsString>");
            response.getOutputStream().write(responseAsString.getBytes());
        }
    }
}
