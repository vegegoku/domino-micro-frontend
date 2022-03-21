package org.dominokit.samples.transactions.shared.events;

import org.dominokit.domino.api.shared.extension.ActivationEvent;

public class TransactionsEvent extends ActivationEvent {
    public TransactionsEvent(boolean status) {
        super(status);
    }
}
