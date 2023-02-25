package com.example.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    WebView web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        web=findViewById(R.id.webView);

        web.setWebViewClient(new WebViewClient());

        web.getSettings().setJavaScriptEnabled(true);
        String url = "https://www.pawoon.com/";
        web.loadUrl(url);
        web.setWebViewClient(new WebViewClient(){
            @Override
            public boolean onRenderProcessGone(WebView view, RenderProcessGoneDetail detail) {
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                getSupportActionBar().setTitle(view.getTitle());
                super.onPageFinished(view, url);
            }
        });


    }
}