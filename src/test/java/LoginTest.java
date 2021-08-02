import mstv.pages.base.IHomePage;
import org.testng.annotations.Test;

import static mstv.pages.base.IHomePage.createHomePage;

public class LoginTest extends AbstractTest {

    @Test
    void test() {
        IHomePage homePage = createHomePage(getConfiguration(), getProtocol());
        homePage.openLoginPage();
    }
}