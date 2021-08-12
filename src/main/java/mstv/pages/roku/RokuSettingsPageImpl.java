package mstv.pages.roku;

import models.Selector;
import mstv.pages.base.ISettingsPage;
import protocol.IPlatformProtocol;
import protocol.PlatformElement;

import static models.Selector.Data.attr;
import static protocol.Button.DOWN;
import static protocol.Button.OK;

public class RokuSettingsPageImpl extends RokuMenuPage implements ISettingsPage {

    public static final String SIGN_OUT_BUTTON_ID = "sign_in_out_button";
    private final Selector buttonsSelector = new Selector.Builder()
            .addElementData(attr("focused", "true"))
            .addParentData(attr("name", "settings_screen"))
            .addParentData(attr("extends", "Group"))
            .build();

    public RokuSettingsPageImpl(IPlatformProtocol<Selector> protocol) {
        super(protocol);
    }

    @Override
    public void signOut() {
        PlatformElement element = protocol.findElement(buttonsSelector);
        while (!element.getId().equals(SIGN_OUT_BUTTON_ID)) {
            protocol.pressButton(DOWN);
            element = protocol.findElement(buttonsSelector);
        }
        protocol.pressButton(OK, OK);
    }
}