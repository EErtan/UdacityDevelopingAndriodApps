package com.nullcognition.lesson04;

import android.app.Fragment;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nullcognition.lesson04.data.WeatherContract;
import com.nullcognition.lesson04.http.HttpGetWeatherData;

public class MainActivityFragment extends Fragment{

	private final String TAG = "MainActivityFragment";

	private ForecastAdapter mForecastAdapter;
	public MainActivityFragment(){
	}

	@Override
	public void onCreate(final Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		setRetainInstance(true);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.fragment_main, container, false);

		String locationSetting = Utility.getPreferredLocation(getActivity());

		Uri weatherForLocationUri = WeatherContract.WeatherEntry.buildWeatherLocationWithStartDate(
				locationSetting, System.currentTimeMillis());

		String sortOrder = WeatherContract.WeatherEntry.COLUMN_DATE + " ASC";

		Cursor cur = getActivity().getContentResolver().query(weatherForLocationUri,
				null, null, null, sortOrder);

		mForecastAdapter = new ForecastAdapter(getActivity(), cur, 0);
		// Sort order:  Ascending, by date.
		Object[] contextAndList = {getActivity(), rootView.findViewById(R.id.listView)};
		HttpGetWeatherData.get(contextAndList);


		return rootView;
	}

	private void updateWeather(){
		FetchWeatherTask weatherTask = new FetchWeatherTask(getActivity());
		String location = Utility.getPreferredLocation(getActivity());
		weatherTask.execute(location);
	}

	@Override
	public void onStart(){
		super.onStart();
		updateWeather();
	}


}
