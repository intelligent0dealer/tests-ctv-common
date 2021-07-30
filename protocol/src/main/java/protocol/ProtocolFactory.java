package protocol;

import protocol.androidtv.AndroidTvProtocolImpl;
import protocol.roku.RokuProtocolImpl;
import protocol.tvos.TvOsProtocolImpl;

public class ProtocolFactory {

    public static IPlatformProtocol createProtocol(Configuration configuration) {
        switch (configuration.getPlatform()) {
            case ANDROID_TV:
                return new AndroidTvProtocolImpl();
            case ROKU:
                return new RokuProtocolImpl(configuration);
            case TVOS:
                return new TvOsProtocolImpl();
            default:
                throw new IllegalArgumentException("Unknown platform");
        }
    }
}