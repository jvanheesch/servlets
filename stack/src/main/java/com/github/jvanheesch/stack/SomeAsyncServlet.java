package com.github.jvanheesch.stack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.AsyncContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * EDIT2: No longer broken, and I don't think I changed anything ...
 * EDIT: even /someServlet is failing, so it's something else...
 * TODO: this IS failing: container tries to call filter again, which results in error:
 * <p>
 * 10:54:17.425 [http-nio-8080-exec-1] INFO  com.github.jvanheesch.stack.Filter1 - Filter1 - start.
 * 10:54:17.426 [http-nio-8080-exec-1] INFO  com.github.jvanheesch.stack.Filter2 - Filter2 - start.
 * 10:54:17.426 [http-nio-8080-exec-1] INFO  com.github.jvanheesch.stack.SomeAsyncServlet - SomeAsyncServlet - start.
 * 10:54:17.428 [http-nio-8080-exec-1] INFO  com.github.jvanheesch.stack.SomeAsyncServlet - SomeAsyncServlet - end.
 * 10:54:17.428 [http-nio-8080-exec-1] INFO  com.github.jvanheesch.stack.Filter2 - Filter2 - end.
 * 10:54:17.428 [http-nio-8080-exec-1] INFO  com.github.jvanheesch.stack.Filter1 - Filter1 - end.
 * 10:54:17.430 [http-nio-8080-exec-1] INFO  com.github.jvanheesch.stack.SomeServlet - SomeServlet - start.
 * 10:54:17.430 [http-nio-8080-exec-1] INFO  com.github.jvanheesch.stack.SomeServlet - SomeServlet - end.
 * 10:54:17.567 [http-nio-8080-exec-2] INFO  org.springframework.web.servlet.DispatcherServlet - Initializing Servlet 'dispatcherServlet'
 * 10:54:17.571 [http-nio-8080-exec-2] INFO  org.springframework.web.servlet.DispatcherServlet - Completed initialization in 4 ms
 * 10:54:17.572 [http-nio-8080-exec-2] INFO  com.github.jvanheesch.stack.Filter1 - Filter1 - start.
 * 10:54:17.572 [http-nio-8080-exec-2] INFO  com.github.jvanheesch.stack.Filter2 - Filter2 - start.
 * 10:54:17.677 [http-nio-8080-exec-2] ERROR org.apache.catalina.core.ContainerBase.[Tomcat].[localhost].[/].[dispatcherServlet] - Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is java.lang.IllegalStateException: getWriter() has already been called for this response] with root cause
 * java.lang.IllegalStateException: getWriter() has already been called for this response
 * at org.apache.catalina.connector.Response.getOutputStream(Response.java:548) ~[tomcat-embed-core-9.0.12.jar:9.0.12]
 * at org.apache.catalina.connector.ResponseFacade.getOutputStream(ResponseFacade.java:210) ~[tomcat-embed-core-9.0.12.jar:9.0.12]
 * at org.springframework.http.server.ServletServerHttpResponse.getBody(ServletServerHttpResponse.java:83) ~[spring-web-5.1.2.RELEASE.jar:5.1.2.RELEASE]
 * at org.springframework.http.converter.ResourceHttpMessageConverter.writeContent(ResourceHttpMessageConverter.java:132) ~[spring-web-5.1.2.RELEASE.jar:5.1.2.RELEASE]
 * at org.springframework.http.converter.ResourceHttpMessageConverter.writeInternal(ResourceHttpMessageConverter.java:124) ~[spring-web-5.1.2.RELEASE.jar:5.1.2.RELEASE]
 * at org.springframework.http.converter.ResourceHttpMessageConverter.writeInternal(ResourceHttpMessageConverter.java:45) ~[spring-web-5.1.2.RELEASE.jar:5.1.2.RELEASE]
 * at org.springframework.http.converter.AbstractHttpMessageConverter.write(AbstractHttpMessageConverter.java:227) ~[spring-web-5.1.2.RELEASE.jar:5.1.2.RELEASE]
 * at org.springframework.web.servlet.resource.ResourceHttpRequestHandler.handleRequest(ResourceHttpRequestHandler.java:488) ~[spring-webmvc-5.1.2.RELEASE.jar:5.1.2.RELEASE]
 * at org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter.handle(HttpRequestHandlerAdapter.java:53) ~[spring-webmvc-5.1.2.RELEASE.jar:5.1.2.RELEASE]
 * at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1038) ~[spring-webmvc-5.1.2.RELEASE.jar:5.1.2.RELEASE]
 * at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:942) ~[spring-webmvc-5.1.2.RELEASE.jar:5.1.2.RELEASE]
 * at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:998) ~[spring-webmvc-5.1.2.RELEASE.jar:5.1.2.RELEASE]
 * at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:890) ~[spring-webmvc-5.1.2.RELEASE.jar:5.1.2.RELEASE]
 * at javax.servlet.http.HttpServlet.service(HttpServlet.java:634) ~[tomcat-embed-core-9.0.12.jar:9.0.12]
 * at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:875) ~[spring-webmvc-5.1.2.RELEASE.jar:5.1.2.RELEASE]
 * at javax.servlet.http.HttpServlet.service(HttpServlet.java:741) ~[tomcat-embed-core-9.0.12.jar:9.0.12]
 * at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231) ~[tomcat-embed-core-9.0.12.jar:9.0.12]
 * at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) ~[tomcat-embed-core-9.0.12.jar:9.0.12]
 * at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53) ~[tomcat-embed-websocket-9.0.12.jar:9.0.12]
 * at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) ~[tomcat-embed-core-9.0.12.jar:9.0.12]
 * at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) ~[tomcat-embed-core-9.0.12.jar:9.0.12]
 * at com.github.jvanheesch.stack.Filter2.doFilter(Filter2.java:16) ~[classes/:?]
 * at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) ~[tomcat-embed-core-9.0.12.jar:9.0.12]
 * at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) ~[tomcat-embed-core-9.0.12.jar:9.0.12]
 * at com.github.jvanheesch.stack.Filter1.doFilter(Filter1.java:16) ~[classes/:?]
 * at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) ~[tomcat-embed-core-9.0.12.jar:9.0.12]
 * at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) ~[tomcat-embed-core-9.0.12.jar:9.0.12]
 * at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:99) ~[spring-web-5.1.2.RELEASE.jar:5.1.2.RELEASE]
 * at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) ~[spring-web-5.1.2.RELEASE.jar:5.1.2.RELEASE]
 * at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) ~[tomcat-embed-core-9.0.12.jar:9.0.12]
 * at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) ~[tomcat-embed-core-9.0.12.jar:9.0.12]
 * at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:92) ~[spring-web-5.1.2.RELEASE.jar:5.1.2.RELEASE]
 * at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) ~[spring-web-5.1.2.RELEASE.jar:5.1.2.RELEASE]
 * at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) ~[tomcat-embed-core-9.0.12.jar:9.0.12]
 * at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) ~[tomcat-embed-core-9.0.12.jar:9.0.12]
 * at org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:93) ~[spring-web-5.1.2.RELEASE.jar:5.1.2.RELEASE]
 * at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) ~[spring-web-5.1.2.RELEASE.jar:5.1.2.RELEASE]
 * at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) ~[tomcat-embed-core-9.0.12.jar:9.0.12]
 * at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) ~[tomcat-embed-core-9.0.12.jar:9.0.12]
 * at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:200) ~[spring-web-5.1.2.RELEASE.jar:5.1.2.RELEASE]
 * at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) ~[spring-web-5.1.2.RELEASE.jar:5.1.2.RELEASE]
 * at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) ~[tomcat-embed-core-9.0.12.jar:9.0.12]
 * at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) ~[tomcat-embed-core-9.0.12.jar:9.0.12]
 * at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:199) [tomcat-embed-core-9.0.12.jar:9.0.12]
 * at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96) [tomcat-embed-core-9.0.12.jar:9.0.12]
 * at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:490) [tomcat-embed-core-9.0.12.jar:9.0.12]
 * at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:139) [tomcat-embed-core-9.0.12.jar:9.0.12]
 * at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92) [tomcat-embed-core-9.0.12.jar:9.0.12]
 * at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74) [tomcat-embed-core-9.0.12.jar:9.0.12]
 * at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343) [tomcat-embed-core-9.0.12.jar:9.0.12]
 * at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:408) [tomcat-embed-core-9.0.12.jar:9.0.12]
 * at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66) [tomcat-embed-core-9.0.12.jar:9.0.12]
 * at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:770) [tomcat-embed-core-9.0.12.jar:9.0.12]
 * at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1415) [tomcat-embed-core-9.0.12.jar:9.0.12]
 * at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49) [tomcat-embed-core-9.0.12.jar:9.0.12]
 * at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149) [?:1.8.0_181]
 * at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624) [?:1.8.0_181]
 * at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61) [tomcat-embed-core-9.0.12.jar:9.0.12]
 * at java.lang.Thread.run(Thread.java:748) [?:1.8.0_181]
 */
@WebServlet(name = "SomeAsyncServlet", urlPatterns = "/someAsyncServlet", asyncSupported = true)
public class SomeAsyncServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOGGER.info("SomeAsyncServlet - start.");
        AsyncContext asyncContext = request.startAsync(request, response);
        asyncContext.dispatch("/someServlet");
        LOGGER.info("SomeAsyncServlet - end.");
    }
}
