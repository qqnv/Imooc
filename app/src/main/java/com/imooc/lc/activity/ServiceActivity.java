package com.imooc.lc.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.imooc.lc.IMyAidlInterface;
import com.imooc.lc.R;
import com.imooc.lc.service.MyService;

import androidx.appcompat.app.AppCompatActivity;

public class ServiceActivity extends AppCompatActivity {

    //IBinder和ServiceConnection
    //进度监控
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
//            方法一：利用binder进行进度监控
//            MyService.MyBinder mb = (MyService.MyBinder) service;
//            int step = mb.getProcess();
//            Log.e("TAG","当前进度是：" + step);
            //方法二：利用aidl进行进度监控
            IMyAidlInterface imy = IMyAidlInterface.Stub.asInterface(service);
            try {
                imy.showProgress();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
    }

    public void operate(View view) {
        switch (view.getId()){
            case R.id.btn_startService:
                startService(new Intent(this, MyService.class));
                break;
            case R.id.btn_stopService:
                stopService(new Intent(this, MyService.class));
                break;
            case R.id.btn_bindService:
                bindService(new Intent(this,MyService.class),conn,BIND_AUTO_CREATE);
                break;
            case R.id.btn_unbindService:
                unbindService(conn);
                break;
        }
    }
}