package models;

import com.google.gson.annotations.SerializedName;

public class ChannelID {
    public ChannelID(String channelId) {
        this.channelId = channelId;
    }

    @SerializedName("channelId")
    private String channelId;
}
