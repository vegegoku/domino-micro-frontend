package org.dominokit.samples.shell.shared.services;

import org.dominokit.rest.shared.request.service.annotations.RequestFactory;
import org.dominokit.samples.shell.shared.model.AppDescriptor;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@RequestFactory
public interface AppsService {

    @GET
    @Path("apps")
    List<AppDescriptor> list();
}
