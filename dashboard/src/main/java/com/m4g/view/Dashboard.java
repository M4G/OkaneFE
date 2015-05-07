package com.m4g.view;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;

/**
 * Created with IntelliJ IDEA.
 * User: Meir Gutnik
 * Date: 5/7/15
 * Time: 2:46 PM
 */
public class Dashboard extends HorizontalSplitPanel implements View{

    private HorizontalLayout header;
    private VerticalLayout workspace;
    private VerticalLayout sideMenu;
    private VerticalLayout content;

    public Dashboard() {
        configureComponents();
        buildLayout();
    }

    private void configureComponents() {
        header = new HorizontalLayout();
        header.addComponent(new Header());
        workspace = new VerticalLayout();
        sideMenu = new VerticalLayout();
        sideMenu.addComponent(new SideMenu());
        content = new VerticalLayout();
    }

    private void buildLayout() {
        workspace.addComponent(header);
        workspace.setComponentAlignment(header, Alignment.MIDDLE_RIGHT);
        workspace.addComponent(content);
        setFirstComponent(sideMenu);
        setSecondComponent(workspace);
        setSplitPosition(15, Unit.PERCENTAGE);
        setWidth(100, Unit.PERCENTAGE);
        setHeight(100, Unit.PERCENTAGE);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
