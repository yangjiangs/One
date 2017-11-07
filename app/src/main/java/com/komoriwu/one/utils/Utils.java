package com.komoriwu.one.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.ImageView;

import com.komoriwu.one.R;
import com.komoriwu.one.application.MyApplication;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by KomoriWu
 * on 2017/9/15.
 */

public class Utils {
    public static boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) MyApplication.getInstance().
                getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null;
    }

    public static DisplayImageOptions getImageOptions(int defaultIconId, int cornerRadiusPixels) {
        return new DisplayImageOptions.Builder()
                .displayer(new RoundedBitmapDisplayer(cornerRadiusPixels))
                .showImageOnLoading(defaultIconId)
                .showImageOnFail(defaultIconId)
                .showImageForEmptyUri(defaultIconId)
                .cacheInMemory(true)
                .cacheOnDisc()
                .build();
    }

    public static DisplayImageOptions getImageOptions() {
        return getImageOptions(R.mipmap.ic_launcher_round, 0);
    }

    public static void displayImage(Context context, String uri, ImageView imageView) {
        MyApplication.getImageLoader(context).displayImage(uri, imageView, getImageOptions());
    }

    public static void displayImage(Context context, String uri, ImageView imageView,
                                    DisplayImageOptions displayImageOptions) {
        MyApplication.getImageLoader(context).displayImage(uri, imageView, displayImageOptions);
    }

    public static String showDate(Context context, String date) {
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getDefault());
        String year = String.valueOf(c.get(Calendar.YEAR)); // 获取当前年份
        String month =formatData(c.get(Calendar.MONTH) + 1) ;// 获取当前月份
        String day = formatData(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
        if (date.contains(year + "-" + month + "-" + day)) {
            return context.getString(R.string.today);
        } else {
            date = date.substring(0, 10);
            String[] dates = date.split("-");
            return dates[1] + context.getString(R.string.month) + dates[2] + context.getString(R.string.
                    day);
        }
    }
    public static String formatData(int data) {
        return data < 10 ? "0" + data : data + "";
    }

}
