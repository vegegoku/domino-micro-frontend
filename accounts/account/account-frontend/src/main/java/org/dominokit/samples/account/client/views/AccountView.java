package org.dominokit.samples.account.client.views;

import org.dominokit.domino.api.client.mvp.view.ContentView;
import org.dominokit.samples.account.shared.model.Account;

import java.util.List;

public interface AccountView extends ContentView {
    void updateAccounts(List<Account> accounts);
}