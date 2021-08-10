import mstv.pages.base.IHomePage;
import org.testng.annotations.Test;

import static mstv.pages.base.IHomePage.openHomePage;

public class LoginTest extends AbstractTest {

    @Test
    void test() {
        IHomePage homePage = openHomePage(getConfiguration(), getProtocol());
        homePage.openLoginPage().typeEmail("login").typePassword("password").submit();
    }
}