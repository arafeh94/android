package com.arafeh.base.internal.modules;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.arafeh.base.App;
import com.arafeh.base.BuildConfig;
import com.arafeh.base.R;
import com.arafeh.base.data.local.LocalRepository;
import com.arafeh.base.data.local.room.RoomRepository;
import com.arafeh.base.data.om.User;
import com.arafeh.base.data.remote.RemoteRepository;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.arafeh.base.internal.core.DelayInterceptor;
import com.arafeh.base.internal.core.ObserverAdapterFactory;
import com.arafeh.base.internal.core.RunCounter;


import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.nlopez.smartlocation.SmartLocation;
import io.reactivex.android.schedulers.AndroidSchedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Arafeh on 7/11/2018.
 */

@Module
public class ApplicationModule {
    private final App app;

    public ApplicationModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return app.getApplicationContext();
    }

    @Provides
    @Singleton
    LocalRepository provideRepository(Context context) {
        return Room.databaseBuilder(context, RoomRepository.class, "we-sports").build().repository();
    }

    @Provides
    @Singleton
    RemoteRepository provideApi() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
            clientBuilder.addInterceptor(logging);
            clientBuilder.addInterceptor(new DelayInterceptor(3, TimeUnit.SECONDS));
        }
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.END_POINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(ObserverAdapterFactory.create(AndroidSchedulers.mainThread()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .client(clientBuilder.build())
                .build().create(RemoteRepository.class);
    }


    @Provides
    @Singleton
    SharedPreferences providePreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new Gson();
    }

    @Provides
    @Singleton
    Class<?> provideUserClass() {
        return User.class;
    }

    @Provides
    @Singleton
    EventBus provideEvents() {
        return EventBus.getDefault();
    }

    @Provides
    @Singleton
    Glide provideGlide(Context context) {
        return Glide.get(context);
    }

    @Provides
    @Singleton
    SmartLocation provideLocation(Context context) {
        return SmartLocation.with(context);
    }

    @Provides
    @Singleton
    Toast provideToaster(Context context) {
        Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_LONG);
        View view = LayoutInflater.from(context).inflate(R.layout.toast, null);
        view.findViewById(R.id.container).getBackground().setAlpha(0xcc);
        toast.setView(view);
        return toast;
    }

    @Provides
    @Singleton
    RunCounter.CounterInfo provideRunCounter(RunCounter runCounter) {
        return runCounter.getCounter();
    }
}
