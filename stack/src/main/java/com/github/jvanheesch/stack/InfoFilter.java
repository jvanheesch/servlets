package com.github.jvanheesch.stack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;

@WebFilter(urlPatterns = "/info")
public class InfoFilter extends HttpFilter {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOGGER.info("InfoFilter - start.");
        PrintWriter writer = response.getWriter();
        writer.write("InfoFilter - start. \n");
        writer.write(String.format("RequestURI: %s \n", request.getRequestURI()));
        writer.write(String.format("Method: %s \n", request.getMethod()));
        writer.write(String.format("ContextPath: %s \n", request.getContextPath()));
        writer.write(String.format("ServletPath: %s \n", request.getServletPath()));
        writer.write(String.format("PathInfo: %s \n", request.getPathInfo()));
        writer.write(String.format("ContentType: %s \n", request.getContentType()));
        writer.write(String.format("CharacterEncoding: %s \n", request.getCharacterEncoding()));
        writer.write(String.format("ContentLengthLong: %s \n", request.getContentLengthLong()));
        writer.write(String.format("DispatcherType: %s \n", request.getDispatcherType()));
        writer.write(String.format("Protocol: %s \n", request.getProtocol()));
        writer.write(String.format("LocalAddr: %s \n", request.getLocalAddr()));
        writer.write(String.format("LocalName: %s \n", request.getLocalName()));
        writer.write(String.format("LocalPort: %s \n", request.getLocalPort()));
        writer.write(String.format("RemoteAddr: %s \n", request.getRemoteAddr()));
        writer.write(String.format("RemoteHost: %s \n", request.getRemoteHost()));
        writer.write(String.format("RemotePort: %s \n", request.getRemotePort()));
        writer.write(String.format("RemoteUser: %s \n", request.getRemoteUser()));
        writer.write(String.format("Scheme: %s \n", request.getScheme()));
        writer.write(String.format("ServerName: %s \n", request.getServerName()));
        writer.write(String.format("ServerPort: %s \n", request.getServerPort()));
        writer.write(String.format("Secure: %s \n", request.isSecure()));
        writer.write(String.format("Cookies: %s \n", Arrays.toString(request.getCookies())));
        writer.write("Headers: \n");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            // https://webaim.org/blog/user-agent-string-history/
            String headerName = headerNames.nextElement();
            writer.write(String.format("  %s: %s \n", headerName, request.getHeader(headerName)));
        }
        writer.write("Attributes: \n");
        Enumeration<String> attributeNames = request.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            writer.write(String.format("  %s: %s \n", attributeName, request.getAttribute(attributeName)));
        }
        writer.write("Parameters    : \n");
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            writer.write(String.format("  %s: %s \n", paramName, request.getParameter(paramName)));
        }
        ServletContext servletContext = request.getServletContext();
        writer.write(String.format("ServletContext: %s \n", servletContext));
        // TODO_JORIS: fix effectiveMajorVersion
//        writer.write(String.format("  ContextPath: %s \n", servletContext.getContextPath()));
//        writer.write(String.format("  MajorVersion: %s \n", servletContext.getMajorVersion()));
        writer.write(String.format("  EffectiveMajorVersion: %s \n", servletContext.getEffectiveMajorVersion()));
//        writer.write(String.format("  MinorVersion: %s \n", servletContext.getMinorVersion()));
//        writer.write(String.format("  EffectiveMinorVersion: %s \n", servletContext.getEffectiveMinorVersion()));
        // TODO_JORIS: getInitParameterNames
        // TODO_JORIS: getAttributeNames
        // TODO_JORIS: getServletRegistrations
        // TODO_JORIS: getFilterRegistrations
//        writer.write(String.format("  ContextPath: %s \n", servletContext.getContextPath()));
        LOGGER.info("InfoFilter - end.");
    }
}
