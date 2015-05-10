package com.m4g.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.EnableVaadin;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.spring.server.SpringVaadinServlet;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;

/**
 * Created with IntelliJ IDEA.
 * User: Meir Gutnik
 * Date: 5/6/15
 * Time: 1:24 PM
 */

@Title("Okane")
@Theme("okane")
@SpringUI
public class EntryPointUI extends UI {

    private Navigator navigator;

    @Autowired
    private SpringViewProvider viewProvider;

    @Override
    protected void init(VaadinRequest request) {
        navigator = new Navigator(this, this);
        for(Views view : Views.values()){
            navigator.addView(view.getViewName(), view.getViewClass());
        }
        setNavigator(navigator);
        navigator.navigateTo(Views.LOGIN.getViewName());
        setStyleName("loginview");

        Navigator navigator = new Navigator(this, this);
        navigator.addProvider(viewProvider);
    }
}
