package bzu.gc.gcfinalwork.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import bzu.gc.gcfinalwork.R;
import bzu.gc.gcfinalwork.db.QDBManger;
import bzu.gc.gcfinalwork.entity.Question;

/**
 * Time:         2021/2/26
 * Author:       C
 * Description:  collectList
 * on:
 */
public class collectList extends AppCompatActivity {
    //错题本集合
    private List<Question> list;
    //存题数据库类
    private QDBManger qdbManger;
    //正确答案编号
    private int answer;
    //ListView
    private ListView lv;
    //对象
    Question question;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collectlist);

        //去除标题栏
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        lv=findViewById(R.id.lv);

        //初始化数据库
        qdbManger = new QDBManger(this);
        //初始化集合数据
        list = qdbManger.getCollect();


    }

    @Override
    protected void onResume() {
        super.onResume();
        lv.setAdapter(new MyAdapter());

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    private class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            if (convertView==null){
                view =View.inflate(getApplicationContext(),R.layout.item,null);
            }else {
                view=convertView;
            }
            TextView text1=view.findViewById(R.id.text1);
            TextView text2=view.findViewById(R.id.text2);

            text1.setText(position+1+"");
            text2.setText(list.get(position).getQuestion());

            return view;
        }
    }

    //关闭点击时间内
    public void newfinish(View view) {
        finish();
    }
}
