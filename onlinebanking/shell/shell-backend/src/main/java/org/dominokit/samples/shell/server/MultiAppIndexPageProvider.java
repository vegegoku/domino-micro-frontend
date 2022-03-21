package org.dominokit.samples.shell.server;

import com.google.auto.service.AutoService;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;
import org.dominokit.domino.api.server.PluginContext;
import org.dominokit.domino.api.server.plugins.IndexPageProvider;
import org.dominokit.samples.shell.shared.model.AppDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@AutoService(IndexPageProvider.class)
public class MultiAppIndexPageProvider implements IndexPageProvider {

    public static final Logger LOGGER = LoggerFactory.getLogger(MultiAppIndexPageProvider.class);

    private List<AppDescriptor> applications = new ArrayList<>();
    private final AppDescriptor shellApp;

    public MultiAppIndexPageProvider(AppDescriptor shellApp) {
        this.shellApp = shellApp;
    }

    @Override
    public HttpServerResponse serveIndexPage(PluginContext context, RoutingContext routingContext, int statusCode) {
        LOGGER.info("Loading index page using multi-app index page loader...");
        String content = getPageContent();
        HttpServerResponse response = routingContext
                .response()
                .setStatusCode(statusCode)
                .putHeader("Content-type", "text/html")
                .putHeader("Content-length", content.length() + "")
                .write(content);

        response.end();
        return response;
    }

    public List<AppDescriptor> getApplications() {
        return applications;
    }

    private String getPageContent() {
        StringBuffer sb = new StringBuffer("<!doctype html>\n" +
                "\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=UTF-8>\n" +
                "<link type=\"text/css\" rel=\"stylesheet\" href=\"/onlinebanking.css\">\n" +
                "    <meta content=\"width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no\" name=\"viewport\">\n" +
                "\n" +
                "<link type=\"text/css\" rel=\"stylesheet\" href=\"/assets/lib/domino-ui/css/domino-ui.css\">\n" +
                "    <link type=\"text/css\" rel=\"stylesheet\" href=\"/assets/lib/domino-ui/css/themes/all-themes.css\">"+
                "\n" +
                "\n" +
                "    <title>Online Banking</title>\n" +
                "\n");
        sb.append("    <script src=\"" + shellApp.getScript() + "\"></script>\n");
        applications.forEach(app -> sb.append("    <script src=\"" + app.getScript() + "\"></script>\n"));

        sb.append("</head>\n" +
                "\n" +
                "<body>\n" +
                "\n" +
                "<iframe src=\"javascript:''\" id=\"__gwt_historyFrame\" tabIndex='-1'\n" +
                "        style=\"position:absolute;width:0;height:0;border:0\"></iframe>\n" +
                "\n" +
                "<noscript>\n" +
                "    <div style=\"width: 22em; position: absolute; left: 50%; margin-left: -11em; color: red; background-color: white; border: 1px solid red; padding: 4px; font-family: sans-serif\">\n" +
                "        Your web browser must have JavaScript enabled\n" +
                "        in order for this application to display correctly.\n" +
                "    </div>\n" +
                "</noscript>\n" +
                "\n" +
                "</body>\n" +
                "</html>");

        return sb.toString();
    }

    public void addApplication(AppDescriptor appDescriptor) {
        applications.add(appDescriptor);
    }
}
