package org.dominokit.samples;

import com.google.gwt.core.client.EntryPoint;
import org.dominokit.domino.api.client.ClientApp;
import org.dominokit.domino.gwt.client.app.DominoGWT;
import org.dominokit.domino.view.DominoViewOptions;
import org.dominokit.rest.DominoRestConfig;

import java.util.logging.Logger;

public class AppClientModule implements EntryPoint {

    private static final Logger LOGGER = Logger.getLogger(AppClientModule.class.getName());

    public void onModuleLoad() {
        DominoRestConfig.initDefaults()
                .setDefaultServiceRoot("http://localhost:8082/service/");
        DominoGWT.init(DominoViewOptions.getInstance());
        ClientApp.make().run();
        LOGGER.info("payments Application frontend have been initialized.");
    }
}
