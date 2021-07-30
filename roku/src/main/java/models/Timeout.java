package models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

    @Generated("jsonschema2pojo")
    public class Timeout {

        @SerializedName("type")
        @Expose
        private TimeoutType type;
        @SerializedName("value")
        @Expose
        private Integer value;

        public Timeout(TimeoutType type, Integer value) {
            this.type = type;
            this.value = value;
        }

        public TimeoutType getType() {
            return type;
        }

        public void setType(TimeoutType type) {
            this.type = type;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }
        public static enum TimeoutType {
            @SerializedName("implicit")
            IMPLICIT,
            @SerializedName("pressDelay")
            PRESS_DELAY
        }

    }
