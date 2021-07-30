package mstv.pages.roku;

import mstv.pages.base.IEpisodePage;
import protocol.Configuration;
import protocol.IPlatformProtocol;

public class RokuEpisodePageImpl implements IEpisodePage {

    private final Configuration configuration;
    private final IPlatformProtocol protocol;

    public RokuEpisodePageImpl(Configuration configuration, IPlatformProtocol protocol) {
        this.configuration = configuration;
        this.protocol = protocol;
    }

    @Override
    public void playVideo() {
        //do something here
    }
}