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

    public static final String DEFAULT_CHANNEL_ID = "dev";
    private final RokuDriver driver;

    public RokuProtocolImpl(Configuration configuration) {
        driver = new RokuDriver(
                configuration.getDriverUrl(),
                configuration.getClientIpAddress(),
                configuration.getTimeout(),
                configuration.getDelay(),
                configuration.isLogEnabled()
        );
        driver.openChannel(DEFAULT_CHANNEL_ID);
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
            default:
                throw new IllegalArgumentException("Unknown button");
        }
    }

    private PlatformElement toPlatformElement(Element element) {
        PlatformElement platformElement = new PlatformElement();
        String name = readAttr(element.getAttrs().stream(), "name", "");
        String visible = readAttr(element.getAttrs().stream(), "visible", "true");
        platformElement.setId(name);
        platformElement.setVisible(Boolean.parseBoolean(visible));
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