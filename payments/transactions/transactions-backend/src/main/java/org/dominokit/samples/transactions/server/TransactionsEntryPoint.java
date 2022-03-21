package org.dominokit.samples.transactions.server;

import com.google.auto.service.AutoService;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.client.WebClient;
import org.dominokit.domino.api.server.entrypoint.DominoHttpServerOptions;
import org.dominokit.domino.api.server.entrypoint.ServerAppEntryPoint;
import org.dominokit.domino.api.server.entrypoint.VertxContext;
import org.dominokit.samples.shell.shared.model.AppDescriptor;
import org.dominokit.samples.shell.shared.model.MenuEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@AutoService(ServerAppEntryPoint.class)
public class TransactionsEntryPoint implements ServerAppEntryPoint<VertxContext> {

    public static final Logger LOGGER = LoggerFactory.getLogger(TransactionsEntryPoint.class);

    @Override
    public void onModulesLoaded(VertxContext context) {

        DominoHttpServerOptions server = context.httpServerOptions();
        WebClient webClient = WebClient.create(context.vertx());
        AppDescriptor loansApp = new AppDescriptor("Transactions", "http://localhost:" + server.getPort() + "/payments.nocache.js");
        loansApp.getMenuEntries().add(new MenuEntry("Transactions", "transactions"));
        loansApp.setHealthCheckUrl("http://localhost:" + server.getPort() + "/lookup/health");

        context.router()
                .route(HttpMethod.GET, "/lookup/health")
                .consumes("plain/text")
                .handler(event -> {
                    event.response().setStatusCode(200).end();
                });

        webClient.post(8080, "localhost", "/lookup/apps")
                .sendJson(loansApp, event -> {
                    LOGGER.info("Loans app have been registered.");
                });

    }
}
