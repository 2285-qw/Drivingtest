package bzu.gc.gcfinalworkhuihaoda;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bytedance.sdk.openadsdk.TTAdNative;
import com.loopj.android.image.SmartImageView;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import bzu.gc.gcfinalworkhuihaoda.Util.BannerUtil;
import bzu.gc.gcfinalworkhuihaoda.Util.JsonReadUtil;
import bzu.gc.gcfinalworkhuihaoda.Util.StaticClass;
import bzu.gc.gcfinalworkhuihaoda.Util.UtilTools;
import bzu.gc.gcfinalworkhuihaoda.Util.utils;
import bzu.gc.gcfinalworkhuihaoda.config.TTAdManagerHolder;
import bzu.gc.gcfinalworkhuihaoda.db.DBManger;
import bzu.gc.gcfinalworkhuihaoda.db.QDBManger;
import bzu.gc.gcfinalworkhuihaoda.entity.Question;
import bzu.gc.gcfinalworkhuihaoda.entity.ShopInfo;
import bzu.gc.gcfinalworkhuihaoda.tools.shuaJsonParse;
import bzu.gc.gcfinalworkhuihaoda.ui.PrivacyActivity;
import bzu.gc.gcfinalworkhuihaoda.ui.WebViewActivity;
import bzu.gc.gcfinalworkhuihaoda.ui.collectList;
import bzu.gc.gcfinalworkhuihaoda.ui.wronglist;

import static bzu.gc.gcfinalworkhuihaoda.Util.AppSigning.getSha1;

public class MainActivity extends AppCompatActivity {

    //文件名称
    private final static String CityFileName = "allcity.json";

    private ArrayList<View> pageview;
    private ViewPager viewPager;

    private TextView homeout;
    private TextView shuaout;
    private TextView mineout;

    private int islogin = 0;
    private int time = 21;

    private DBManger dbManger;
    private QDBManger qdbManger;
    private String username;
    private String password;

    private int allnum;


    //对象
    Question question;
    //错题本集合
    private List<Question> list;
    //解析
    private TextView analytic;


    private View viewhome;
    private TextView home_allnum;
    private TextView home_aimnum;
    private TextView home_errornum;
    private TextView home_errornum1;

    private View viewshua;
    private List<Question> Qlist;
    private TextView tittle;
    private TextView t_item1;
    private TextView t_item2;
    private TextView t_item3;
    private TextView t_item4;
    private TextView explain;
    private SmartImageView questionimg;
    private int num;
    private int answer;

    private List<ShopInfo> lists;

    private View viewmine;

    //底部主页图标
    private ImageView homei;
    //底部答题图标
    private ImageView answeri;
    //底部我的图标
    private ImageView myi;
    //底部对题
    TextView t_true;
    //底部错题
    TextView t_false;
    //底部收藏
    LinearLayout lin1;
    //底部收藏图片
    ImageView collect;
    //底部收藏文字
    TextView t_collect;
    //单选判断
    TextView choice;
    //我
    TextView i;
    //答题
    TextView da;
    //首页
    TextView shou;
    //Banner广告布局
    private FrameLayout mBannerContainer1;
    //Banner广告布局
    private FrameLayout mBannerContainer2;
    //Banner广告布局
    private FrameLayout mBannerContainer3;
    //
    TTAdNative mTTAdNative;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("sha1",getSha1(this));

        //去除标题栏
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        //创建轻量级储存
        SharedPreferences sp = MainActivity.this.getSharedPreferences("shu", Context.MODE_PRIVATE);
        //读取
        num=sp.getInt("num",0);
        Log.d("num",num+"");


        //查找布局文件
        LayoutInflater inflater = getLayoutInflater();
        viewhome = inflater.inflate(R.layout.home_layout, null);
        viewshua = inflater.inflate(R.layout.shua_layout, null);
        viewmine = inflater.inflate(R.layout.mine_layout, null);
        pageview = new ArrayList<View>();

