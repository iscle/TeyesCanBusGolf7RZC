package com.syu.canbus.module.sound;

public class FFuncSound {
    public static void showVol() {
        DataSound.PROXY.cmd(0, -6);
    }
}
