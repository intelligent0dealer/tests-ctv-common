import mstv.pages.base.IRegisterPage;
import org.testng.annotations.Test;

public class RegisterTest extends AbstractTest {

    @Test
    void shouldRegisterSuccessfully() {
        IRegisterPage registerPage = homePage.openRegisterPage();
        registerPage.typeEmail("");
        registerPage.typePassword("");
    }
}