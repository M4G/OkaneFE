package com.m4g.view;

import com.vaadin.navigator.Navigator.EmptyView;
import com.vaadin.navigator.View;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;

public enum ContentType {
    DASHBOARD("dashboard", EmptyView.class, FontAwesome.HOME, true), SALES(
            "sales", EmptyView.class, FontAwesome.BAR_CHART_O, false), TRANSACTIONS(
            "transactions", EmptyView.class, FontAwesome.TABLE, false), REPORTS(
            "reports", EmptyView.class, FontAwesome.FILE_TEXT_O, true), SCHEDULE(
            "schedule", CalendarView.class, FontAwesome.CALENDAR_O, false);

    private final String viewName;
    private final Class<? extends View> viewClass;
    private final Resource icon;
    private final boolean stateful;

    private ContentType(final String viewName,
                        final Class<? extends View> viewClass, final Resource icon,
                        final boolean stateful) {
        this.viewName = viewName;
        this.viewClass = viewClass;
        this.icon = icon;
        this.stateful = stateful;
    }

    public boolean isStateful() {
        return stateful;
    }

    public String getViewName() {
        return viewName;
    }

    public Class<? extends View> getViewClass() {
        return viewClass;
    }

    public Resource getIcon() {
        return icon;
    }

    public static ContentType getByViewName(final String viewName) {
        ContentType result = null;
        for (ContentType viewType : values()) {
            if (viewType.getViewName().equals(viewName)) {
                result = viewType;
                break;
            }
        }
        return result;
    }

}
