package com.m4g.controller;

import com.vaadin.server.Sizeable;
import com.vaadin.ui.*;

/**
 * Created by MaxG on 07-May-15.
 */
public class HeaderController implements Button.ClickListener {
    @Override
    public void buttonClick(Button.ClickEvent event) {
        final Button button = event.getButton();
        if("logout".equals(button.getId())) {
            button.getUI().getPage().setLocation("/");
        }
        else if("info".equals(button.getId())){
            final Window window = new Window("Information");
            window.setWidth(300.0f, Sizeable.Unit.PIXELS);
            final FormLayout content = new FormLayout();
            window.setContent(content);
            window.setModal(true);
            window.center();
            UI.getCurrent().addWindow(window);
        }
        else if("config".equals(button.getId())){

        }
    }
}
