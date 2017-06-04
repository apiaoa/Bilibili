package com.github.apiaoa.bilibili.framework.utils;

/**
 * Created by a176 on 2017/2/7.
 */

public class ConvertUtils {
    public static int string2int(String s) {
        try {
            return Integer.valueOf(s) == null ? 0 : Integer.valueOf(s);
        } catch (Exception e) {
            return 0;
        }

    }

    public static double string2double(String s) {
        try {
            return Double.valueOf(s) == null ? 0 : Double.valueOf(s);
        } catch (Exception e) {
            return 0;
        }
    }

    public static float string2float(String s) {
        try {
            return Float.valueOf(s) == null ? 0 : Float.valueOf(s);
        } catch (Exception e) {
            return 0;
        }

    }
}
