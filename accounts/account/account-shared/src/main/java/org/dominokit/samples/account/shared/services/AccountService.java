package org.dominokit.samples.account.shared.services;

import org.dominokit.rest.shared.request.service.annotations.RequestFactory;
import org.dominokit.samples.account.shared.model.Account;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@RequestFactory
public interface AccountService {
    @Path("accounts")
    @GET
    List<Account> list();
}
