package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class RokuResult<T> {

    @SerializedName("sessionId")
    @Expose
    private String sessionId;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("value")
    @Expose
    private T value;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "models.RokuResult{" +
                "sessionId='" + sessionId + '\'' +
                ", status=" + status +
                ", value=" + value +
                '}';
    }
}
