package mstv.pages.roku;

import mstv.pages.base.IEpisodePage;
import mstv.pages.base.IHomePage;
import protocol.Configuration;
import protocol.IPlatformProtocol;

import static mstv.pages.base.IEpisodePage.createEpisodePage;

public class RokuHomePageImpl implements IHomePage {

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
}