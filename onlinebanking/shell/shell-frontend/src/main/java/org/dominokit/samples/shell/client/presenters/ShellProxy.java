package org.dominokit.samples.shell.client.presenters;

import elemental2.dom.DomGlobal;
import org.dominokit.domino.api.client.annotations.presenter.*;
import org.dominokit.domino.api.client.mvp.presenter.ViewablePresenter;
import org.dominokit.domino.api.shared.extension.MainDominoEvent;
import org.dominokit.domino.api.shared.extension.PredefinedSlots;
import org.dominokit.samples.shell.client.views.ShellView;
import org.dominokit.samples.shell.shared.model.ShellSlots;
import org.dominokit.samples.shell.shared.services.AppsServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PresenterProxy(name = "shell")
@AutoRoute(routeOnce = true)
@Slot(PredefinedSlots.BODY_SLOT)
@AutoReveal
@Singleton
@RegisterSlots(ShellSlots.CONTENT)
@DependsOn(@EventsGroup(MainDominoEvent.class))
public class ShellProxy extends ViewablePresenter<ShellView> implements ShellView.ShellUiHandlers {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShellProxy.class);

    @OnReveal
    public void loadApplications() {
        AppsServiceFactory.INSTANCE
                .list()
                .onSuccess(appDescriptors -> view.updateMenu(appDescriptors))
                .onFailed(failedResponseBean -> {
                    DomGlobal.console.error("failed", failedResponseBean.getThrowable());
                })
                .send();
    }

    @Override
    public void onMenuItemSelected(String token) {
        history().fireState(token);
    }
}