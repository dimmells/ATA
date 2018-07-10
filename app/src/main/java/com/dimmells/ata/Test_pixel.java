package com.dimmells.ata;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by dimic on 31.01.2018.
 */

public class Test_pixel extends Activity implements View.OnClickListener{
    Button  button = null;
    int     click = 0;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_pixel);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        button = (Button)findViewById(R.id.test_pixel);
        button.setOnClickListener(this);
    }

    public void onClick(View view)
    {
        switch (click) {
            case 0:
                button.setBackgroundColor(Color.GREEN);
                click++;
                break;
            case 1:
                button.setBackgroundColor(Color.BLUE);
                click++;
                break;
            case 2:
                button.setBackgroundColor(Color.BLACK);
                click++;
                break;
            case 3:
                button.setBackgroundColor(Color.WHITE);
                click++;
                break;
            case 4:
                Context context = getApplicationContext();
                CharSequence text = "           Тест завершено\nДля виходу натисніть ще раз";
                int duration = 300;
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(200);
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                click++;
                break;
            case 5:
                finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
