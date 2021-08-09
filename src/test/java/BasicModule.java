import com.google.inject.AbstractModule;
import protocol.Configuration;
import protocol.IPlatformProtocol;
import protocol.Platform;

import static java.lang.System.getProperty;
import static java.util.Locale.ROOT;
import static protocol.ProtocolFactory.createProtocol;

public class BasicModule extends AbstractModule {

    private final Platform platform = Platform.valueOf(getProperty("ctv.platform").toUpperCase(ROOT));
    private final String baseUrl = getProperty("ctv.baseUrl");
    private final String clientIpAddress = getProperty("ctv.clientIpAddress");
    private final boolean isLogEnabled = Boolean.parseBoolean(getProperty("ctv.isLogEnabled"));
    private final int delay = Integer.parseInt(getProperty("ctv.delay"));

    @Override
    public void configure() {
        Configuration configuration = new Configuration.Builder()
                .platform(platform)
                .driverUrl(baseUrl)
                .clientIpAddress(clientIpAddress)
                .log(isLogEnabled)
                .delay(delay)
                .build();
        bind(Configuration.class).toInstance(configuration);
        bind(IPlatformProtocol.class).toInstance(createProtocol(configuration));
    }
}