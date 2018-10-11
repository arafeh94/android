package com.arafeh.base.internal.core;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import javax.inject.Inject;

/**
 * Created by Arafeh on 7/12/2018.
 */

public class AppPreferences {
    private final SharedPreferences sharedPreferences;
    private final Gson gson;

    @Inject
    public AppPreferences(SharedPreferences sharedPreferences, Gson gson) {
        this.sharedPreferences = sharedPreferences;
        this.gson = gson;
    }

    public boolean contains(String key) {
        return sharedPreferences.contains(key);
    }

    public void setUser(Object user) {
        if (user == null) {
            sharedPreferences.edit().remove("app_user").apply();
        } else {
            sharedPreferences.edit().putString("app_user", gson.toJson(user)).apply();
        }
    }

    public Object getUser(Class<?> userClass) {
        String pref = sharedPreferences.getString("app_user", null);
        if (pref == null) return null;

        return gson.fromJson(pref, userClass);
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    @SuppressLint("ApplySharedPref")
    public void putSync(String key, Object object) {
        sharedPreferences.edit().putString(key, gson.toJson(object)).commit();
    }

    public void put(String key, Object object) {
        sharedPreferences.edit().putString(key, gson.toJson(object)).apply();
    }

    public <T> T get(String key, Class<T> tClass, T def) {
        String asString = sharedPreferences.getString(key, null);
        if (asString == null) return def;
        return gson.fromJson(asString, tClass);
    }

    public <T> T get(String key, Class<T> tClass) {
        return get(key, tClass, null);
    }

    public int count(String tag) {
        int res = sharedPreferences.getInt(tag, 0);
        res += 1;
        sharedPreferences.edit().putInt(tag, res).apply();
        return res;
    }

}
