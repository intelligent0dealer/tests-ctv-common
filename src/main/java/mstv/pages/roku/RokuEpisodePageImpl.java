package mstv.pages.roku;

import models.Selector;
import mstv.pages.base.IEpisodePage;
import protocol.IPlatformProtocol;

public class RokuEpisodePageImpl extends RokuMenuPage implements IEpisodePage {

    public RokuEpisodePageImpl(IPlatformProtocol<Selector> protocol) {
        super(protocol);
    }
}