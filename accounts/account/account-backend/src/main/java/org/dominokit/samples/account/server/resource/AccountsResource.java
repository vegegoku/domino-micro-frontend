package org.dominokit.samples.account.server.resource;

import org.dominokit.samples.account.shared.model.Account;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("service/")
public class AccountsResource {

    @GET
    @Path("accounts")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Account> list(){
        List<Account> accounts = new ArrayList<>();

        accounts.add(new Account("Ahmad", "1234567890", true));
        accounts.add(new Account("Ali", "1234567891", true));
        accounts.add(new Account("Saving", "1234567892", true));
        accounts.add(new Account("Wallet", "1234567893", true));
        accounts.add(new Account("Mohammad", "1234567894", true));
        accounts.add(new Account("Dollar", "1234567895", false));
        accounts.add(new Account("Other", "1234567896", true));

        return accounts;
    }
}
