package com.nullcognition.lesson04;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nullcognition.lesson04.http.HttpGetWeatherData;

public class MainActivityFragment extends Fragment{

	private final String TAG = "MainActivityFragment";

	public MainActivityFragment(){
	}

	@Override
	public void onCreate(final Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.fragment_main, container, false);

		Object[] contextAndList = {getActivity(), rootView.findViewById(R.id.listView)};
		HttpGetWeatherData.get(contextAndList);


		return rootView;
	}


}
