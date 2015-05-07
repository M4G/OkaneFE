package com.m4g.view;

import com.m4g.controller.HeaderController;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;

/**
 * Created by MaxG on 07-May-15.
 */
public class Header extends CustomComponent {

    public Header() {
        setSizeUndefined();
        setCompositionRoot(buildContent());
    }

    private Component buildContent() {
        HorizontalLayout content = new HorizontalLayout();

        Button config = new Button();
        config.setIcon(FontAwesome.GEAR);
        content.addComponent(config);

        Button info = new Button();
        info.setIcon(FontAwesome.INFO_CIRCLE);
        content.addComponent(info);

        Button logout = new Button();
        logout.setIcon(FontAwesome.POWER_OFF);
        //TODO: change to inject
        logout.addClickListener(new HeaderController());
        content.addComponent(logout);

        return  content;
    }
}
