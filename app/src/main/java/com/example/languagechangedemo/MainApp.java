package com.example.languagechangedemo;

import android.app.Application;
import android.content.Context;

import java.util.Locale;

public class MainApp extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, Locale.getDefault().getDisplayLanguage()));
    }
}
