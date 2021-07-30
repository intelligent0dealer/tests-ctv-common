package protocol;

public interface IPlatformProtocol {

    void pressButton(Button button);

    void sendKeys(String text);

    PlatformElement getActiveElement();

    PlatformElement findElement(Selector selector);
}