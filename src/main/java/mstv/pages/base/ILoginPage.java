package mstv.pages.base;

import mstv.pages.roku.RokuLoginPageImpl;
import protocol.Configuration;
import protocol.IPlatformProtocol;

public interface ILoginPage {

    static ILoginPage openLoginPage(Configuration configuration, IPlatformProtocol protocol) {
        switch (configuration.getPlatform()) {
            case ROKU: {
                return new RokuLoginPageImpl(configuration, protocol);
            }
            default:
                throw new IllegalArgumentException();
        }
    }
}