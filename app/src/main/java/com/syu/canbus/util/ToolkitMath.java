package com.syu.canbus.util;

public class ToolkitMath {
    public static int clamp(int value, int min, int max) {
        if (value < min) {
            return min;
        }
        return value > max ? max : value;
    }
}
