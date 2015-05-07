package com.m4g.view;

import com.vaadin.annotations.Theme;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.themes.ValoTheme;

@Theme("valo")
public class SideMenu extends CustomComponent {

    public static final String REPORTS_BADGE_ID = "dashboard-menu-reports-badge";
    public static final String NOTIFICATIONS_BADGE_ID = "dashboard-menu-notifications-badge";
    private static final String STYLE_VISIBLE = "valo-menu-visible";
    private Label notificationsBadge;
    private Label reportsBadge;
    private MenuItem settingsItem;

    public SideMenu() {
        setSizeUndefined();
        setCompositionRoot(buildContent());
    }

    private Component buildContent() {
        VerticalLayout menuContent = new VerticalLayout();
        menuContent.addStyleName(ValoTheme.MENU_PART);
        menuContent.setWidth(null);
        menuContent.setHeight(100, Unit.PERCENTAGE);

        menuContent.addComponent(buildTitle());
        //menuContent.addComponent(buildUserMenu());
        //menuContent.addComponent(buildToggleButton());
        menuContent.addComponent(buildMenuItems());

        return menuContent;
    }

    private Component buildTitle() {
        Label logo = new Label("<strong>Okane</strong> management suite", ContentMode.HTML);
        logo.setSizeUndefined();
        HorizontalLayout logoWrapper = new HorizontalLayout(logo);
        logoWrapper.setComponentAlignment(logo, Alignment.MIDDLE_CENTER);
        return logoWrapper;
    }
/*
    private Component buildUserMenu() {
        final MenuBar settings = new MenuBar();
        settings.addStyleName("user-menu");
        final User user = getCurrentUser();
        settingsItem = settings.addItem("", new ThemeResource(
                "img/profile-pic-300px.jpg"), null);
        updateUserName(null);
        settingsItem.addItem("Edit Profile", new Command() {
            @Override
            public void menuSelected(final MenuItem selectedItem) {
                ProfilePreferencesWindow.open(user, false);
            }
        });
        settingsItem.addItem("Preferences", new Command() {
            @Override
            public void menuSelected(final MenuItem selectedItem) {
                ProfilePreferencesWindow.open(user, true);
            }
        });
        settingsItem.addSeparator();
        settingsItem.addItem("Sign Out", new Command() {
            @Override
            public void menuSelected(final MenuItem selectedItem) {
                DashboardEventBus.post(new UserLoggedOutEvent());
            }
        });
        return settings;
    }

    private Component buildToggleButton() {
        Button valoMenuToggleButton = new Button("Menu", new ClickListener() {
            @Override
            public void buttonClick(final ClickEvent event) {
                if (getCompositionRoot().getStyleName().contains(STYLE_VISIBLE)) {
                    getCompositionRoot().removeStyleName(STYLE_VISIBLE);
                } else {
                    getCompositionRoot().addStyleName(STYLE_VISIBLE);
                }
            }
        });
        valoMenuToggleButton.setIcon(FontAwesome.LIST);
        valoMenuToggleButton.addStyleName("valo-menu-toggle");
        valoMenuToggleButton.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        valoMenuToggleButton.addStyleName(ValoTheme.BUTTON_SMALL);
        return valoMenuToggleButton;
    }
*/
    private Component buildMenuItems() {
        VerticalLayout menuItemsLayout = new VerticalLayout();
        menuItemsLayout.setHeight(100.0f, Unit.PERCENTAGE);

        for (final ContentType view : ContentType.values()) {
            Component menuItemComponent = new ValoMenuItemButton(view);

            /*if (view == ContentType.REPORTS) {
                // Add drop target to reports button
                DragAndDropWrapper reports = new DragAndDropWrapper(menuItemComponent);
                reports.setDragStartMode(DragStartMode.NONE);
                reports.setDropHandler(new DropHandler() {

                    @Override
                    public void drop(final DragAndDropEvent event) {
                        UI.getCurrent()
                                .getNavigator()
                                .navigateTo(
                                        ContentType.REPORTS.getViewName());
                        Table table = (Table) event.getTransferable()
                                .getSourceComponent();
                        DashboardEventBus.post(new TransactionReportEvent(
                                (Collection<Transaction>) table.getValue()));
                    }

                    @Override
                    public AcceptCriterion getAcceptCriterion() {
                        return AcceptItem.ALL;
                    }

                });
                menuItemComponent = reports;
            }

            if (view == ContentType.DASHBOARD) {
                notificationsBadge = new Label();
                notificationsBadge.setId(NOTIFICATIONS_BADGE_ID);
                menuItemComponent = buildBadgeWrapper(menuItemComponent,
                        notificationsBadge);
            }
            if (view == ContentType.REPORTS) {
                reportsBadge = new Label();
                reportsBadge.setId(REPORTS_BADGE_ID);
                menuItemComponent = buildBadgeWrapper(menuItemComponent,
                        reportsBadge);
            }*/

            menuItemsLayout.addComponent(menuItemComponent);
        }
        return menuItemsLayout;

    }

