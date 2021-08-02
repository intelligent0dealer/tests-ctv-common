import com.google.inject.AbstractModule;
import protocol.Configuration;
import protocol.IPlatformProtocol;
import protocol.Platform;

import static protocol.ProtocolFactory.createProtocol;

public class BasicModule extends AbstractModule {

    @Override
    public void configure() {
        Configuration configuration = new Configuration.Builder()
                .platform(Platform.ROKU)
                .driverUrl("http://localhost:9000")
                .clientIpAddress("192.168.0.4")
                .build();
        bind(Configuration.class).toInstance(configuration);
        bind(IPlatformProtocol.class).toInstance(createProtocol(configuration));
    }
}