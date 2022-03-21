package org.dominokit.samples.account.shared.events;

import org.dominokit.domino.api.shared.extension.ActivationEvent;

public class AccountEvent extends ActivationEvent {
    public AccountEvent(boolean status) {
        super(status);
    }
}
