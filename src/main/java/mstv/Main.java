package mstv;

import mstv.pages.base.IHomePage;
import protocol.Configuration;
import protocol.Configuration.Builder;
import protocol.IPlatformProtocol;
import protocol.Platform;
import protocol.ProtocolFactory;

import static mstv.pages.base.IHomePage.openHomePage;

public class Main {

    public static void main(String[] args) {
        Configuration configuration = new Builder()
                .platform(Platform.ROKU)
                .driverUrl("http://localhost:9000")
                .clientIpAddress("192.168.0.4")
                .log(true)
                .build();
        IPlatformProtocol protocol = ProtocolFactory.createProtocol(configuration);
        IHomePage homePage = openHomePage(configuration, protocol);
        homePage.openLoginPage();
        protocol.closeSession();
    }
}