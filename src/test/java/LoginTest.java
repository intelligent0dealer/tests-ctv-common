import data.User;
import models.Selector;
import mstv.pages.base.ILoginPage;
import mstv.pages.roku.RokuHomePageImpl;
import org.testng.annotations.*;
import protocol.IPlatformProtocol;
import protocol.roku.RokuProtocolImpl;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

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
    private final static String USER_EMAIL = "autotestmstv+ctvuser@gmail.com";
    private final static String ADMIN_EMAIL = "autotestmstv+ctvadmin@gmail.com";
    private final static String PASS = "1t2E3s4T";
    private final static String SHORT_PASS = "1";
    private final static String INVALID_PASS = "invalid";
    private final static String EMPTY_EMAIL = "";
    private final static String EMPTY_PASS = "";
    private IPlatformProtocol<Selector> protocol;
    private RokuHomePageImpl homePage;

    @DataProvider
    private Iterator<Object[]> providePositiveData() {
        return singletonList(new Object[]{new User(USER_EMAIL, PASS)}).iterator();
    }

    @DataProvider
    private Object[][] provideNegativeData() {
        return new Object[][]{
                new Object[]{new User(EMPTY_EMAIL, EMPTY_PASS), asList(EMAIL_SHOULD_NOT_BE_BLANK, PASSWORD_SHOULD_NOT_BE_BLANK)},
                new Object[]{new User(USER_EMAIL, INVALID_PASS), singletonList(EMAIL_AND_PASSWORD_DO_NOT_MATCH)},
                new Object[]{new User(USER_EMAIL, SHORT_PASS), singletonList(PASSWORD_IS_TOO_SHORT)},
                new Object[]{new User(USER_EMAIL, EMPTY_PASS), singletonList(PASSWORD_SHOULD_NOT_BE_BLANK)},
                new Object[]{new User(EMPTY_EMAIL, PASS), singletonList(EMAIL_SHOULD_NOT_BE_BLANK)}
        };
    }

    @BeforeClass
    void beforeClass() {
        protocol = new RokuProtocolImpl(getConfiguration());
    }

    @BeforeMethod
    void beforeMethod() {
        homePage = openHomePage(protocol);
    }

    @Test(dataProvider = "providePositiveData")
    void shouldLoginSuccessfully(User user) {
        homePage.openLoginPage()
                .typeEmail(user.getEmail())
                .typePassword(user.getPassword())
                .submit();
        assertFalse(homePage.isLoginButtonsVisible());
    }

    @Test(dataProvider = "provideNegativeData")
    void shouldRejectLogin(User user, List<String> errorMessages) {
        ILoginPage loginPage = homePage.openLoginPage();
        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            loginPage.typeEmail(user.getEmail());
        }
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            loginPage.typePassword(user.getPassword());
        }
        loginPage.submit();
        assertThat(loginPage.getErrorMessages()).containsExactlyElementsOf(errorMessages);
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
            protocol = null;
        }
        homePage = null;
    }
}