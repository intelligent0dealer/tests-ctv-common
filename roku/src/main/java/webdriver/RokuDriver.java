package webdriver;

import exception.NoSuchElementException;
import exception.SessionNotCreatedException;
import exception.WebDriverException;
import models.*;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;
import static okhttp3.logging.HttpLoggingInterceptor.Level.NONE;
import static webdriver.Utils.parseError;

public class RokuDriver {

    private final Retrofit retrofit;
    private final WebDriver driver;
    private final String sessionId;
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private final long delay;

    public RokuDriver(String driverURL, String clientIpAddress, long delay, boolean isLogEnabled) {
        retrofit = buildRetrofit(driverURL, isLogEnabled);
        driver = retrofit.create(WebDriver.class);
        this.delay = delay;
        try {
            if (clientIpAddress == null || clientIpAddress.isEmpty()) {
                throw new IllegalArgumentException("The client's IP address must not be null or empty.");
            }
            RokuResult<Session> result = execute(driver.createSession(new IpAddress(clientIpAddress)),
                    String.format("Unable to create new session using next url: %s", driverURL));
            if (result != null && result.getSessionId() != null && !result.getSessionId().isEmpty()) {
                sessionId = result.getSessionId();
            } else {
                throw new SessionNotCreatedException("Session ID is null or empty");
            }
        } catch (WebDriverException exception) {
            throw new SessionNotCreatedException(exception);
        }
    }

    public void closeSession() {
        execute(driver.deleteSession(sessionId), String.format("Unable to close session with ID: %s", sessionId));
    }

    public Element findElement(Selector selector) {
        try {
            return execute(
                    driver.findElement(sessionId, selector),
                    String.format("Unable to locate element %s", selector)
            ).getValue();
        } catch (WebDriverException exception) {
            throw new NoSuchElementException(exception);
        }
    }

    public Element getActiveElement() {
        try {
            return execute(driver.getActiveElement(sessionId), "Unable to locate active element").getValue();
        } catch (WebDriverException exception) {
            throw new NoSuchElementException(exception);
        }
    }

    public Player getPlayerInfo() {
        return execute(driver.getPlayerInfo(sessionId), "Unable to get player information").getValue();
    }

    public void openChannel(String channelID) {
        execute(driver.openChannel(sessionId, new ChannelID(channelID)),
                String.format("Unable to open channel with ID %s", channelID));
    }

    public void pressKeySequence(Sequence sequence) {
        execute(driver.pressKeySequence(sessionId, sequence), "Unable to simulate keypress event");
    }

    public void timeout(Timeout timeout) {
        execute(driver.timeouts(sessionId, timeout), "Unable to configure timeout");
    }

    private Retrofit buildRetrofit(String driverURL, boolean isLogEnabled) {
        if (driverURL == null || driverURL.isEmpty()) {
            throw new IllegalArgumentException("The driver URL must not be null or empty");
        }
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        HttpLoggingInterceptor.Level level = isLogEnabled ? BODY : NONE;
        interceptor.setLevel(level);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(driverURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private <R> RokuResult<R> execute(Call<RokuResult<R>> call, String defaultErrorMessage) {
        try {
            ScheduledFuture<Response<RokuResult<R>>> future = executorService.schedule(call::execute, delay, MILLISECONDS);
            Response<RokuResult<R>> response = future.get();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                if (response.errorBody() != null) {
                    Error error = parseError(retrofit, response.errorBody());
                    throw new WebDriverException(error);
                } else {
                    throw new WebDriverException(defaultErrorMessage);
                }
            }
        } catch (IOException | ExecutionException | InterruptedException exception) {
            throw new WebDriverException(exception);
        }
    }
}