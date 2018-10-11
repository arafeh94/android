package com.arafeh.base;


import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

import com.arafeh.base.data.local.LocalRepository;
import com.arafeh.base.data.remote.RemoteRepository;
import com.arafeh.base.internal.components.ApplicationComponent;
import com.arafeh.base.internal.components.DaggerApplicationComponent;
import com.arafeh.base.internal.core.AppEasy;
import com.arafeh.base.internal.core.AppLoadingDialog;
import com.arafeh.base.internal.core.AppLogger;
import com.arafeh.base.internal.core.AppPreferences;
import com.arafeh.base.internal.core.AppStore;
import com.arafeh.base.internal.core.AppUser;
import com.arafeh.base.internal.core.Navigator;
import com.arafeh.base.internal.core.RunCounter;
import com.arafeh.base.internal.modules.ApplicationModule;
import com.bumptech.glide.Glide;

import net.danlew.android.joda.JodaTimeAndroid;

import org.greenrobot.eventbus.EventBus;

import io.nlopez.smartlocation.SmartLocation;

/**
 * Created by Arafeh on 7/11/2018.
 */

public class App extends android.app.Application {
    private static App instance;
    private ApplicationComponent applicationComponent;

    public static LocalRepository repository() {
        return instance.applicationComponent.repository();
    }

    public static RemoteRepository remote() {
        return instance.applicationComponent.api();
    }

    public static Context context() {
        return instance.applicationComponent.context();
    }

    public static AppUser user() {
        return instance.applicationComponent.user();
    }

    public static Navigator navigator() {
        return instance.applicationComponent.navigator();
    }

    public static AppLoadingDialog loadingDialog() {
        return instance.applicationComponent.loadingDialog();
    }

    public static AppPreferences preferences() {
        return instance.applicationComponent.preferences();
    }

    public static AppStore store() {
        return instance.applicationComponent.store();
    }

    public static SmartLocation location() {
        return instance.applicationComponent.location();
    }

    public static Glide glide() {
        return instance.applicationComponent.glide();
    }

    public static AppLogger logger() {
        return instance.applicationComponent.logger();
    }

    public static AppEasy easy() {
        return instance.applicationComponent.easy();
    }

    public static EventBus events() {
        return instance.applicationComponent.events();
    }

    public static void log(String msg) {
        App.logger().e(msg);
    }

    public static RunCounter.CounterInfo runCounter() {
        return App.instance.applicationComponent.runCounter();
    }

    public static void toast(String msg) {
        Toast toast = instance.applicationComponent.toast();
        toast.setText(msg);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void toast(@StringRes int resId) {
        toast(instance.getResources().getString(resId));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        App.instance = this;
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
        JodaTimeAndroid.init(getApplicationContext());
    }

}