        //添加想要跳转的页面
        pageview.add(viewhome);
        pageview.add(viewshua);
        pageview.add(viewmine);
        //初始化
        init();

        //设置字体
        UtilTools.setFont(MainActivity.this,i,"fonts/DIN-Medium.otf");
        UtilTools.setFont(MainActivity.this,da,"fonts/DIN-Medium.otf");
        UtilTools.setFont(MainActivity.this,shou,"fonts/DIN-Medium.otf");

        getresource();
        PagerAdapter mpagerAdapter = new PagerAdapter() {
            @Override
            //获取当前页面数量
            public int getCount() {
                return pageview.size();
            }

            //判断是否由对象生成页面
            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            //使从ViewGroup中移出当前View
            public void destroyItem(View arg0, int arg1, Object arg2) {
                ((ViewPager) arg0).removeView(pageview.get(arg1));
            }

            //返回一个对象，这个对象表明了PageAdapter适配器选择哪个对象放在当前的ViewPage中
            public Object instantiateItem(View arg0, int arg1) {
                ((ViewPager) arg0).addView(pageview.get(arg1));
                return pageview.get(arg1);
            }
        };
        //绑定适配器
        viewPager.setAdapter(mpagerAdapter);
        //设置viewPager的初始化界面为第一个界面并初始化
        viewPager.setCurrentItem(0);
        homei.setImageResource(R.mipmap.homeclick);
        homeout.setTextColor(Color.parseColor("#55CAC2"));
        shuaout.setTextColor(Color.parseColor("#999999"));
        mineout.setTextColor(Color.parseColor("#999999"));

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (i == 0) {

                }

                switch (i) {
                    case 0:
                        homei.setImageResource(R.mipmap.homeclick);
                        answeri.setImageResource(R.mipmap.answer);
                        myi.setImageResource(R.mipmap.my);
                        homeout.setTextColor(Color.parseColor("#55CAC2"));
                        shuaout.setTextColor(Color.parseColor("#999999"));
                        mineout.setTextColor(Color.parseColor("#999999"));


                        //设置错题数
                        home_errornum.setText(qdbManger.getWrongnum("111") + "");
                        //设置刷题总量
                        home_allnum.setText(qdbManger.getWrongnum() + "");
                        //设置收藏题数
                        home_errornum1.setText(qdbManger.getCollect().size() + "");

                        break;
                    case 1:
                        homei.setImageResource(R.mipmap.home);
                        answeri.setImageResource(R.mipmap.answerclick);
                        myi.setImageResource(R.mipmap.my);
                        homeout.setTextColor(Color.parseColor("#999999"));
                        shuaout.setTextColor(Color.parseColor("#55CAC2"));
                        mineout.setTextColor(Color.parseColor("#999999"));

                        mTTAdNative = TTAdManagerHolder.get().createAdNative(MainActivity.this);
                        BannerUtil.loadBannerAd(StaticClass.BANNERID2,mTTAdNative, MainActivity.this,mBannerContainer2);
                        break;
                    case 2:
                        homei.setImageResource(R.mipmap.home);
                        answeri.setImageResource(R.mipmap.answer);
                        myi.setImageResource(R.mipmap.myclick);
                        homeout.setTextColor(Color.parseColor("#999999"));
                        shuaout.setTextColor(Color.parseColor("#999999"));
                        mineout.setTextColor(Color.parseColor("#55CAC2"));

                        mTTAdNative = TTAdManagerHolder.get().createAdNative(MainActivity.this);
                        BannerUtil.loadBannerAd(StaticClass.BANNERID3,mTTAdNative, MainActivity.this,mBannerContainer3);
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


    }


