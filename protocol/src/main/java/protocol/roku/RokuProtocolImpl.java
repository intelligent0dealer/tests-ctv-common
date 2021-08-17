package protocol.roku;

import models.Element;
import models.RokuButton;
import models.Selector;
import protocol.Button;
import protocol.Configuration;
import protocol.IPlatformProtocol;
import protocol.PlatformElement;
import webdriver.RokuDriver;
import webdriver.Utils;

import java.util.Arrays;
import java.util.stream.Stream;

import static models.RokuButton.*;
import static models.Sequence.createSequence;

public class RokuProtocolImpl implements IPlatformProtocol<Selector> {

    private final RokuDriver driver;

    public RokuProtocolImpl(Configuration configuration) {
        driver = new RokuDriver(
                configuration.getDriverUrl(),
                configuration.getClientIpAddress(),
                configuration.getTimeout(),
                configuration.getDelay(),
                configuration.isLogEnabled()
        );
    }

    @Override
    public void closeSession() {
        driver.closeSession();
    }

    @Override
    public void openChannel(String channelID) {
        driver.openChannel(channelID);
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
        return toPlatformElement(driver.findElement(selector));
    }

    private RokuButton mapButton(Button button) {
        switch (button) {
            case UP:
                return UP;
            case RIGHT:
                return RIGHT;
            case OK:
                return OK;
            case DOWN:
                return DOWN;
            case LEFT:
                return LEFT;
            case BACK:
                return BACK;
            case REFRESH:
                return REFRESH;
            case FAST_FORWARD:
                return FAST_FORWARD;
            case REWIND:
                return REWIND;
            default:
                throw new IllegalArgumentException("Unknown button");
        }
    }

    private PlatformElement toPlatformElement(Element element) {
        PlatformElement platformElement = new PlatformElement();
        String name = readAttr(element.getAttrs().stream(), "name", "");
        String isVisible = readAttr(element.getAttrs().stream(), "visible", "true");
        String focusedItemIndex = readAttr(element.getAttrs().stream(), "focusItem", "-1");
        String isFocused = readAttr(element.getAttrs().stream(), "focused", "false");
        String text = readAttr(element.getAttrs().stream(), "text", "");
        String childCount = readAttr(element.getAttrs().stream(), "children", "0");
        String uri = readAttr(element.getAttrs().stream(), "uri", "");
        platformElement.setId(name);
        platformElement.setVisible(Boolean.parseBoolean(isVisible));
        platformElement.setFocusedItemIndex(Integer.parseInt(focusedItemIndex));
        platformElement.setFocused(Boolean.parseBoolean(isFocused));
        platformElement.setChildCount(Integer.parseInt(childCount));
        platformElement.setText(text);
        platformElement.setUri(uri);
        return platformElement;
    }

    private RokuButton[] toRokuButton(Button... button) {
        return Arrays.stream(button).map(this::mapButton).toArray(RokuButton[]::new);
    }

    private String readAttr(Stream<Element.Attr> stream, String key, String defaultValue) {
        return stream.filter(attr -> Utils.filterAttr(attr, key))
                .map(Element.Attr::getValue)
                .findFirst()
                .orElse(defaultValue);
    }
}