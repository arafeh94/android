package com.arafeh.base.internal.core;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.RequiresPermission;

import javax.inject.Inject;
import javax.inject.Singleton;

import github.nisrulz.easydeviceinfo.base.EasyAppMod;
import github.nisrulz.easydeviceinfo.base.EasyBatteryMod;
import github.nisrulz.easydeviceinfo.base.EasyBluetoothMod;
import github.nisrulz.easydeviceinfo.base.EasyConfigMod;
import github.nisrulz.easydeviceinfo.base.EasyCpuMod;
import github.nisrulz.easydeviceinfo.base.EasyDeviceMod;
import github.nisrulz.easydeviceinfo.base.EasyDisplayMod;
import github.nisrulz.easydeviceinfo.base.EasyFingerprintMod;
import github.nisrulz.easydeviceinfo.base.EasyIdMod;
import github.nisrulz.easydeviceinfo.base.EasyLocationMod;
import github.nisrulz.easydeviceinfo.base.EasyMemoryMod;
import github.nisrulz.easydeviceinfo.base.EasyNetworkMod;
import github.nisrulz.easydeviceinfo.base.EasyNfcMod;
import github.nisrulz.easydeviceinfo.base.EasySensorMod;
import github.nisrulz.easydeviceinfo.base.EasySimMod;

/**
 * Created by Arafeh on 7/14/2018.
 */

@Singleton
public class AppEasy {
    private Context context;

    @Inject
    public AppEasy(Context context) {
        this.context = context;
    }

    public EasyAppMod app() {
        return new EasyAppMod(context);
    }

    public EasyLocationMod location() {
        return new EasyLocationMod(context);
    }

    public EasyBatteryMod battery() {
        return new EasyBatteryMod(context);
    }

    public EasyBluetoothMod bluetooth() {
        return new EasyBluetoothMod(context);
    }

    public EasyNetworkMod network() {
        return new EasyNetworkMod(context);
    }

    public EasyNfcMod nfc() {
        return new EasyNfcMod(context);
    }

    public EasyDeviceMod device() {
        return new EasyDeviceMod(context);
    }

    public EasyDisplayMod display() {
        return new EasyDisplayMod(context);
    }

    public EasyCpuMod cpu() {
        return new EasyCpuMod();
    }

    @TargetApi(23)
    @RequiresPermission(Manifest.permission.USE_FINGERPRINT)
    public EasyFingerprintMod fingerprint() {
        return new EasyFingerprintMod(context);
    }

    public EasyMemoryMod memory() {
        return new EasyMemoryMod(context);
    }

    public EasyConfigMod configs() {
        return new EasyConfigMod(context);
    }

    public EasyIdMod id() {
        return new EasyIdMod(context);
    }

    public EasySensorMod sensors() {
        return new EasySensorMod(context);
    }

    public EasySimMod sim() {
        return new EasySimMod(context);
    }


}