    @Override
    protected void onResume() {
        super.onResume();

        //step2:创建TTAdNative对象
        mTTAdNative = TTAdManagerHolder.get().createAdNative(this);
        BannerUtil.loadBannerAd(StaticClass.BANNERID1,mTTAdNative, this,mBannerContainer1);

        MobclickAgent.onResume(this);
        //设置错题数
        home_errornum.setText(qdbManger.getWrongnum("111") + "");
        t_false.setText(qdbManger.getWrongnum("111") + "");
        //设置刷题总量
        home_allnum.setText(qdbManger.getWrongnum() + "");
        //设置对题数
        t_true.setText(qdbManger.getWrongnum() - qdbManger.getWrongnum("111") + "");
        //获取收藏题数
        List list = new ArrayList();
        list = qdbManger.getCollect();
        Log.d("list", list.size() + "++++" + list);

        //设置收藏题数
        home_errornum1.setText(qdbManger.getCollect().size() + "");

    }

    //底部按钮点击跳转页面事件
    //初始化页面
    public void init() {
        dbManger = new DBManger(this);
        qdbManger = new QDBManger(this);
        viewPager = (ViewPager) findViewById(R.id.ViewPage);

        homeout = findViewById(R.id.homeout);
        shuaout = findViewById(R.id.shuaout);
        mineout = findViewById(R.id.mineout);
        homei = findViewById(R.id.homeI);
        answeri = findViewById(R.id.answeri);
        myi = findViewById(R.id.myi);



        home_allnum = viewhome.findViewById(R.id.home_allnum);
        home_errornum = viewhome.findViewById(R.id.home_errornum);
        home_errornum1 = viewhome.findViewById(R.id.home_errornum1);
        shou=viewhome.findViewById(R.id.shou);
        mBannerContainer1=viewhome.findViewById(R.id.banner_container);



        tittle = viewshua.findViewById(R.id.tittle);
        t_item1 = viewshua.findViewById(R.id.t_item1);
        t_item2 = viewshua.findViewById(R.id.t_item2);
        t_item3 = viewshua.findViewById(R.id.t_item3);
        t_item4 = viewshua.findViewById(R.id.t_item4);
        explain = viewshua.findViewById(R.id.explain);
        questionimg = viewshua.findViewById(R.id.questionimg);
        analytic = viewshua.findViewById(R.id.analytic);
        t_true = viewshua.findViewById(R.id.t_true);
        t_false = viewshua.findViewById(R.id.t_false);
        collect=viewshua.findViewById(R.id.collect);
        t_collect=viewshua.findViewById(R.id.t_collect);
        choice=viewshua.findViewById(R.id.choice);
        da=viewshua.findViewById(R.id.da);
        mBannerContainer2=viewshua.findViewById(R.id.banner_container);

        i=viewmine.findViewById(R.id.i);
        mBannerContainer3=viewmine.findViewById(R.id.banner_container);

        t_item1.setOnClickListener(new MainActivity.MyOnclick());
        t_item2.setOnClickListener(new MainActivity.MyOnclick());
        t_item3.setOnClickListener(new MainActivity.MyOnclick());
        t_item4.setOnClickListener(new MainActivity.MyOnclick());


    }

    //点击跳转首页
    public void click1(View v) {
        viewPager.setCurrentItem(0);
        dbManger.updatewrong(qdbManger.getWrongnum("111"), "111");

        //设置刷题总量
        home_allnum.setText(qdbManger.getWrongnum() + "");  //设置刷题总量

    }

    //点击跳转刷题页面
    public void click2(View v) {
        viewPager.setCurrentItem(1);
    }


    //点击跳转我的页面
    public void click4(View v) {
        viewPager.setCurrentItem(3);

    }

