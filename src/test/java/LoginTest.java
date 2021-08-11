import data.User;
import models.Selector;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import protocol.IPlatformProtocol;
import protocol.roku.RokuProtocolImpl;

import java.util.Collections;
import java.util.Iterator;

import static mstv.pages.roku.RokuHomePageImpl.openHomePage;

public class LoginTest extends AbstractTest {

    private IPlatformProtocol<Selector> protocol;

    @DataProvider
    private Iterator<Object[]> provideUsers() {
        return Collections.singletonList(new Object[]{new User("log", "pass")}).iterator();
    }

    @BeforeClass
    void beforeClass() {
        protocol = new RokuProtocolImpl(getConfiguration());
    }

    @Test(dataProvider = "provideUsers")
    void test(User user) {
        openHomePage(protocol).openLoginPage()
                .typeEmail(user.getEmail())
                .typePassword(user.getPassword())
                .submit();
    }

    @AfterClass
    void afterClass() {
        if (protocol != null) {
            protocol.closeSession();
        }
    }
}