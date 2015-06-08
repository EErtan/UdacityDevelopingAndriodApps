package com.nullcognition.developingandroidapps02;// Created by ersin on 07/06/15

import android.net.Uri;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;


public class HttpWeatherRequest{

	// required
	String baseUrl = "http://api.openweathermap.org/data/2.5";
	String queryParam = "q";

	// weather types
	String weatherQuery = "/weather?";
	String forecastQuery = "/forecast?";

	String byId = "5992996";

	String format = "json";
	String units = "metric";
	int numDays = 7;


	final String QUERY_PARAM = "id"; // note was q
	final String FORMAT_PARAM = "mode";
	final String UNITS_PARAM = "units";
	final String DAYS_PARAM = "cnt";

	StringRequest stringRequest;
	JsonObjectRequest jsonObjectRequest;

	public HttpWeatherRequest(String[] params){
		Uri requestUri = Uri.parse(baseUrl + forecastQuery).buildUpon()
		                    .appendQueryParameter(QUERY_PARAM, params[0])
		                    .appendQueryParameter(FORMAT_PARAM, format)
		                    .appendQueryParameter(UNITS_PARAM, units)
		                    .appendQueryParameter(DAYS_PARAM, Integer.toString(numDays))
		                    .build();

		stringRequest = new StringRequest(Request.Method.GET, requestUri.toString(), new Response.Listener<String>(){
			@Override
			public void onResponse(final String response){
				Log.e("logErr", "" + response);
			}
		}, new Response.ErrorListener(){
			@Override
			public void onErrorResponse(final VolleyError volleyError){
				Log.e("logErr", "error " + volleyError.toString());

			}
		});
//		jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, requestUrl, null, new Response.Listener<JSONObject>(){
//			@Override
//			public void onResponse(final JSONObject jsonObject){
//
//			}
//		}, new Response.ErrorListener(){
//			@Override
//			public void onErrorResponse(final VolleyError volleyError){
//
//			}
//		}
//		);
		SingletonRequestQueue.INSTANCE.addToRequestQueue(stringRequest);
	}

	private void httpRequestWeather(){

		// SingletonRequestQueue.INSTANCE.getRequestQueue();

	}
}