    private Component buildBadgeWrapper(final Component menuItemButton,
            final Component badgeLabel) {
        CssLayout dashboardWrapper = new CssLayout(menuItemButton);
        dashboardWrapper.addStyleName("badgewrapper");
        dashboardWrapper.addStyleName(ValoTheme.MENU_ITEM);
        dashboardWrapper.setWidth(100.0f, Unit.PERCENTAGE);
        badgeLabel.addStyleName(ValoTheme.MENU_BADGE);
        badgeLabel.setWidthUndefined();
        badgeLabel.setVisible(false);
        dashboardWrapper.addComponent(badgeLabel);
        return dashboardWrapper;
    }

    @Override
    public void attach() {
        super.attach();
        //updateNotificationsCount(null);
    }
/*
    @Subscribe
    public void postViewChange(final PostViewChangeEvent event) {
        // After a successful view change the menu can be hidden in mobile view.
        getCompositionRoot().removeStyleName(STYLE_VISIBLE);
    }

    @Subscribe
    public void updateNotificationsCount(
            final NotificationsCountUpdatedEvent event) {
        int unreadNotificationsCount = Dashboard.getDataProvider()
                .getUnreadNotificationsCount();
        notificationsBadge.setValue(String.valueOf(unreadNotificationsCount));
        notificationsBadge.setVisible(unreadNotificationsCount > 0);
    }

    @Subscribe
    public void updateReportsCount(final ReportsCountUpdatedEvent event) {
        reportsBadge.setValue(String.valueOf(event.getCount()));
        reportsBadge.setVisible(event.getCount() > 0);
    }

    @Subscribe
    public void updateUserName(final ProfileUpdatedEvent event) {
        User user = getCurrentUser();
        settingsItem.setText(user.getFirstName() + " " + user.getLastName());
    }
*/
    public final class ValoMenuItemButton extends Button {

        private static final String STYLE_SELECTED = "selected";

        private final ContentType view;

        public ValoMenuItemButton(final ContentType view) {
            this.view = view;
            //setPrimaryStyleName("valo-menu-item");
            setIcon(view.getIcon());
            setCaption(view.getViewName().substring(0, 1).toUpperCase()
                    + view.getViewName().substring(1));
            //DashboardEventBus.register(this);
            addClickListener(new ClickListener() {
                @Override
                public void buttonClick(final ClickEvent event) {
                    //UI.getCurrent().getNavigator()
                    //        .navigateTo(view.getViewName());
                }
            });

        }

/*        @Subscribe
        public void postViewChange(final PostViewChangeEvent event) {
            removeStyleName(STYLE_SELECTED);
            if (event.getView() == view) {
                addStyleName(STYLE_SELECTED);
            }
        }*/
    }
}
