package mstv.pages.base;

import mstv.pages.roku.RokuEpisodePageImpl;
import protocol.Configuration;
import protocol.IPlatformProtocol;

public interface IEpisodePage {

    static IEpisodePage openEpisodePage(Configuration configuration, IPlatformProtocol protocol) {
        switch (configuration.getPlatform()) {
            case ROKU: {
                return new RokuEpisodePageImpl(configuration, protocol);
            }
            default:
                throw new IllegalArgumentException();
        }
    }
}