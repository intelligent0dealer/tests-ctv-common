package protocol;

public interface IPlatformProtocol<T> {

    void closeSession();

    void openChannel(String channelID);

    void pressButton(Button... button);

    void sendKeys(String text);

    PlatformElement getActiveElement();

    PlatformElement findElement(T selector);
}