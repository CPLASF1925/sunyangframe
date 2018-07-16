package com.bril.base.utils;

import android.content.Context;
import android.widget.Toast;

/**
 *
 */

public class ToastUtils {
    public ToastUtils() {
    }

    public static void shortShow(Context context, int resId) {
        showToast(context, resId, 0);
    }

    public static void longShow(Context context, int resId) {
        showToast(context, resId, 1);
    }

    public static void shortShow(Context context, String string) {
        showToast(context, string, 0);
    }

    public static void longShow(Context context, String string) {
        showToast(context, string, 1);
    }

    private static void showToast(Context context, String str, int showTime) {
        ToastUtils.MyToast.getToast(context, str, showTime).show();
    }

    private static void showToast(Context context, int resId, int showTime) {
        ToastUtils.MyToast.getToast(context, context.getString(resId), showTime).show();
    }

    static class MyToast {
        private static Context context = null;
        private static Toast toast = null;

        MyToast() {
        }

        public static Toast getToast(Context context, String words, int showTime) {
            if(ToastUtils.MyToast.context == context) {
                toast.setText(words);
                toast.setDuration(showTime);
            } else {
                ToastUtils.MyToast.context = context;
                toast = Toast.makeText(context, words, showTime);
            }

            return toast;
        }
    }
}
