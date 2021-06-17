package com.imooc.lc.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.imooc.lc.R;

/**
 * @类名: LuckyPan
 * @描述:
 * @作者: huangchao
 * @时间: 2018/6/19 下午2:29
 * @版本: 1.0.0
 */
public class LuckyPan extends SurfaceView implements SurfaceHolder.Callback,Runnable{
    //获得canvas
    private SurfaceHolder mHolder;

    private Canvas mCanvas;
    //用户绘制的线程
    private Thread mThread;
    //线程的控制开关
    private boolean isRunning;
    //奖池文字
    private String[] mStrs = new String[]{"单反相机","IPAD","恭喜发财","IPHONE","妹纸","恭喜发财"};
    //奖池图片
    private int[] mImgs = new int[]{R.mipmap.p_danfan,R.mipmap.p_ipad,R.mipmap.p_xiaolian,
            R.mipmap.p_iphone,R.mipmap.p_meizhi,R.mipmap.p_xiaolian};
    //奖项背景色
    private int[] mColors = new int[]{0xFFFFC300,0xFFF17E01,0xFFFFC300,
            0xFFF17E01,0xFFFFC300,0xFFF17E01};
    //奖池的数量
    private int mItemCount = 6;
    //与奖池图片对应的Bitmap
    private Bitmap[] mImgsBitmap;
    //整个盘块的范围
    private RectF  mRange = new RectF();
    //整个盘块的直径
    private int mRadius;
    //绘制盘块的画笔
    private Paint mArcPaint;
    //绘制文本的画笔
    private Paint mTextPaint;
    //盘块滚动速度
    private double mSpeed;
    //盘块起始角度（volatile保证线程间变量的可见性）
    private volatile float mStartAngle = 0;
    //判断是否点击了停止按钮
    private boolean isShouldEnd;
    //盘块的中心位置
    private int mCenter;
    //padding值
    private int mPadding;
    //设置盘块背景
    private Bitmap mBgBitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.bg2);
    //设置字体大小
    private float mTextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,20,getResources().getDisplayMetrics());

    public LuckyPan(Context context) {
        super(context, null);
    }

    public LuckyPan(Context context, AttributeSet attrs) {
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
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = Math.min(getMeasuredWidth(),getMeasuredHeight());
        mPadding = getPaddingLeft();
        //直径
        mRadius = width - mPadding*2;
        //中心点
        mCenter = width/2;
        //设置view的区域
        setMeasuredDimension(width,width);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //初始化绘制盘块的画笔
        mArcPaint = new Paint();
        mArcPaint.setAntiAlias(true);
        mArcPaint.setDither(true);

        //初始化绘制盘块的画笔
        mTextPaint = new Paint();
        mTextPaint.setColor(0xFFFFFFFF);
        mTextPaint.setTextSize(mTextSize);

        //初始化盘块的范围(左上右下)
        mRange = new RectF(mPadding,mPadding,mPadding + mRadius,mPadding + mRadius);

        //初始化图片
        mImgsBitmap = new Bitmap[mItemCount];
        for (int i = 0; i < mItemCount; i++){
            mImgsBitmap[i] = BitmapFactory.decodeResource(getResources(),mImgs[i]);
        }

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
            long start = System.currentTimeMillis();
            draw();
            long end = System.currentTimeMillis();
            //保证绘制时间不小于50ms
            if (end - start < 50){
                try {
                    Thread.sleep(50-(start-end));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void draw() {
        try {
            mCanvas = mHolder.lockCanvas();
            if (mCanvas != null){
                //绘制背景
                drawBg();
                //绘制盘块
                float tmpAngle = mStartAngle;
                //每个盘块的角度
                float sweepAngle = 360 / mItemCount;
                for (int i = 0; i < mItemCount; i++){
                    mArcPaint.setColor(mColors[i]);
                    //绘制盘块背景
                    mCanvas.drawArc(mRange,tmpAngle,sweepAngle,true,mArcPaint);
                    //绘制盘块文字
                    drawText(tmpAngle,sweepAngle,mStrs[i]);
                    //绘制盘块奖品
                    drawIcon(tmpAngle,mImgsBitmap[i]);
                    tmpAngle += sweepAngle;
                }
                mStartAngle += mSpeed;
                //如果点击了停止按钮
                if (isShouldEnd){
                    mSpeed -= 1;
                }
                if (mSpeed <= 0){
                    mSpeed = 0;
                    isShouldEnd = false;
                }
            }
        } catch (Exception e){
        } finally {
            //释放mCanvas
            if (mCanvas != null){
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        }

    }

    /**
     * 点击启动按钮
     */
    public void luckyStart(int index){
        //每个奖项的角度
        float angle = 360/mItemCount;
        //当前index奖项中奖范围
        //1：150-210
        //0：210-270
        float from = 270 -(index+1)*angle;
        float end = from + angle;
        //设置停下来需要停下的距离(区间)
        float targetFrom = 4*360 + from;
        float targetEnd = 4*360 + end;
        //速度由有到零是个递减的等差数列
        //根据等差数列公式 （v1+0）*(v1+1)/2 = targetFrom
        float v1 = (float) ((-1+Math.sqrt(1+8*targetFrom))/2);
        float v2 = (float) ((-1+Math.sqrt(1+8*targetEnd))/2);
        mSpeed = v1+Math.random()*(v2-v1);
        isShouldEnd = false;
    }

    /**
     * 点击停止按钮
     */
    public void luckyEnd(){
        mStartAngle = 0;
        isShouldEnd = true;
    }

    /**
     * 转盘是否在旋转
     * @return
     */
    public boolean isStart(){
        return mSpeed != 0;
    }

    public boolean isShouldEnd(){
        return isShouldEnd;
    }

    /**
     * 绘制每个盘块的奖品图
     * @param tmpAngle
     * @param bitmap
     */
    private void drawIcon(float tmpAngle, Bitmap bitmap) {
        //图片的宽高为直径的1/8
        int imgWidth = mRadius/8;
        //每个盘块幅度的一半的弧度
        float angle = (float) ((tmpAngle+360/mItemCount/2)*Math.PI/180);
        //图片中心点的坐标
        int x = (int) (mCenter+mRadius/2/2*Math.cos(angle));
        int y = (int) (mCenter+mRadius/2/2*Math.sin(angle));
        //确定图片的位置
        Rect rect = new Rect(x-imgWidth/2,y-imgWidth/2,x+imgWidth/2,y+imgWidth/2);
        mCanvas.drawBitmap(bitmap,null,rect,null);
    }

    /**
     *  绘制每个盘块的文本
     * @param tmpAngle
     * @param sweepAngle
     * @param mStr
     */
    private void drawText(float tmpAngle, float sweepAngle, String mStr) {
        Path path = new Path();
        path.addArc(mRange,tmpAngle,sweepAngle);
        //利用水平和垂直偏移量让文字居中
        float textWidth = mTextPaint.measureText(mStr);
        int hOffset = (int) (mRadius*Math.PI/mItemCount/2-textWidth/2);
        int vOffset = mRadius/2/6;
        mCanvas.drawTextOnPath(mStr,path,hOffset,vOffset,mTextPaint);
    }

    /**
     * 绘制背景
     */
    private void drawBg() {
        mCanvas.drawColor(0xFFFFFFFF);
        mCanvas.drawBitmap(mBgBitmap,null,new RectF(mPadding/2,mPadding/2,
                getMeasuredWidth()-mPadding/2,getMeasuredHeight()-mPadding/2),null);
    }
}
