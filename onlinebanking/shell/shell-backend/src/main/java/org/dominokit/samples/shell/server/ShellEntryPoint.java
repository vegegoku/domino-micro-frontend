package org.dominokit.samples.shell.server;

import com.google.auto.service.AutoService;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.Json;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.handler.BodyHandler;
import org.dominokit.domino.api.server.entrypoint.ServerAppEntryPoint;
import org.dominokit.domino.api.server.entrypoint.VertxContext;
import org.dominokit.domino.api.server.plugins.IndexPageContext;
import org.dominokit.samples.shell.shared.model.AppDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@AutoService(ServerAppEntryPoint.class)
public class ShellEntryPoint implements ServerAppEntryPoint<VertxContext> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShellEntryPoint.class);

    @Override
    public void onModulesLoaded(VertxContext context) {
        AppDescriptor shellApp = new AppDescriptor("Shell", "/app.nocache.js");
        MultiAppIndexPageProvider indexPageProvider = new MultiAppIndexPageProvider(shellApp);
        IndexPageContext.INSTANCE.setIndexPageProvider(indexPageProvider);

        context.router()
                .route(HttpMethod.POST, "/lookup/apps")
                .consumes("application/json")
                .handler(BodyHandler.create())
                .handler(event -> {
                    AppDescriptor appDescriptor = Json.decodeValue(event.getBodyAsString(), AppDescriptor.class);
                    indexPageProvider.addApplication(appDescriptor);
                    LOGGER.info("----- > Registering new Application : "+appDescriptor);
                    event.response()
                            .setStatusCode(200)
                            .end();
                });

        context.vertx()
                .setPeriodic(5000, event -> {
                    updateAppsList(context, indexPageProvider);
                });
    }

    private void updateAppsList(VertxContext context, MultiAppIndexPageProvider indexPageProvider) {
        WebClient webClient = WebClient.create(context.vertx());

        List<AppDescriptor> allApps = new ArrayList<>(indexPageProvider.getApplications());
        allApps.forEach(appDescriptor -> {
            webClient.requestAbs(HttpMethod.GET, appDescriptor.getHealthCheckUrl())
                    .putHeader("Content-type", "plain/text")
                    .send(event -> {
                        if (event.failed() || event.result().statusCode() != 200){
                            LOGGER.warn("App ["+appDescriptor+"] is not healthy, will be removed .!");
                            indexPageProvider.getApplications().remove(appDescriptor);
                        }
                    });
        });
    }
}
