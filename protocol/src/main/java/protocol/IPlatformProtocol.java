package protocol;

public interface IPlatformProtocol<T> {

    void closeSession();

    void pressButton(Button... button);

    void sendKeys(String text);

    PlatformElement getActiveElement();

    PlatformElement findElement(T selector);
}