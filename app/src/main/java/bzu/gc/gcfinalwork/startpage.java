package bzu.gc.gcfinalwork;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

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
