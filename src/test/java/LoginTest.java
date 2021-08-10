import data.User;
import mstv.pages.base.IHomePage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.Iterator;

import static mstv.pages.base.IHomePage.openHomePage;

public class LoginTest extends AbstractTest {
    @DataProvider
    private Iterator<Object[]> provideUsers() {
        return Collections.singletonList(new Object[]{new User("log", "pass")}).iterator();
    }

    @Test(dataProvider = "provideUsers")
    void test(User user) {
        IHomePage homePage = openHomePage(getConfiguration(), getProtocol());
        homePage.openLoginPage().typeEmail(user.getEmail()).typePassword(user.getPassword()).submit();
    }
}