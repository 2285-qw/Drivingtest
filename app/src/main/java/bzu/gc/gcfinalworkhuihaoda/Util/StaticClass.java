package bzu.gc.gcfinalworkhuihaoda.Util;

import android.Manifest;

/*项目名：   SmartButer
 *包名：     com.example.smartbuter.utils
 *文件名：   StaticClass
 *创建者：   CEH
 *创建时间： 2020/11/18 17:20
 *描述：     数据/常量
 */
public class StaticClass {
    //闪屏业延时名
    public static final int HANDLER_SPLASH=1001;
    //判断程序是否是第一次运行
    public static final String SPLASH_IS_FIRST="isFIRST";
    //bugly id
    public static final String BUGLY_APP_ID="3f6658c59c";
    //Bmob id
    public static final String BMOB_APP_ID="aa3100090c2e08a48cf15d16cd3354f0";
    //访问GPS定位
    public static final String Q_LOCATION0= Manifest.permission.ACCESS_COARSE_LOCATION;
    //用于访问wifi网络信息，wifi信息会用于进行网络定位
    public static final String Q_LOCATION1= Manifest.permission.ACCESS_FINE_LOCATION;
    //获取运营商信息，用于支持提供运营商信息相关的接口
    public static final String Q_LOCATION2= Manifest.permission.ACCESS_WIFI_STATE;
    //访问网络，网络定位需要上网
    public static final String Q_LOCATION3= Manifest.permission.ACCESS_NETWORK_STATE;
    //SD卡读取权限，用户写入离线定位数据
    public static final String Q_LOCATION4= Manifest.permission.INTERNET;
    //SD卡读取权限，用户写入离线定位数据
    public static final String Q_LOCATION5= Manifest.permission.READ_EXTERNAL_STORAGE;
    //应用id
    public static final String APPID="5126344";
    //开屏广告id
    public static final String SPALSHID="887411785";
    //Banner广告位id
    public static final String BANNERID="945820774";
    //Banner广告位id2
    public static final String BANNERID2="945675546";








}
