package com.dimmells.ata;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

/**
 * Created by dimic on 04.01.2018.
 */

public class Battery extends Activity
{
    TextView battV = null;
    TextView header = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.battery);
        battV = (TextView) findViewById(R.id.battV);
        header = (TextView) findViewById(R.id.batt_header);
        header.setText("Батарея");
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                battV.setText("");
                battV.append("Максимальне значення: " + intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1) + "%");
                battV.append("\nПоточне значення: " + intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) + "%");
                battV.append("\nТемпература: " + (float) intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1) / 10.0f + "°C");
                battV.append("\nНапруга: " + (float) intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1) / 1000.0f + "V");
                battV.append("\nТехннологія: " + intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY));
                battV.append("\nСтан: " + Func.batteryHealth(intent.getIntExtra(BatteryManager.EXTRA_HEALTH, -1)));
                battV.append("\nСтатус: " + Func.batteryStatus(intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1)));
                battV.append("\nДжерело: " + Func.batteryPlugged(intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1)));
            }

        };
        registerReceiver(receiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
