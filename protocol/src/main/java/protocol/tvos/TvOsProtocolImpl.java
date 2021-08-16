package protocol.tvos;

import protocol.Button;
import protocol.IPlatformProtocol;
import protocol.PlatformElement;

public class TvOsProtocolImpl implements IPlatformProtocol<Object> {

    @Override
    public void closeSession() {

    }

    @Override
    public void openChannel(String channelID) {

    }

    @Override
    public void pressButton(Button... button) {
    }

    @Override
    public void sendKeys(String text) {

    }

    @Override
    public PlatformElement getActiveElement() {
        return null;
    }

    @Override
    public PlatformElement findElement(Object selector) {
        return null;
    }
}
