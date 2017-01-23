package com.whizzosoftware.hobson.setup;

import com.whizzosoftware.hobson.api.hub.HubConfigurationClass;
import com.whizzosoftware.hobson.api.hub.HubContext;
import com.whizzosoftware.hobson.api.hub.HubWebApplication;
import com.whizzosoftware.hobson.api.plugin.AbstractHobsonPlugin;
import com.whizzosoftware.hobson.api.plugin.PluginType;
import com.whizzosoftware.hobson.api.property.PropertyContainer;
import com.whizzosoftware.hobson.api.property.TypedProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.net.URI;

public class SetupPlugin extends AbstractHobsonPlugin {
    private Logger logger = LoggerFactory.getLogger(SetupPlugin.class);

    public SetupPlugin(String pluginId, String version, String description) {
        super(pluginId, version, description);
    }

    @Override
    protected TypedProperty[] getConfigurationPropertyTypes() {
        return null;
    }

    @Override
    public String getName() {
        return "Web Console Plugin";
    }

    @Override
    public PluginType getType() {
        return PluginType.CORE;
    }

    @Override
    public void onStartup(PropertyContainer config) {
        getHubManager().getLocalManager().publishWebApplication(new HubWebApplication(SetupApplication.PATH, SetupApplication.class));

        // determine web app URL prefix
        PropertyContainer pc = getHubManager().getHub(HubContext.createLocal()).getConfiguration();
        if (!pc.getBooleanPropertyValue(HubConfigurationClass.SETUP_COMPLETE)) {
            String consoleURI;
            if (pc.getBooleanPropertyValue(HubConfigurationClass.SSL_MODE)) {
                consoleURI = "https://localhost:8183/setup/index.html";
            } else {
                consoleURI = "http://localhost:8182/setup/index.html";
            }

            // launch a browser
            try {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().browse(new URI(consoleURI));
                }
            } catch (Throwable t) {
                logger.warn("Unable to launch web browser", t);
            }
            System.out.println("To set up Hobson, go the the following address in your browser: " + consoleURI);
        }
    }

    @Override
    public void onShutdown() {
        getHubManager().getLocalManager().unpublishWebApplication(SetupApplication.PATH);
    }

    @Override
    public void onPluginConfigurationUpdate(PropertyContainer config) {
    }
}
