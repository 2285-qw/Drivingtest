package bzu.gc.gcfinalworkhuihaoda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.List;

import bzu.gc.gcfinalworkhuihaoda.Adapter.QuestionAdapter;
import bzu.gc.gcfinalworkhuihaoda.db.DBManger;
import bzu.gc.gcfinalworkhuihaoda.db.QDBManger;
import bzu.gc.gcfinalworkhuihaoda.entity.Question;

public class wrnglists extends AppCompatActivity {
    private ListView w_LV;
    private List<Question> list;
    private LinearLayout loading;
    private QDBManger qdbManger;
    private String usernmae;
    private DBManger dbManger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrnglists);
        dbManger=new DBManger(this);

        //去除标题栏
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        Intent intent=getIntent();
        usernmae=intent.getStringExtra("username");

        w_LV=findViewById(R.id.wronglist);
        loading=findViewById(R.id.wrong_loading);

        qdbManger=new QDBManger(this);
        list=qdbManger.finderror(usernmae);
        if (list!=null){
            loading.setVisibility(View.INVISIBLE);
            Log.d("list",list.size()+"");
            for (int i=0;i<list.size();i++){
                Log.d("list",list.get(i).getId()+"");
            }
            QuestionAdapter questionAdapter=new QuestionAdapter(this,R.layout.wrong_item,list);
            w_LV.setAdapter(questionAdapter);
        }
        w_LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("你好！");
                Question question=list.get(position);
                qdbManger.deleterror(question.getId());
                list.remove(position);
                QuestionAdapter questionAdapter=new QuestionAdapter(wrnglists.this,R.layout.wrong_item,list);
                w_LV.setAdapter(questionAdapter);
            }
        });

    }
    public void closeWl(View view){
        finish();
    }
}
