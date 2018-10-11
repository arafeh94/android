package com.arafeh.base.internal.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

/**
 * Created by Arafeh on 7/17/2018.
 */

@SuppressWarnings("ALL")
public final class ObserverAdapterFactory extends CallAdapter.Factory {
    private final Scheduler scheduler;

    private ObserverAdapterFactory(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public static ObserverAdapterFactory create(Scheduler scheduler){
        return new ObserverAdapterFactory(scheduler);
    }

    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        if (getRawType(returnType) != Observable.class) {
            return null;
        }

        final CallAdapter<Object, Observable<?>> delegate = (CallAdapter<Object, Observable<?>>) retrofit.nextCallAdapter(this, returnType, annotations);

        return new CallAdapter<Object, Object>() {
            @Override
            public Object adapt(Call<Object> call) {
                Observable<?> o = delegate.adapt(call);
                return o.observeOn(scheduler);
            }

            @Override
            public Type responseType() {
                return delegate.responseType();
            }
        };
    }
}