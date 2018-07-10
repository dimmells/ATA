package com.dimmells.ata;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;

/**
 * Created by dimic on 01.02.2018.
 */

public class Test_multitouch extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int pointIndex = event.getActionIndex();


        return super.onTouchEvent(event);
    }
}
