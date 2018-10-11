package com.arafeh.base.data.om

import android.annotation.SuppressLint
import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Arafeh on 7/14/2018.
 */
@Entity
@Parcelize
@SuppressLint("ParcelCreator")
data class Report(
        @PrimaryKey var id: Int,
        @ColumnInfo var lat: Double,
        @ColumnInfo var lng: Double,
        @ColumnInfo var acc: Float,
        @ColumnInfo var place: String,
        @ColumnInfo var type: Int
) : Parcelable