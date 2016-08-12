package com.earl.customview;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.earl.customview.view.ClockView;
import com.earl.customview.view.CustomSwitchView;

public class MainActivity extends AppCompatActivity {
    private getData getData;
    private String name = "test";
    private ClockView mClock;
    private Handler mHandler;
    private CustomSwitchView buttonCSView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, InterFaceTest.class);
        //        startActivity(intent);

        //以下是钟表实现
        //        lock();
                listenerMother();
    }

    private void lock() {
        mClock = new ClockView(this);
        mHandler = new Handler();
        mHandler.post(updateTime);
        setContentView(mClock);
    }

    private void listenerMother() {
        buttonCSView = (CustomSwitchView) findViewById(R.id.csv_button);
        // 绑定监听事件
        buttonCSView.setOnSwitchStateUpdateListener(new CustomSwitchView.OnSwitchStateUpdateListener() {

            @Override
            public void onStateUpdate(boolean state) {
                if (state) {
                    Toast.makeText(MainActivity.this, "打开", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "关闭", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private Runnable updateTime = new Runnable() {
        public void run() {
            mClock.refreshClock();
            mHandler.postDelayed(updateTime, 1000);
        }
    };

    public interface getData {
        void setData(String name);
    }

    public void setListener(getData getData) {
        this.getData = getData;
        getData.setData(name);
    }
}
