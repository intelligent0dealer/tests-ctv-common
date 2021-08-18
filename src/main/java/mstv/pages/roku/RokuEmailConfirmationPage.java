package mstv.pages.roku;

import models.Selector;
import mstv.db.DatabaseUtils;
import mstv.pages.base.IEmailConfirmationPage;
import mstv.pages.base.ISubscriptionPage;
import protocol.IPlatformProtocol;

public class RokuEmailConfirmationPage implements IEmailConfirmationPage {

    private final IPlatformProtocol<Selector> protocol;

    public RokuEmailConfirmationPage(IPlatformProtocol<Selector> protocol) {
        this.protocol = protocol;
    }

    @Override
    public ISubscriptionPage confirmEmail(String email) {
        DatabaseUtils.confirmEmail(email);
        return new RokuSubscriptionPageImpl(protocol);
    }
}