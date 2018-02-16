/* Copyright 2017, 2018 Unity{Cloud}Ware - UCW Industries Ltd. All rights reserved.
 */

package com.unitycloudware.portal.tutorial.dashboard.gadget;

import java.util.HashMap;
import java.util.Map;

import org.nsys.portal.gadget.AbstractGadget;

/**
 * Dashboard Administration Gadget
 *
 * @author Tomas Hrdlicka <tomas@hrdlicka.co.uk>
 * @see <a href="http://unitycloudware.com">Unity{Cloud}Ware</a>
 */
public class DashboardAdminGadget extends AbstractGadget {

    public static final String TEMPLATE = "/templates/gadget/dashboard-admin.vm";

    public DashboardAdminGadget() {
        setTemplate(TEMPLATE);
    }

    @Override
    protected Map<String, Object> createVelocityParams(final Map<String, Object> context) {
        Map<String, Object> velocityParams = new HashMap<String, Object>();
        return velocityParams;
    }
}