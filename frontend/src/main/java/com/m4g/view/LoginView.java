package com.m4g.view;

import com.m4g.config.Paths;
import com.m4g.config.Views;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinService;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import javax.annotation.PostConstruct;
import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: Meir Gutnik
 * Date: 5/6/15
 * Time: 1:24 PM
 */

@SpringView(name = Views.LOGIN)
public class LoginView extends GridLayout implements View, Button.ClickListener{

    @Autowired
    MessageSource messageSource;

    private PasswordField password;
    private TextField     username;
    private Button        loginButton;
    private Image         logo;

    public LoginView() {
        super(3,3);
    }

    @PostConstruct
    public void init(){
        configureComponents();
        buildLayout();
    }

    private void configureComponents() {
        username = new TextField(messageSource.getMessage("title.username",null,UI.getCurrent().getLocale()));
        username.setIcon(FontAwesome.USER);
        username.focus();

        password = new PasswordField("Password");
        password.setIcon(FontAwesome.LOCK);

        loginButton = new Button("Login");
        //TODO: change instantination to injection
        loginButton.addClickListener(this);

        String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
        FileResource resource = new FileResource(new File(basepath + "/WEB-INF/images/logo.png"));
        logo = new Image("",resource);
    }

    private void buildLayout() {
        setWidth(100, Unit.PERCENTAGE);
        setHeight(100, Unit.PERCENTAGE);

        addComponent(logo, 1, 0);
        setComponentAlignment(logo, Alignment.MIDDLE_CENTER);

        VerticalLayout loginForm = new VerticalLayout();
        loginForm.setSpacing(true);
        loginForm.addComponent(username);
        loginForm.setComponentAlignment(username, Alignment.MIDDLE_CENTER);
        loginForm.addComponent(password);
        loginForm.setComponentAlignment(password, Alignment.MIDDLE_CENTER);
        loginForm.addComponent(loginButton);
        loginForm.setComponentAlignment(loginButton, Alignment.MIDDLE_CENTER);
        loginForm.addStyleName("login-panel");

        addComponent(loginForm, 1, 1);
        setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        getSession().setAttribute("user", username.getValue());
        getUI().getPage().setLocation(Paths.MAIN);
    }
}
