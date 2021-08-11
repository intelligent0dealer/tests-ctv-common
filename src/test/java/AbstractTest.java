import org.testng.annotations.Guice;
import protocol.Configuration;

import javax.inject.Inject;

@Guice(modules = BasicModule.class)
public abstract class AbstractTest {

    @Inject
    private Configuration configuration;

    public Configuration getConfiguration() {
        return configuration;
    }
}