package mstv.pages.base;

import java.util.List;

public interface IBaseLoginPage {

    ILoginPage typeEmail(String email);

    ILoginPage typePassword(String password);

    void submit();

    List<String> getErrorMessages();
}
