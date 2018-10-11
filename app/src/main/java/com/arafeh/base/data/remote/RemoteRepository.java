package com.arafeh.base.data.remote;

import com.arafeh.base.data.om.Report;
import com.arafeh.base.data.om.User;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Arafeh on 7/11/2018.
 */

public interface RemoteRepository {
    @FormUrlEncoded
    @POST("register.html")
    Single<User> register(@Field("uid") String uid);

    @FormUrlEncoded
    @POST("register.html")
    Single<User> validate();

    @POST("showReportCreator.html")
    Single<Boolean> report(@Body Report report);

    @GET("getReports.html")
    Single<List<Report>> getReports();
}
