package mstv.pages.base;

public interface IHomePage {

    boolean isLoginButtonsVisible();

    IEpisodePage openEpisodePage();

    ILoginPage openLoginPage();

    IRegisterPage openRegisterPage();
}