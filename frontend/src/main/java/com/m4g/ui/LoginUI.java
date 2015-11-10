package com.m4g.ui;

import com.m4g.config.Paths;
import com.m4g.config.Views;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: Meir Gutnik
 * Date: 5/6/15
 * Time: 1:24 PM
 */

@Title("Welcome")
@Theme("okane")
@SpringUI
public class LoginUI extends UI {

    @Autowired
    private SpringViewProvider viewProvider;

    @Override
    protected void init(VaadinRequest request) {
        new Navigator(this, this);
        getNavigator().addProvider(viewProvider);
        setStyleName("loginview");

        boolean isLoggedIn = getSession().getAttribute("user") != null;
        if(isLoggedIn){
            getUI().getPage().setLocation(Paths.MAIN);
        }
        else{
            getNavigator().navigateTo(Views.LOGIN);
        }
    }
}
