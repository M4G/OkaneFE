package com.m4g.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Calendar;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.calendar.CalendarComponentEvents;
import com.vaadin.ui.components.calendar.handler.BasicBackwardHandler;

import java.util.Date;

/**
 * Created by MaxG on 09-May-15.
 */
public class CalendarView extends VerticalLayout implements View {

    private Calendar calendar;

    public CalendarView(){
        calendar = new Calendar();
        calendar.setStartDate(new Date());
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(java.util.Calendar.DATE, 8);
        calendar.setEndDate(cal.getTime());
        calendar.setHandler(new BasicBackwardHandler() {
            protected void setDates(CalendarComponentEvents.BackwardEvent event,
                                    Date start, Date end) {

                java.util.Calendar calendar = event.getComponent()
                        .getInternalCalendar();
                if (isThisYear(calendar, end)
                        && isThisYear(calendar, start)) {
                    super.setDates(event, start, end);
                }
            }});
        addComponent(calendar);
    }

    private boolean isThisYear(java.util.Calendar calendar, Date date) {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(date);
        int endYear = cal.get(java.util.Calendar.YEAR);
        int todayYear = calendar.get(java.util.Calendar.YEAR);
        return endYear==todayYear;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
