package com.m4g.config;

import com.vaadin.spring.annotation.EnableVaadin;
import com.vaadin.spring.server.SpringVaadinServlet;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebServlet;

/**
 * Created by MaxG on 09-May-15.
 */
@Component
public class OkaneWebConfiguration {
    @WebServlet(value = "/*", asyncSupported = true )
    public static class Servlet extends SpringVaadinServlet {
    }

    @Configuration
    @EnableVaadin
    public static class MyConfiguration {
    }
}
