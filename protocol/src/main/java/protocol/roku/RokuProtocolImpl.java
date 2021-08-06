package protocol.roku;

import models.Element;
import models.RokuButton;
import protocol.*;
import webdriver.RokuDriver;

import static models.Selector.Builder;
import static models.Selector.Data.*;
import static models.Sequence.createSequence;

public class RokuProtocolImpl implements IPlatformProtocol {

    private final RokuDriver driver;

    public RokuProtocolImpl(Configuration configuration) {
        driver = new RokuDriver(
                configuration.getDriverUrl(),
                configuration.getClientIpAddress(),
                configuration.isLogEnabled()
        );
    }

    @Override
    public void closeSession() {
        driver.closeSession();
    }

    @Override
    public void pressButton(Button button) {
        driver.pressKeySequence(createSequence(toRokuButton(button)));
    }

    @Override
    public void sendKeys(String text) {
        driver.pressKeySequence(createSequence(text));
    }

    @Override
    public PlatformElement getActiveElement() {
        return toPlatformElement(driver.getActiveElement());
    }

    @Override
    public PlatformElement findElement(Selector selector) {
        return toPlatformElement(driver.findElement(toRokuSelector(selector)));
    }

    private PlatformElement toPlatformElement(Element element) {
        PlatformElement platformElement = new PlatformElement();
        //todo map Element to PlatformElement
        return platformElement;
    }

    private RokuButton toRokuButton(Button button) {
        switch (button) {
            case UP:
                return RokuButton.UP;
            default:
                throw new IllegalArgumentException("Unknown button");
        }
    }

    public models.Selector toRokuSelector(Selector selector) {
        Builder builder = new Builder();
        if (selector.getText() != null && !selector.getText().isEmpty()) {
            builder.addElementData(text(selector.getText()));
        }
        if (selector.getTag() != null && !selector.getTag().isEmpty()) {
            builder.addElementData(tag(selector.getTag()));
        }
        if (selector.getAttributes().size() > 0) {
            selector.getAttributes().forEach((name, value) -> builder.addElementData(attr(name, value)));
        }
        if (selector.getParent() != null) {
            if (selector.getParent().getText() != null && !selector.getParent().getText().isEmpty()) {
                builder.addParentData(text(selector.getParent().getText()));
            }
            if (selector.getParent().getTag() != null && !selector.getParent().getTag().isEmpty()) {
                builder.addParentData(text(selector.getParent().getTag()));
            }
            if (selector.getParent().getAttributes().size() > 0) {
                selector.getParent().getAttributes().forEach((name, value) -> builder.addParentData(attr(name, value)));
            }
        }
        return builder.build();
    }
}