package com.github.jvanheesch;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

public class RequestLoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try (ServletInputStream inputStream = request.getInputStream()) {
            byte[] input = read(inputStream);

            String requestAsString = new String(input);
            System.out.println("<requestAsString>");
            System.out.println(requestAsString);
            System.out.println("</requestAsString>");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(input))) {
                @Override
                public void close() throws IOException {
                    super.close();

                    System.out.println("Closed printWriter, stracktrace: ");
                    new Exception().printStackTrace(System.out);
                }
            };

            chain.doFilter(
                    new HttpServletRequestWrapper((HttpServletRequest) request) {
                        @Override
                        public ServletInputStream getInputStream() {
                            throw new RuntimeException("Not sure when this is called - if this is needed, it should probably be changed.");
                        }

                        @Override
                        public BufferedReader getReader() {
                            return bufferedReader;
                        }
                    },
                    response
            );
        }
    }

    private static byte[] read(InputStream inputStream) throws IOException {
        try (ByteArrayOutputStream result = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            return buffer;
        }
    }
}
