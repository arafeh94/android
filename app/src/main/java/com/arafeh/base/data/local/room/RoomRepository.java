package com.arafeh.base.data.local.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.arafeh.base.data.local.LocalRepository;
import com.arafeh.base.data.om.Report;

/**
 * Created by Arafeh on 7/11/2018.
 */

@Database(entities = {Report.class}, version = 1)
public abstract class RoomRepository extends RoomDatabase {
    public abstract LocalRepository repository();
}
