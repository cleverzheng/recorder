package com.example.forcezheng.android_qiniu_up_down_load.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.forcezheng.android_qiniu_up_down_load.R;

/**
 * @author zhengwang
 * @email zhengwang043@foxmail.com
 * @date 2016/11/2
 */
public class RoundProgressBar extends View {
    private int roundColor;
    private int roundFirstProgressColor;
    private int roundSecondProgressColor;
    private float roundWidth;
    private Paint paint;

    private float radius;

    private float max;
    private float progress;
    //    private TimeHandler handler;
//    private MyTimerTask task;
    //    private Timer timer;
//    private Handler mHandler = new Handler() {
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case 1:
//                    setProgress((int) (getProgress() + (radius * Math.PI) / 10));
//                    break;
//            }
//        }
//    };

    public RoundProgressBar(Context context) {
        super(context);
    }

    public RoundProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initProgress(context, attrs);
    }

    public RoundProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initProgress(context, attrs);
    }

    private void initProgress(Context context, AttributeSet attrs) {
        paint = new Paint();

        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundProgressBar);

        roundColor = mTypedArray.getColor(R.styleable.RoundProgressBar_roundColor, Color.GRAY);
        roundFirstProgressColor = mTypedArray.getColor(R.styleable.RoundProgressBar_roundFirstProgressColor, Color.GREEN);
        roundSecondProgressColor = mTypedArray.getColor(R.styleable.RoundProgressBar_roundSecondProgressColor, Color.GREEN);

//        textColor = mTypedArray.getColor(R.styleable.RoundProgressBar_textColor, Color.GREEN);
//        textSize = mTypedArray.getDimension(R.styleable.RoundProgressBar_textSize, 15);
        roundWidth = mTypedArray.getDimension(R.styleable.RoundProgressBar_roundWidth, 28);
//        radius = mTypedArray.getFloat(R.styleable.RoundProgressBar_radius, 100);
        max = mTypedArray.getInteger(R.styleable.RoundProgressBar_max, (int) (306 * Math.PI * 2));
//        max = mTypedArray.getInteger(R.styleable.RoundProgressBar_max, 100);
//        textIsDisplayable = mTypedArray.getBoolean(R.styleable.RoundProgressBar_textIsDisplayable, true);
//        style = mTypedArray.getInt(R.styleable.RoundProgressBar_style, 0);

        mTypedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        // 画最外层背景圆环
        float width = getWidth();
        float centre = getWidth() / 2; //获取圆心的x坐标
        radius = centre - roundWidth / 2; //圆环的半径
        paint.setColor(roundColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(roundWidth); //设置圆环的宽度
        paint.setAntiAlias(true);
        canvas.drawCircle(centre, centre, radius, paint);

        //画第一个进度
        paint.setStrokeWidth(roundWidth);
        paint.setColor(roundFirstProgressColor);
        RectF oval = new RectF(centre - radius, centre - radius, centre + radius, centre + radius);  //用于定义的圆弧的形状和大小的界限
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(oval, -90, 360 * progress / max, false, paint);  //根据进度画圆弧
    }


    public void setRoundColor(int roundColor) {
        this.roundColor = roundColor;
    }

    private int getRoundColor() {
        return roundColor;
    }

    private void setRoundFirstProgressColor(int roundFirstProgressColor) {
        this.roundFirstProgressColor = roundFirstProgressColor;
    }

    public int getRoundFirstProgressColor() {
        return roundFirstProgressColor;
    }


    public void setRoundWidth(float roundWidth) {
        this.roundWidth = roundWidth;
    }

    public float getRoundWith() {
        return roundWidth;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public float getMax() {
        return max;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getRadius() {
        return radius;
    }

    /**
     * 获取进度.需要同步
     *
     * @return
     */
    public synchronized double getProgress() {
        return progress;
    }

    /**
     * 设置进度，此为线程安全控件，由于考虑多线的问题，需要同步
     * 刷新界面调用postInvalidate()能在非UI线程刷新
     *
     * @param progress
     */
    public synchronized void setProgress(float progress) {
        if (progress < 0) {
            throw new IllegalArgumentException("progress not less than 0");
        }
        if (progress > max) {
            progress = max;
        }
        if (progress <= max) {
            this.progress = progress;
            postInvalidate();
        }

    }

    public void refresh() {
//        setProgress((float) (getProgress() + 0.1));
        setProgress((float) (getProgress() + (radius * Math.PI) / 600));
    }


//    private class TimeHandler extends Handler {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            if (msg.what == 1) {
//                setProgress((int) (getProgress() + (radius * Math.PI) / 10));
//            }
//        }
//    }

//    private class MyTimerTask extends TimerTask {
//
//        @Override
//        public void run() {
//            Message message = new Message();
//            message.what = 1;
//            handler.sendMessage(message);
//        }
//    }

//    private Handler handler;
//    private MyHandler handler2;
//    private MyHandler1 handler1;
//
//    public void refresh(boolean isStop) {
//
//
////            timer.schedule(new MyTimerTask(), 0, 100);
////            float radius = getRadius();
////            setMax((float) (2 * getRadius() * Math.PI));
//        delayStart(isStop);
//    }
//
//    private void delayStart(final boolean isStop) {
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Message msg = new Message();
//                msg.what = 1;
//                if (isStop){
//                    handler2 = null;
//                } else{
//                    if (handler2 == null) {
//                        handler2 = new MyHandler();
//
//                    }
//                    handler2.sendMessage(msg);
//                }
//            }
//        }, 100);
////        if (handler1 == null) {
////            handler1 = new MyHandler1();
////        }
////        handler1.postDelayed(new Runnable() {
////            @Override
////            public void run() {
////                Message msg = new Message();
////                msg.what = 1;
////                if (handler2 == null) {
////                    handler2 = new MyHandler();
////                }
////                if (isStop) {
////                    handler1 = null;
////                    handler2 = null;
////                } else {
////                    handler2.sendMessage(msg);
////                }
////            }
////        }, 100);
////        new Handler().postDelayed(new Runnable() {
////            @Override
////            public void run() {
////                Message msg = new Message();
////                msg.what = 1;
////                MyHandler handler = new MyHandler();
////                handler.sendMessage(msg);
////            }
////        }, 100);
//    }
//
//    class MyHandler1 extends Handler {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//        }
//    }
//
//    class MyHandler extends Handler {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            if (msg.what == 1) {
//                setProgress((float) (getProgress() + (radius * Math.PI) / 600));
////                setProgress((getProgress() + 100 / 12));
//
//                delayStart(false);
//
//            }
//        }
//    }
}
