package models;

import com.google.gson.annotations.SerializedName;

public class ChannelID {
    @SerializedName("channelId")
    private String channelId;

    public ChannelID(String channelId) {
        this.channelId = channelId;
    }
}
