package mstv.pages.roku;

import models.Selector;
import mstv.pages.base.IEpisodePage;
import protocol.IPlatformProtocol;

public class RokuEpisodePageImpl implements IEpisodePage {

    private final IPlatformProtocol<Selector> protocol;

    public RokuEpisodePageImpl(IPlatformProtocol<Selector> protocol) {
        this.protocol = protocol;
    }
}