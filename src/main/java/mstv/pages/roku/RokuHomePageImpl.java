package mstv.pages.roku;

import models.Selector;
import mstv.pages.base.IEpisodePage;
import mstv.pages.base.IHomePage;
import mstv.pages.base.ILoginPage;
import protocol.IPlatformProtocol;

import static models.Selector.Data.attr;
import static protocol.Button.*;

public class RokuHomePageImpl implements IHomePage {

    private static final String CAROUSEL_ID = "vertical_scroll_group";
    private static final String JOIN_NOW_BUTTON_ID = "join";
    private final IPlatformProtocol<Selector> protocol;
    private final Selector selector = new Selector.Builder()
            .addElementData(attr("focused", "true"))
            .addParentData(attr("name", "home_screen"))
            .addParentData(attr("extends", "Group"))
            .build();

    public RokuHomePageImpl(IPlatformProtocol<Selector> protocol) {
        this.protocol = protocol;
    }

    public static IHomePage openHomePage(IPlatformProtocol<Selector> protocol) {
        return new RokuHomePageImpl(protocol);
    }

    @Override
    public IEpisodePage openEpisodePage() {
        //do something here
        return new RokuEpisodePageImpl(protocol);
    }

    @Override
    public ILoginPage openLoginPage() {
        String activeElementID = protocol.findElement(selector).getId();
        if (activeElementID.equals(CAROUSEL_ID)) {
            do {
                protocol.pressButton(UP);
                activeElementID = protocol.findElement(selector).getId();
            }
            while (activeElementID.equals(CAROUSEL_ID));
        }
        if (activeElementID.equals(JOIN_NOW_BUTTON_ID)) {
            protocol.pressButton(RIGHT);
        }
        protocol.pressButton(OK);
        return new RokuLoginPageImpl(protocol);
    }
}