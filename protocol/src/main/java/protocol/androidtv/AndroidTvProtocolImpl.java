package protocol.androidtv;

import protocol.Button;
import protocol.IPlatformProtocol;
import protocol.PlatformElement;

public class AndroidTvProtocolImpl implements IPlatformProtocol<Object> {

/*    public AndroidTvProtocolImpl() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        Intent intent = appContext.getPackageManager().getLaunchIntentForPackage("com.example.myapplication");
        appContext.startActivity(intent);
        device.wait(Until.hasObject(By.pkg("com.example.myapplication").depth(0)), 30000);
    }*/

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