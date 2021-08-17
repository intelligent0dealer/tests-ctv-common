package mstv.pages.base;

import java.util.List;

public interface IBaseLoginPage {

    void typeEmail(String email);

    void typePassword(String password);

    void submit();

    List<String> getErrorMessages();
}
