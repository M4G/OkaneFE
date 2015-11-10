package com.m4g.view;

import com.m4g.config.Views;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;

/**
 * Created with IntelliJ IDEA.
 * User: Meir Gutnik
 * Date: 5/7/15
 * Time: 2:46 PM
 */
@SpringView(name = Views.DASHBOARD)
public class DashboardView extends MainAbstractView{

    @Override
    protected void addContent() {
    }
}
