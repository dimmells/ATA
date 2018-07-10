package com.dimmells.ata;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by dimic on 01.02.2018.
 */

public class Info extends Activity implements View.OnClickListener{
    TextView info = null;
    Button cpu = null;
    Button battery = null;
    Button telef = null;
    Button screen = null;
    Button memory = null;
    private static long back_pressed = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);
        DisplayMetrics metrics = new DisplayMetrics();
        int b_height =  metrics.heightPixels;
        info = (TextView) findViewById(R.id.info);
        cpu = (Button) findViewById(R.id.cpu);
        battery = (Button) findViewById(R.id.battery);
        telef = (Button) findViewById(R.id.telef);
        screen = (Button) findViewById(R.id.screen);
        memory = (Button) findViewById(R.id.memory);

        b_height = b_height - info.getHeight() - 200;
        int height = b_height / 6;
        info.setHeight(height);
        cpu.setHeight(height);
        battery.setHeight(height);
        telef.setHeight(height);
        screen.setHeight(height);
        memory.setHeight(height);

        cpu.setOnClickListener(this);
        battery.setOnClickListener(this);
        telef.setOnClickListener(this);
        screen.setOnClickListener(this);
        memory.setOnClickListener(this);

        info.setText("");
        info.append("" + Build.MANUFACTURER + " " + Build.MODEL + "\n");
        info.append("Android: " + Build.VERSION.RELEASE + "\n");
        info.append("Рівень патчу безпеки: " + Build.VERSION.SECURITY_PATCH + "\n");
        info.append("Рівень SDK: " + Build.VERSION.SDK_INT);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.cpu:
                startActivity(new Intent(this, Cpu.class));
                break;
            case R.id.battery:
                startActivity(new Intent(this, Battery.class));
                break;
            case R.id.telef:
                startActivity(new Intent(this, M_network.class));
                break;
            case R.id.screen:
                startActivity(new Intent(this, Screen.class));
                break;
            case R.id.memory:
                startActivity(new Intent(this, Memory.class));
                break;
        }
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
