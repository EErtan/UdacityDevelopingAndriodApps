package com.nullcognition.lesson04.http;// Created by ersin on 17/06/15

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;

public class HttpGetWeatherData{

	public static void get(final Object[] contextAndList){

		if(client == null){
			try{ CacheResponse(new File(((Context) contextAndList[0]).getFilesDir(), "myCache"));}
			catch(Exception e){
				e.printStackTrace();
				Log.e(TAG, "get caching error");
			}
		}
		AsyncHttpGet asyncHttpGet = new AsyncHttpGet();
		asyncHttpGet.execute(contextAndList);
	}

	public static String httpGetWeatherData(String apiKey) throws Exception{

		Request request = new Request.Builder()
				.url(buildURL())
				.header(paramApiKey, apiKey)
				.build();

		Response response = client.newCall(request).execute();
		if(!response.isSuccessful()){ throw new IOException("Unexpected code " + response); }

		Headers responseHeaders = response.headers();
		for(int i = 0; i < responseHeaders.size(); i++){
			Log.e(TAG, responseHeaders.name(i) + ": " + responseHeaders.value(i));
		}

		String responseBody = response.body().string();
		Log.e(TAG, "Response 1 response:          " + response);
		Log.e(TAG, "Response 1 cache response:    " + response.cacheResponse());
		Log.e(TAG, "Response 1 network response:  " + response.networkResponse());

		return responseBody;
	}

	private static String buildURL(){
		return Uri.parse(baseURL + forecastQuery).buildUpon() // ? is added after forecastQuery
				.appendQueryParameter(paramQuery, cityId) // & are added after the queries
				.appendQueryParameter(paramUnits, units)
				.appendQueryParameter(paramCount, count)
				.build().toString();
	}

	private static void CacheResponse(File cacheDirectory) throws Exception{
		int cacheSize = 10 * 1024 * 1024; // 10 MiB
		Cache cache = new Cache(cacheDirectory, cacheSize);

		client = new OkHttpClient();
		client.setCache(cache);
	}

	private static OkHttpClient client = null;
	public static final String TAG = "HtGeWeData";

	private static final String baseURL = "http://api.openweathermap.org/data/2.5";
	private static final String forecastQuery = "/forecast/daily";

	private static final String paramQuery = "q";
	private static final String paramUnits = "units";
	private static final String paramCount = "cnt";
	private static final String cityId = "94043";
	private static final String units = "metric";
	private static final String count = "7";

	private static final String paramApiKey = "x-api-key";
	// add &APPID=eonutg93th2euho939hoeuth if the header does not work

}
