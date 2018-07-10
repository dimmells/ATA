package com.dimmells.ata;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;
import android.widget.TextView;

/**
 * Created by dimic on 04.01.2018.
 */

public class M_network extends Activity {
    TextView tellV = null;
    TextView header = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_network);
        tellV = (TextView) findViewById(R.id.tellV);
        header = (TextView) findViewById(R.id.tell_header);
        header.setText("Зв'язок");
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        tellV.setText("");
        tellV.append("Стан виклику: " + Func.callState(tm.getCallState()));
        tellV.append("\nТип телефону: " + Func.phoneType(tm.getPhoneType()));
        tellV.append("\nТип моб. мережі: " + Func.networkType(tm.getNetworkType()));
        tellV.append("\nКод країни оператора: " + tm.getNetworkCountryIso());
        tellV.append("\nКод оператора(MCC+MNC): " + tm.getNetworkOperator());
        tellV.append("\nІм'я оператора: " + tm.getNetworkOperatorName());
        tellV.append("\nПередача даних: " + Func.dataState(tm.getDataState()));
        tellV.append("\nНапрям передачі: " + Func.dataActivity(tm.getDataActivity()));
        tellV.append("\nРоумінг: " + (tm.isNetworkRoaming() ? "true" : "false"));
        tellV.append("\nСтан сім-карти: " + Func.simState(tm.getSimState()));
        tellV.append("\nКод країни провайдера: " + tm.getSimCountryIso());
        tellV.append("\nСерійний номер SIM: " + tm.getSimOperatorName());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
