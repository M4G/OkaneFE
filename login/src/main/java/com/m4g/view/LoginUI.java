package com.m4g.view;

import com.m4g.controller.LoginController;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.*;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.io.File;

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
    private Image         logo;

    @Override
    protected void init(VaadinRequest request) {
        configureComponents();
        buildLayout();
    }

    private void configureComponents() {
        username = new TextField("Username");
        username.setIcon(FontAwesome.USER);
        username.focus();

        password = new PasswordField("Password");
        password.setIcon(FontAwesome.LOCK);

        loginButton = new Button("Login");
        //TODO: change instantination to injection
        loginButton.addClickListener(new LoginController());

        String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
        FileResource resource = new FileResource(new File(basepath + "/WEB-INF/images/logo.png"));
        logo = new Image("",resource);
    }

    private void buildLayout() {
        GridLayout grid = new GridLayout(3,3);
        grid.setWidth(100, Unit.PERCENTAGE);
        grid.setHeight(100, Unit.PERCENTAGE);

        grid.addComponent(logo, 1, 0);
        grid.setComponentAlignment(logo, Alignment.MIDDLE_CENTER);

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
