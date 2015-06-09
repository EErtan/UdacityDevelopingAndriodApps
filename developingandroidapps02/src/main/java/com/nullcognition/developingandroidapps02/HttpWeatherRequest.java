package com.nullcognition.developingandroidapps02;// Created by ersin on 07/06/15

import android.net.Uri;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;


public class HttpWeatherRequest{

	// http://api.openweathermap.org/data/2.5/forecast/daily?q=94043&mode=json&units=metric&cnt=7
	// required
	String baseUrl = "http://api.openweathermap.org/data/2.5";
	String queryParam = "q";

	// weather types
	String weatherQuery = "/weather?";
	String forecastQuery = "/forecast/daily?";


	String format = "json";
	String units = "metric";
	int numDays = 7;

	final String QUERY_PARAM = "id"; // was q
	final String FORMAT_PARAM = "mode";
	final String UNITS_PARAM = "units";
	final String DAYS_PARAM = "cnt";

	Uri requestUri;
	private final OkHttpClient client = new OkHttpClient();

	public HttpWeatherRequest(String[] params){
		requestUri = Uri.parse(baseUrl + forecastQuery).buildUpon()
		                .appendQueryParameter(QUERY_PARAM, params[0])
		                .appendQueryParameter(FORMAT_PARAM, format)
		                .appendQueryParameter(UNITS_PARAM, units)
		                .appendQueryParameter(DAYS_PARAM, Integer.toString(numDays))
		                .build();

	}

	public String makeRequest() throws IOException{
		Request request = new Request.Builder().url(requestUri.toString()).build();
		Response response = client.newCall(request).execute();
		if(!response.isSuccessful()){ throw new IOException("Unexpected code " + response); }
		return response.body().string();
	}
}
