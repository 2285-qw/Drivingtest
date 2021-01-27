
package bzu.gc.gcfinalwork;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.image.SmartImageView;

import org.apache.http.Header;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import bzu.gc.gcfinalwork.Adapter.ShopAdapter;
import bzu.gc.gcfinalwork.db.DBManger;
import bzu.gc.gcfinalwork.db.QDBManger;
import bzu.gc.gcfinalwork.entity.Question;
import bzu.gc.gcfinalwork.entity.ShopInfo;
import bzu.gc.gcfinalwork.entity.user;
import bzu.gc.gcfinalwork.tools.JsonParse;
import bzu.gc.gcfinalwork.tools.shuaJsonParse;

public class MainActivity extends AppCompatActivity {
    private ArrayList<View> pageview;
    private ViewPager viewPager;

    private TextView homeout;
    private TextView shuaout;
    private TextView shopout;
    private TextView mineout;

    private int islogin = 0;
    private int time =21;

    private DBManger dbManger;
    private QDBManger qdbManger;
    private String username;
    private String password;
    private int aimnum;
    private int allnum;
    private int errornum;

    private View viewhome;
    private TextView home_allnum;
    private TextView home_aimnum;
    private TextView home_errornum;

    private View viewshua;
    private List<Question> Qlist;
    private TextView tittle;
    private RadioButton item1;
    private RadioButton item2;
    private RadioButton item3;
    private RadioButton item4;
    private TextView explain;
    private SmartImageView questionimg;
    private int num=0;
    private RadioGroup select;
    private int answer;
    private ImageView right;
    private ImageView error;
    private TextView timelimit;
    Timer timer=new Timer();

    private View viewshop;
    private ListView listView;
    private LinearLayout loading;
    private List<ShopInfo> lists;

    private View viewmine;
    private TextView mine_username;
    final Timer timer1=new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获得登陆页面账号密码登陆信息
        Intent intentforlogin = getIntent();
        username = intentforlogin.getStringExtra("username");
        password=intentforlogin.getStringExtra("password");
        allnum = intentforlogin.getIntExtra("allnum", 0);
        aimnum = intentforlogin.getIntExtra("aimnum", 0);
        errornum = intentforlogin.getIntExtra("errornum", 0);
        islogin = intentforlogin.getIntExtra("islogin", 0);
        //去除标题栏
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        //查找布局文件
        LayoutInflater inflater = getLayoutInflater();
        viewhome = inflater.inflate(R.layout.home_layout, null);
        viewshua = inflater.inflate(R.layout.shua_layout, null);
        viewshop = inflater.inflate(R.layout.shop_layout, null);
        viewmine = inflater.inflate(R.layout.mine_layout, null);
        pageview = new ArrayList<View>();

