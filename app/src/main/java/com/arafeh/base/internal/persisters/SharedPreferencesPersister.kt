package com.arafeh.base.internal.persisters

import com.arafeh.base.App
import com.nytimes.android.external.store3.base.Persister
import io.reactivex.Maybe
import io.reactivex.Single

/**
 * Created by Arafeh on 7/15/2018.
 */

@Suppress("UNCHECKED_CAST")
class SharedPreferencesPersister<T>(val clazz: Class<T>) : Persister<T, String> {
    override fun read(key: String): Maybe<T> {
        return if (App.preferences().contains(key)) Maybe.fromCallable { App.preferences().get(key, clazz) }
        else Maybe.empty()
    }

    override fun write(key: String, raw: T): Single<Boolean> {
        return Single.fromCallable { App.preferences().putSync(key, raw); true }
    }

}