    //聚合数据获取
    public void getresource() {
        //本地获取数据
        String cityListJson = JsonReadUtil.getJsonStr(this, CityFileName);
        Qlist = shuaJsonParse.getquestioninfo(cityListJson);
        if (Qlist != null) {
            setdata(num);
        }


        //网络获取数据
        /*AsyncHttpClient asyncHttpClient=new AsyncHttpClient();
        asyncHttpClient.get("http://v.juhe.cn/jztk/query?subject=1&model=c1&key=862acb53c483cea2bc5dd5aed3b33bfa&testType=rand", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                try {
                    String json=new String(bytes,"utf-8");
                    Log.d("json",json);
                    System.out.println(json);
                    Qlist= shuaJsonParse.getquestioninfo(json);
                    //System.out.println(Qlist.size());
                    if (Qlist!=null){
                        setdata(qth);
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    System.out.println("000000000000000000000");

                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(MainActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    //下一题点击按钮
    public void newtquestion(View view) {
        explain.setVisibility(View.INVISIBLE);
        allnum = dbManger.getallnum(username);
        dbManger.upallnum(allnum, username);
        num++;
        Log.d("id", question.getId() + "");
        if (num == Qlist.size()) {
            getresource();
            num = 0;
        }
        setdata(num);

        utils.setTextviewdraw(t_item1, R.mipmap.aa1, MainActivity.this);
        utils.setTextviewdraw(t_item2, R.mipmap.b, MainActivity.this);
        utils.setTextviewdraw(t_item3, R.mipmap.c, MainActivity.this);
        utils.setTextviewdraw(t_item4, R.mipmap.d, MainActivity.this);

        isture(true);

        analytic.setVisibility(View.GONE);
        explain.setVisibility(View.GONE);

        //收藏
        collect.setImageResource(R.mipmap.icon1);
        t_collect.setTextColor(Color.parseColor("#111111"));

        BannerUtil.loadBannerAd(StaticClass.BANNERID2,mTTAdNative, MainActivity.this,mBannerContainer2);
    }

    //跳转至我的错题本
    public void tomywronglist(View view) {
        Intent intent = new Intent(MainActivity.this, wronglist.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    //跳转至我的收藏
    public void skipCollect(View view) {
        Intent intent = new Intent(MainActivity.this, collectList.class);
        startActivity(intent);
    }

    //跳转至我的收藏主页面
    public void skip(View view) {
        Intent intent = new Intent(MainActivity.this, collectList.class);
        startActivity(intent);
    }

    //跳转至隐私政策页面
    public void jump(View view) {
        Intent intent = new Intent(MainActivity.this, PrivacyActivity.class);
        startActivity(intent);
    }

    //跳转至隐私政策页面
    public void user(View view) {
        WebViewActivity.openActivity(getApplicationContext(),"");
    }

    //设置题目加载显示
    private void setdata(int qth) {
        question = Qlist.get(qth);
        if (question != null) {
            tittle.setText(question.getQuestion());
            t_item1.setText(question.getItem1());
            t_item2.setText(question.getItem2());
            t_item3.setText(question.getItem3());
            t_item4.setText(question.getItem4());

            //判断是单选还是判断
            switch (question.getItem3()) {
                case "":
                    choice.setText("判断");
                    t_item3.setVisibility(View.GONE);
                    t_item4.setVisibility(View.GONE);
                    break;
                default: choice.setText("单选");
                    t_item3.setVisibility(View.VISIBLE);
                    t_item4.setVisibility(View.VISIBLE);
                    break;
            }

            //答案数
            answer = question.getAnswer();
            switch (answer) {
                case 1:
                    analytic.setText("解析：答案（" + "A" + "）");
                    break;
                case 2:
                    analytic.setText("解析：答案（" + "B" + "）");
                    break;
                case 3:
                    analytic.setText("解析：答案（" + "C" + "）");
                    break;
                case 4:
                    analytic.setText("解析：答案（" + "D" + "）");
                    break;

            }


            //设置图片加载没有图片时隐藏
            switch (question.getUrl()) {
                case "":
                    questionimg.setVisibility(View.GONE);
                    break;
                default:
                    questionimg.setVisibility(View.VISIBLE);
                    break;
            }
            questionimg.setImageUrl(question.getUrl(), R.mipmap.nonet, R.mipmap.nonet);

            explain.setText(question.getExplains());

            Log.d("getcollect", question.getCollect().trim());
            /*switch (question.getCollect()){
                case "0":
                    collect.setImageResource(R.mipmap.icon);
                    t_collect.setTextColor(Color.parseColor("#fa6e52"));
                    break;
                case "1":
                    collect.setImageResource(R.mipmap.icon1);
                    t_collect.setTextColor(Color.parseColor("#000000"));
                    break;

            }*/
        }
    }

    //答题点击事件
    public class MyOnclick implements View.OnClickListener {
        int selanswer;

        @Override
        public void onClick(View v) {
            System.out.println(answer);
            Log.d("QQQ", qdbManger.isId(question.getId()) + "");
            if (!qdbManger.isId(question.getId())) {
                //添加刷题数
                qdbManger.add(question, "");
            }

            switch (v.getId()) {
                case R.id.t_item1:
                    if (answer != 1) {
                        utils.setTextviewdraw(t_item1, R.mipmap.d_false, MainActivity.this);
                        //显示答案解析
                        analytic.setVisibility(View.VISIBLE);
                        explain.setVisibility(View.VISIBLE);
                        //添加错题数
                        qdbManger.addDelete(question.getId(), "111");
                    }
                    break;
                case R.id.t_item2:
                    if (answer != 2) {
                        utils.setTextviewdraw(t_item2, R.mipmap.d_false, MainActivity.this);
                        analytic.setVisibility(View.VISIBLE);
                        explain.setVisibility(View.VISIBLE);
                        //添加错题数
                        qdbManger.addDelete(question.getId(), "111");
                    }
                    break;
                case R.id.t_item3:
                    if (answer != 3) {
                        utils.setTextviewdraw(t_item3, R.mipmap.d_false, MainActivity.this);
                        analytic.setVisibility(View.VISIBLE);
                        explain.setVisibility(View.VISIBLE);
                        //添加错题数
                        qdbManger.addDelete(question.getId(), "111");
                    }
                    break;
                case R.id.t_item4:
                    if (answer != 4) {
                        utils.setTextviewdraw(t_item4, R.mipmap.d_false, MainActivity.this);
                        analytic.setVisibility(View.VISIBLE);
                        explain.setVisibility(View.VISIBLE);
                        //添加错题数
                        qdbManger.addDelete(question.getId(), "111");
                    }
                    break;
            }
            //设置错题数
            t_false.setText(qdbManger.getWrongnum("111") + "");
            //设置对题数
            t_true.setText(qdbManger.getWrongnum() - qdbManger.getWrongnum("111") + "");

            //设置答题框不能被点击
            isture(false);

            switch (answer) {
                case 1:
                    utils.setTextviewdraw(t_item1, R.mipmap.d_true, MainActivity.this);
                    break;
                case 2:
                    utils.setTextviewdraw(t_item2, R.mipmap.d_true, MainActivity.this);
                    break;
                case 3:
                    utils.setTextviewdraw(t_item3, R.mipmap.d_true, MainActivity.this);
                    break;
                case 4:
                    utils.setTextviewdraw(t_item4, R.mipmap.d_true, MainActivity.this);
                    break;
            }
            System.out.println(selanswer);

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onResume(this);
    }

    //答题框是否可以被点击
    private void isture(boolean on) {
        t_item1.setClickable(on);
        t_item2.setClickable(on);
        t_item3.setClickable(on);
        t_item4.setClickable(on);
    }

    //点击收藏题目
    public void collect(View view) {
        question.getId();
        if (!qdbManger.isId(question.getId())) {
            //添加刷题数
            qdbManger.add(question, "");
        }
        Log.d("id",question.getId()+"");
        qdbManger.addCollect(question.getId());


        collect.setImageResource(R.mipmap.icon);
        t_collect.setTextColor(Color.parseColor("#FEB354"));



    }

    @Override
    protected void onStop() {
        super.onStop();
        //创建轻量级储存
        SharedPreferences sp = MainActivity.this.getSharedPreferences("shu", Context.MODE_PRIVATE);
        //设置可编辑
        SharedPreferences.Editor spe = sp.edit();
        //储存答题id
        spe.putInt("num", num).commit();
        int i=sp.getInt("num",0);
        Log.d("num",num+"");
        Log.d("num",i+"");
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();


    }
}
