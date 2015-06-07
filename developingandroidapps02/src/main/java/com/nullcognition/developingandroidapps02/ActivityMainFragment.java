package com.nullcognition.developingandroidapps02;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class ActivityMainFragment extends Fragment{

	public ActivityMainFragment(){}

	@Override
	public void onActivityCreated(final Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		SingletonRequestQueue.INSTANCE.initContext(getActivity().getApplicationContext());

		new HttpWeatherRequest(); // also do this example with the async task,
		// not needed if using volley which handles a new thread for you
	}

	@Override
	public void onCreate(final Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}
	@Override
	public void  onCreateOptionsMenu(Menu menu,MenuInflater inflater){
		inflater.inflate(R.menu.menu_activity_main, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		int id = item.getItemId();
		if(id == R.id.action_settings){return true;}
		else if(id == R.id.action_refresh){new HttpWeatherRequest();}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		// inflate the view first, then search the vg and add the aa
		View rootView = inflater.inflate(R.layout.fragment_activity_main, container, false);

		// in the viewgroup traverse to find the listview, be sure to cast, set the adapter to the arrayadapter from the list population method
		((ListView) rootView.findViewById(R.id.fragment_activity_main_listview)).setAdapter(createArrayAdapterFromStringArray());

		// after the rootView has the adapter set to its child list view, we can return the root view(whole tree(view group)) back to the caller
		return rootView;
	}

	// prefer this method due to the atomic nature of the population
	private ArrayAdapter<String> createArrayAdapterFromStringArray(){
		String[] listItems = {
				"Today- 0",
				"Tomorrow - 1",
				"Wednesday - 2",
				"Thursday - 3",
				"Friday - 4"};

		ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(listItems));
		return new ArrayAdapter<String>(getActivity(), R.layout.list_item_forecast, R.id.list_item_forecast_textview, arrayList);
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



	@Override
	public void onStop(){
		super.onStop();
		//stringRequest.cancel();

	}
}
