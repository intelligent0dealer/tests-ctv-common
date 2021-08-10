package exception;

import webdriver.Error;

public class TimeoutException extends WebDriverException {

    public TimeoutException(Error error) {
        super(error);
    }

    public TimeoutException(String message) {
        super(message);
    }

    public TimeoutException(Throwable cause) {
        super(cause);
    }
}
