package com.m4g.view;

import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

public class SideMenu extends CustomComponent {


    public SideMenu() {
        setSizeUndefined();
        setCompositionRoot(buildContent());
    }

    private Component buildContent() {
        addStyleName("valo-menu");
        CssLayout menuContent = new CssLayout();
        menuContent.addStyleName("sidebar");
        menuContent.addStyleName(ValoTheme.MENU_PART);
        menuContent.setWidth(null);
        menuContent.setHeight(100, Unit.PERCENTAGE);
        menuContent.addComponent(buildTitle());
        menuContent.addComponent(buildMenuItems());

        return menuContent;
    }

    private Component buildTitle() {
        Label logo = new Label("<strong>Okane</strong> management suite", ContentMode.HTML);
        logo.setSizeUndefined();
        HorizontalLayout logoWrapper = new HorizontalLayout(logo);
        logoWrapper.setComponentAlignment(logo, Alignment.MIDDLE_CENTER);
        Button closeButton = new Button();
        closeButton.setIcon(FontAwesome.BARS);
        logoWrapper.addComponent(closeButton);
        logoWrapper.setComponentAlignment(closeButton, Alignment.MIDDLE_RIGHT);
        logoWrapper.addStyleName("valo-menu-title");

        return logoWrapper;
    }

    private Component buildMenuItems() {
        CssLayout menuItemsLayout = new CssLayout();
        menuItemsLayout.addStyleName("valo-menuitems");
        menuItemsLayout.setHeight(100.0f, Unit.PERCENTAGE);

        for (final ContentType view : ContentType.values()) {
            Component menuItemComponent = new ValoMenuItemButton(view);
            menuItemsLayout.addComponent(menuItemComponent);
        }
        return menuItemsLayout;

    }

    public final class ValoMenuItemButton extends Button {

        public ValoMenuItemButton(final ContentType view) {
            setPrimaryStyleName("valo-menu-item");
            setIcon(view.getIcon());
            setCaption(view.getViewName().substring(0, 1).toUpperCase()
                    + view.getViewName().substring(1));
            //DashboardEventBus.register(this);
            addClickListener(new ClickListener() {
                @Override
                public void buttonClick(final ClickEvent event) {
                    //UI.getCurrent().getNavigator()
                    //        .navigateTo(view.getViewName());
                    if (UI.getCurrent().getContent() instanceof Dashboard) {
                        try {
                            ((Dashboard) UI.getCurrent().getContent()).setContent((Component) view.getViewClass().newInstance());
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }
}
