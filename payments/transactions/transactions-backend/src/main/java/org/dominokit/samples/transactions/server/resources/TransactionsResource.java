package org.dominokit.samples.transactions.server.resources;

import org.dominokit.samples.transactions.shared.model.Transaction;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Path("/service")
public class TransactionsResource {

    @Path("transactions")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Transaction> sayHello() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("1234567890", "1234567891", BigDecimal.valueOf(100)));
        transactions.add(new Transaction("1234567892", "1234567893", BigDecimal.valueOf(1000)));
        transactions.add(new Transaction("1234567894", "1234567895", BigDecimal.valueOf(400)));
        transactions.add(new Transaction("1234567896", "1234567897", BigDecimal.valueOf(5500)));
        transactions.add(new Transaction("1234567898", "1234567899", BigDecimal.valueOf(3445)));
        return transactions;
    }
}