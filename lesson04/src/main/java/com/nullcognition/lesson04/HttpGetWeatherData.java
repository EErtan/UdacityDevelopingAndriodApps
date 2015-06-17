package com.nullcognition.lesson04;// Created by ersin on 17/06/15

import android.net.Uri;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class HttpGetWeatherData{

	public static void get(final Object[] contextAndList){

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

//		Headers responseHeaders = response.headers();
//		for(int i = 0; i < responseHeaders.size(); i++){
//			Log.e(TAG, responseHeaders.name(i) + ": " + responseHeaders.value(i));
//		}

		return response.body().string();
	}

	private static String buildURL(){
		return Uri.parse(baseURL + forecastQuery).buildUpon() // ? is added after forecastQuery
				.appendQueryParameter(paramQuery, cityId) // & are added after the queries
				.appendQueryParameter(paramUnits, units)
				.appendQueryParameter(paramCount, count)
				.build().toString();
	}

	private static final OkHttpClient client = new OkHttpClient();
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
