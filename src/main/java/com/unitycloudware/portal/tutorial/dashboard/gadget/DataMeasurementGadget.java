/* Copyright 2017 Unity{Cloud}Ware - UCW Industries Ltd. All rights reserved.
 */

package com.unitycloudware.portal.tutorial.dashboard.gadget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nsys.util.RandomRange;
import org.nsys.util.TimeUtils;
import org.nsys.portal.gadget.AbstractGadget;

import com.unitycloudware.portal.tutorial.dashboard.model.DataItem;

/**
 * Data Measurement Gadget
 *
 * @author Tomas Hrdlicka <tomas@hrdlicka.co.uk>
 * @see <a href="http://unitycloudware.com">Unity{Cloud}Ware</a>
 */
public class DataMeasurementGadget extends AbstractGadget {

    public static final String TEMPLATE = "/templates/gadget/data-measurement.vm";

    public DataMeasurementGadget() {
        setTemplate(TEMPLATE);
    }

    @Override
    protected Map<String, Object> createVelocityParams(final Map<String, Object> context) {
        Map<String, Object> velocityParams = new HashMap<String, Object>();
        velocityParams.put("items", getData());
        return velocityParams;
    }

    protected List<DataItem> getData() {
        List<DataItem> items = new ArrayList<DataItem>();

        for (int i = 0; i < 10; i++) {
            items.add(DataItem.create(
                    RandomRange.getRandomDouble(0, 100),
                    RandomRange.getRandomInt(0, 100),
                    TimeUtils.getNow().getTime()));
        }

        return items;
    }
}