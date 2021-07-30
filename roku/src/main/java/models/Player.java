package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

public class Player {

    @SerializedName("Error")
    @Expose
    private String error;
    @SerializedName("State")
    @Expose
    private String state;
    @SerializedName("Format")
    @Expose
    private Format format;
    @SerializedName("Buffering")
    @Expose
    private Buffering buffering;
    @SerializedName("NewStream")
    @Expose
    private NewStream newStream;
    @SerializedName("Position")
    @Expose
    private String position;
    @SerializedName("Duration")
    @Expose
    private String duration;
    @SerializedName("IsLive")
    @Expose
    private String isLive;
    @SerializedName("Runtime")
    @Expose
    private String runtime;
    @SerializedName("StreamSegment")
    @Expose
    private StreamSegment streamSegment;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    public Buffering getBuffering() {
        return buffering;
    }

    public void setBuffering(Buffering buffering) {
        this.buffering = buffering;
    }

    public NewStream getNewStream() {
        return newStream;
    }

    public void setNewStream(NewStream newStream) {
        this.newStream = newStream;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getIsLive() {
        return isLive;
    }

    public void setIsLive(String isLive) {
        this.isLive = isLive;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public StreamSegment getStreamSegment() {
        return streamSegment;
    }

    public void setStreamSegment(StreamSegment streamSegment) {
        this.streamSegment = streamSegment;
    }

    @Override
    public String toString() {
        return "PlayerInfo{" +
                "error='" + error + '\'' +
                ", state='" + state + '\'' +
                ", format=" + format +
                ", buffering=" + buffering +
                ", newStream=" + newStream +
                ", position='" + position + '\'' +
                ", duration='" + duration + '\'' +
                ", isLive='" + isLive + '\'' +
                ", runtime='" + runtime + '\'' +
                ", streamSegment=" + streamSegment +
                '}';
    }

    @Generated("jsonschema2pojo")
    public static class Buffering {

        @SerializedName("Current")
        @Expose
        private String current;
        @SerializedName("Max")
        @Expose
        private String max;
        @SerializedName("Target")
        @Expose
        private String target;

        public String getCurrent() {
            return current;
        }

        public void setCurrent(String current) {
            this.current = current;
        }

        public String getMax() {
            return max;
        }

        public void setMax(String max) {
            this.max = max;
        }

        public String getTarget() {
            return target;
        }

        public void setTarget(String target) {
            this.target = target;
        }

    }

    @Generated("jsonschema2pojo")
    class Format {

        @SerializedName("Audio")
        @Expose
        private String audio;
        @SerializedName("Captions")
        @Expose
        private String captions;
        @SerializedName("Container")
        @Expose
        private String container;
        @SerializedName("Drm")
        @Expose
        private String drm;
        @SerializedName("Video")
        @Expose
        private String video;
        @SerializedName("VideoRes")
        @Expose
        private String videoRes;

        public String getAudio() {
            return audio;
        }

        public void setAudio(String audio) {
            this.audio = audio;
        }

        public String getCaptions() {
            return captions;
        }

        public void setCaptions(String captions) {
            this.captions = captions;
        }

        public String getContainer() {
            return container;
        }

        public void setContainer(String container) {
            this.container = container;
        }

        public String getDrm() {
            return drm;
        }

        public void setDrm(String drm) {
            this.drm = drm;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public String getVideoRes() {
            return videoRes;
        }

        public void setVideoRes(String videoRes) {
            this.videoRes = videoRes;
        }

    }

    @Generated("jsonschema2pojo")
    class NewStream {

        @SerializedName("Speed")
        @Expose
        private String speed;

        public String getSpeed() {
            return speed;
        }

        public void setSpeed(String speed) {
            this.speed = speed;
        }

    }

    @Generated("jsonschema2pojo")
    class StreamSegment {

        @SerializedName("Bitrate")
        @Expose
        private String bitrate;
        @SerializedName("MediaSequence")
        @Expose
        private String mediaSequence;
        @SerializedName("SegmentType")
        @Expose
        private String segmentType;
        @SerializedName("Time")
        @Expose
        private String time;

        public String getBitrate() {
            return bitrate;
        }

        public void setBitrate(String bitrate) {
            this.bitrate = bitrate;
        }

        public String getMediaSequence() {
            return mediaSequence;
        }

        public void setMediaSequence(String mediaSequence) {
            this.mediaSequence = mediaSequence;
        }

        public String getSegmentType() {
            return segmentType;
        }

        public void setSegmentType(String segmentType) {
            this.segmentType = segmentType;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

    }

}

