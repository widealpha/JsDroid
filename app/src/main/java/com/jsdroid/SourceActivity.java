package com.jsdroid;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jsdroid.util.DataUtil;

public class SourceActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO 文本懒加载，源代码格式化
        setContentView(R.layout.activity_source);
        textView = findViewById(R.id.sourceCode);
        textView.setText(DataUtil.sourceCode);
        textView.setMovementMethod(new ScrollingMovementMethod());
    }
}
