package com.github.jvanheesch;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

public class MyWebApplication extends WebApplication {
    @Override
    public Class<? extends WebPage> getHomePage() {
        return HelloWorldPage.class;
    }
}
