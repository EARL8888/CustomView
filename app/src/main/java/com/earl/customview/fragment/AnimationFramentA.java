package com.earl.customview.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;

import com.earl.customview.R;

/**
 * 作者：earl on 16/08/08 00:09
 * 邮箱：z604458675@gmail.com
 * 描述：CustomView
 */
public class AnimationFramentA extends Fragment {
    public AnimationFramentA() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout rootView = (LinearLayout) inflater.inflate(R.layout.animation_fragment, container, false);
        ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1);
        sa.setDuration(1000);
        LayoutAnimationController lac = new LayoutAnimationController(sa, 0.5f);
        rootView.setLayoutAnimation(lac);
        rootView.findViewById(R.id.button15).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().add(R.id.ll_fragment_a, new ClockFrament(), null).commit();
            }
        });
        return rootView;
    }
}
