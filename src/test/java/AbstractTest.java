import org.testng.annotations.AfterSuite;
import org.testng.annotations.Guice;
import protocol.Configuration;
import protocol.IPlatformProtocol;

import javax.inject.Inject;

@Guice(modules = BasicModule.class)
public abstract class AbstractTest {

    @Inject
    private Configuration configuration;
    @Inject
    private IPlatformProtocol protocol;

    public Configuration getConfiguration() {
        return configuration;
    }

    public IPlatformProtocol getProtocol() {
        return protocol;
    }

    @AfterSuite
    void afterSuite() {
        protocol.closeSession();
    }
}