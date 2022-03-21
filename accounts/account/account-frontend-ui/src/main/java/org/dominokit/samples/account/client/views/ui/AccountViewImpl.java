package org.dominokit.samples.account.client.views.ui;

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

import org.dominokit.samples.account.client.presenters.AccountProxy;
import org.dominokit.samples.account.client.views.AccountView;
import org.dominokit.samples.account.shared.model.Account;

import java.util.List;

import static org.jboss.elemento.Elements.h;

@UiView(presentable = AccountProxy.class)
public class AccountViewImpl extends BaseElementView<HTMLDivElement> implements AccountView {

    private Card card;
    private LocalListDataStore<Account> store;

    @Override
    public HTMLDivElement init() {
        card = Card.create("Accounts")
                .setHeaderBackground(Color.BLUE);
        TableConfig<Account> tableConfig = new TableConfig<>();
        tableConfig
                .addColumn(ColumnConfig.<Account>create("name", "Name")
                        .setCellRenderer(cellInfo -> TextNode.of(cellInfo.getRecord().getName()))
                )
                .addColumn(ColumnConfig.<Account>create("accountNumber", "Account number")
                        .setCellRenderer(cellInfo -> TextNode.of(cellInfo.getRecord().getAccountNumber()))
                )
                .addColumn(ColumnConfig.<Account>create("status", "Active")
                        .setCellRenderer(cellInfo -> cellInfo.getRecord().isActive() ? TextNode.of("Yes") : TextNode.of("No"))
                )
        ;

        store = new LocalListDataStore<>();
        DataTable<Account> dataTable = new DataTable<>(tableConfig, store);
        card.appendChild(dataTable);
        return card
                .element();
    }

    @Override
    public void updateAccounts(List<Account> accounts) {
        store.setData(accounts);
    }
}