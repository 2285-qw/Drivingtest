package bzu.gc.gcfinalworkhuihaoda;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import bzu.gc.gcfinalworkhuihaoda.Util.StaticClass;
import bzu.gc.gcfinalworkhuihaoda.Util.shareUtils;
import bzu.gc.gcfinalworkhuihaoda.ui.PrivacyActivity;

public class startpage extends AppCompatActivity {
    private static final long dalay = 3000;
    private TimerTask Task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startpage);

        //去除标题栏
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        if(isFirst()){
            showSecurityDialog();
        }else {
            Timer timer = new Timer();
            TimerTask Task = new TimerTask() {

                @Override
                public void run() {
                    Intent intent = new Intent(startpage.this, MainActivity.class);
                    startActivity(intent);
                    startpage.this.finish();
                }
            };
            timer.schedule(Task, dalay);
        }


    }


    //判断程序是否第一次运行
    private boolean isFirst() {
        Boolean isFirst = shareUtils.getBoolean(this, StaticClass.SPLASH_IS_FIRST, true);
        if (isFirst) {
            //是第一次运行
            return true;
        } else {
            return false;
        }
    }

    private void showSecurityDialog() {
        //TODO 显示提醒对话框
        final Dialog securityDialog = new Dialog(this);
        securityDialog.setCancelable(false);//返回键也会屏蔽
        securityDialog.setCanceledOnTouchOutside(false);
        View view = View.inflate(this, R.layout.dialog_activity_sercurity, null);
        TextView tv_msg = view.findViewById(R.id.sercurity_tv_msgnotice);
        TextView tv_cancel = view.findViewById(R.id.sercurity_tv_cancel);
        TextView tv_positive = view.findViewById(R.id.sercurity_tv_positive);

        SpannableStringBuilder spannable = new SpannableStringBuilder(tv_msg.getText());
        spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#C89C3C")), 100, 106, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_msg.setMovementMethod(LinkMovementMethod.getInstance());
        spannable.setSpan(new TextClick(), 100, 106, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_msg.setText(spannable);

        tv_msg.setHighlightColor(Color.parseColor("#00ffffff"));

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                securityDialog.dismiss();
                shareUtils.putBoolean(startpage.this,StaticClass.SPLASH_IS_FIRST,true);
                finish();
            }
        });
        tv_positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                securityDialog.dismiss();
                //TODO 进入主界面
                shareUtils.putBoolean(startpage.this,StaticClass.SPLASH_IS_FIRST,false);
                startActivity(new Intent(startpage.this,MainActivity.class));
            }
        });

        securityDialog.setContentView(view);
        securityDialog.show();
    }

    private class TextClick extends ClickableSpan {
        @Override
        public void onClick(View widget) { //在此处理点击事件
            startActivity(new Intent(startpage.this, PrivacyActivity.class));
            //Log.e("eeee_click", "点击");
            //TODO 点击事件处理
        }

        @Override
        public void updateDrawState(@NonNull TextPaint ds) {
            ds.setColor(Color.parseColor("#C89C3C"));
        }
    }
}
