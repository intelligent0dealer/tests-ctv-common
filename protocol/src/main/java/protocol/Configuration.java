package protocol;

public class Configuration {

    private String clientIpAddress;
    private String driverUrl;
    private Platform platform;
    private boolean isLogEnabled;

    private Configuration() {
    }

    public String getClientIpAddress() {
        return clientIpAddress;
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