package mstv.pages.base;

import mstv.pages.roku.RokuEpisodePageImpl;
import protocol.Configuration;
import protocol.IPlatformProtocol;

public interface IEpisodePage {

    static IEpisodePage createEpisodePage(Configuration configuration, IPlatformProtocol protocol) {
        switch (configuration.getPlatform()) {
            case ROKU: {
                return new RokuEpisodePageImpl(configuration, protocol);
            }
            default:
                throw new IllegalArgumentException();
        }
    }

    void playVideo();
}