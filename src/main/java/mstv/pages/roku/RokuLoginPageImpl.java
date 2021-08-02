package mstv.pages.roku;

import mstv.pages.base.ILoginPage;
import protocol.Configuration;
import protocol.IPlatformProtocol;

public class RokuLoginPageImpl implements ILoginPage {

    private final Configuration configuration;
    private final IPlatformProtocol protocol;

    public RokuLoginPageImpl(Configuration configuration, IPlatformProtocol protocol) {
        this.configuration = configuration;
        this.protocol = protocol;
    }
}