package com.dimmells.ata;

import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by dimic on 07.12.2017.
 */

public class Func extends IOException {
    public static String batteryHealth (Integer health) {
        switch (health) {
            case BatteryManager.BATTERY_HEALTH_DEAD:
                return "Непрацююча";
            case BatteryManager.BATTERY_HEALTH_GOOD:
                return "Хороший";
            case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                return "Перегріта";
            case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                return "Підвищенна напруга";
            case BatteryManager.BATTERY_HEALTH_UNKNOWN:
                return "Невідомий";
            case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                return "Несправна";
            default:
                return "НЕВИЗНАЧЕНО";
        }
    }

    public static String batteryPlugged (Integer plug) {
        switch (plug) {
            case BatteryManager.BATTERY_PLUGGED_AC:
                return "Зарядний пристрій";
            case BatteryManager.BATTERY_PLUGGED_USB:
                return "USB";
            default:
                return "НЕМАЄ ";
        }
    }

    public static String batteryStatus (Integer status) {
        switch (status) {
            case BatteryManager.BATTERY_STATUS_CHARGING:
                return "Заряджається";
            case BatteryManager.BATTERY_STATUS_DISCHARGING:
                return "Не заряджається";
            case BatteryManager.BATTERY_STATUS_FULL:
                return "Заряджена";
            case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                return "Не заряджається";
            case BatteryManager.BATTERY_STATUS_UNKNOWN:
                return "Невідомо";
            default:
                return "НЕВИЗНАЧЕНО";
        }
    }

    public static String callState (Integer state) {
        switch (state) {
            case TelephonyManager.CALL_STATE_IDLE:
                return "Телефон не активний";
            case TelephonyManager.CALL_STATE_OFFHOOK:
                return "Спроба виклику";
            case TelephonyManager.CALL_STATE_RINGING:
                return "З'єднання з абонентом";
            default:
                return "НЕВИЗНАЧЕНО";
        }
    }

    public static String phoneType (Integer type) {
        switch (type) {
            case TelephonyManager.PHONE_TYPE_CDMA:
                return "CDMA";
            case TelephonyManager.PHONE_TYPE_GSM:
                return "GSM";
            case TelephonyManager.PHONE_TYPE_NONE:
                return "NONE";
            default:
                return "НЕВИЗНАЧЕНО";
        }
    }

    public static String networkType (Integer type) {
        switch (type) {
            case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                return "NONE";
            case TelephonyManager.NETWORK_TYPE_GPRS:
                return "GPRS";
            case TelephonyManager.NETWORK_TYPE_EDGE:
                return "EDGE";
            case TelephonyManager.NETWORK_TYPE_UMTS:
                return "UMTS";
            case TelephonyManager.NETWORK_TYPE_CDMA:
                return "CDMA";
            default:
                return "НЕВИЗНАЧЕНО";
        }
    }

    public static String dataState (Integer state) {
        switch (state) {
            case TelephonyManager.DATA_DISCONNECTED:
                return "Роз'єднано";
            case TelephonyManager.DATA_CONNECTING:
                return "З'єднання";
            case TelephonyManager.DATA_CONNECTED:
                return "З'єднано";
            case TelephonyManager.DATA_SUSPENDED:
                return "Призупинено";
            default:
                return "НЕВИЗНАЧЕНО";
        }
    }

    public static String dataActivity (Integer act) {
        switch (act) {
            case TelephonyManager.DATA_ACTIVITY_NONE:
                return "NONE";
            case TelephonyManager.DATA_ACTIVITY_IN:
                return "IN";
            case TelephonyManager.DATA_ACTIVITY_OUT:
                return "OUT";
            case TelephonyManager.DATA_ACTIVITY_INOUT:
                return "INOUT";
            case TelephonyManager.DATA_ACTIVITY_DORMANT:
                return "В очікувані";
            default:
                return "НЕВИЗНАЧЕНО";
        }
    }

    public static String simState (Integer state) {
        switch (state) {
            case TelephonyManager.SIM_STATE_UNKNOWN:
                return "Невідомо";
            case TelephonyManager.SIM_STATE_ABSENT:
                return "Відсутня";
            case TelephonyManager.SIM_STATE_PIN_REQUIRED:
                return "Необхідний PIN";
            case TelephonyManager.SIM_STATE_PUK_REQUIRED:
                return "Необхідний PUK";
            case TelephonyManager.SIM_STATE_NETWORK_LOCKED:
                return "Мережа заблокована";
            case TelephonyManager.SIM_STATE_READY:
                return "Готова";
            default:
                return "НЕВИЗНАЧЕНО";
        }
    }

    public float readUsage() {
        try {
            RandomAccessFile reader = new RandomAccessFile("/proc/stat", "r");
            String load = reader.readLine();

            String[] toks = load.split(" +");
            long idle1 = Long.parseLong(toks[4]);
            long cpu1 = Long.parseLong(toks[2]) + Long.parseLong(toks[3]) + Long.parseLong(toks[5])
                    + Long.parseLong(toks[6]) + Long.parseLong(toks[7]) + Long.parseLong(toks[8]);
            try {
                Thread.sleep(360);
            } catch (Exception e) {}
            reader.seek(0);
            load = reader.readLine();
            reader.close();
            toks = load.split(" +");
            long idle2 = Long.parseLong(toks[4]);
            long cpu2 = Long.parseLong(toks[2]) + Long.parseLong(toks[3]) + Long.parseLong(toks[5])
                    + Long.parseLong(toks[6]) + Long.parseLong(toks[7]) + Long.parseLong(toks[8]);
            return (float)(cpu2 - cpu1) / ((cpu2 + idle2) - (cpu1 + idle1));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public String getCPUinfo() {
        StringBuffer sb = new StringBuffer();
        int cores = 0;
        //sb.append("abi: ").append(Build.CPU_ABI).append("\n");
        if (new File("/proc/cpuinfo").exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(new File("/proc/cpuinfo")));
                String aLine;
                while ((aLine = br.readLine()) != null) {
                    if (aLine.length() > 5 && aLine.substring(0, 4).equals("proc"))
                        cores++;
                    if (aLine.length() > 8 && aLine.substring(0, 8).equals("Hardware"))
                        sb.append("Процесор:" + aLine.split(":")[1]);
                    if (aLine.length() > 9 && aLine.substring(0, 9).equals("Processor"))
                        sb.append(aLine.split(":")[1] + "\n");
                    //sb.append(aLine + "\n");
                }
                sb.append("Кількість ядер: " + cores + "\n");
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
