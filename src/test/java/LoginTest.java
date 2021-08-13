import data.User;
import models.Selector;
import mstv.pages.base.ILoginPage;
import mstv.pages.roku.RokuHomePageImpl;
import org.testng.annotations.*;
import protocol.IPlatformProtocol;
import protocol.roku.RokuProtocolImpl;

import java.lang.reflect.Method;
import java.util.Iterator;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static mstv.pages.roku.RokuHomePageImpl.openHomePage;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertFalse;

public class LoginTest extends AbstractTest {

    private final static String EMAIL_SHOULD_NOT_BE_BLANK = "email: this value should not be blank.";
    private final static String PASSWORD_SHOULD_NOT_BE_BLANK = "password: this value should not be blank.";
    private final static String EMAIL_AND_PASSWORD_DO_NOT_MATCH = "Email and password don't match. Please try again.";
    private final static String PASSWORD_IS_TOO_SHORT = "password: this value is too short. it should have 2 characters or more.";
    private IPlatformProtocol<Selector> protocol;
    private RokuHomePageImpl homePage;

    @DataProvider
    private Iterator<Object[]> provideUsers() {
        return singletonList(new Object[]{new User("efim.filippov@motorsport.com", "dfg43jh7")}).iterator();
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

    @Test
    void shouldNotLoginSuccessfully() {
        ILoginPage loginPage = openHomePage(protocol).openLoginPage();
        loginPage.submit();
        assertThat(loginPage.getErrorMessages())
                .containsExactlyElementsOf(asList(EMAIL_SHOULD_NOT_BE_BLANK, PASSWORD_SHOULD_NOT_BE_BLANK));
    }

    @AfterMethod
    void afterMethod(Method method) {
        if (method.getName().equals("shouldLoginSuccessfully")) {
            homePage.openSettingsPage().signOut();
        }
    }

    @AfterClass
    void afterClass() {
        if (protocol != null) {
            protocol.closeSession();
        }
    }
}