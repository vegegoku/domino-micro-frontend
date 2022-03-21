package org.dominokit.samples.shell.shared.events;

import org.dominokit.domino.api.shared.extension.ActivationEvent;

public class ShellEvent extends ActivationEvent {
    public ShellEvent(boolean status) {
        super(status);
    }
}
