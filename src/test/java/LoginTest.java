import data.User;
import models.Selector;
import mstv.pages.base.IHomePage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import protocol.IPlatformProtocol;
import protocol.roku.RokuProtocolImpl;

import java.util.Collections;
import java.util.Iterator;

import static mstv.pages.roku.RokuHomePageImpl.openHomePage;
import static org.testng.Assert.assertFalse;

public class LoginTest extends AbstractTest {

    private IPlatformProtocol<Selector> protocol;

    @DataProvider
    private Iterator<Object[]> provideUsers() {
        return Collections.singletonList(new Object[]{new User("efim.filippov@motorsport.com", "dfg43jh7")}).iterator();
    }

    @BeforeClass
    void beforeClass() {
        protocol = new RokuProtocolImpl(getConfiguration());
    }

    @Test(dataProvider = "provideUsers")
    void shouldLoginSuccessfully(User user) {
        IHomePage homePage = openHomePage(protocol).openLoginPage()
                .typeEmail(user.getEmail())
                .typePassword(user.getPassword())
                .submit();
        assertFalse(homePage.isLoginButtonsVisible());
    }

    @AfterClass
    void afterClass() {
        if (protocol != null) {
            protocol.closeSession();
        }
    }
}