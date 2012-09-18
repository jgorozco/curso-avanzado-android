package com.tid.Ejemplo73_manifest;

import android.app.Activity;
import android.os.Bundle;

public class Ejemplo73_manifestActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    //http://developer.android.com/guide/topics/manifest/activity-element.html#nohist
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}