package mstv.pages.roku.auth;

import models.Selector;
import protocol.IPlatformProtocol;

public class RokuLoginPageImpl extends BaseRokuAuthPage {

    public RokuLoginPageImpl(IPlatformProtocol<Selector> protocol) {
        super(protocol);
    }
}