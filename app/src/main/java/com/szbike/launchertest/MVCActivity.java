package com.szbike.launchertest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.szbike.launchertest.MVC_test.ButtonView;

public class MVCActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButtonView contentView = new ButtonView(this);
        Log.e("MVCActivity->", contentView.toString());
        setContentView(contentView.getContentView());
    }
}
