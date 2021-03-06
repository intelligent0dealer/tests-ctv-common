package mstv.pages.roku;

import exception.NoSuchElementException;
import models.Selector;
import mstv.pages.base.IEpisodePage;
import mstv.pages.base.IHomePage;
import mstv.pages.base.ILoginPage;
import mstv.pages.base.IRegisterPage;
import mstv.pages.roku.auth.RokuLoginPageImpl;
import mstv.pages.roku.auth.RokuRegisterPageImpl;
import protocol.IPlatformProtocol;

import static models.Selector.Data.attr;
import static protocol.Button.*;

public class RokuHomePageImpl extends RokuMenuPage implements IHomePage {

    private static final String CAROUSEL_ID = "vertical_scroll_group";
    private static final String JOIN_NOW_BUTTON_ID = "join";
    private static final String SIGN_IN_BUTTON_ID = "sign_in";
    private final Selector buttonsSelector = new Selector.Builder()
            .addElementData(attr("focused", "true"))
            .addParentData(attr("name", "home_screen"))
            .addParentData(attr("extends", "Group"))
            .build();
    private final Selector loginContainerSelector = new Selector.Builder()
            .addElementData(attr("name", "login_container"))
            .addElementData(attr("visible", "false"))
            .addParentData(attr("name", "home_screen"))
            .addParentData(attr("focused", "true"))
            .build();

    public RokuHomePageImpl(IPlatformProtocol<Selector> protocol) {
        super(protocol);
    }

    public static RokuHomePageImpl openHomePage(IPlatformProtocol<Selector> protocol) {
        protocol.openChannel();
        return new RokuHomePageImpl(protocol);
    }

    @Override
    public boolean isLoginButtonsVisible() {
        try {
            return protocol.findElement(loginContainerSelector).isVisible();
        } catch (NoSuchElementException exception) {
            return true;
        }
    }

    @Override
    public IEpisodePage openEpisodePage() {
        //do something here
        return new RokuEpisodePageImpl(protocol);
    }

    @Override
    public ILoginPage openLoginPage() {
        String activeElementID = protocol.findElement(buttonsSelector).getId();
        if (activeElementID.equals(CAROUSEL_ID)) {
            do {
                protocol.pressButton(UP);
                activeElementID = protocol.findElement(buttonsSelector).getId();
            }
            while (activeElementID.equals(CAROUSEL_ID));
        }
        if (activeElementID.equals(JOIN_NOW_BUTTON_ID)) {
            protocol.pressButton(RIGHT);
        }
        protocol.pressButton(OK);
        return new RokuLoginPageImpl(protocol, this);
    }

    @Override
    public IRegisterPage openRegisterPage() {
        String activeElementID = protocol.findElement(buttonsSelector).getId();
        if (activeElementID.equals(CAROUSEL_ID)) {
            do {
                protocol.pressButton(UP);
                activeElementID = protocol.findElement(buttonsSelector).getId();
            }
            while (activeElementID.equals(CAROUSEL_ID));
        }
        if (activeElementID.equals(SIGN_IN_BUTTON_ID)) {
            protocol.pressButton(LEFT);
        }
        protocol.pressButton(OK);
        return new RokuRegisterPageImpl(protocol);
    }
}