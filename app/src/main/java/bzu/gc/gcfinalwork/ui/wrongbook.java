package bzu.gc.gcfinalwork.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;

import java.util.List;

import bzu.gc.gcfinalwork.R;
import bzu.gc.gcfinalwork.Util.utils;
import bzu.gc.gcfinalwork.db.QDBManger;
import bzu.gc.gcfinalwork.entity.Question;

/**
 * Time:         2021/2/22
 * Author:       C
 * Description:  wrongbook
 * on: 我的错题本页面
 */
public class wrongbook extends AppCompatActivity {

    //错题本集合
    private List<Question> list;
    //存题数据库类
    private QDBManger qdbManger;
    //无用用户名
    private String usernmae;
    //正确答案编号
    private int answer;
    //计数器
    private int num = 0;
    //对象
    Question question;

    private TextView tittle;
    //答题选项
    private TextView t_item1;
    //答题选项
    private TextView t_item2;
    //答题选项
    private TextView t_item3;
    //答题选项
    private TextView t_item4;
    //解析
    private TextView analytic;
    private TextView explain;
    private SmartImageView questionimg;
    //收藏图标
    private ImageView collect;
    //收藏文字
    private TextView t_collect;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrongbook);

        //去除标题栏
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        //初始化数据库
        qdbManger = new QDBManger(this);
        //初始化数据
        list = qdbManger.finderror(usernmae);
        //初始化数据
        initdata();

        if (list != null) {
            int qth = 0;
            setdata(qth);
        }
    }

    private void initdata() {
        tittle = findViewById(R.id.tittle);

        t_item1 = findViewById(R.id.t_item1);
        t_item2 = findViewById(R.id.t_item2);
        t_item3 = findViewById(R.id.t_item3);
        t_item4 = findViewById(R.id.t_item4);

        analytic = findViewById(R.id.analytic);


        t_item1.setOnClickListener(new MyOnclick());
        t_item2.setOnClickListener(new MyOnclick());
        t_item3.setOnClickListener(new MyOnclick());
        t_item4.setOnClickListener(new MyOnclick());

        explain = findViewById(R.id.explain);
        questionimg = findViewById(R.id.questionimg);
        collect=findViewById(R.id.collect);
        t_collect=findViewById(R.id.t_collect);
    }

    private void setdata(int qth) {
        question = list.get(qth);
        if (question != null) {
            tittle.setText(question.getQuestion());
            t_item1.setText(question.getItem1());
            t_item2.setText(question.getItem2());
            t_item3.setText(question.getItem3());
            t_item4.setText(question.getItem4());

            //判断是单选还是判断
            switch (question.getItem3()) {
                case "":
                    t_item3.setVisibility(View.GONE);
                    t_item4.setVisibility(View.GONE);
                    break;
                default:
                    t_item3.setVisibility(View.VISIBLE);
                    t_item4.setVisibility(View.VISIBLE);
                    break;
            }

            //答案数
            answer = question.getAnswer();
            switch (answer) {
                case 1:
                    analytic.setText("解析：答案（"+"A"+"）");
                    break;
                case 2:
                    analytic.setText("解析：答案（"+"B"+"）");
                    break;
                case 3:
                    analytic.setText("解析：答案（"+"C"+"）");
                    break;
                case 4:
                    analytic.setText("解析：答案（"+"D"+"）");
                    break;

            }


            //设置图片加载没有图片时隐藏
            switch (question.getUrl()){
                case "":
                    questionimg.setVisibility(View.GONE);
                    break;
                default:
                    questionimg.setVisibility(View.VISIBLE);
                    break;
            }
            questionimg.setImageUrl(question.getUrl(),R.mipmap.icon,R.mipmap.icon1);

            explain.setText(question.getExplains());

            Log.d("getcollect",question.getCollect().trim());
            switch (question.getCollect()){
                case "0":
                    collect.setImageResource(R.mipmap.icon);
                    t_collect.setTextColor(Color.parseColor("#fa6e52"));
                    break;
                case "1":
                    collect.setImageResource(R.mipmap.icon1);
                    t_collect.setTextColor(Color.parseColor("#000000"));
                    break;

            }
        }
    }

    //下一题点击事件
    public void newtquestion(View view) {
        int i = qdbManger.getWrongnum("111");
        num++;
        if (num >= i) {
            num = 0;
        }
        setdata(num);

        utils.setTextviewdraw(t_item1, R.mipmap.aa1, wrongbook.this);
        utils.setTextviewdraw(t_item2, R.mipmap.b, wrongbook.this);
        utils.setTextviewdraw(t_item3, R.mipmap.c, wrongbook.this);
        utils.setTextviewdraw(t_item4, R.mipmap.d, wrongbook.this);

        isture(true);

        analytic.setVisibility(View.GONE);
        explain.setVisibility(View.GONE);
    }

    //关闭点击时间内
    public void newfinish(View view) {
        finish();
    }

    //点击收藏题目
    public void collect(View view) {
        question.getId();
        Log.d("id",question.getId()+"");
        qdbManger.addCollect(question.getId());
        collect.setImageResource(R.mipmap.icon);
        t_collect.setTextColor(Color.parseColor("#FEB354"));

    }

    //点击删除错题
    public void deleteWrong(View view){
        question.getId();
        qdbManger.deleteWrong(question.getId());

        //更新list集合数据
        list = qdbManger.finderror(usernmae);
        int i = qdbManger.getWrongnum("111");
        if (num >= i) {
            num = 0;
        }
        setdata(num);
    }
    //答题点击事件
    public class MyOnclick implements View.OnClickListener {
        int selanswer;

        @Override
        public void onClick(View v) {
            System.out.println(answer);
            switch (v.getId()) {
                case R.id.t_item1:
                    if (answer != 1) {
                        utils.setTextviewdraw(t_item1, R.mipmap.d_false, wrongbook.this);
                        //显示答案解析
                        analytic.setVisibility(View.VISIBLE);
                        explain.setVisibility(View.VISIBLE);
                    }
                    break;
                case R.id.t_item2:
                    if (answer != 2) {
                        utils.setTextviewdraw(t_item2, R.mipmap.d_false, wrongbook.this);
                        analytic.setVisibility(View.VISIBLE);
                        explain.setVisibility(View.VISIBLE);
                    }
                    break;
                case R.id.t_item3:
                    if (answer != 3) {
                        utils.setTextviewdraw(t_item3, R.mipmap.d_false, wrongbook.this);
                        analytic.setVisibility(View.VISIBLE);
                        explain.setVisibility(View.VISIBLE);
                    }
                    break;
                case R.id.t_item4:
                    if (answer != 4) {
                        utils.setTextviewdraw(t_item4, R.mipmap.d_false, wrongbook.this);
                        analytic.setVisibility(View.VISIBLE);
                        explain.setVisibility(View.VISIBLE);
                    }
                    break;
            }

            //设置答题框不能被点击
            isture(false);
            switch (answer) {
                case 1:
                    utils.setTextviewdraw(t_item1, R.mipmap.d_true, wrongbook.this);
                    break;
                case 2:
                    utils.setTextviewdraw(t_item2, R.mipmap.d_true, wrongbook.this);
                    break;
                case 3:
                    utils.setTextviewdraw(t_item3, R.mipmap.d_true, wrongbook.this);
                    break;
                case 4:
                    utils.setTextviewdraw(t_item4, R.mipmap.d_true, wrongbook.this);
                    break;
            }
            System.out.println(selanswer);

        }
    }

    //答题框是否可以被点击


    private void isture(boolean on) {
        t_item1.setClickable(on);
        t_item2.setClickable(on);
        t_item3.setClickable(on);
        t_item4.setClickable(on);
    }
}
