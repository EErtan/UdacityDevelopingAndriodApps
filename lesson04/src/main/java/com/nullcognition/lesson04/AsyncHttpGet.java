package com.nullcognition.lesson04;// Created by ersin on 17/06/15

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;

public class AsyncHttpGet extends AsyncTask<Object, Void, Object[]>{
	@Override
	protected Object[] doInBackground(final Object... params){

		String jsonResponse = null;
		try{jsonResponse = HttpGetWeatherData.httpGetWeatherData(((Activity) params[0]).getString(R.string.open_weather_api_key));}
		catch(Exception e){
			e.printStackTrace();
			Log.e(TAG, "doInBackground httpGetWeatherData() error");
		}

		String[] forecasts = null;
		try{forecasts = Parse.json(jsonResponse, Integer.valueOf(count));}
		catch(Exception e){
			e.printStackTrace();
			Log.e(TAG, "doInBackground parseJson() error");
		}
		Object[] newObjects = Arrays.copyOf(params, 3); // add the forecasts
		newObjects[2] = forecasts;
		return newObjects;
	}
	@Override
	protected void onPostExecute(final Object[] contextListForecasts){
		super.onPostExecute(contextListForecasts);
		ListView listView = ((ListView) contextListForecasts[1]);
		listView.setAdapter(new ArrayAdapter<String>((Context) contextListForecasts[0], R.layout.list_item, R.id.textView, (String[]) contextListForecasts[2]));
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
			@Override
			public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id){
				Toast.makeText((Activity) contextListForecasts[0], "Clicked", Toast.LENGTH_SHORT).show();
			}
		});
	}

	public static final String TAG = "AsHtGe";
	private static final String count = "7";
}
