package com.dimmells.ata;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TabHost;
import android.widget.Toast;

public class main extends TabActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        TabHost tabHost = getTabHost();
        TabHost.TabSpec tabSpec;

        tabSpec = tabHost.newTabSpec("info_tab");
        tabSpec.setIndicator("Інформація");
        tabSpec.setContent(new Intent(this, Info.class));
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("test_tab");
        tabSpec.setIndicator("Tecти");
        tabSpec.setContent(new Intent(this, Test.class));
        tabHost.addTab(tabSpec);
    }
}