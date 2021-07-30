package models;

import com.google.gson.annotations.SerializedName;

public class IpAddress {

    @SerializedName("ip")
    private String ip;

    public IpAddress(String ip) {
        this.ip = ip;
    }
}
