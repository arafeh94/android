package com.arafeh.base.internal.core;

import javax.inject.Inject;

/**
 * Created by Arafeh on 7/12/2018.
 */

public class AppUser {
    private Object identity;
    private AppPreferences preferences;

    @Inject
    AppUser(AppPreferences preferences, Class<?> userClass) {
        this.preferences = preferences;
        this.identity = preferences.getUser(userClass);
    }

    @SuppressWarnings("unchecked")
    public <T> T getIdentity() {
        return (T) identity;
    }

    public boolean isGuest() {
        return identity == null;
    }

    public void logout() {
        identity = null;
        preferences.setUser(null);
    }

    public void login(Object object) {
        identity = object;
        preferences.setUser(object);
    }

}
