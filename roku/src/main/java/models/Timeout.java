package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Timeout {

    @SerializedName("type")
    @Expose
    private TimeoutType type;
    @SerializedName("value")
    @Expose
    private Long value;

    public Timeout(TimeoutType type, Long value) {
        this.type = type;
        this.value = value;
    }

    public TimeoutType getType() {
        return type;
    }

    public void setType(TimeoutType type) {
        this.type = type;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public static enum TimeoutType {
        @SerializedName("implicit")
        IMPLICIT,
        @SerializedName("pressDelay")
        PRESS_DELAY
    }
}