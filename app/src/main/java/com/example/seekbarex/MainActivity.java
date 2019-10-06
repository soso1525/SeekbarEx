package com.example.seekbarex;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.seekbarex.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();


    private ActivityMainBinding binding;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        tv = new TextView(this);
        tv.setId(View.generateViewId());
        tv.setGravity(Gravity.CENTER);
        tv.setBackgroundResource(R.drawable.seekbar_noti);
        tv.setIncludeFontPadding(false);

        binding.progressbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                Rect rect = seekBar.getThumb().getBounds();

                Log.e(TAG, String.valueOf(-90 + progress));
                Log.e(TAG, rect.left + ", " + rect.top);

                tv.setText((-90 + progress) + "dB");

                binding.mainLayout.removeView(tv);
                binding.mainLayout.addView(tv, 0);
                ConstraintSet set = new ConstraintSet();
                set.clone(binding.mainLayout);
                set.connect(tv.getId(), ConstraintSet.BOTTOM, binding.progressbar.getId(), ConstraintSet.TOP, 30);
                set.connect(tv.getId(), ConstraintSet.START, binding.progressbar.getId(), ConstraintSet.START, rect.left);
                set.applyTo(binding.mainLayout);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
//                binding.mainLayout.removeView(tv);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
//                binding.mainLayout.removeView(tv);
            }
        });

    }
}
