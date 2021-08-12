package mstv.pages.roku;

import models.Selector;
import mstv.pages.base.IHomePage;
import mstv.pages.base.ILoginPage;
import protocol.IPlatformProtocol;

import static models.Selector.Data.attr;
import static models.Selector.Data.tag;
import static protocol.Button.*;

public class RokuLoginPageImpl implements ILoginPage {

    private static final String EMAIL_BUTTON_ID = "email_button";
    private static final String PASSWORD_BUTTON_ID = "password_button";
    private static final String SUBMIT_BUTTON_ID = "login_button";
    private static final String RESET_PASSWORD_BUTTON_ID = "reset_password_button";
    private final IHomePage homePage;
    private final IPlatformProtocol<models.Selector> protocol;
    private final Selector selector = new Selector.Builder()
            .addElementData(attr("focused", "true"))
            .addParentData(attr("extends", "MsTvScreen"))
            .addParentData(tag("MsTvButton"))
            .build();

    public RokuLoginPageImpl(IHomePage homePage, IPlatformProtocol<Selector> protocol) {
        this.homePage = homePage;
        this.protocol = protocol;
    }

    @Override
    public ILoginPage typeEmail(String email) {
        String activeElementID = protocol.findElement(selector).getId();
        if (!activeElementID.equals(EMAIL_BUTTON_ID)) {
            do {
                protocol.pressButton(UP);
                activeElementID = protocol.findElement(selector).getId();
            }
            while (!activeElementID.equals(EMAIL_BUTTON_ID));
        }
        protocol.pressButton(OK);
        protocol.sendKeys(email);
        protocol.pressButton(BACK);
        return this;
    }

    @Override
    public ILoginPage typePassword(String password) {
        String activeElementID = protocol.findElement(selector).getId();
        if (activeElementID.equals(EMAIL_BUTTON_ID)) {
            protocol.pressButton(DOWN);
        } else if (!activeElementID.equals(PASSWORD_BUTTON_ID)) {
            do {
                protocol.pressButton(UP);
                activeElementID = protocol.findElement(selector).getId();
            }
            while (!activeElementID.equals(PASSWORD_BUTTON_ID));
        }
        protocol.pressButton(OK);
        protocol.sendKeys(password);
        protocol.pressButton(BACK);
        return this;
    }

    @Override
    public IHomePage submit() {
        String activeElementID = protocol.findElement(selector).getId();
        if (activeElementID.equals(EMAIL_BUTTON_ID) || activeElementID.equals(PASSWORD_BUTTON_ID)) {
            do {
                protocol.pressButton(DOWN);
                activeElementID = protocol.findElement(selector).getId();
            } while (!activeElementID.equals(SUBMIT_BUTTON_ID));
        } else {
            while (!activeElementID.equals(SUBMIT_BUTTON_ID)) {
                protocol.pressButton(UP);
                activeElementID = protocol.findElement(selector).getId();
            }
        }
        protocol.pressButton(OK);
        return homePage;
    }
}