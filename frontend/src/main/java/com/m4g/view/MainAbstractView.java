package com.m4g.view;

import com.m4g.components.Header;
import com.m4g.components.SideMenu;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Created with IntelliJ IDEA.
 * User: Meir Gutnik
 * Date: 5/7/15
 * Time: 2:46 PM
 */
public abstract class MainAbstractView extends HorizontalLayout implements View {

    @Autowired
    private SideMenu sideMenu;

    @Autowired
    private Header header;

    private HorizontalLayout headerLayout;
    private VerticalLayout workspace;
    private Panel content;

    protected abstract void addContent();

    @PostConstruct
    public void init(){
        configureComponents();
        buildLayout();
        addContent();
    }

    private void configureComponents() {
        headerLayout = new HorizontalLayout();
        headerLayout.addComponent(header);
        workspace = new VerticalLayout();
        content = new Panel();
    }

    private void buildLayout() {
        addStyleName("mainview");
        workspace.addComponent(header);
        workspace.setComponentAlignment(header, Alignment.MIDDLE_RIGHT);
        content.setWidth(100, Unit.PERCENTAGE);
        content.setHeight(100, Unit.PERCENTAGE);
        workspace.setWidth(100, Unit.PERCENTAGE);
        workspace.addComponent(content);
        addComponent(sideMenu);
        addComponent(workspace);
        setExpandRatio(workspace, 1.0f);
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
