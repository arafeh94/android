package com.arafeh.base.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.arafeh.base.data.om.Report;

import java.util.List;

import io.reactivex.Maybe;

/**
 * Created by Arafeh on 7/11/2018.
 */
@Dao
public interface LocalRepository {
    @Query("select * from report")
    Maybe<List<Report>> getReports();

    @Insert
    void addReports(List<Report> reports);
}
