package com.redudant.browserkita;

import android.webkit.WebView;
import android.webkit.WebViewClient;

class ourWebViewClient extends WebViewClient {


    /*
    remove parameter WebResourceRequest request and return super.shouldOverrideUrlLoading(view, request);

    because minimum api is 15 and the new method is only valide in android N
     */
    @SuppressWarnings("deprecation")
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {

        view.loadUrl(url);
        return true;
    }
}
