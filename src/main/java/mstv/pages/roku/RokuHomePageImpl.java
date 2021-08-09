package mstv.pages.roku;

import mstv.pages.base.IEpisodePage;
import mstv.pages.base.IHomePage;
import mstv.pages.base.ILoginPage;
import protocol.Button;
import protocol.Configuration;
import protocol.IPlatformProtocol;
import protocol.Selector;

import static protocol.By.attr;

public class RokuHomePageImpl implements IHomePage {

    private static final String CAROUSEL_ID = "vertical_scroll_group";
    private static final String JOIN_NOW_BUTTON_ID = "join";
    private final Configuration configuration;
    private final IPlatformProtocol protocol;

    public RokuHomePageImpl(Configuration configuration, IPlatformProtocol protocol) {
        this.configuration = configuration;
        this.protocol = protocol;
    }

    @Override
    public IEpisodePage openEpisodePage() {
        //do something here
        return IEpisodePage.openEpisodePage(configuration, protocol);
    }

    @Override
    public ILoginPage openLoginPage() {
        Selector parent = attr("name", "home_screen").addAttribute("extends", "Group");
        Selector selector = attr("focused", "true").setParent(parent);
        String activeElementID = protocol.findElement(selector).getId();
        if (activeElementID.equals(CAROUSEL_ID)) {
            do {
                protocol.pressButton(Button.UP);
                activeElementID = protocol.findElement(selector).getId();
            }
            while (activeElementID.equals(CAROUSEL_ID));
        }
        if (activeElementID.equals("join")) {
            protocol.pressButton(Button.RIGHT);
        }
        protocol.pressButton(Button.OK);
        return ILoginPage.openLoginPage(configuration, protocol);
    }
}