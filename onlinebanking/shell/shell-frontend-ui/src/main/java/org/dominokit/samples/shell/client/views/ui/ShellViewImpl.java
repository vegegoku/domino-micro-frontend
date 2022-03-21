package org.dominokit.samples.shell.client.views.ui;

import elemental2.dom.HTMLDivElement;
import org.dominokit.domino.api.client.mvp.slots.IsSlot;
import org.dominokit.domino.ui.layout.Layout;
import org.dominokit.domino.ui.tree.Tree;
import org.dominokit.domino.ui.tree.TreeItem;
import org.dominokit.domino.view.BaseElementView;

import org.dominokit.domino.api.client.annotations.UiView;
import org.dominokit.domino.ui.utils.DominoElement;

import org.dominokit.domino.view.slots.SingleElementSlot;
import org.dominokit.samples.shell.client.presenters.ShellProxy;
import org.dominokit.samples.shell.client.views.ShellView;
import org.dominokit.samples.shell.shared.model.AppDescriptor;

import java.util.List;

import static org.jboss.elemento.Elements.h;

@UiView(presentable = ShellProxy.class)
public class ShellViewImpl extends BaseElementView<HTMLDivElement> implements ShellView{

    private Layout layout;
    private ShellUiHandlers uiHandlers;
    private Tree<String> menu;

    @Override
    public HTMLDivElement init() {
        layout = Layout.create("Online banking");

        layout
                .fixLeftPanelPosition()
                .getContentPanel()
                .setAttribute("id", "content");

        menu = Tree.create("Menu")
                .addItemClickListener(treeItem -> {
                    uiHandlers.onMenuItemSelected(treeItem.getValue());
                    if(!treeItem.getValue().equalsIgnoreCase("loans")){
                        layout.getContentPanel().clearElement();
                    }
                });

        layout.getLeftPanel().appendChild(menu);
        return layout.element();
    }

    @Override
    public void updateMenu(List<AppDescriptor> appDescriptors) {
        appDescriptors.forEach(appDescriptor -> {
            appDescriptor.getMenuEntries()
                    .forEach(menuEntry -> menu.appendChild(TreeItem.create(menuEntry.getLabel(), menuEntry.getToken())));
        });
    }

    @Override
    public void setUiHandlers(ShellUiHandlers uiHandlers) {
        this.uiHandlers = uiHandlers;
    }

    @Override
    public IsSlot<?> getContentSlot() {
        return SingleElementSlot.of(layout.getContentPanel());
    }

}