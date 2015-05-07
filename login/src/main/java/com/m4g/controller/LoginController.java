package com.m4g.controller;

import com.vaadin.ui.Button;

/**
 * Created with IntelliJ IDEA.
 * User: Meir Gutnik
 * Date: 5/7/15
 * Time: 2:57 PM
 */
public class LoginController implements Button.ClickListener {
    @Override
    public void buttonClick(Button.ClickEvent event) {
        String host = event.getButton().getUI().getPage().getLocation().getHost();
        event.getButton().getUI().getPage().setLocation(host+"/dashboard");
    }
}
