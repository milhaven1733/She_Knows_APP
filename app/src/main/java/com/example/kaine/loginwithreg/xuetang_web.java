package com.example.kaine.loginwithreg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class xuetang_web extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xuetang_web);
        WebView webView=(WebView) findViewById(R.id.web_view2);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://list.youku.com/show/id_z0bcc3dd8359711e4abda.html?");
    }
}
