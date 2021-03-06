import com.google.inject.AbstractModule;
import protocol.Configuration;
import protocol.Platform;

import static java.lang.System.getProperty;
import static java.util.Locale.ROOT;

public class BasicModule extends AbstractModule {

    private final Platform platform = Platform.valueOf(getProperty("ctv.platform").toUpperCase(ROOT));
    private final String baseUrl = getProperty("ctv.baseUrl");
    private final String clientIpAddress = getProperty("ctv.clientIpAddress");
    private final String channelID = getProperty("ctv.channelID");
    private final boolean isLogEnabled = Boolean.parseBoolean(getProperty("ctv.isLogEnabled"));
    private final int delay = Integer.parseInt(getProperty("ctv.delay"));
    private final int timeout = Integer.parseInt(getProperty("ctv.timeout"));

    @Override
    public void configure() {
        Configuration configuration = new Configuration.Builder()
                .platform(platform)
                .driverUrl(baseUrl)
                .clientIpAddress(clientIpAddress)
                .channelID(channelID)
                .log(isLogEnabled)
                .delay(delay)
                .timeout(timeout)
                .build();
        bind(Configuration.class).toInstance(configuration);
    }
}