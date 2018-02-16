/* Copyright 2017, 2018 Unity{Cloud}Ware - UCW Industries Ltd. All rights reserved.
 */

package com.unitycloudware.portal.tutorial.dashboard;

import java.util.Properties;

import org.nsys.util.ConfigurationManager;

/**
 * Dashboard Config
 *
 * @author Tomas Hrdlicka <tomas@hrdlicka.co.uk>
 * @see <a href="http://unitycloudware.com">Unity{Cloud}Ware</a>
 */
public class DashboardConfig {

    public static final String CONFIG_NAME = "ucw-dashboard.cfg";
    public static final String CONFIG_NAME_INTERNAL = "ucw-dashboard.cfg.internal";
    public static final String VERSION = "ucw.dashboard.version";
    public static final String PLUGIN_KEY = "ucw.dashboard.pluginKey";

    public static void loadConfig() {
        ConfigurationManager config = ConfigurationManager.getInstance();

        Properties props = config.loadConfig(String.format("/%s", CONFIG_NAME), DashboardConfig.class);

        if (props != null) {
            config.merge(props);
        }

        props = config.loadConfig(String.format("/%s", CONFIG_NAME_INTERNAL), DashboardConfig.class);

        if (props != null) {
            config.merge(props, true);
        }
    }

    public static String getVersion() {
        String version = ConfigurationManager.getInstance().getProperty(VERSION);
        return ConfigurationManager.getVersion(version);
    }

    public static String getBuildNumber() {
        String version = ConfigurationManager.getInstance().getProperty(VERSION);
        return ConfigurationManager.getBuildNumber(version);
    }

    public static String getPluginKey() {
        return ConfigurationManager.getInstance().getProperty(PLUGIN_KEY);
    }
}