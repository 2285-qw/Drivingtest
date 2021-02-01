
package bzu.gc.gcfinalwork;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
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

import bzu.gc.gcfinalwork.db.DBManger;
import bzu.gc.gcfinalwork.db.QDBManger;
import bzu.gc.gcfinalwork.entity.Question;
import bzu.gc.gcfinalwork.entity.ShopInfo;
import bzu.gc.gcfinalwork.entity.user;
import bzu.gc.gcfinalwork.tools.shuaJsonParse;

public class MainActivity extends AppCompatActivity {
    private ArrayList<View> pageview;
    private ViewPager viewPager;

    private TextView homeout;
    private TextView shuaout;
    private TextView mineout;

    private int islogin = 0;
    private int time =21;

    private DBManger dbManger;
    private QDBManger qdbManger;
    private String username;
    private String password;

    private int allnum;


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
    Timer timer=new Timer();


    private List<ShopInfo> lists;

    private View viewmine;
    final Timer timer1=new Timer();

    //底部主页图标
    private ImageView homei;
    //底部答题图标
    private ImageView answeri;
    //底部我的图标
    private ImageView myi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //去除标题栏
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


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
        //查看是否登陆,未登录则进入登入页面

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
                switch (i){
                    case 0:
                        homei.setImageResource(R.mipmap.homeclick);
                        answeri.setImageResource(R.mipmap.answer);
                        myi.setImageResource(R.mipmap.my);
                        homeout.setTextColor(Color.parseColor("#55CAC2"));
                        shuaout.setTextColor(Color.parseColor("#999999"));
                        mineout.setTextColor(Color.parseColor("#999999"));
                        break;
                    case 1:
                        homei.setImageResource(R.mipmap.home);
                        answeri.setImageResource(R.mipmap.answerclick);
                        myi.setImageResource(R.mipmap.my);
                        homeout.setTextColor(Color.parseColor("#999999"));
                        shuaout.setTextColor(Color.parseColor("#55CAC2"));
                        mineout.setTextColor(Color.parseColor("#999999"));
                        break;
                    case 2:
                        homei.setImageResource(R.mipmap.home);
                        answeri.setImageResource(R.mipmap.answer);
                        myi.setImageResource(R.mipmap.myclick);
                        homeout.setTextColor(Color.parseColor("#999999"));
                        shuaout.setTextColor(Color.parseColor("#999999"));
                        mineout.setTextColor(Color.parseColor("#55CAC2"));
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        select.setOnCheckedChangeListener(new myselect());
    }


    @Override
    protected void onResume() {
        super.onResume();
        home_errornum.setText(qdbManger.getWrongnum("111")+"");
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (i==0){
                    home_errornum.setText(qdbManger.getWrongnum("111")+"");
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    //底部按钮点击跳转页面事件
    //初始化页面
    public void init() {
        dbManger = new DBManger(this);
        qdbManger=new QDBManger(this);
        viewPager = (ViewPager) findViewById(R.id.ViewPage);

        homeout=findViewById(R.id.homeout);
        shuaout=findViewById(R.id.shuaout);
        mineout=findViewById(R.id.mineout);
        homei=findViewById(R.id.homeI);
        answeri=findViewById(R.id.answeri);
        myi=findViewById(R.id.myi);


        home_allnum = viewhome.findViewById(R.id.home_allnum);
        home_errornum = viewhome.findViewById(R.id.home_errornum);


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
    }

    //点击跳转主页
    public void click1(View v) {
        viewPager.setCurrentItem(0);
        dbManger.updatewrong(qdbManger.getWrongnum("111"),"111");
        user User = dbManger.selectuser("111");
        System.out.println(User.allnum+"------");
        home_allnum.setText(User.allnum+"1");

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
                    System.out.println(Qlist.size());
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


    public void newtquestion(View view){
        timer.cancel();
        explain.setVisibility(View.INVISIBLE);

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
        Log.d("xxx","item1:"+question.getItem1()+"item2:"+question.getItem2()+"item3:"+question.getItem3()+"item4:"+question.getItem4());
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

                error.setVisibility(View.INVISIBLE);
                right.setVisibility(View.VISIBLE);
                explain.setVisibility(View.VISIBLE);
                if (!qdbManger.idfrist((Qlist.get(num).getId()))){
                    qdbManger.add(Qlist.get(num),"");
                }

            }else
            {
                //====================================================================================
                dbManger.updatewrong(qdbManger.getWrongnum("111"),"111");
                if (!qdbManger.idfrist((Qlist.get(num).getId()))){
                qdbManger.add(Qlist.get(num),"111");
            }

                error.setVisibility(View.VISIBLE);
                right.setVisibility(View.INVISIBLE);
                explain.setVisibility(View.VISIBLE);
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
