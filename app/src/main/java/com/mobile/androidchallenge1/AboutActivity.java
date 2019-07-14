package com.mobile.androidchallenge1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.http.SslError;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.security.ProviderInstaller;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;


public class AboutActivity extends AppCompatActivity {
    WebView browser;
    SwipeRefreshLayout mySwipeRefreshLayout;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getSupportActionBar().setTitle("About ALC");

        progressDialog = ProgressDialog.show(this, "Loading...", "", true, true);
        browser = findViewById(R.id.webview);
        browser.setWebViewClient(new MyBrowser());
        mySwipeRefreshLayout = findViewById(R.id.swipeContainer);
        browser.getSettings().setLoadsImagesAutomatically(true);
        browser.getSettings().setJavaScriptEnabled(true);
        String url = "https://andela.com/alc/";
        browser.loadUrl(url);

        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        browser.reload();
                    }
                }
        );
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
            super.onReceivedSslError(view, handler, error);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            progressDialog.setMessage("Please wait...");
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(true);
            progressDialog.show();
            view.loadUrl(url);
            mySwipeRefreshLayout.setRefreshing(true);
            return true;
        }

        @Override
        public void onPageFinished(WebView browser, String url) {
            super.onPageFinished(browser, url);
            progressDialog.dismiss();
            mySwipeRefreshLayout.setRefreshing(false);
        }

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AboutActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}
