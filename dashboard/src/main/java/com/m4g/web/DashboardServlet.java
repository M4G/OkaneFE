package com.m4g.web;

import com.m4g.view.DashboardUI;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;

import javax.servlet.annotation.WebServlet;

/**
 * Created with IntelliJ IDEA.
 * User: Meir Gutnik
 * Date: 5/6/15
 * Time: 1:26 PM
 */

@WebServlet(urlPatterns = "/dashboard")
@VaadinServletConfiguration(ui = DashboardUI.class, productionMode = false)
public class DashboardServlet extends VaadinServlet {
}
