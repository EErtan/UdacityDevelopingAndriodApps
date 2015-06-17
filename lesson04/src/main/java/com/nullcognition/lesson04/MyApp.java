package com.nullcognition.lesson04;// Created by ersin on 17/06/15

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public class MyApp extends Application{
	public static RefWatcher getRefWatcher(Context context) {
		MyApp application = (MyApp) context.getApplicationContext();
		return application.refWatcher;
	}

	private RefWatcher refWatcher;

	@Override public void onCreate() {
		super.onCreate();
		refWatcher = LeakCanary.install(this);
	}
}
