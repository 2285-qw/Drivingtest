package bzu.gc.gcfinalwork.Application;

import android.app.Application;

import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

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
        UMConfigure.init(this,"5fec3edaadb42d582695f09e", "yunhaojiakao", UMConfigure.DEVICE_TYPE_PHONE,null);

        //页面采集模式
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);


    }
}
