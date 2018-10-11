package com.arafeh.base.internal.core;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import javax.inject.Inject;
import javax.inject.Singleton;


/**
 * Created by Arafeh on 7/14/2018.
 */

@Singleton
public class AppLogger {
    @Inject
    public AppLogger() {
        PrettyFormatStrategy strategy = PrettyFormatStrategy.newBuilder().methodOffset(2).methodCount(3).showThreadInfo(true).build();
        Logger.addLogAdapter(new AndroidLogAdapter(strategy));
        Logger.addLogAdapter(new DiskLogAdapter());
    }


    /**
     * General log function that accepts all configurations as parameter
     */
    public void log(int priority, @Nullable String tag, @Nullable String message, @Nullable Throwable throwable) {
        Logger.log(priority, tag, message, throwable);
    }

    public void d(@NonNull String message, @Nullable Object... args) {
        Logger.d(message, args);
    }

    public void d(@Nullable Object object) {
        Logger.d(object);
    }

    public void e(@NonNull String message, @Nullable Object... args) {
        Logger.e(message, args);
    }

    public void e(@Nullable Throwable throwable, @NonNull String message, @Nullable Object... args) {
        Logger.e(throwable, message, args);
    }

    public void i(@NonNull String message, @Nullable Object... args) {
        Logger.i(message, args);
    }

    public void v(@NonNull String message, @Nullable Object... args) {
        Logger.v(message, args);
    }

    public void w(@NonNull String message, @Nullable Object... args) {
        Logger.w(message, args);
    }

    /**
     * Tip: Use this for exceptional situations to log
     * ie: Unexpected errors etc
     */
    public void wtf(@NonNull String message, @Nullable Object... args) {
        Logger.wtf(message, args);
    }

    /**
     * Formats the given json content and print it
     */
    public void json(@Nullable String json) {
        Logger.json(json);
    }

    /**
     * Formats the given xml content and print it
     */
    public void xml(@Nullable String xml) {
        Logger.xml(xml);
    }

}
