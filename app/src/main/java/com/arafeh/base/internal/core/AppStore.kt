package com.arafeh.base.internal.core;

import android.content.Context
import android.util.Log
import com.arafeh.base.data.local.LocalRepository
import com.arafeh.base.data.om.Report
import com.arafeh.base.data.remote.RemoteRepository
import com.arafeh.base.internal.persisters.ListStringPersister
import com.nytimes.android.external.store3.base.Persister
import com.nytimes.android.external.store3.base.impl.Store;
import com.nytimes.android.external.store3.base.impl.StoreBuilder
import io.reactivex.Maybe
import io.reactivex.Single

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Arafeh on 7/12/2018.
 */
@Singleton
class AppStore @Inject constructor(var context: Context, var api: RemoteRepository, var repo: LocalRepository) {
    fun reports(): Store<List<Report>, Int> {
        return StoreBuilder.key<Int, List<Report>>()
                .fetcher({ id -> api.reports })
                .persister(ReportsPersister())
                .open()
    }

    fun lastMapSearchSuggestions(list: List<String>? = null): Store<List<String>, String> {
        return StoreBuilder.key<String, List<String>>()
                .fetcher({ Single.fromCallable { list ?: listOf() } })
                .persister(ListStringPersister())
                .open()
    }

    inner class ReportsPersister : Persister<List<Report>, Int> {
        override fun read(key: Int): Maybe<List<Report>> {
            Log.e("tag", "reading from database")
            return repo.reports;
        }

        override fun write(key: Int, raw: List<Report>): Single<Boolean> {
            Log.e("tag", "writing to database")
            repo.addReports(raw)
            return Single.just(true)
        }

    }
}
