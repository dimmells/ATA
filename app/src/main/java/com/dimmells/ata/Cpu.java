package com.dimmells.ata;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.params.Face;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.CpuUsageInfo;
import android.os.Environment;
import android.os.HardwarePropertiesManager;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.support.annotation.Nullable;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.TimerTask;
import java.util.regex.MatchResult;

/**
 * Created by dimic on 04.01.2018.
 */

public class    Cpu extends Activity {
    TextView    cpuV = null;
    TextView    freq_and_use = null;
    TextView    header = null;
    int         seconds = 1000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cpu);
        cpuV = (TextView) findViewById(R.id.cpuV);
        freq_and_use = (TextView) findViewById(R.id.freq_and_use);
        header = (TextView) findViewById(R.id.cpu_header);
        header.setText("Процесор");
        get_freq_and_use();
        cpuV.setText("");
        cpuV.append(new Func().getCPUinfo());
        start();
    }

    protected void start()
    {
        CountDownTimer countDownTimer = new CountDownTimer(seconds * 1000, 1000) {
            @Override
            public void onTick(long l) {
                get_freq_and_use();
                seconds++;
            }
            @Override
            public void onFinish() {

            }
        }.start();
    }

    protected void get_freq_and_use()
    {
        Frequency su = new Frequency();
        freq_and_use.setText("");
        try {
            freq_and_use.append("\nПоточна частота: " + (double) (su.getCPUFrequencyCurrent() / 1000) + "MHz");
            freq_and_use.append("\nЧастота: " + (double) (su.getCPUFrequencyMin() / 1000) + " - ");
            freq_and_use.append((double) (su.getCPUFrequencyMax() / 1000) + "MHz");
            freq_and_use.append("\nВикористання: " + returnToDecimalPlaces(new Func().readUsage() * 100.0f) + "%\n");
        }
        catch (Exception e) {}
    }

    private String returnToDecimalPlaces(double values){
        DecimalFormat df = new DecimalFormat("0.00");
        String angleFormated = df.format(values);
        return angleFormated;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}