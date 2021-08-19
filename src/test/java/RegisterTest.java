import data.User;
import mstv.pages.base.IRegisterPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class RegisterTest extends AbstractTest {
    public static final String TEMP_PASS = "123456";
    private final static String USER_EMAIL = "autotestmstv+ctvuser@gmail.com";
    private final static String EMAIL_SHOULD_NOT_BE_BLANK = "email: this value should not be blank.";
    private final static String EMAIL_NOT_VALID = "email: this value is not valid.";
    private final static String EMAIL_EXISTS = "email: this email already exists";
    private final static String SHORT_PASSWORD_ERROR = "password: password must be at least 4 characters long.";
    private final static String SHORT_PASS = "1";
    private final static String TEMP_USER_EMAIL = "autotestmstv+ctvtemp@gmail.com";
    private final static String EMPTY_EMAIL = "";
    private final static String INVALID_USER_EMAIL = "invalid";

    @DataProvider
    private Object[][] provideNegativeData() {
        return new Object[][]{
                new Object[]{new User(EMPTY_EMAIL, TEMP_PASS), singletonList(EMAIL_SHOULD_NOT_BE_BLANK)},
                new Object[]{new User(INVALID_USER_EMAIL, SHORT_PASS), asList(EMAIL_NOT_VALID, SHORT_PASSWORD_ERROR)},
                new Object[]{new User(USER_EMAIL, TEMP_PASS), singletonList(EMAIL_EXISTS)}
        };
    }

    @DataProvider
    private Iterator<Object[]> provideNewUserData() {
        return singletonList(new Object[]{new User(TEMP_USER_EMAIL, TEMP_PASS)}).iterator();
    }

    @Test(dataProvider = "provideNewUserData")
    void shouldRegisterSuccessfully(User user) {
        IRegisterPage registerPage = homePage.openRegisterPage();
        registerPage.typeEmail(user.getEmail());
        registerPage.typePassword(user.getPassword());
        registerPage.submit().confirmEmail(user.getEmail()).chooseFreePlan();
        assertThat(homePage.isLoginButtonsVisible()).isFalse();
    }

    @Test(dataProvider = "provideNegativeData")
    void shouldRejectRegister(User user, List<String> errorMessages) {
        IRegisterPage registerPage = homePage.openRegisterPage();
        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            registerPage.typeEmail(user.getEmail());
        }
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            registerPage.typePassword(user.getPassword());
        }
        registerPage.submit();
        assertThat(registerPage.getErrorMessages()).containsExactlyElementsOf(errorMessages);
    }
}