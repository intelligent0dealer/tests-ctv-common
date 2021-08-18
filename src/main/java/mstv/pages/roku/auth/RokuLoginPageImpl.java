package mstv.pages.roku.auth;

import models.Selector;
import mstv.pages.base.IHomePage;
import mstv.pages.base.ILoginPage;
import protocol.IPlatformProtocol;

public class RokuLoginPageImpl extends AbstractRokuAuthPage implements ILoginPage {

    private final IHomePage homePage;

    public RokuLoginPageImpl(IPlatformProtocol<Selector> protocol, IHomePage homePage) {
        super(protocol);
        this.homePage = homePage;
    }

    @Override
    public IHomePage submit() {
        pressNextButton();
        return homePage;
    }
}