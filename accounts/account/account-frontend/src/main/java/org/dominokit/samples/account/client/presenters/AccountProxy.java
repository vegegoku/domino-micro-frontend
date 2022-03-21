package org.dominokit.samples.account.client.presenters;

import org.dominokit.domino.api.client.annotations.presenter.*;
import org.dominokit.domino.api.client.mvp.presenter.ViewablePresenter;
import org.dominokit.samples.account.client.views.AccountView;
import org.dominokit.samples.account.shared.events.AccountEvent;
import org.dominokit.samples.account.shared.services.AccountServiceFactory;
import org.dominokit.samples.shell.shared.model.ShellSlots;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PresenterProxy(parent = "shell")
@AutoRoute(token = "accounts")
@Slot(ShellSlots.CONTENT)
@AutoReveal
@OnStateChanged(AccountEvent.class)
public class AccountProxy extends ViewablePresenter<AccountView> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountProxy.class);

    @OnReveal
    public void loadAccounts() {
        AccountServiceFactory.INSTANCE
                .list()
                .onSuccess(accounts -> view.updateAccounts(accounts))
                .onFailed(failedResponseBean -> LOGGER.error("failed to list accounts", failedResponseBean.getThrowable()))
                .send();
    }

}