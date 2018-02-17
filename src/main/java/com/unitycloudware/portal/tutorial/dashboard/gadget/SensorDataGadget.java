/* Copyright 2017, 2018 Unity{Cloud}Ware - UCW Industries Ltd. All rights reserved.
 */

package com.unitycloudware.portal.tutorial.dashboard.gadget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nsys.util.JsonUtils;
import org.nsys.system.ComponentProvider;
import org.nsys.portal.gadget.AbstractGadget;

import com.unitycloudware.core.model.Device;
import com.unitycloudware.core.model.DataMessage;
import com.unitycloudware.core.model.DataStream;
import com.unitycloudware.core.model.DataStreamItem;
import com.unitycloudware.core.model.DataStreamType;
import com.unitycloudware.core.service.DataManager;
import com.unitycloudware.core.service.DeviceManager;
import com.unitycloudware.portal.tutorial.dashboard.util.TestDataUtils;
import com.unitycloudware.portal.tutorial.dashboard.model.DataItem;

/**
 * Sensor Data Gadget
 *
 * @author Tomas Hrdlicka <tomas@hrdlicka.co.uk>
 * @see <a href="http://unitycloudware.com">Unity{Cloud}Ware</a>
 */
public class SensorDataGadget extends AbstractGadget {
    private DeviceManager deviceManager;
    private DataManager dataManager;

    public static final String TEMPLATE = "/templates/gadget/sensor-data.vm";

    public SensorDataGadget() {
        setTemplate(TEMPLATE);
    }

    public DeviceManager getDeviceManager() {
        if (deviceManager == null) {
            deviceManager = ComponentProvider.getInstance().getComponent(DeviceManager.class);
        }

        return deviceManager;
    }

    public DataManager getDataManager() {
        if (dataManager == null) {
            dataManager = ComponentProvider.getInstance().getComponent(DataManager.class);
        }

        return dataManager;
    }

    @Override
    protected Map<String, Object> createVelocityParams(final Map<String, Object> context) {
        Map<String, Object> velocityParams = new HashMap<String, Object>();
        velocityParams.put("items", getData());
        return velocityParams;
    }

    protected List<DataItem> getData() {
        List<DataItem> data = new ArrayList<DataItem>();

        Device device = getDeviceManager().getDeviceByName(TestDataUtils.PROJECT_KEY, TestDataUtils.DEVICE_NAME);

        if (device == null) {
            return data;
        }

        DataStream dataStream = getDataManager().getDataStream(TestDataUtils.PROJECT_KEY, TestDataUtils.DATA_STREAM_NAME);

        if (dataStream == null) {
            return data;
        }

        // Load 10 last records
        List<DataStreamItem> items = getDataManager().loadStream(dataStream, device, 0, 10);

        if (items == null || items.isEmpty()) {
            return data;
        }

        for (DataStreamItem item : items) {
            // Process only data for data stream with type of DATA_MESSAGE
            if (item.getType() != DataStreamType.DATA_MESSAGE) {
                continue;
            }

            DataMessage dataMessage = (DataMessage) item.getData();

            // Transform JSON payload to DataItem object
            data.add(JsonUtils.fromJson(dataMessage.getData(), DataItem.class));
        }

        return data;
    }
}