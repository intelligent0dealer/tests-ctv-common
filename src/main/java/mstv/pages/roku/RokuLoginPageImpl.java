package mstv.pages.roku;

import mstv.pages.base.ILoginPage;
import protocol.Configuration;
import protocol.IPlatformProtocol;
import protocol.Selector;

import static protocol.Button.*;
import static protocol.By.attr;
import static protocol.By.tag;

public class RokuLoginPageImpl implements ILoginPage {

    private static final String EMAIL_BUTTON_ID = "email_button";
    private static final String PASSWORD_BUTTON_ID = "password_button";
    private static final String SUBMIT_BUTTON_ID = "login_button";
    private static final String RESET_PASSWORD_BUTTON_ID = "reset_password_button";
    private final Configuration configuration;
    private final IPlatformProtocol protocol;
    private final Selector parent = tag("MsTvButton").addAttribute("extends", "MsTvScreen");
    private final Selector selector = attr("focused", "true").setParent(parent);

    public RokuLoginPageImpl(Configuration configuration, IPlatformProtocol protocol) {
        this.configuration = configuration;
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
    public void submit() {
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
    }
}