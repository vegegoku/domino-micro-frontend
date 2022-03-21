package org.dominokit.samples.transactions.client.views;

import org.dominokit.domino.api.client.mvp.view.ContentView;
import org.dominokit.samples.transactions.shared.model.Transaction;

import java.util.List;

public interface TransactionsView extends ContentView {
    void updateTransactions(List<Transaction> transactions);
}