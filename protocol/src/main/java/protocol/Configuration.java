package protocol;

public class Configuration {

    private static final long DEFAULT_DELAY = 1000L;

    private String clientIpAddress;
    private long delay = DEFAULT_DELAY;
    private String driverUrl;
    private Platform platform;
    private boolean isLogEnabled;

    private Configuration() {
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

    public boolean isLogEnabled() {
        return isLogEnabled;
    }

    public static class Builder {

        private final Configuration configuration;

        public Builder() {
            configuration = new Configuration();
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

        public Configuration build() {
            return configuration;
        }
    }
}