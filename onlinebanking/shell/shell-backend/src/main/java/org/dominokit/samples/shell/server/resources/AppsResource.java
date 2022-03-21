package org.dominokit.samples.shell.server.resources;


import org.dominokit.domino.api.server.plugins.IndexPageContext;
import org.dominokit.samples.shell.server.MultiAppIndexPageProvider;
import org.dominokit.samples.shell.shared.model.AppDescriptor;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("service/")
public class AppsResource {

    @GET
    @Path("apps")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AppDescriptor> list(){
        MultiAppIndexPageProvider provider = (MultiAppIndexPageProvider) IndexPageContext.INSTANCE.getIndexPageProvider();
        return provider.getApplications();
    }
}
