package com.imooc.lc.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.imooc.lc.IMyAidlInterface;

public class MyService extends Service {

    private int i;

    public MyService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("TAG", "服务创建了");
        //开启一个线程，模拟耗时任务
        new Thread() {
            @Override
            public void run() {
                super.run();
                for (i = 1; i < 100; i++) {
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("TAG", "服务启动了");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.e("TAG", "服务绑定了");
//        throw new UnsupportedOperationException("Not yet implemented");
        //方法一：利用binder进行进度监控
//        return new MyBinder();
        //方法二：利用aidl进行进度监控（实际上该接口继承了binder）
        return new IMyAidlInterface.Stub() {
            @Override
            public void showProgress() throws RemoteException {
                Log.e("TAG","当前的进度是" + i);
            }
        };
    }

    public class MyBinder extends Binder {
        public int getProcess() {
            return i;
        }
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("TAG", "服务解绑了");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.e("TAG", "服务销毁了");
        super.onDestroy();
    }
}