package webdriver;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Error {

    private int status;
    private String sessionId;
    private Value value;

    public String getSessionId() {
        return sessionId;
    }

    public int getStatus() {
        return status;
    }

    public Value getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Error{" +
                "status=" + status +
                ", sessionId='" + sessionId + '\'' +
                ", value=" + value +
                '}';
    }

    public static class Value {

        @Expose
        @SerializedName("message")
        private String message;

        public String getMessage() {
            return message;
        }

        @Override
        public String toString() {
            return "Value{" +
                    "message='" + message + '\'' +
                    '}';
        }
    }
}
