package org.dominokit.samples.transactions.client.presenters;

import org.dominokit.domino.api.client.annotations.presenter.*;
import org.dominokit.domino.api.client.mvp.presenter.ViewablePresenter;
import org.dominokit.samples.shell.shared.model.ShellSlots;
import org.dominokit.samples.transactions.client.views.TransactionsView;
import org.dominokit.samples.transactions.shared.events.TransactionsEvent;
import org.dominokit.samples.transactions.shared.services.TransactionsServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PresenterProxy(parent = "shell")
@AutoRoute(token = "transactions")
@Slot(ShellSlots.CONTENT)
@AutoReveal
@OnStateChanged(TransactionsEvent.class)
public class TransactionsProxy extends ViewablePresenter<TransactionsView> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionsProxy.class);

    @OnReveal
    public void loadTransactions(){
        TransactionsServiceFactory.INSTANCE
                .list()
                .onSuccess(transactions -> view.updateTransactions(transactions))
                .onFailed(failedResponseBean -> LOGGER.error("failed to list transactions", failedResponseBean.getThrowable()))
                .send();
    }

}