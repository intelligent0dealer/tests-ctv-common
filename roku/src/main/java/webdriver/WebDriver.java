package webdriver;

import models.*;
import retrofit2.Call;
import retrofit2.http.*;

interface WebDriver {

    /**
     * Creates a new session.
     *
     * @param ipAddress the IP address of the device.
     * @return the result of session creation.
     */
    @POST("v1/session")
    Call<RokuResult<Session>> createSession(@Body IpAddress ipAddress);

    /**
     * Deletes the session.
     *
     * @param sessionId id of active session
     */
    @DELETE("v1/session/{sessionId}")
    Call<RokuResult<Void>> deleteSession(@Path("sessionId") String sessionId);

    /**
     * Searches for an element on the page, starting from the screen root.
     * The first located element will be returned as a WebElement JSON object.
     *
     * @param sessionId id of active session
     * @param selector  characteristics of the required element
     * @return info about element
     */
    @POST("v1/session/{sessionId}/element")
    Call<RokuResult<Element>> findElement(@Path("sessionId") String sessionId, @Body Selector selector);

    /**
     * Retrieves the element on the page that currently has focus.
     *
     * @param sessionId id of active session
     * @return info about active element
     */
    @POST("v1/session/{sessionId}/element/active")
    Call<RokuResult<Element>> getActiveElement(@Path("sessionId") String sessionId);

    /**
     * Retrieves information about the Roku media player.
     *
     * @param sessionId id of active session
     */
    @GET("v1/session/{sessionId}/player")
    Call<RokuResult<Player>> getPlayerInfo(@Path("sessionId") String sessionId);

    /**
     * Launches the specified channel.
     *
     * @param sessionId id of active session
     * @param channelId The ID of the channel to be launched.
     */
    @POST("v1/session/{sessionId}/launch")
    Call<RokuResult<Void>> openChannel(@Path("sessionId") String sessionId, @Body ChannelID channelId);

    /**
     * Simulates the press and release of the specified key.
     *
     * @param sessionId id of active session
     * @param sequence  sequence of symbols
     */
    @POST("v1/session/{sessionId}/press")
    Call<RokuResult<Void>> pressKeySequence(@Path("sessionId") String sessionId, @Body Sequence sequence);

    /**
     * Configures the amount of time that an operation can be executed before it is aborted.
     *
     * @param sessionId id of active session
     * @param timeout   information about the timeout to be applied
     */
    @POST("v1/session/{sessionId}/timeouts")
    Call<RokuResult<Void>> timeouts(@Path("sessionId") String sessionId, @Body Timeout timeout);
}