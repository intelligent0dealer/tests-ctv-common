import models.Selector;
import mstv.pages.roku.RokuHomePageImpl;
import org.testng.annotations.*;
import protocol.Configuration;
import protocol.IPlatformProtocol;
import protocol.roku.RokuProtocolImpl;

import javax.inject.Inject;
import java.lang.reflect.Method;

import static mstv.pages.roku.RokuHomePageImpl.openHomePage;

@Guice(modules = BasicModule.class)
public abstract class AbstractTest {

    @Inject
    protected Configuration configuration;
    protected IPlatformProtocol<Selector> protocol;
    protected RokuHomePageImpl homePage;

    @BeforeClass
    void beforeClass() {
        protocol = new RokuProtocolImpl(configuration);
    }

    @BeforeMethod
    void beforeMethod() {
        homePage = openHomePage(protocol);
    }

    @AfterMethod
    void afterMethod(Method method) {
        switch (method.getName()) {
            case "shouldLoginSuccessfully":
            case "shouldRegisterSuccessfully": {
                homePage.openSettingsPage().signOut();
                break;
            }
        }
    }

    @AfterClass
    void afterClass() {
        if (protocol != null) {
            protocol.closeSession();
            protocol = null;
        }
        homePage = null;
    }
}