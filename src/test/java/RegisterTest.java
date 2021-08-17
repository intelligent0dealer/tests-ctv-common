import org.testng.annotations.Test;

public class RegisterTest extends AbstractTest {

    @Test
    void shouldRegisterSuccessfully() {
        homePage.openRegisterPage().toggleNewsletterNotification(true);
    }
}