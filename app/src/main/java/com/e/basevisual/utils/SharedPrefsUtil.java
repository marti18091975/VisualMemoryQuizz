package com.e.basevisual.utils;

import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SharedPrefsUtil {
    private SharedPreferences mSharedPreferences;
    public static String PREF_KEY = "dagger_key";

    @Inject
    public SharedPrefsUtil(SharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;
    }

    public void put(String key, String value) {
        mSharedPreferences.edit().putString(key, value).apply();
    }

    public String get(String key, String defaultValue) {
        return mSharedPreferences.getString(key, defaultValue);
    }
}

