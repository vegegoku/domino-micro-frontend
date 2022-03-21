package org.dominokit.samples.shell.client.views;

import org.dominokit.domino.api.client.mvp.view.ContentView;
import org.dominokit.domino.api.client.mvp.view.HasUiHandlers;
import org.dominokit.domino.api.client.mvp.view.UiHandlers;
import org.dominokit.samples.shell.client.presenters.ShellProxySlots;
import org.dominokit.samples.shell.shared.model.AppDescriptor;

import java.util.List;

public interface ShellView extends ContentView , HasUiHandlers<ShellView.ShellUiHandlers>, ShellProxySlots {

    void updateMenu(List<AppDescriptor> appDescriptors);

    interface ShellUiHandlers extends UiHandlers {
        void onMenuItemSelected(String token);
    }
}