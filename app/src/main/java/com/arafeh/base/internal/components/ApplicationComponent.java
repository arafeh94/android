package com.arafeh.base.internal.components;

import android.content.Context;
import android.widget.Toast;

import com.arafeh.base.data.local.LocalRepository;
import com.arafeh.base.data.remote.RemoteRepository;
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

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Component;
import io.nlopez.smartlocation.SmartLocation;

/**
 * Created by Arafeh on 7/11/2018.
 */
@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    Context context();

    LocalRepository repository();

    RemoteRepository api();

    AppPreferences preferences();

    AppUser user();

    Navigator navigator();

    AppLoadingDialog loadingDialog();

    AppStore store();

    Glide glide();

    AppLogger logger();

    AppEasy easy();

    EventBus events();

    SmartLocation location();

    Toast toast();

    RunCounter.CounterInfo runCounter();
}
