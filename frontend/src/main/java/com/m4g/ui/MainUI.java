package com.m4g.ui;

import com.m4g.config.Paths;
import com.m4g.config.Views;
import com.m4g.view.DashboardView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
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
@SpringUI(path = Paths.MAIN)
public class MainUI extends UI {
    @Autowired
    private SpringViewProvider viewProvider;

    @Override
    protected void init(VaadinRequest request) {
        new Navigator(this, this);
        getNavigator().addProvider(viewProvider);
        getNavigator().navigateTo(Views.DASHBOARD);
        setStyleName("loginview");

        getPage().addUriFragmentChangedListener(new Page.UriFragmentChangedListener() {
            @Override
            public void uriFragmentChanged(Page.UriFragmentChangedEvent event) {
                String fragment = event.getUriFragment();
                View view = viewProvider.getView(fragment);
                DashboardView dashboardView = (DashboardView) viewProvider.getView(Views.DASHBOARD);
                dashboardView.setContent((com.vaadin.ui.Component) view);
            }
        });

        getNavigator().addViewChangeListener(new ViewChangeListener() {

            @Override
            public boolean beforeViewChange(ViewChangeEvent event) {

                // Check if a user has logged in
                boolean isLoggedIn = getSession().getAttribute("user") != null;
                boolean isLoginView = event.getNewView().equals(viewProvider.getView(Views.LOGIN));

                if (!isLoggedIn && !isLoginView) {
                    // Redirect to login view always if a user has not yet
                    // logged in
                    getNavigator().navigateTo(Views.LOGIN);
                    return false;

                } else if (isLoggedIn && isLoginView) {
                    // If someone tries to access to login view while logged in,
                    // then cancel
                    return false;
                }

                return true;
            }

            @Override
            public void afterViewChange(ViewChangeEvent event) {

            }
        });
    }
}
