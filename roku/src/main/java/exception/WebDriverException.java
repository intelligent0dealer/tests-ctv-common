package exception;

import webdriver.Error;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WebDriverException extends RuntimeException {

    private Error error;

    public WebDriverException(Error error) {
        this.error = error;
    }

    public WebDriverException(String message) {
        super(message);
    }

    public WebDriverException(Throwable cause) {
        super(cause);
    }

    @Override
    public String toString() {
        String className = getClass().getName();
        String message = getLocalizedMessage();
        String errorMessage = null;
        if (error != null) {
            errorMessage = error.getValue().getMessage();
        }
        String result = Stream.of(message, errorMessage)
                .filter(s -> s != null && !s.isEmpty())
                .collect(Collectors.joining(","));
        return (!result.isEmpty()) ? (className + ": " + result) : className;
    }
}
