package com.m4g.view;

import com.m4g.config.Views;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.components.calendar.CalendarComponentEvents;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.BackwardEvent;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.ForwardEvent;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.RangeSelectEvent;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.RangeSelectHandler;
import com.vaadin.ui.components.calendar.event.BasicEvent;
import com.vaadin.ui.components.calendar.event.CalendarEvent;
import com.vaadin.ui.components.calendar.handler.BasicBackwardHandler;
import com.vaadin.ui.components.calendar.handler.BasicDateClickHandler;
import com.vaadin.ui.components.calendar.handler.BasicForwardHandler;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by MaxG on 09-May-15.
 */
@SpringView(name = Views.ACCOUNTS)
public class AccountsView extends MainAbstractView{

    private Grid grid;
    private VerticalLayout layout;

    @Override
    protected void addContent() {
        setSizeFull();
        layout = new VerticalLayout();
        layout.setSizeFull();

        grid = new Grid();
        grid.setSizeFull();
        layout.addComponent(grid);
        setContent(layout);
    }
}
