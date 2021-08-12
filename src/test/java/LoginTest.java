import data.User;
import models.Selector;
import mstv.pages.roku.RokuHomePageImpl;
import org.testng.annotations.*;
import protocol.IPlatformProtocol;
import protocol.roku.RokuProtocolImpl;

import java.util.Collections;
import java.util.Iterator;

import static mstv.pages.roku.RokuHomePageImpl.openHomePage;
import static org.testng.Assert.assertFalse;

public class LoginTest extends AbstractTest {

    private IPlatformProtocol<Selector> protocol;
    private RokuHomePageImpl homePage;

    @DataProvider
    private Iterator<Object[]> provideUsers() {
        return Collections.singletonList(new Object[]{new User("efim.filippov@motorsport.com", "dfg43jh7")}).iterator();
    }

    @BeforeClass
    void beforeClass() {
        protocol = new RokuProtocolImpl(getConfiguration());
        homePage = openHomePage(protocol);
    }

    @Test(dataProvider = "provideUsers")
    void shouldLoginSuccessfully(User user) {
        homePage.openLoginPage()
                .typeEmail(user.getEmail())
                .typePassword(user.getPassword())
                .submit();
        assertFalse(homePage.isLoginButtonsVisible());
    }

    @AfterMethod
    void afterMethod() {
        homePage.openSettingsPage().signOut();
    }

    @AfterClass
    void afterClass() {
        if (protocol != null) {
            protocol.closeSession();
        }
    }
}