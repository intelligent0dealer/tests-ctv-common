import data.User;
import mstv.pages.base.IRegisterPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class RegisterTest extends AbstractTest {

    public static final String TEMP_PASS = "123456";
    private static final String TEMP_USER_EMAIL = "autotestmstv+ctvtemp@gmail.com";

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
}