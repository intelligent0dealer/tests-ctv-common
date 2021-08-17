package mstv.pages.roku.auth;

import models.Selector;
import mstv.pages.base.IRegisterPage;
import protocol.IPlatformProtocol;

import static models.Selector.Data.attr;
import static models.Selector.Data.tag;
import static protocol.Button.*;

public class RokuRegisterPageImpl extends BaseRokuAuthPage implements IRegisterPage {

    private final static String NEWSLETTER_NOTIFICATION_BUTTON_ID = "ok_by_email_button";
    private final static String IC_CHECKBOX_MARKED_OUTLINE_URI = "pkg:/images/ic_checkbox_marked_outline.png";
    private final static String IC_CHECKBOX_BLANK_OUTLINE_URI = "pkg:/images/ic_checkbox_blank_outline.png";

    public RokuRegisterPageImpl(IPlatformProtocol<Selector> protocol) {
        super(protocol);
    }

    @Override
    public void toggleNewsletterNotification(boolean isEnabled) {
        String activeElementID = protocol.findElement(buttonSelector).getId();
        if (activeElementID.equals(EMAIL_BUTTON_ID) || activeElementID.equals(PASSWORD_BUTTON_ID)) {
            do {
                protocol.pressButton(DOWN);
                activeElementID = protocol.findElement(buttonSelector).getId();
            } while (!activeElementID.equals(NEWSLETTER_NOTIFICATION_BUTTON_ID));
        } else {
            while (!activeElementID.equals(NEWSLETTER_NOTIFICATION_BUTTON_ID)) {
                protocol.pressButton(UP);
                activeElementID = protocol.findElement(buttonSelector).getId();
            }
        }
        final Selector iconSelector = new Selector.Builder()
                .addElementData(attr("focused", "true"))
                .addElementData(attr("name", "left_icon"))
                .addParentData(attr("extends", "MsTvScreen"))
                .addParentData(tag("MsTvButton"))
                .build();
        String uri = protocol.findElement(iconSelector).getUri();
        if ((!isEnabled && IC_CHECKBOX_MARKED_OUTLINE_URI.equals(uri)) || (isEnabled && IC_CHECKBOX_BLANK_OUTLINE_URI.equals(uri))) {
            protocol.pressButton(OK);
        }
    }
}