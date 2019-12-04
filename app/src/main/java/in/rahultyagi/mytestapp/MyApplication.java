package in.rahultyagi.mytestapp;

import android.app.Application;
import android.content.Context;

import in.rahultyagi.mytestapp.roomdb.AppDatabase;

public class MyApplication extends Application {
	
	private static Context context;
	private static AppDatabase mDB;
	
	public void onCreate() {
		super.onCreate();
		MyApplication.context = getApplicationContext();
		mDB = AppDatabase.getInstance(context);
	}
	
	public static synchronized AppDatabase getDB() {
		if (mDB == null) {
			mDB = AppDatabase.getInstance(context);
		}
		return mDB;
	}
	
	public static Context getContext() {
		return MyApplication.context;
	}
	
}