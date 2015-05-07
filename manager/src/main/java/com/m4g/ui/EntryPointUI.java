package com.m4g.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

/**
 * Created with IntelliJ IDEA.
 * User: Meir Gutnik
 * Date: 5/6/15
 * Time: 1:24 PM
 */

@Title("Okane")
@Theme("valo")
public class EntryPointUI extends UI {

    private Navigator navigator;

    @Override
    protected void init(VaadinRequest request) {
        navigator = new Navigator(this, this);
        for(Views view : Views.values()){
            navigator.addView(view.getViewName(), view.getViewClass());
        }
        setNavigator(navigator);
        navigator.navigateTo(Views.LOGIN.getViewName());
    }
}
