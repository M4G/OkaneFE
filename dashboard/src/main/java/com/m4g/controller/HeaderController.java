package com.m4g.controller;

import com.vaadin.ui.Button;

/**
 * Created by MaxG on 07-May-15.
 */
public class HeaderController implements Button.ClickListener {
    @Override
    public void buttonClick(Button.ClickEvent event) {
        event.getButton().getUI().getPage().setLocation("/");
    }
}
