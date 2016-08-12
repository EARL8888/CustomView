package com.earl.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.format.Time;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作者：earl on 16/08/08 10:03
 * 邮箱：z604458675@gmail.com
 * 描述：CustomView
 */
public class ClockView extends View {
    private int mHeight, mWidth;
    private Time mTime;

    public ClockView(Context context) {
        super(context);
    }

    public ClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ClockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 获取宽高参数
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        // 画外圆
        Paint paintCircle = new Paint();
        paintCircle.setStyle(Paint.Style.STROKE);
        paintCircle.setAntiAlias(true);
        paintCircle.setStrokeWidth(5);
        canvas.drawCircle(mWidth / 2,
                mHeight / 2, mWidth / 2, paintCircle);
        // 画刻度
        Paint painDegree = new Paint();
        paintCircle.setStrokeWidth(3);
        for (int i = 0; i < 24; i++) {
            // 区分整点与非整点
            if (i == 0 || i == 6 || i == 12 || i == 18) {
                painDegree.setStrokeWidth(5);
                painDegree.setTextSize(30);
                canvas.drawLine(mWidth / 2, mHeight / 2 - mWidth / 2,
                        mWidth / 2, mHeight / 2 - mWidth / 2 + 60,
                        painDegree);
                String degree = String.valueOf(i);
                canvas.drawText(degree,
                        mWidth / 2 - painDegree.measureText(degree) / 2,
                        mHeight / 2 - mWidth / 2 + 90,
                        painDegree);
            } else {
                painDegree.setStrokeWidth(3);
                painDegree.setTextSize(15);
                canvas.drawLine(mWidth / 2, mHeight / 2 - mWidth / 2,
                        mWidth / 2, mHeight / 2 - mWidth / 2 + 30,
                        painDegree);
                String degree = String.valueOf(i);
                canvas.drawText(degree,
                        mWidth / 2 - painDegree.measureText(degree) / 2,
                        mHeight / 2 - mWidth / 2 + 60,
                        painDegree);
            }
            // 通过旋转画布简化坐标运算
            canvas.rotate(15, mWidth / 2, mHeight / 2);
        }
        // 画圆心
        Paint paintPointer = new Paint();
        paintPointer.setStrokeWidth(30);
        canvas.drawPoint(mWidth / 2, mHeight / 2, paintPointer);
        // 当前时间
        mTime = new Time();
        mTime.setToNow();

        // 当前时间对应的角度
        float secRot = mTime.second * 6;
        float minRot = mTime.minute * 6 + 6 * mTime.second / 60F;
        float hrRot = (mTime.hour % 12) * 30 + 30 * minRot / 360F;

        // 画指针
        Paint paintHour = new Paint();
        paintHour.setStrokeWidth(20);
        Paint paintMinute = new Paint();
        paintMinute.setStrokeWidth(10);
        Paint paintSecond = new Paint();
        paintSecond.setStrokeWidth(5);

        canvas.save();

        // 时针
        canvas.rotate(hrRot, mWidth / 2, mHeight / 2);
        canvas.drawLine(mWidth / 2, mHeight / 2, mWidth / 2, mHeight / 2 - mWidth / 2 + 320,
                paintHour);
        canvas.rotate(-hrRot, mWidth / 2, mHeight / 2);

        // 分针
        canvas.rotate(minRot, mWidth / 2, mHeight / 2);
        canvas.drawLine(mWidth / 2, mHeight / 2, mWidth / 2, mHeight / 2 - mWidth / 2 + 230,
                paintMinute);
        canvas.rotate(-minRot, mWidth / 2, mHeight / 2);

        // 秒针
        canvas.rotate(secRot, mWidth / 2, mHeight / 2);
        canvas.drawLine(mWidth / 2, mHeight / 2, mWidth / 2, mHeight / 2 - mWidth / 2 + 150,
                paintSecond);
        canvas.rotate(-secRot, mWidth / 2, mHeight / 2);

        canvas.restore();
    }

    public void refreshClock() {
        postInvalidate();
    }
}
