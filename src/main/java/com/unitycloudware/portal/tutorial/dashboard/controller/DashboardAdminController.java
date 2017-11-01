/* Copyright 2017 Unity{Cloud}Ware - UCW Industries Ltd. All rights reserved.
 */

package com.unitycloudware.portal.tutorial.dashboard.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import org.nsys.portal.controller.PortalAdminController;

/**
 * Dashboard Administration Controller
 *
 * @author Tomas Hrdlicka <tomas@hrdlicka.co.uk>
 * @see <a href="http://unitycloudware.com">Unity{Cloud}Ware</a>
 */
@Controller
@RequestMapping("/ucw-dashboard/admin")
public class DashboardAdminController extends PortalAdminController {

    public static final String DISPLAY_NAME = "UCW Dashboard Administration";
    public static final String HEADER_SETTINGS = "Settings";
    public static final String LOCATION_HEADER_ACTIONS = "ucw.tutorial.dashboard.header.actions";

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showAdmin(
            final HttpServletRequest request,
            final HttpServletResponse response) {

        return new ModelAndView("redirect:/ucw-dashboard/admin/settings");
    }

    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public ModelAndView showDashboardSettings(
            final HttpServletRequest request,
            final HttpServletResponse response) {

        Map<String, Object> context = new HashMap<String, Object>();
        context.put("headerName", HEADER_SETTINGS);
        context.put("props", getSettings());

        setDisplayName(DISPLAY_NAME);
        showHeader(null, LOCATION_HEADER_ACTIONS, null);

        return render("/templates/dashboard-settings.vm", context, request, response);
    }

    protected Map<String, String> getSettings() {
        Map<String, String> settings = new TreeMap<String, String>();
        settings.put("param1", "value1");
        settings.put("param2", "value2");
        settings.put("param3", "value3");
        return settings;
    }
}