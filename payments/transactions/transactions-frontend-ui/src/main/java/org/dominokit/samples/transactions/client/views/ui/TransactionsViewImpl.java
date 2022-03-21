package org.dominokit.samples.transactions.client.views.ui;

import elemental2.dom.HTMLDivElement;
import org.dominokit.domino.ui.cards.Card;
import org.dominokit.domino.ui.datatable.ColumnConfig;
import org.dominokit.domino.ui.datatable.DataTable;
import org.dominokit.domino.ui.datatable.TableConfig;
import org.dominokit.domino.ui.datatable.store.LocalListDataStore;
import org.dominokit.domino.ui.style.Color;
import org.dominokit.domino.ui.utils.TextNode;
import org.dominokit.domino.view.BaseElementView;

import org.dominokit.domino.api.client.annotations.UiView;
import org.dominokit.domino.ui.utils.DominoElement;

import org.dominokit.samples.transactions.client.presenters.TransactionsProxy;
import org.dominokit.samples.transactions.client.views.TransactionsView;
import org.dominokit.samples.transactions.shared.model.Transaction;

import java.util.List;

import static org.jboss.elemento.Elements.h;

@UiView(presentable = TransactionsProxy.class)
public class TransactionsViewImpl extends BaseElementView<HTMLDivElement> implements TransactionsView {
    private Card card;
    private LocalListDataStore<Transaction> store;

    @Override
    public HTMLDivElement init() {
        card = Card.create("Transactions")
                .setHeaderBackground(Color.TEAL);
        TableConfig<Transaction> tableConfig = new TableConfig<>();
        tableConfig
                .addColumn(ColumnConfig.<Transaction>create("fromAccount", "From")
                        .setCellRenderer(cellInfo -> TextNode.of(cellInfo.getRecord().getFromAccount()))
                )
                .addColumn(ColumnConfig.<Transaction>create("toAccount", "To")
                        .setCellRenderer(cellInfo -> TextNode.of(cellInfo.getRecord().getToAccount()))
                )
                .addColumn(ColumnConfig.<Transaction>create("amount", "Amount")
                        .setCellRenderer(cellInfo -> TextNode.of(cellInfo.getRecord().getAmount().toString()))
                )
        ;

        store = new LocalListDataStore<>();
        DataTable<Transaction> dataTable = new DataTable<>(tableConfig, store);
        card.appendChild(dataTable);
        return card
                .element();
    }

    @Override
    public void updateTransactions(List<Transaction> transactions) {
        store.setData(transactions);
    }
}