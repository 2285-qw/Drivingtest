package bzu.gc.gcfinalworkhuihaoda.Util;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Typeface;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Time:         2020/12/25
 * Author:       C
 * Description:  UtilTools
 * on:工具统一类
 */
public class UtilTools {
    //隐藏键盘
    public static void Hide_key(Activity activity){
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        // 隐藏软键盘
        imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
    }
    //复制翻译文本
    public static void text_copy(Activity activity, String text){
        ClipboardManager copy = (ClipboardManager) activity
                .getSystemService(Context.CLIPBOARD_SERVICE);
        copy.setText(text);
    }
    //设置字体
    public static void setFont(Context context, TextView textView, String path){
        Typeface typeface= Typeface.createFromAsset(context.getAssets(),path);
        textView.setTypeface(typeface);
    }
    //设置字体
    public static void setFont_button(Context context, Button textView, String path){
        Typeface typeface= Typeface.createFromAsset(context.getAssets(),path);
        textView.setTypeface(typeface);
    }


    //设置字体
    public static void setFont(Context context, EditText textView, String path){
        Typeface typeface= Typeface.createFromAsset(context.getAssets(),path);
        textView.setTypeface(typeface);
    }

    //设置字体
    public static void setFontt(Context context, EditText textView, String path){
        Typeface typeface= Typeface.createFromAsset(context.getAssets(),path);
        textView.setTypeface(typeface);
    }
}


