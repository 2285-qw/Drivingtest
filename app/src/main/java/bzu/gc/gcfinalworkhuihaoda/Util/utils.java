package bzu.gc.gcfinalworkhuihaoda.Util;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.TextView;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Time:         2021/2/26
 * Author:       C
 * Description:  utils
 * on:  公共类方法
 */
public class utils {

    //修改答题选项正错图
    public static void setTextviewdraw(TextView textView, int i, Activity activity) {
        Drawable nav_up = activity.getResources().getDrawable(i);
        nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
        textView.setCompoundDrawables(nav_up, null, null, null);
    }

    //广告计时显示
    public static void clockTime() {
        final long cTime = 6000;
        Time time = new Time(cTime);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        //
        Date date = new Date();
        Log.d("time", date + "");




    }


}
