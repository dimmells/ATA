package com.dimmells.ata;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by dimic on 04.01.2018.
 */

public class Screen extends Activity implements View.OnClickListener{
    TextView scrV = null;
    TextView header = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen);
        scrV = (TextView)findViewById(R.id.scrV);
        header = (TextView) findViewById(R.id.screen_header);
        header.setText("Дисплей");
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        scrV.setText("");
        scrV.append("DPI: " + metrics.densityDpi);
        scrV.append("\nФактор масштабування шрифтів: " + metrics.scaledDensity);
        scrV.append("\nВисота: " + metrics.heightPixels);
        scrV.append("\nШирина: " + metrics.widthPixels);
        scrV.append("\nxDPI: " + metrics.xdpi);
        scrV.append("\nyDPI " + metrics.ydpi);
    }

    @Override
    public void onClick(View view)
    {
        Intent intent = new Intent(this, Test_pixel.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
