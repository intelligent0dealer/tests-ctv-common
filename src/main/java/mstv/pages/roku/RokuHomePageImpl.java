package mstv.pages.roku;

import mstv.pages.base.IEpisodePage;
import mstv.pages.base.IHomePage;
import mstv.pages.base.ILoginPage;
import protocol.*;

import static protocol.By.attr;
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
        Selector parent = attr("name", "home_screen").addAttribute("extends", "Group");
        Selector selector = attr("focused", "true").setParent(parent);
        PlatformElement activeElement = protocol.findElement(selector);
        //3. произвести переход на кнопку SIGN IN в зависимости от текущей позиции
        switch (activeElement.getId()){
            case "join": {
                protocol.pressButton(Button.RIGHT);
                protocol.pressButton(Button.OK);
                break;
            }
            case "sign_in":{
                protocol.pressButton(Button.OK);
                break;
            }
            case "vertical_scroll_group":{
                do{
                    protocol.pressButton(Button.UP);
                    activeElement = protocol.findElement(selector);
                }
                while (activeElement.getId().equals("vertical_scroll_group"));
                if (activeElement.getId().equals("join")) {
                    protocol.pressButton(Button.RIGHT);
                }
                protocol.pressButton(Button.OK);
                break;
            }
        }
        //4. нажать на кнопку SING IN
        //protocol.pressButton(Button.OK);
        return ILoginPage.openLoginPage(configuration, protocol);
    }
}