package bzu.gc.gcfinalwork.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;

import java.util.List;

import bzu.gc.gcfinalwork.R;
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
    private RadioButton item1;
    private RadioButton item2;
    private RadioButton item3;
    private RadioButton item4;
    private TextView explain;
    private SmartImageView questionimg;

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
        item1 = findViewById(R.id.item1);
        item2 = findViewById(R.id.item2);
        item3 = findViewById(R.id.item3);
        item4 = findViewById(R.id.item4);
        explain = findViewById(R.id.explain);
        questionimg = findViewById(R.id.questionimg);
    }

    private void setdata(int qth) {
        question = list.get(qth);
        if (question != null) {
            tittle.setText(question.getQuestion());
            item1.setText(question.getItem1());
            item2.setText(question.getItem2());
            item3.setText(question.getItem3());
            item4.setText(question.getItem4());
            answer = question.getAnswer();
            questionimg.setImageUrl(question.getUrl());
            explain.setText(question.getExplains());
        }
    }

    //下一题点击事件
    public void newtquestion(View view) {
        int i = qdbManger.getWrongnum("111");
        num++;
        if (num == i) {
            num = 0;
        }
        setdata(num);
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

    }

}
