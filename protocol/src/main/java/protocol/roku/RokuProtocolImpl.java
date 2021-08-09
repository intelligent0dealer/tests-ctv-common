package protocol.roku;

import models.Element;
import models.RokuButton;
import models.Timeout;
import protocol.*;
import webdriver.RokuDriver;
import webdriver.Utils;

import java.util.stream.Stream;

import static models.Selector.Builder;
import static models.Selector.Data.*;
import static models.Sequence.createSequence;

public class RokuProtocolImpl implements IPlatformProtocol {

    private final RokuDriver driver;

    public RokuProtocolImpl(Configuration configuration) {
        driver = new RokuDriver(
                configuration.getDriverUrl(),
                configuration.getClientIpAddress(),
                configuration.getDelay(),
                configuration.isLogEnabled()
        );
    }

    @Override
    public void closeSession() {
        driver.closeSession();
    }

    @Override
    public void pressButton(Button... button) {
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

        Stream<Element.Attr> stream = element.getAttrs().stream();
        String name = stream.filter(attr -> Utils.filterAttr(attr, "name"))
                .map(Element.Attr::getValue)
                .findFirst()
                .orElse("");
        platformElement.setId(name);
        return platformElement;
    }

    private RokuButton[] toRokuButton(Button... button) {
        RokuButton[] rokuButtons = new RokuButton[button.length];
        for (int i = 0; i < button.length; i++) {
            Button tempButton = button[i];
            switch (tempButton) {
                case UP:
                    rokuButtons[i] = RokuButton.UP;
                case RIGHT:
                    rokuButtons[i] = RokuButton.RIGHT;
                case OK:
                    rokuButtons[i] = RokuButton.OK;
                case DOWN:
                    rokuButtons[i] = RokuButton.DOWN;
                case LEFT:
                    rokuButtons[i] = RokuButton.LEFT;
                case BACK:
                    rokuButtons[i] = RokuButton.BACK;
                case REFRESH:
                    rokuButtons[i] = RokuButton.REFRESH;
                default:
                    throw new IllegalArgumentException("Unknown button");
            }
        }
        return rokuButtons;
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