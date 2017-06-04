package com.github.apiaoa.bilibili;

import android.os.Bundle;
import android.widget.Toast;

import com.github.apiaoa.bilibili.framework.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this,"aa",Toast.LENGTH_LONG).show();
    }
}
