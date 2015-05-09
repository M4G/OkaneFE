package com.m4g.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

/**
 * Created with IntelliJ IDEA.
 * User: Meir Gutnik
 * Date: 5/7/15
 * Time: 2:46 PM
 */
public class Dashboard extends HorizontalLayout implements View{

    private HorizontalLayout header;
    private VerticalLayout workspace;
    private Panel content;

    public Dashboard() {
        configureComponents();
        buildLayout();
    }

    private void configureComponents() {
        header = new HorizontalLayout();
        header.addComponent(new Header());
        workspace = new VerticalLayout();
        content = new Panel();
    }

    private void buildLayout() {
        addStyleName("mainview");
        workspace.addComponent(header);
        workspace.setComponentAlignment(header, Alignment.MIDDLE_RIGHT);
        content.setWidth(100, Unit.PERCENTAGE);
        content.setHeight(100, Unit.PERCENTAGE);
        workspace.addComponent(content);
        SideMenu menu = new SideMenu();
        addComponent(menu);
        addComponent(workspace);
        setWidth(100, Unit.PERCENTAGE);
        setHeight(100, Unit.PERCENTAGE);
    }

    public void setContent(Component component){
        content.setContent(component);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
