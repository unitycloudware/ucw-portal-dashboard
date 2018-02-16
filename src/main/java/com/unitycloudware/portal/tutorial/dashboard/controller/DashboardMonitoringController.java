/* Copyright 2017, 2018 Unity{Cloud}Ware - UCW Industries Ltd. All rights reserved.
 */

package com.unitycloudware.portal.tutorial.dashboard.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.nsys.portal.controller.AbstractPortalController;

/**
 * Dashboard Monitoring Controller
 *
 * @author Tomas Hrdlicka <tomas@hrdlicka.co.uk>
 * @see <a href="http://unitycloudware.com">Unity{Cloud}Ware</a>
 */
@Controller
@RequestMapping("/ucw-dashboard")
public class DashboardMonitoringController extends AbstractPortalController {

    public static final String DISPLAY_NAME = "UCW Dashboard Monitoring";
    public static final String LOCATION_HEADER_ACTIONS = "ucw.tutorial.dashboard.header.actions";

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showDefaultPage(
            final HttpServletRequest request,
            final HttpServletResponse response) {

        return new ModelAndView("redirect:/ucw-dashboard/monitoring");
    }

    @RequestMapping(value = "/monitoring", method = RequestMethod.GET)
    public ModelAndView showMonitoring(
            final HttpServletRequest request,
            final HttpServletResponse response) {

        String imageUri = getResourceUrl("/resources/images/ucw_logo.png", request);
        String msg = "Greetings from UCW dashboard monitoring!";

        Map<String, Object> context = new HashMap<String, Object>();
        context.put("msg", msg);

        setDisplayName(DISPLAY_NAME);
        showHeader(imageUri, LOCATION_HEADER_ACTIONS, null);

        return render("/templates/dashboard-monitoring.vm", context, request, response);
    }
}