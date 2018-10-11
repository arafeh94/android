package com.arafeh.base.core.exceptions;

/**
 * Created by Arafeh on 7/18/2018.
 */

public class UnknownDeviceException extends RuntimeException {
    public UnknownDeviceException() {
        super("unknown device id");
    }
}
