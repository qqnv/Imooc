package com.example.dataproviderdemo;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //检测读写权限和录音权限
        PermissionUtils.verifyStoragePermissions(this);
    }
}
