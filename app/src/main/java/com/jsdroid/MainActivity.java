package com.jsdroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.textfield.TextInputLayout;
import com.jsdroid.bean.SourceCode;
import com.jsdroid.util.DataUtil;

public class MainActivity extends AppCompatActivity {
    BottomSheetBehavior bottomSheetBehavior;
    ImageButton back;
    ImageButton next;
    ImageButton more;
    ImageButton source;
    ImageButton refresh;
    WebView webView;
    EditText urlEdit;
    TextInputLayout urlInput;

    SourceCode sourceCode = new SourceCode("","");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.web_view);
        urlEdit = findViewById(R.id.url_edit);
        urlInput = findViewById(R.id.url_input);
        refresh = findViewById(R.id.refresh);
        back = findViewById(R.id.backward);
        next = findViewById(R.id.forward);
        source = findViewById(R.id.source);
        more = findViewById(R.id.more);
        bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.nested_scroll_view));

//        urlInput.setStartIconDrawable(R.drawable.ic_menu_black_24dp);
        urlInput.setEndIconDrawable(R.drawable.ic_send_black_24dp);
        urlInput.setEndIconVisible(true);

        initWebView();

        addListeners();
        webView.loadUrl("https://www.baidu.com");
        urlEdit.setText("https://www.baidu.com");
    }

    void initWebView(){
        webView.getSettings().setJavaScriptEnabled(true);//让WebView支持JavaScript脚本
        webView.setWebViewClient(new SourceWebClient());//在WebView上显示网页
        webView.addJavascriptInterface(new SourceWebClient.SourceCodeJavaScript(), "source");
        webView.canGoBackOrForward(10);
    }

    void addListeners(){
        urlEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
                    DataUtil.sourceCode = "";
                    webView.loadUrl(urlEdit.getText().toString());
                }
                return true;
            }
        });
        urlInput.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataUtil.sourceCode = "";
                webView.loadUrl(urlEdit.getText().toString());
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webView.canGoBack()){
                    DataUtil.sourceCode = "";
                    webView.goBack();
                } else {
                    Toast.makeText(MainActivity.this,"已经是第一页",Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webView.canGoForward()){
                    DataUtil.sourceCode = "";
                    webView.goForward();
                } else {
                    Toast.makeText(MainActivity.this,"已经最后第一页",Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED){
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED){
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.reload();
            }
        });
        source.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DataUtil.sourceCode != null && DataUtil.sourceCode.length() > 0){
                    Intent intent = new Intent(MainActivity.this, SourceActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this,"请等待网页加载完成",Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }
}
