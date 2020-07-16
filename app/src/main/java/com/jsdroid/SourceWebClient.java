package com.jsdroid;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jsdroid.bean.SourceCode;
import com.jsdroid.util.DataUtil;

public class SourceWebClient extends WebViewClient {
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        view.loadUrl("javascript:window.source.showSource('<head>'+" +
                "document.getElementsByTagName('html')[0].innerHTML+'</head>');");
        super.onPageFinished(view, url);
    }

    static final class SourceCodeJavaScript {
        @android.webkit.JavascriptInterface
        public void showSource(String html) {
            DataUtil.sourceCode = html;
        }
    }

}

