package com.dimmells.ata;

import android.app.Activity;
import android.app.ActivityManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.StatFs;
import android.support.annotation.Nullable;
import android.widget.TextView;

import java.io.File;
import java.text.DecimalFormat;

/**
 * Created by dimic on 01.02.2018.
 */

public class Memory extends Activity
{
    TextView header = null;
    TextView memory = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memory);
        header = (TextView)findViewById(R.id.ram_header);
        header.setText("RAM");
        header = (TextView)findViewById(R.id.sys_header);
        header.setText("Системна пам'ять");
        header = (TextView)findViewById(R.id.inter_header);
        header.setText("Зовнішня пам'ять");
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);

        memory = (TextView) findViewById(R.id.sys);
        long totalInternalValue = getTotalInternalMemorySize();
        long freeInternalValue = getAvailableInternalMemorySize();
        long usedInternalValue = totalInternalValue - freeInternalValue;
        memory.append("Всього: " + formatSize(totalInternalValue));
        memory.append("\nВільно: " + formatSize(freeInternalValue));
        memory.append("\nВикористано: " + formatSize(usedInternalValue));
        memory.append("\n");

        memory = (TextView) findViewById(R.id.inter);
        long totalExternalValue = getTotalExternalMemorySize();
        long freeExternalValue = getAvailableExternalMemorySize();
        long usedExternalValue = totalExternalValue - freeExternalValue;
        memory.append("Всього: " + formatSize(totalExternalValue));
        memory.append("\nВільно: " + formatSize(freeExternalValue));
        memory.append("\nВикористано: " + formatSize(usedExternalValue));
        memory.append("\n");
        start();
    }

    protected void start()
    {

        CountDownTimer countDownTimer = new CountDownTimer(1000 * 1000, 1000) {
            @Override
            public void onTick(long l) {
                memory = (TextView) findViewById(R.id.ram);
                memory.setText("");
                long totalRamValue = totalRamMemorySize();
                long freeRamValue = freeRamMemorySize();
                long usedRamValue = totalRamValue - freeRamValue;
                memory.append("Всього: " + returnToDecimalPlaces((double)totalRamValue / 1000) + " GB");
                memory.append("\nВільно: " + freeRamValue + " MB");
                memory.append("\nВикористано: " + returnToDecimalPlaces((double)usedRamValue / 1000)+ " MB");
                memory.append("\n");
            }
            @Override
            public void onFinish() {

            }
        }.start();
    }

    private String formatSize(long memory)
    {
        double  d_memory;
        int     del;

        del = 1024;
        d_memory = (double)memory / del;
        while (d_memory > 1.0) {
            d_memory /= del;
            del *= 1024;
        }
        d_memory *= 1024;

        if (memory > 1024) {
            if (memory > 1024 * 1024) {
                if (memory > 1024 * 1024 * 1024) {
                    if (memory > 1024 * 1024 * 1024 * 1024) {
                        return "" + returnToDecimalPlaces(d_memory) + " GB";
                    }
                    return "" + returnToDecimalPlaces(d_memory) + " GB";
                }
                return "" + returnToDecimalPlaces(d_memory) + " MB";
            }
            return "" + returnToDecimalPlaces(d_memory) + " KB";
        }
        return " - ";
    }

    private long freeRamMemorySize() {
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);
        long availableMegs = mi.availMem / 1048576L;
        return availableMegs;
    }

    private long totalRamMemorySize() {
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager)getSystemService(ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);
        long availableMegs = mi.totalMem / 1048576L;
        return availableMegs;
    }

    public static boolean externalMemoryAvailable() {
        return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }

    public static long getAvailableInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return availableBlocks * blockSize;
    }

    public static long getTotalInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return totalBlocks * blockSize;
    }

    public static long getAvailableExternalMemorySize() {
        if (externalMemoryAvailable()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long availableBlocks = stat.getAvailableBlocks();
            return availableBlocks * blockSize;
        } else {
            return 0;
        }
    }

    public static long getTotalExternalMemorySize() {
        if (externalMemoryAvailable()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long totalBlocks = stat.getBlockCount();
            return totalBlocks * blockSize;
        } else {
            return 0;
        }
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
