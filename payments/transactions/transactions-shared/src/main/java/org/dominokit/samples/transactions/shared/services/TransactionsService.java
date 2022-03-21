package org.dominokit.samples.transactions.shared.services;

import org.dominokit.rest.shared.request.service.annotations.RequestFactory;
import org.dominokit.samples.transactions.shared.model.Transaction;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@RequestFactory
public interface TransactionsService {
    @Path("transactions")
    @GET
    List<Transaction> list();
}
