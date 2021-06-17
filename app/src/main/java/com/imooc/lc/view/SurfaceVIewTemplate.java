package com.imooc.lc.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * @类名: SurfaceVIewTemplate
 * @描述:
 * @作者: huangchao
 * @时间: 2018/6/19 下午2:01
 * @版本: 1.0.0
 */
public class SurfaceVIewTemplate extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    //获得canvas
    private SurfaceHolder mHolder;

    private Canvas mCanvas;

    //用户绘制的线程
    private Thread mThread;

    //线程的控制开关
    private boolean isRunning;


    public SurfaceVIewTemplate(Context context) {
        super(context, null);
    }

    public SurfaceVIewTemplate(Context context, AttributeSet attrs) {
        super(context, attrs);
        mHolder = getHolder();
        mHolder.addCallback(this);
        //设置可获得焦点
        setFocusable(true);
        setFocusableInTouchMode(true);
        //设置常量
        setKeepScreenOn(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isRunning = true;
        mThread = new Thread(this);
        mThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isRunning = false;
    }

    @Override
    public void run() {
        //不断进行绘制
        while (isRunning){
            draw();
        }
    }

    private void draw() {
        try {
            mCanvas = mHolder.lockCanvas();
            if (mCanvas != null){

            }
        } catch (Exception e){
        } finally {
            //释放mCanvas
            if (mCanvas != null){
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        }

    }
}
