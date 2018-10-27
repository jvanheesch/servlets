package com.github.jvanheesch;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

public class HelloWorldPage extends WebPage {
    private static final long serialVersionUID = 1662231773525391863L;

    @Override
    protected void onInitialize() {
        super.onInitialize();

        this.add(new Label("message", "Hello World!"));
    }
}
