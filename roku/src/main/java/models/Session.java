package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Session {

    @SerializedName("ip")
    @Expose
    private String ip;
    @SerializedName("timeout")
    @Expose
    private Integer timeout;
    @SerializedName("pressDelay")
    @Expose
    private Integer pressDelay;
    @SerializedName("vendorName")
    @Expose
    private String vendorName;
    @SerializedName("modelName")
    @Expose
    private String modelName;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("country")
    @Expose
    private String country;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Integer getPressDelay() {
        return pressDelay;
    }

    public void setPressDelay(Integer pressDelay) {
        this.pressDelay = pressDelay;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "models.RokuSessionInfo{" +
                "ip='" + ip + '\'' +
                ", timeout=" + timeout +
                ", pressDelay=" + pressDelay +
                ", vendorName='" + vendorName + '\'' +
                ", modelName='" + modelName + '\'' +
                ", language='" + language + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
