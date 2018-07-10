package com.dimmells.ata;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by dimic on 02.02.2018.
 */

public class Test extends Activity implements View.OnClickListener{
    Button pixel = null;
    Button vibration = null;
    private static long back_pressed = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        pixel = (Button) findViewById(R.id.pixel);
        pixel.setOnClickListener(this);
        pixel.setText("Тест на наявність битих пікселів");
        vibration = (Button) findViewById(R.id.vibration);
        vibration.setOnClickListener(this);
        vibration.setText("Тест на вібрацію");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.pixel:
                startActivity(new Intent(this, Test_pixel.class));
                break;
            case R.id.vibration:
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(5000);
                start();
        }
    }

    protected void start()
    {
        CountDownTimer countDownTimer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long l) {
            }
            @Override
            public void onFinish() {
                Context context = getApplicationContext();
                CharSequence text = "Тест завершено";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }.start();
    }

    @Override
    public void onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis())
            finish();
        else
            Toast.makeText(getBaseContext(), "Натисніть ще раз для виходу",
                    Toast.LENGTH_SHORT).show();
        back_pressed = System.currentTimeMillis();
    }
}
