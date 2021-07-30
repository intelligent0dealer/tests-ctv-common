package exception;

import webdriver.Error;

public class NoSuchElementException extends WebDriverException {

    public NoSuchElementException(Error error) {
        super(error);
    }

    public NoSuchElementException(String message) {
        super(message);
    }

    public NoSuchElementException(Throwable cause) {
        super(cause);
    }
}