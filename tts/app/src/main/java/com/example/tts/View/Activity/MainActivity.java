package com.example.tts.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.tts.R;
import com.example.tts.View.widget.PickView.ScrollPickerAdapter;
import com.example.tts.databinding.ActivityMainBinding;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setKeyBoardHeightListener();
        binding.speakerBtn.setOnClickListener(v -> {
            showSpeakerSelectDialog();
        });
        binding.speedBtn.setOnClickListener(v -> {
            showSpeedPickerDialog();
        });
    }

    public void showMenuDialog(){

    }

    public void showPolyphonicDialog(){

    }

    public void showPauseAdjustDialog(){

    }

    public void showInsertSilenceDialog(){

    }

    public void showSpeakerSelectDialog(){

    }

    public void showSpeedPickerDialog(){
        List<Float> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            float value = i * 0.1f;
            list.add(value);
        }

        ScrollPickerAdapter.ScrollPickerAdapterBuilder<Float> builder =
                new ScrollPickerAdapter.ScrollPickerAdapterBuilder<Float>(this)
                        .setDataList(list)
                        .selectedItemOffset(2)
                        .visibleItemNumber(5)
                        .setDivideLineColor("#ED5275")
                        .setOnClickListener(v -> {
                            Float myData = (Float) v.getTag();
                            if (myData != null) {
                            }
                        });
        ScrollPickerAdapter mScrollPickerAdapter = builder.build();
    }

    public void setKeyBoardHeightListener(){
        final Context context = getApplicationContext();
        final RelativeLayout parentLayout = findViewById(R.id.parent);
        final View myLayout = getWindow().getDecorView();
        parentLayout.getViewTreeObserver(). addOnGlobalLayoutListener(() -> {
            Rect r = new Rect();
            // r will be populated with the coordinates of your view that area still visible.
            parentLayout.getWindowVisibleDisplayFrame(r);
            int screenHeight = myLayout.getRootView().getHeight();
            int heightDiff = screenHeight - (r.bottom - r.top);
            int statusBarHeight = 0;
            try {
                Class<?> c = Class.forName("com.android.internal.R$dimen");
                Object obj = c.newInstance();
                Field field = c.getField("status_bar_height");
                int x = Integer.parseInt(field.get(obj).toString());
                statusBarHeight = context.getResources().getDimensionPixelSize(x);
            } catch (Exception e) {
                e.printStackTrace();
            }
            int realKeyboardHeight = heightDiff - statusBarHeight;
            Log.i(TAG,"keyboard height = " + realKeyboardHeight);
        });
    }
}