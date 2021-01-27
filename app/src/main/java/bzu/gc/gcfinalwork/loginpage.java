package bzu.gc.gcfinalwork;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import bzu.gc.gcfinalwork.db.DBManger;
import bzu.gc.gcfinalwork.entity.user;

public class loginpage extends AppCompatActivity {
    private DBManger dbManger;
    private TextView usernameTV;
    private TextView passwordTV;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        dbManger = new DBManger(this);
        init();
        //去除标题栏
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


    }

    //点击返回回到主页面并结束此页面
    public void ClickToReturn(View view) {
        Intent intent = new Intent(loginpage.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    //跳转到注册页面
    public void ClickToRegist(View view) {
        Intent intent = new Intent(loginpage.this, registpage.class);
        startActivity(intent);
    }

    //登陆并返回已登录信号和账号密码
    public void LoginToReturn(View view) {

        String username = usernameTV.getText().toString();
        String password = passwordTV.getText().toString();


        user User = dbManger.selectuser(username);
        if (User.username.equals("0") && User.password.equals("0")) {
            Toast.makeText(this, "账号或密码错误", Toast.LENGTH_SHORT).show();
        } else {
            if (User.username.equals(username) && User.password.equals(password)) {
                Intent intent = new Intent(loginpage.this, MainActivity.class);
                intent.putExtra("username", User.username);
                intent.putExtra("password", User.password);
                System.out.println("sda"+User.password);
                intent.putExtra("allnum", User.allnum);
                intent.putExtra("aimnum", User.aimnum);
                intent.putExtra("errornum", User.errornum);
                intent.putExtra("islogin", 1);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "账号或密码错误", Toast.LENGTH_SHORT).show();
            }

        }


    }

    //初始化控件
    public void init() {
        usernameTV = findViewById(R.id.loginusername);
        passwordTV = findViewById(R.id.loginpassword);
    }

}
