package com.syu.canbus.util;

public class ToolkitMisc {
    public static boolean strEqual(String a, String b) {
        if (a == null) {
            if (b != null) {
                return false;
            }
        } else if (a.equals(b)) {
            return true;
        } else {
            return false;
        }
        return true;
    }

    public static boolean strsOk(String[] strs, int min) {
        return strs != null && strs.length >= min;
    }
}
