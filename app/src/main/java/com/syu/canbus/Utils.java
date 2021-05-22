package com.syu.canbus;

import android.util.Log;

import com.syu.canbus.module.canbus.DataCanbus;
import com.syu.canbus.module.canbus.FinalCanbus;

import java.text.DecimalFormatSymbols;

public class Utils {

    public static char decimalSeparator() {
        return DecimalFormatSymbols.getInstance().getDecimalSeparator();
    }

    public static void dumpOnNotify(String tag, int updateCode, int[] ints, float[] flts, String[] strs) {
        Log.d(tag, "dumpOnNotify: updateCode: " + updateCode);
        for (int i = 0; ints != null && i < ints.length; i++) {
            Log.d(tag, "dumpOnNotify: ints[i]" + ints[i]);
        }
        for (int i = 0; flts != null && i < flts.length; i++) {
            Log.d(tag, "dumpOnNotify: flts[i]" + flts[i]);
        }
        for (int i = 0; strs != null && i < strs.length; i++) {
            Log.d(tag, "dumpOnNotify: strs[i]" + strs[i]);
        }
    }
}
