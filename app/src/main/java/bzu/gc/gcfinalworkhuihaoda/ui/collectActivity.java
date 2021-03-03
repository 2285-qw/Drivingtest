package bzu.gc.gcfinalworkhuihaoda.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;

import java.util.List;

import bzu.gc.gcfinalworkhuihaoda.R;
import bzu.gc.gcfinalworkhuihaoda.Util.utils;
import bzu.gc.gcfinalworkhuihaoda.db.QDBManger;
import bzu.gc.gcfinalworkhuihaoda.entity.Question;

/**
 * Time:         2021/2/24
 * Author:       C
 * Description:  collectActivity
 * on: 我的收藏页面
 */
public class collectActivity extends AppCompatActivity {
    //错题本集合
    private List<Question> list;
    //存题数据库类
    private QDBManger qdbManger;
    //正确答案编号
    private int answer;
    //计数器
    private int num = 0;
    //对象
    Question question;

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
    //单选、判断
    TextView choice;

    private TextView tittle;
    private TextView explain;
    private SmartImageView questionimg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);

        //去除标题栏
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        //初始化数据库
        qdbManger = new QDBManger(this);
        //初始化集合数据
        list = qdbManger.getCollect();
        //初始化数据
        initdata();
        Intent intent = getIntent();
        num = intent.getIntExtra("position", num);
        Log.d("num",num+"");
        if (list != null) {
            setdata(num);
        }


    }


    private void initdata() {
        tittle = findViewById(R.id.tittle);

        explain = findViewById(R.id.explain);
        questionimg = findViewById(R.id.questionimg);
        t_item1 = findViewById(R.id.t_item1);
        t_item2 = findViewById(R.id.t_item2);
        t_item3 = findViewById(R.id.t_item3);
        t_item4 = findViewById(R.id.t_item4);
        choice=findViewById(R.id.choice);

        analytic = findViewById(R.id.analytic);


        t_item1.setOnClickListener(new MyOnclick());
        t_item2.setOnClickListener(new MyOnclick());
        t_item3.setOnClickListener(new MyOnclick());
        t_item4.setOnClickListener(new MyOnclick());


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
                    choice.setText("判断");
                    t_item3.setVisibility(View.GONE);
                    t_item4.setVisibility(View.GONE);
                    break;
                default:
                    choice.setText("单选");
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
            questionimg.setImageUrl(question.getUrl(), R.mipmap.icon, R.mipmap.icon1);

            explain.setText(question.getExplains());
            Log.d("ex", question.getExplains());
        }
    }

    //下一题点击事件
    public void newtquestion(View view) {
        int i = qdbManger.getCollect().size();
        num++;
        if (num == i) {
            num = 0;
        }
        setdata(num);

        utils.setTextviewdraw(t_item1, R.mipmap.aa1, collectActivity.this);
        utils.setTextviewdraw(t_item2, R.mipmap.b, collectActivity.this);
        utils.setTextviewdraw(t_item3, R.mipmap.c, collectActivity.this);
        utils.setTextviewdraw(t_item4, R.mipmap.d, collectActivity.this);

        isture(true);

        analytic.setVisibility(View.GONE);
        explain.setVisibility(View.GONE);
    }

    //关闭点击时间内
    public void newfinish(View view) {
        finish();
    }

    //点击删除我的收藏
    public void deleteCollect(View view) {
        qdbManger.deleteCollect(question.getId());
        //更新list集合数据
        list = qdbManger.getCollect();
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
                        utils.setTextviewdraw(t_item1, R.mipmap.d_false, collectActivity.this);
                        //显示答案解析
                        analytic.setVisibility(View.VISIBLE);
                        explain.setVisibility(View.VISIBLE);
                    }
                    break;
                case R.id.t_item2:
                    if (answer != 2) {
                        utils.setTextviewdraw(t_item2, R.mipmap.d_false, collectActivity.this);
                        analytic.setVisibility(View.VISIBLE);
                        explain.setVisibility(View.VISIBLE);
                    }
                    break;
                case R.id.t_item3:
                    if (answer != 3) {
                        utils.setTextviewdraw(t_item3, R.mipmap.d_false, collectActivity.this);
                        analytic.setVisibility(View.VISIBLE);
                        explain.setVisibility(View.VISIBLE);
                    }
                    break;
                case R.id.t_item4:
                    if (answer != 4) {
                        utils.setTextviewdraw(t_item4, R.mipmap.d_false, collectActivity.this);
                        analytic.setVisibility(View.VISIBLE);
                        explain.setVisibility(View.VISIBLE);
                    }
                    break;
            }

            //设置答题框不能被点击
            isture(false);
            switch (answer) {
                case 1:
                    utils.setTextviewdraw(t_item1, R.mipmap.d_true, collectActivity.this);
                    break;
                case 2:
                    utils.setTextviewdraw(t_item2, R.mipmap.d_true, collectActivity.this);
                    break;
                case 3:
                    utils.setTextviewdraw(t_item3, R.mipmap.d_true, collectActivity.this);
                    break;
                case 4:
                    utils.setTextviewdraw(t_item4, R.mipmap.d_true, collectActivity.this);
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
