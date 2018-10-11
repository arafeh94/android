package com.arafeh.base.internal.core;

import android.app.Activity;

import com.kaopiz.kprogresshud.KProgressHUD;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Arafeh on 7/12/2018.
 */
@Singleton
public class AppLoadingDialog {
    private KProgressHUD dialog;

    @Inject
    public AppLoadingDialog() {

    }

    public void show(Activity activity) {
        show(activity, null, null);
    }

    public void change(Activity activity, boolean state) {
        if (state) {
            show(activity);
        } else {
            dismiss();
        }
    }

    public void show(Activity activity, String label, String details) {
        dialog = KProgressHUD.create(activity)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(label)
                .setDetailsLabel(details)
                .setCancellable(true)
                .setAnimationSpeed(1)
                .setDimAmount(0.5f)
                .show();
    }

    public void dismiss() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

}
