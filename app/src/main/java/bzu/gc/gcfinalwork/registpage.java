package bzu.gc.gcfinalwork;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import bzu.gc.gcfinalwork.db.DBManger;
import bzu.gc.gcfinalwork.entity.user;

public class registpage extends AppCompatActivity {
    DBManger dbManger;

    private EditText reusername;
    private EditText repsw;
    private EditText rerepsw;
    private Button registbutton;


    private String username;
    private String password;
    private String repassword;


    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registpage);
        //初始化控件
        Init();
        //初始化DBManger
        dbManger = new DBManger(this);
        //去除标题栏
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        //点击登录按钮进行登陆并且验证密码是否合法
        registbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                username = reusername.getText().toString();
                password = repsw.getText().toString();
                repassword = rerepsw.getText().toString();
                if (password.equals(repassword)) {
                    add();
                    finish();

                } else {
                    Toast.makeText(registpage.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    //初始化控件
    public void Init() {
        reusername = findViewById(R.id.registusername);
        repsw = findViewById(R.id.registpassword);
        rerepsw = findViewById(R.id.repassword);
        registbutton = findViewById(R.id.Regist);

    }

    //获取注册账号密码，转化为sql语句
    public void add() {
        user User = new user(null, username, password, 0, null, 0);
        dbManger.add(User);

    }

    //点击返回箭头返回首页
    public void ClickToLogin(View view) {
        finish();
    }


}
