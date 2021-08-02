package mstv.pages.base;

import mstv.pages.roku.RokuHomePageImpl;
import protocol.Configuration;
import protocol.IPlatformProtocol;

public interface IHomePage {

    static IHomePage createHomePage(Configuration configuration, IPlatformProtocol protocol) {
        switch (configuration.getPlatform()) {
            case ROKU: {
                return new RokuHomePageImpl(configuration, protocol);
            }
            default:
                throw new IllegalArgumentException();
        }
    }

    IEpisodePage openEpisodePage();

    ILoginPage openLoginPage();
}