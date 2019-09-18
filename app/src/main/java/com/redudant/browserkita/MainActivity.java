package com.redudant.browserkita;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    WebView webViewId;
    EditText searchId;
    Button btnSearchId;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webViewId = (WebView) findViewById(R.id.webView_id);
        searchId = (EditText) findViewById(R.id.search_id);
        btnSearchId = (Button) findViewById(R.id.btnSearch_id);
        progressBar = (ProgressBar) findViewById(R.id.progressBar_id);

        //enabling javascript
        WebSettings webSettings = webViewId.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webViewId.loadUrl("https://facebook.com");

        /*
        create new object class

        class ini akan bejalan pada halaman yang sama tanpa melempar ke halama asli url
         */
        webViewId.setWebViewClient(new ourWebViewClient());
        webViewId.setWebChromeClient(new WebChromeClient(){

            //method onProgressChange

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
               //delete  super.onProgressChanged(view, newProgress);

                progressBar.setProgress(newProgress);

                if (newProgress == 100)
                {
                    progressBar.setVisibility(View.GONE);
                }
                else
                {
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
        });


    }

    public void btnSearch(View view) {

        String editTextValue = searchId.getText().toString();

        if (editTextValue != "")
        {

        }
        if (editTextValue.contains(".") && editTextValue.contains(" ") || editTextValue.length() < 4){
            editTextValue = "https://www.google.com.np/search?q=" + searchId.getText().toString();
            webViewId.loadUrl(editTextValue);
        }

        if (editTextValue.contains(".com") || editTextValue.contains("."))
        {
            if (editTextValue.contains("http://") || editTextValue.contains("https://"))
            {
                webViewId.loadUrl(editTextValue);
            } else if (searchId.getText().toString().contentEquals("")) {
            }else {
                editTextValue = "http;//" +searchId.getText().toString();
                webViewId.loadUrl(editTextValue);
            }
        }else {
            if (searchId.getText().toString().contentEquals("")){
            } else {
                editTextValue = "https://www.google.com.np/search?q=" + searchId.getText().toString();
                webViewId.loadUrl(editTextValue);
            }
        }

        //cara sebelumnya
        /*
        if (!editTextValue.startsWith("http://")) {
            editTextValue = "http://" + editTextValue;
        }

        String url = editTextValue;
        webViewId.loadUrl(url);
        */

        //Hide the keyboard after using editText
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(searchId.getWindowToken(), 0);
    }

    //backpress
    @Override
    public void onBackPressed() {

        if (webViewId.canGoBack()) {

            webViewId.goBack();
        } else {
            super.onBackPressed();

        }
    }
}
