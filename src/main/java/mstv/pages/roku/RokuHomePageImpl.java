package mstv.pages.roku;

import mstv.pages.base.IEpisodePage;
import mstv.pages.base.IHomePage;
import mstv.pages.base.ILoginPage;
import protocol.Configuration;
import protocol.IPlatformProtocol;
import protocol.PlatformElement;

import static protocol.By.text;

public class RokuHomePageImpl implements IHomePage {

    private static final String SIGN_IN_TEXT = "SIGN IN";
    private final Configuration configuration;
    private final IPlatformProtocol protocol;

    public RokuHomePageImpl(Configuration configuration, IPlatformProtocol protocol) {
        this.configuration = configuration;
        this.protocol = protocol;
    }

    @Override
    public IEpisodePage openEpisodePage() {
        //do something here
        return IEpisodePage.openEpisodePage(configuration, protocol);
    }

    @Override
    public ILoginPage openLoginPage() {
        //1. найти кнопку SIGN IN
        PlatformElement element = protocol.findElement(text(SIGN_IN_TEXT));
        //2. получить текущий активный элемент
        PlatformElement activeElement = protocol.getActiveElement();
        //3. произвести переход на кнопку SIGN IN в зависимости от текущей позиции
        //4. нажать на кнопку SING IN
        //protocol.pressButton(Button.OK);
        return ILoginPage.openLoginPage(configuration, protocol);
    }
}