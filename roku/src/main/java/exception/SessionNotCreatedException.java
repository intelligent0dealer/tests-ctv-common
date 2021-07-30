package exception;

import webdriver.Error;

public class SessionNotCreatedException extends WebDriverException {

    public SessionNotCreatedException(Error error) {
        super(error);
    }

    public SessionNotCreatedException(String message) {
        super(message);
    }

    public SessionNotCreatedException(Throwable cause) {
        super(cause);
    }
}