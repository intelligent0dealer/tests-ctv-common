package protocol.roku;

import models.Element;
import models.RokuButton;
import protocol.*;
import webdriver.RokuDriver;

import static models.Selector.Builder;
import static models.Selector.Data;
import static models.Selector.LocatorStrategy.Tag;
import static models.Selector.LocatorStrategy.Text;
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
            builder.addElementData(new Data(Text, selector.getText()));
        }
        if (selector.getTag() != null && !selector.getTag().isEmpty()) {
            builder.addElementData(new Data(Tag, selector.getTag()));
        }
        return builder.build();
    }
}