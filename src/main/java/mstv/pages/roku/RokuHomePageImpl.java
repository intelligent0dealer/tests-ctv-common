package mstv.pages.roku;

import mstv.pages.base.IEpisodePage;
import mstv.pages.base.IHomePage;
import mstv.pages.base.ILoginPage;
import protocol.Configuration;
import protocol.IPlatformProtocol;
import protocol.PlatformElement;

import static mstv.pages.base.IEpisodePage.createEpisodePage;
import static mstv.pages.base.ILoginPage.createLoginPage;
import static protocol.By.text;

public class RokuHomePageImpl implements IHomePage {

    private static final String SIGN_IN_TEXT = "SIGN IN";
    private final Configuration configuration;
    private final IPlatformProtocol protocol;

    public RokuHomePageImpl(Configuration configuration, IPlatformProtocol protocol) {
        this.configuration = configuration;
        this.protocol = protocol;
    }

    @Override
    public IEpisodePage openEpisodePage() {
        //do something here
        return createEpisodePage(configuration, protocol);
    }

    @Override
    public ILoginPage openLoginPage() {
        PlatformElement element = protocol.findElement(text(SIGN_IN_TEXT));
        return createLoginPage(configuration, protocol);
    }
}