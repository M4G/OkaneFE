package com.m4g.view;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;

/**
 * Created with IntelliJ IDEA.
 * User: Meir Gutnik
 * Date: 5/6/15
 * Time: 1:24 PM
 */

@Title("Login")
@Theme("valo")
public class LoginUI extends UI {

    private PasswordField password;
    private TextField     username;
    private Button        loginButton;

    @Override
    protected void init(VaadinRequest request) {
        configureComponents();
        buildLayout();
    }

    private void configureComponents() {
        username = new TextField();
        password = new PasswordField();
        loginButton = new Button("Login");
    }

    private void buildLayout() {
        GridLayout grid = new GridLayout(3,3);
        grid.setWidth(100, Unit.PERCENTAGE);
        grid.setHeight(100, Unit.PERCENTAGE);

        VerticalLayout loginForm = new VerticalLayout();
        loginForm.setSpacing(true);
        loginForm.addComponent(username);
        loginForm.setComponentAlignment(username, Alignment.MIDDLE_CENTER);
        loginForm.addComponent(password);
        loginForm.setComponentAlignment(password, Alignment.MIDDLE_CENTER);
        loginForm.addComponent(loginButton);
        loginForm.setComponentAlignment(loginButton, Alignment.MIDDLE_CENTER);

        grid.addComponent(loginForm, 1, 1);
        grid.setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);

        setContent(grid);
    }
}
