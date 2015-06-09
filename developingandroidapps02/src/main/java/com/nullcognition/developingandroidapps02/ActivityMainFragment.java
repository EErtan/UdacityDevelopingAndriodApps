package com.nullcognition.developingandroidapps02;

import android.app.Activity;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ActivityMainFragment extends Fragment{

	public ActivityMainFragment(){}

	public static final String[] cityId = {"5375480"};
	ArrayAdapter<String> arrayAdapter = null;
	@Override
	public void onActivityCreated(final Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onCreate(final Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
		inflater.inflate(R.menu.menu_activity_main, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		int id = item.getItemId();
		if(id == R.id.action_settings){return true;}
		else if(id == R.id.action_refresh){
			createArrayAdapterFromNetworkRequest();
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		// inflate the view first, then search the vg and add the aa
		View rootView = inflater.inflate(R.layout.fragment_activity_main, container, false);

		// in the viewgroup traverse to find the listview, be sure to cast, set the adapter to the arrayadapter from the list population method
		arrayAdapter = createArrayAdapterFromStringArray();
		((ListView) rootView.findViewById(R.id.fragment_activity_main_listview)).setAdapter(arrayAdapter);
		// after the rootView has the adapter set to its child list view, we can return the root view(whole tree(view group)) back to the caller
		return rootView;
	}

	private void createArrayAdapterFromNetworkRequest(){
		FetchWeatherTask fetchWeatherTask = new FetchWeatherTask(arrayAdapter, this.getActivity());
		fetchWeatherTask.execute(cityId);
	}

	// prefer this method due to the atomic nature of the population
	private ArrayAdapter<String> createArrayAdapterFromStringArray(){
		String[] listItems = {
				"Today- 0",
				"Tomorrow - 1",
				"Wednesday - 2",
				"Thursday - 3",
				"Friday - 4"};
		return new ArrayAdapter<String>(getActivity(), R.layout.list_item_forecast, R.id.list_item_forecast_textview, new ArrayList<String>(Arrays.asList(listItems)));
	}

	// another way to add objects to the array list
	private void populateListAddVersion(){
		ArrayList<String> listItems = new ArrayList<>();

		listItems.add("Today - 0");
		listItems.add("Tomorrow - 1");
		listItems.add("Wednesday - 2");
		listItems.add("Thursday - 3");
		listItems.add("Friday - 4");
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item_forecast, R.id.list_item_forecast_textview, listItems);
	}

	public static class FetchWeatherTask extends AsyncTask<String, Void, String[]>{
		FetchWeatherTask(ArrayAdapter<String> arrayAdapter, Activity activity){
			this.arrayAdapter = arrayAdapter;
			this.activity = activity;
		}
		Activity activity;
		ArrayAdapter<String> arrayAdapter;
		@Override
		protected String[] doInBackground(final String... params){

			HttpWeatherRequest httpWeatherRequest = new HttpWeatherRequest(params);
			String[] forecasts = null;
			try{
				forecasts = ForecastParser.getWeatherDataFromJson(httpWeatherRequest.makeRequest(), 7);
			}
			catch(IOException e){
				e.printStackTrace(); // from httpWeatherRequest.makeRequest
			}
			catch(JSONException e){
				e.printStackTrace(); // from forecast parser
			}
			return forecasts;
		}

		@Override
		protected void onPostExecute(final String[] forecasts){
			super.onPostExecute(forecasts);
			if(arrayAdapter != null){
				arrayAdapter.clear();
				arrayAdapter.addAll(forecasts);
			}
			else{
				arrayAdapter = new ArrayAdapter<String>(activity, R.layout.list_item_forecast, R.id.list_item_forecast_textview,
						new ArrayList<String>(Arrays.asList(forecasts)));
			}
		}
	}
}