        //添加想要跳转的页面
        pageview.add(viewhome);
        pageview.add(viewshua);
        pageview.add(viewshop);
        pageview.add(viewmine);
        //初始化
        init();
        //查看是否登陆,未登录则进入登入页面
        islogin();
        initget();
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
        homeout.setBackgroundColor(Color.parseColor("#EEEEEE"));
        shuaout.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
        shopout.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
        mineout.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        homeout.setBackgroundColor(Color.parseColor("#EEEEEE"));
                        shuaout.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                        shopout.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                        mineout.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                        break;
                    case 1:
                        homeout.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                        shuaout.setBackgroundColor(Color.parseColor("#EEEEEE"));
                        shopout.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                        mineout.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                        break;
                    case 2:
                        homeout.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                        shuaout.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                        shopout.setBackgroundColor(Color.parseColor("#EEEEEE"));
                        mineout.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                        break;
                    case 3:
                        homeout.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                        shuaout.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                        shopout.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                        mineout.setBackgroundColor(Color.parseColor("#EEEEEE"));
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        //shoplist点击跳转
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ShopInfo shopInfo=lists.get(position);
                String url=shopInfo.getSrc();
                Intent intent=new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
        select.setOnCheckedChangeListener(new myselect());
        timer.schedule(timerTask,1000,1000);
    }
    //倒计时
    TimerTask timerTask=new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    time--;
                    timelimit.setText(String.valueOf(time));
                    if(time<0){
                        timer.cancel();
                        qdbManger.add(Qlist.get(num),username);
                        error.setVisibility(View.VISIBLE);
                        right.setVisibility(View.INVISIBLE);
                        explain.setVisibility(View.VISIBLE);
                        timelimit.setVisibility(View.VISIBLE);

                    }
                }
            });
        }
    };

    //点击注销用户
    public void quit(View view) {
        Intent tologin = new Intent(MainActivity.this, loginpage.class);
        startActivity(tologin);
    }

    //判断是否已经登陆
    public void islogin() {
        if (islogin == 1) {
            mine_username.setText(username);
            home_allnum.setText(String.valueOf(allnum));
            home_aimnum.setText(String.valueOf(aimnum));
            home_errornum.setText(String.valueOf(errornum));

        }
        if (islogin == 0) {
            Intent intenttologin = new Intent(this, loginpage.class);
            startActivity(intenttologin);
        }
    }

    //底部按钮点击跳转页面事件
    //初始化页面
    public void init() {
        dbManger = new DBManger(this);
        qdbManger=new QDBManger(this);
        viewPager = (ViewPager) findViewById(R.id.ViewPage);

        homeout=findViewById(R.id.homeout);
        shuaout=findViewById(R.id.shuaout);
        shopout=findViewById(R.id.shopout);
        mineout=findViewById(R.id.mineout);

        home_allnum = viewhome.findViewById(R.id.home_allnum);
        home_aimnum = viewhome.findViewById(R.id.home_aimnum);
        home_errornum = viewhome.findViewById(R.id.home_errornum);

        mine_username = viewmine.findViewById(R.id.mine_username);

        listView = viewshop.findViewById(R.id.lvNews);
        loading = viewshop.findViewById(R.id.loading);

        tittle=viewshua.findViewById(R.id.tittle);
        item1=viewshua.findViewById(R.id.item1);
        item2=viewshua.findViewById(R.id.item2);
        item3=viewshua.findViewById(R.id.item3);
        item4=viewshua.findViewById(R.id.item4);
        explain=viewshua.findViewById(R.id.explain);
        questionimg=viewshua.findViewById(R.id.questionimg);
        select=viewshua.findViewById(R.id.select);
        right=viewshua.findViewById(R.id.right);
        error=viewshua.findViewById(R.id.error);
        timelimit=viewshua.findViewById(R.id.timelimit);
    }

    //点击跳转主页
    public void click1(View v) {
        viewPager.setCurrentItem(0);
        dbManger.updatewrong(qdbManger.getWrongnum(username),username);
        user User = dbManger.selectuser(username);
        home_allnum.setText(User.allnum.toString());
        home_aimnum.setText(User.aimnum.toString());
        home_errornum.setText(User.errornum.toString());


    }

    //点击跳转刷题页面
    public void click2(View v) {
        viewPager.setCurrentItem(1);

    }

    //点击跳转商城页面
    public void click3(View v) {
        viewPager.setCurrentItem(2);

    }

    //点击跳转我的页面
    public void click4(View v) {
        viewPager.setCurrentItem(3);

    }

    //从服务器中获取商店页面的商品信息
    public void initget() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(getString(R.string.serverurl), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                try {
                    String jsons = new String(bytes, "utf-8");
                    System.out.println(jsons);
                    lists = JsonParse.getNewsInfo(jsons);
                    if (lists != null) {
                        loading.setVisibility(View.INVISIBLE);
                        ShopAdapter shopAdapter = new ShopAdapter(MainActivity.this, R.layout.shop_items, lists);
                        listView.setAdapter(shopAdapter);
                    } else {
                        Toast.makeText(MainActivity.this, "获取信息失败", Toast.LENGTH_SHORT).show();
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(MainActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
    //聚合数据获取
    public void getresource(){
        final int qth=0;
        AsyncHttpClient asyncHttpClient=new AsyncHttpClient();
        asyncHttpClient.get("http://v.juhe.cn/jztk/query?subject=1&model=c1&key=862acb53c483cea2bc5dd5aed3b33bfa&testType=rand", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                try {
                    String json=new String(bytes,"utf-8");
                    System.out.println(json);
                    Qlist= shuaJsonParse.getquestioninfo(json);
                    if (Qlist!=null){
                        Question question= Qlist.get(qth);
                        tittle.setText(question.getQuestion());
                        item1.setText(question.getItem1());
                        item2.setText(question.getItem2());
                        item3.setText(question.getItem3());
                        item4.setText(question.getItem4());
                        answer=question.getAnswer();
                        questionimg.setImageUrl(question.getUrl());
                        explain.setText(question.getExplains());

                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();

                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(MainActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void tochangepwd(View view){
        Intent intenttochange=new Intent(MainActivity.this,ChangepwdActivity.class);
        intenttochange.putExtra("username",username);
        intenttochange.putExtra("password",password);
        System.out.println(password);
        startActivity(intenttochange);
    }

    public void newtquestion(View view){
        timer.cancel();
        timelimit.setVisibility(View.VISIBLE);
        explain.setVisibility(View.INVISIBLE);
        time=21;
        final Timer timer1=new Timer();
        TimerTask timerTask1=new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time--;
                        timelimit.setText(String.valueOf(time));
                        if(time<0 || time==30){
                            timer1.cancel();
                            qdbManger.add(Qlist.get(num),username);
                            error.setVisibility(View.VISIBLE);
                            right.setVisibility(View.INVISIBLE);
                            explain.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }
        };
        timer1.schedule(timerTask1,2000,2000);

        allnum=dbManger.getallnum(username);
        dbManger.upallnum(allnum,username);
        right.setVisibility(View.INVISIBLE);
        error.setVisibility(View.INVISIBLE);
        num++;
        if(num==100){
            getresource();
            num=0;
        }
        Question question= Qlist.get(num);
        tittle.setText(question.getQuestion());
        item1.setText(question.getItem1());
        item2.setText(question.getItem2());
        item3.setText(question.getItem3());
        answer=question.getAnswer();
        item4.setText(question.getItem4());
        questionimg.setImageUrl(question.getUrl());
        explain.setText(question.getExplains());
    }
    public class myselect implements RadioGroup.OnCheckedChangeListener{
        int selanswer;

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            System.out.println(answer);
            switch (checkedId){
                case R.id.item1:
                    selanswer=1;
                    break;
                case R.id.item2:
                    selanswer=2;
                    break;
                case R.id.item3:
                    selanswer=3;
                    break;
                case R.id.item4:
                    selanswer=4;
                    break;
            }
            System.out.println(selanswer);
            if (selanswer==answer){
                timer1.cancel();
                error.setVisibility(View.INVISIBLE);
                right.setVisibility(View.VISIBLE);
                explain.setVisibility(View.VISIBLE);
                timelimit.setVisibility(View.INVISIBLE);
            }else
            {
                timer1.cancel();
                dbManger.updatewrong(qdbManger.getWrongnum(username),username);
                qdbManger.add(Qlist.get(num),username);
                error.setVisibility(View.VISIBLE);
                right.setVisibility(View.INVISIBLE);
                explain.setVisibility(View.VISIBLE);
                timelimit.setVisibility(View.INVISIBLE);
            }
        }
    }
    //跳转至我的错题本
    public void tomywronglist(View view){
        Intent intent=new Intent(MainActivity.this,wrnglists.class);
        intent.putExtra("username",username);
        startActivity(intent);
    }
}
