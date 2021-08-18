package protocol;

public class Configuration {

    private String driverUrl;
    private String channelID;
    private String clientIpAddress;
    private Platform platform;
    private long timeout;
    private long delay;
    private boolean isLogEnabled;

    private Configuration() {
    }

    public String getChannelID() {
        return channelID;
    }

    public String getClientIpAddress() {
        return clientIpAddress;
    }

    public long getDelay() {
        return delay;
    }

    public String getDriverUrl() {
        return driverUrl;
    }

    public Platform getPlatform() {
        return platform;
    }

    public long getTimeout() {
        return timeout;
    }

    public boolean isLogEnabled() {
        return isLogEnabled;
    }

    public static class Builder {

        private final Configuration configuration;

        public Builder() {
            configuration = new Configuration();
        }

        public Builder channelID(String channelID) {
            configuration.channelID = channelID;
            return this;
        }

        public Builder clientIpAddress(String ipAddress) {
            configuration.clientIpAddress = ipAddress;
            return this;
        }

        public Builder delay(long delay) {
            configuration.delay = delay;
            return this;
        }

        public Builder driverUrl(String driverUrl) {
            configuration.driverUrl = driverUrl;
            return this;
        }

        public Builder log(boolean isLogEnabled) {
            configuration.isLogEnabled = isLogEnabled;
            return this;
        }

        public Builder platform(Platform platform) {
            configuration.platform = platform;
            return this;
        }

        public Builder timeout(long timeout) {
            configuration.timeout = timeout;
            return this;
        }

        public Configuration build() {
            return configuration;
        }
    }
}