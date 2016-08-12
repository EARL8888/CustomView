package com.earl.customview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.earl.customview.fragment.AnimationFramentA;


public class InterFaceTest extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inter_face_test);
        new MainActivity().setListener(new MainActivity.getData() {
            @Override
            public void setData(String name) {
                Toast.makeText(InterFaceTest.this, name, Toast.LENGTH_LONG).show();
            }
        });
       getSupportFragmentManager().beginTransaction().replace(R.id.ll_inter_face,new AnimationFramentA(),null).commit();
    }
}

