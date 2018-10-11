package com.arafeh.base.internal.core;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by Arafeh on 7/15/2018.
 */

public class DelayInterceptor implements Interceptor {

    public interface DelayProvider {
        /**
         * Provides delay duration in milliseconds.
         *
         * @return delay in milliseconds
         */
        long getDelay();
    }

    private final DelayProvider delayProvider;

    /**
     * Constructs fixed time {@link DelayInterceptor}.
     *
     * @param duration duration of delay
     * @param timeUnit unit of delay
     */
    public DelayInterceptor(final long duration, final TimeUnit timeUnit) {
        if (duration < 0) throw new IllegalArgumentException("duration cannot be negative value.");
        if (timeUnit == null) throw new NullPointerException("timeUnit cannot be null.");
        this.delayProvider = () -> timeUnit.toMillis(duration);
    }

    /**
     * Constructs {@link DelayInterceptor} where delay duration is not fixed. For example, using
     *
     * @param delayProvider delay duration in milliseconds provider. Negative values provided by
     *                      this provider will cause no delay.
     */
    public DelayInterceptor(final DelayProvider delayProvider) {
        if (delayProvider == null) throw new NullPointerException("delayProvider cannot be null.");
        this.delayProvider = delayProvider;
    }

    @Override
    public Response intercept(final Chain chain) throws IOException {
        final long delay = delayProvider.getDelay();
        if (delay > 0) {
            try {
                Thread.sleep(delay);
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
        }
        return chain.proceed(chain.request());
    }
}
