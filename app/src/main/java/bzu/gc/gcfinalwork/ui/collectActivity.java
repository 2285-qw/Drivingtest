package bzu.gc.gcfinalwork.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;

import java.util.List;

import bzu.gc.gcfinalwork.R;
import bzu.gc.gcfinalwork.db.QDBManger;
import bzu.gc.gcfinalwork.entity.Question;

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
        t_item1=findViewById(R.id.t_item1);

        Drawable nav_up=getResources().getDrawable(R.mipmap.d_true);
        nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
        t_item1.setCompoundDrawables(nav_up, null, null, null);
    }

    private void setdata(int qth) {
        question = list.get(qth);
        if (question != null) {
            tittle.setText(question.getQuestion());
            item1.setText(question.getItem1());
            item2.setText(question.getItem2());
            item3.setText(question.getItem3());
            item4.setText(question.getItem4());

            item1.setClickable(false);

            answer = question.getAnswer();

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
    }

    //关闭点击时间内
    public void newfinish(View view) {
        finish();
    }

    //点击删除我的收藏
    public void deleteCollect(View view){
        qdbManger.deleteCollect(question.getId());
        //更新list集合数据
        list = qdbManger.getCollect();
        int i = qdbManger.getWrongnum("111");
        if (num >= i) {
            num = 0;
        }
        setdata(num);
    }

}
