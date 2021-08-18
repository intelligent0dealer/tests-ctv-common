package mstv.pages.base;

public interface IRegisterPage extends IBaseLoginPage {

    IEmailConfirmationPage submit();

    void toggleNewsletterNotification(boolean isEnabled);
}