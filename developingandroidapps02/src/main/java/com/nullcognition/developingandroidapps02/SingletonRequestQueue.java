package com.nullcognition.developingandroidapps02;// Created by ersin on 07/06/15

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

// none of the methods will work while the singleton is uninitialized
// must initContext on first usage

public enum SingletonRequestQueue{
	INSTANCE;
	SingletonRequestQueue(){}

	private Context context;
	private RequestQueue requestQueue;

	boolean isInit = false;
	public void initContext(Context context){
		if(this.context == context){
			return; // same object, has already been initialized
		}
		else if(context != null){
			isInit = true;
			this.context = context;
			requestQueue = getRequestQueue();
		}
	}

	public RequestQueue getRequestQueue(){
		if(!isInit){ return null; }
		if(requestQueue == null){
			// getApplicationContext() is key, it keeps you from leaking the
			// Activity or BroadcastReceiver if someone passes one in.
			requestQueue = Volley.newRequestQueue(context.getApplicationContext());
		}
		return requestQueue;
	}

	public <T> void addToRequestQueue(Request<T> req){
		if(!isInit){ return; }
		try{
			getRequestQueue().add(req);

		}
		catch(NullPointerException n){
			Log.e("logErr", "null pointer exception: init singletonRequestQueue");
		}
	}

}
