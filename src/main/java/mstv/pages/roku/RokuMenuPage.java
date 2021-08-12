package mstv.pages.roku;

import models.Selector;
import mstv.pages.base.ISettingsPage;
import protocol.IPlatformProtocol;

import static models.Selector.Data.attr;
import static models.Selector.Data.tag;
import static protocol.Button.*;

public class RokuMenuPage {

    protected final IPlatformProtocol<Selector> protocol;
    private final Selector menu = new Selector.Builder()
            .addElementData(attr("name", "menu"))
            .addElementData(tag("MarkupGrid"))
            .addParentData(attr("focused", "true"))
            .build();
    private final Selector settingsScreen = new Selector.Builder()
            .addElementData(attr("name", "settings_screen"))
            .build();

    public RokuMenuPage(IPlatformProtocol<Selector> protocol) {
        this.protocol = protocol;
    }

    int getFocusedMenuIndex() {
        return protocol.findElement(menu).getFocusedItemIndex();
    }

    public ISettingsPage openSettingsPage() {
        protocol.pressButton(BACK);
        protocol.findElement(menu);
        protocol.pressButton(FAST_FORWARD);
        while (!protocol.findElement(settingsScreen).isFocused()) {
            protocol.pressButton(DOWN);
        }
        return new RokuSettingsPageImpl(protocol);
    }
}