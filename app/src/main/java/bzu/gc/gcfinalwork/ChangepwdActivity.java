package bzu.gc.gcfinalwork;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import bzu.gc.gcfinalwork.db.DBManger;

public class ChangepwdActivity extends AppCompatActivity {
    private EditText oldpwdET;
    private EditText newpwdET;
    private EditText renewpwdET;
    private String old;
    private String newer;
    private String renew;
    private String username;
    private String password;
    private DBManger dbManger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepwd);
        //去除标题栏
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        init();
        dbManger=new DBManger(this);
        Intent intent=getIntent();
        username=intent.getStringExtra("username");
        password=intent.getStringExtra("password");
        System.out.println("username"+username);
        System.out.println("password"+password);
    }

    private void init(){
        oldpwdET=findViewById(R.id.oldpwdET);
        newpwdET=findViewById(R.id.newpwdET);
        renewpwdET=findViewById(R.id.renewpwdET);


    }

    public void ClickToMine(View view){
        finish();
    }

    public void changepwdsub(View view){
        old=oldpwdET.getText().toString();
        newer=newpwdET.getText().toString();
        renew=renewpwdET.getText().toString();
        System.out.println("old:"+old);
        System.out.println("newer:"+newer);
        System.out.println("renew"+renew);
        if(!password.equals(old)){
            Toast.makeText(ChangepwdActivity.this,"密码输入错误",Toast.LENGTH_SHORT).show();
        }else {
            if (!newer.equals(renew)){
                Toast.makeText(ChangepwdActivity.this,"两次密码输入不一致",Toast.LENGTH_SHORT).show();
            }
            else {
                dbManger.updatepassword(username,newer);
                Toast.makeText(ChangepwdActivity.this,"修改成功！",Toast.LENGTH_SHORT).show();
                finish();
            }
        }

    }
}
