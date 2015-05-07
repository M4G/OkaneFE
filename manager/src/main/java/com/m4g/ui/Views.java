package com.m4g.ui;

import com.m4g.view.Dashboard;
import com.m4g.view.LoginView;
import com.vaadin.navigator.View;

public enum Views {
    LOGIN("login", LoginView.class),
    DASHBOARD("dashboard", Dashboard.class);

    private final String viewName;
    private final Class<? extends View> viewClass;

    private Views(final String viewName, final Class<? extends View> viewClass) {
        this.viewName = viewName;
        this.viewClass = viewClass;
    }

    public String getViewName() {
        return viewName;
    }

    public Class<? extends View> getViewClass() {
        return viewClass;
    }

    public static Views getByViewName(final String viewName) {
        Views result = null;
        for (Views viewType : values()) {
            if (viewType.getViewName().equals(viewName)) {
                result = viewType;
                break;
            }
        }
        return result;
    }

}
