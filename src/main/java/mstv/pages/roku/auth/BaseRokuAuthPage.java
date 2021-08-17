package mstv.pages.roku.auth;

import models.Selector;
import mstv.pages.base.ILoginPage;
import protocol.IPlatformProtocol;
import protocol.PlatformElement;

import java.util.ArrayList;
import java.util.List;

import static models.Selector.Data.attr;
import static models.Selector.Data.tag;
import static protocol.Button.*;

public class BaseRokuAuthPage implements ILoginPage {

    protected static final String EMAIL_BUTTON_ID = "email_button";
    protected static final String PASSWORD_BUTTON_ID = "password_button";
    protected static final String SUBMIT_BUTTON_ID = "login_button";
    protected static final String RESET_PASSWORD_BUTTON_ID = "reset_password_button";
    protected final IPlatformProtocol<Selector> protocol;
    protected final Selector buttonSelector = new Selector.Builder()
            .addElementData(attr("focused", "true"))
            .addParentData(attr("extends", "MsTvScreen"))
            .addParentData(tag("MsTvButton"))
            .build();
    protected final Selector bulletTextSelector = new Selector.Builder()
            .addElementData(tag("BulletText"))
            .build();

    public BaseRokuAuthPage(IPlatformProtocol<Selector> protocol) {
        this.protocol = protocol;
    }

    @Override
    public ILoginPage typeEmail(String email) {
        String activeElementID = protocol.findElement(buttonSelector).getId();
        if (!activeElementID.equals(EMAIL_BUTTON_ID)) {
            do {
                protocol.pressButton(UP);
                activeElementID = protocol.findElement(buttonSelector).getId();
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
        String activeElementID = protocol.findElement(buttonSelector).getId();
        if (activeElementID.equals(EMAIL_BUTTON_ID)) {
            protocol.pressButton(DOWN);
        } else if (!activeElementID.equals(PASSWORD_BUTTON_ID)) {
            do {
                protocol.pressButton(UP);
                activeElementID = protocol.findElement(buttonSelector).getId();
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
        String activeElementID = protocol.findElement(buttonSelector).getId();
        if (activeElementID.equals(EMAIL_BUTTON_ID) || activeElementID.equals(PASSWORD_BUTTON_ID)) {
            do {
                protocol.pressButton(DOWN);
                activeElementID = protocol.findElement(buttonSelector).getId();
            } while (!activeElementID.equals(SUBMIT_BUTTON_ID));
        } else {
            while (!activeElementID.equals(SUBMIT_BUTTON_ID)) {
                protocol.pressButton(UP);
                activeElementID = protocol.findElement(buttonSelector).getId();
            }
        }
        protocol.pressButton(OK);
    }

    @Override
    public List<String> getErrorMessages() {
        ArrayList<String> messages = new ArrayList<>();
        int childCount = protocol.findElement(bulletTextSelector).getChildCount();
        for (int i = 0; i < childCount; i = i + 2) {
            Selector errorMsgSelector = new Selector.Builder()
                    .addElementData(attr("focused", "true"))
                    .addElementData(attr("uiElementId", "bullet-line"))
                    .addElementData(attr("index", Integer.toString(i)))
                    .build();
            PlatformElement element = protocol.findElement(errorMsgSelector);
            messages.add(element.getText());
        }
        return messages;
    }
}
