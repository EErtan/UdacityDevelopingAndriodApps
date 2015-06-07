package com.nullcognition.developingandroidapps02;// Created by ersin on 07/06/15

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;


public class HttpWeatherRequest{

	String requestUrl;

	// required
	String baseUrl = "http://api.openweathermap.org";
	String data = "/data";
	String versionNum = "/2.5";

	// weather types
	String weatherQuery = "/weather?";
	String forecastQuery = "/forecast?";

	// location identification
	String byCity = "q=Kitchener,ca";
	String byLatLon = "lat=43.4500&lon=80.4833";
	String byId = "id=5992996";

	StringRequest stringRequest;
	JsonObjectRequest jsonObjectRequest;

	public HttpWeatherRequest(){
		requestUrl= baseUrl+data+versionNum+weatherQuery+byCity;

		stringRequest = new StringRequest(Request.Method.GET, requestUrl, new Response.Listener<String>(){
			@Override
			public void onResponse(final String response){
				Log.e("logErr", "" +response);
			}
		}, new Response.ErrorListener(){
			@Override
			public void onErrorResponse(final VolleyError volleyError){
				Log.e("logErr", "error "+ volleyError.toString());

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
