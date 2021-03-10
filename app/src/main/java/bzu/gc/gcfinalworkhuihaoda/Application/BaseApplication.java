package bzu.gc.gcfinalworkhuihaoda.Application;

import android.app.Application;

import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

import bzu.gc.gcfinalworkhuihaoda.config.TTAdManagerHolder;

/**
 * Time:         2021/2/3
 * Author:       C
 * Description:  BaseApplication
 * on:
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //调试 log
        UMConfigure.setLogEnabled(true);
        //初始化sdk
        UMConfigure.init(this,"603c8b50b8c8d45c1384081a", "yunhaojiakao", UMConfigure.DEVICE_TYPE_PHONE,null);
        //页面采集模式
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);


        //穿山甲SDK初始化
        //强烈建议在应用对应的Application#onCreate()方法中调用，避免出现content为null的异常
        TTAdManagerHolder.init(this);
    }
}
