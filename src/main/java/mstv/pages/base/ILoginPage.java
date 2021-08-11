package mstv.pages.base;

public interface ILoginPage {

    ILoginPage typeEmail(String email);

    ILoginPage typePassword(String password);

    void submit();
}