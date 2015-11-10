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
@SpringView(name = Views.CALENDAR)
public class CalendarView extends MainAbstractView{

    private Calendar calendar;
    private VerticalLayout layout;

    @Override
    protected void addContent() {
        setSizeFull();
        layout = new VerticalLayout();
        layout.setSizeFull();
        calendar = new Calendar();
        calendar.setSizeFull();
        DateTime dateTime = new DateTime(new Date());
        calendar.setStartDate(new Date(dateTime.withDayOfMonth(1).getMillis()));
        calendar.setEndDate(new Date(dateTime.withDayOfMonth(dateTime.dayOfMonth().getMaximumValue()).getMillis()));
        calendar.setHandler(new BasicBackwardHandler() {
            @Override
            protected void setDates(BackwardEvent event, Date start, Date end) {
                java.util.Calendar calendar = event.getComponent()
                        .getInternalCalendar();
                if (isThisYear(calendar, end) && isThisYear(calendar, start)) {
                    super.setDates(event, start, end);
                }
            }
        });

        // Make sure the date is in the same year as today
        calendar.setHandler(new BasicForwardHandler() {

            private static final long serialVersionUID = -4718745721954015665L;

            @Override
            protected void setDates(ForwardEvent event, Date start, Date end) {
                java.util.Calendar calendar = event.getComponent()
                        .getInternalCalendar();
                if (isThisYear(calendar, start) && isThisYear(calendar, end)) {
                    super.setDates(event, start, end);
                }
            }
        });

        // Set a date click handler
        calendar.setHandler(new BasicDateClickHandler() {

            private static final long serialVersionUID = -5736213235806322345L;

            @Override
            public void dateClick(CalendarComponentEvents.DateClickEvent event) {
                Calendar cal = event.getComponent();
                long currentCalDateRange = calendar.getEndDate().getTime()
                        - calendar.getStartDate().getTime();

                if (currentCalDateRange < TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)) {
                    // Change the date range to the current week
                    DateTime dateTime = new DateTime(event.getDate());
                    calendar.setStartDate(new Date(dateTime.withDayOfWeek(1).getMillis()));
                    calendar.setEndDate(new Date(dateTime.withDayOfWeek(7).getMillis()));

                }
                else if (currentCalDateRange < TimeUnit.MILLISECONDS.convert(8, TimeUnit.DAYS) &&
                        currentCalDateRange > TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)) {
                    // Change the date range to the current month
                    DateTime dateTime = new DateTime(event.getDate());
                    calendar.setStartDate(new Date(dateTime.withDayOfMonth(1).getMillis()));
                    calendar.setEndDate(new Date(dateTime.withDayOfMonth(dateTime.dayOfMonth().getMaximumValue()).getMillis()));

                }else {
                    // Default behaviour, change date range to one day
                    super.dateClick(event);
                }
            }
        });

        // allow moving to week view by clicking the weeknumber only if the
        // weeks start and end dates are on the current month
/*        calendar.setHandler(new BasicWeekClickHandler() {

            private static final long serialVersionUID = -4996892647238456687L;

            @Override
            protected void setDates(WeekClick event, Date start, Date end) {
                java.util.Calendar calendar = event.getComponent()
                        .getInternalCalendar();
                if (isThisMonth(calendar, start) && isThisMonth(calendar, end)) {
                    super.setDates(event, start, end);
                }
            }
        });*/

/*        calendar.setHandler(new EventClickHandler() {

            private static final long serialVersionUID = 4548304318112120161L;

            public void eventClick(EventClick event) {
                BasicEvent e = (BasicEvent) event.getCalendarEvent();
                 getMainWindow().showNotification(
                        "Event clicked: " + e.getCaption(), e.getDescription());
            }
        });*/

        // Set the event move listener
/*        calendar.setHandler(new BasicEventMoveHandler() {

            private static final long serialVersionUID = -3196912587103065037L;
            private java.util.Calendar javaCalendar;

            @Override
            public void eventMove(MoveEvent event) {
                javaCalendar = event.getComponent().getInternalCalendar();
                super.eventMove(event);

            }

            @Override
            protected void setDates(CalendarEventEditor event, Date start,
                                    Date end) {
                if (isThisMonth(javaCalendar, start)
                        && isThisMonth(javaCalendar, end)) {
                    super.setDates(event, start, end);
                }
            }
        });*/

        // Set the drag selection handler
        calendar.setHandler(new RangeSelectHandler() {

            private static final long serialVersionUID = 8078355786341501794L;

            public void rangeSelect(RangeSelectEvent event) {
                BasicEvent calendarEvent = new BasicEvent();
                calendarEvent.setStart(event.getStart());
                calendarEvent.setEnd(event.getEnd());

                // Create popup window and add a form in it.
                VerticalLayout layout = new VerticalLayout();
                layout.setMargin(true);
                layout.setSpacing(true);

                final Window w = new Window(null, layout);
                w.setWidth("400px");
                w.setModal(true);
                w.center();

                // Wrap the calendar event to a BeanItem and pass it to the form
                final BeanItem<CalendarEvent> item = new BeanItem<CalendarEvent>(
                        calendarEvent);

                final Form form = new Form();
                //form.setWriteThrough(false);
                form.setItemDataSource(item);
                form.setFormFieldFactory(new FormFieldFactory() {

                    private static final long serialVersionUID = -831979043038483438L;

                    public Field createField(Item item, Object propertyId,
                                             Component uiContext) {
                        if (propertyId.equals("caption")) {
                            TextField f = new TextField("Caption");
                            f.setNullRepresentation("");
                            f.focus();
                            return f;

                        }
                        return null;
                    }
                });
                form.setVisibleItemProperties(new Object[] { "caption" });

                layout.addComponent(form);

                HorizontalLayout buttons = new HorizontalLayout();
                buttons.setSpacing(true);
                buttons.addComponent(new Button("OK", new ClickListener() {

                    private static final long serialVersionUID = 7174826216293514881L;

                    public void buttonClick(ClickEvent event) {
                        form.commit();
                        // Update event provider's data source
                       // provider.addEvent(item.getBean());
                        // Calendar needs to be repainted
                        calendar.requestRepaint();

                       // getMainWindow().removeWindow(w);
                    }
                }));
                buttons.addComponent(new Button("Cancel", new ClickListener() {

                    private static final long serialVersionUID = 3909972672766063318L;

                    public void buttonClick(ClickEvent event) {
                        //getMainWindow().removeWindow(w);
                    }
                }));
                layout.addComponent(buttons);
                layout.setComponentAlignment(buttons, Alignment.BOTTOM_RIGHT);

                //getMainWindow().addWindow(w);
            }
        });

/*        calendar.setHandler(new BasicEventResizeHandler() {

            private static final long serialVersionUID = 1801188236742274733L;

            private static final long twelveHoursInMs = 12 * 60 * 60 * 1000;

            @Override
            protected void setDates(CalendarEventEditor event, Date start,
                                    Date end) {
                long eventLength = end.getTime() - start.getTime();
                if (eventLength <= twelveHoursInMs) {
                    super.setDates(event, start, end);
                }
            }
        });*/
        layout.addComponent(calendar);
        setContent(layout);
    }

    private boolean isThisYear(java.util.Calendar calendar, Date date) {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        calendar.setTime(date);
        int endYear = calendar.get(java.util.Calendar.YEAR);
        int todayYear = calendar.get(java.util.Calendar.YEAR);
        return endYear==todayYear;
    }
}
