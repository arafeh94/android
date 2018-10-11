package com.arafeh.base.internal.persisters

import com.arafeh.base.App
import com.nytimes.android.external.store3.base.Persister
import io.reactivex.Maybe
import io.reactivex.Single

/**
 * Created by Arafeh on 7/15/2018.
 */

@Suppress("UNCHECKED_CAST")
class ListStringPersister : Persister<List<String>, String> {
    override fun read(key: String): Maybe<List<String>> {
        val set = App.preferences().sharedPreferences.getStringSet("key", null)
        return if (set == null) Maybe.empty() else Maybe.fromCallable { set.toList() }
    }

    override fun write(key: String, raw: List<String>): Single<Boolean> {
        App.preferences().putSync(key, raw.toSet())
        return Single.just(true)
    }

}