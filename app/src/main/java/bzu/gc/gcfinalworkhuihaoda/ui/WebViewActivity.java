package bzu.gc.gcfinalworkhuihaoda.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;

import bzu.gc.gcfinalworkhuihaoda.R;

public class WebViewActivity extends AppCompatActivity {
    private BaseWebView webView;
    private ProgressBar progressBar;

    public static void openActivity(Context context, String url) {
        Log.d("URL",url);

        Intent intent = new Intent(context, WebViewActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String url = getIntent().getStringExtra("url");
        setContentView(R.layout.activity_web_view);

        webView = findViewById(R.id.web_view);
        progressBar = findViewById(R.id.pb_web_base);

        webView.setProgressBar(progressBar);
        webView.loadUrl(url);

    }
}