/*
 * Copyright (c)
 * Creator: ersin Date: $today.year.month.day.hour.minute
 * Project: UdacityDevelopingAndriodApps Module: app Path/File: /home/ersin/GithubProjects/UdacityDevelopingAndriodApps/app/src/main/java/com/nullcognition/udacitydevelopingandriodapps/ForecastFragment.java
 * Last Modified: 25/07/14 10:55 AM
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.nullcognition.udacitydevelopingandriodapps;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ForecastFragment extends Fragment {

  public ForecastFragment(){
  }

  @Override
  public void onCreate(Bundle inBundle){
	super.onCreate(inBundle);
	setHasOptionsMenu(true); // refers to the fragments oncreateoptionsmenu and onoptionsitemselected
  }

  @Override
  public void onCreateOptionsMenu(android.view.Menu inMenu, android.view.MenuInflater inMenuInflater){
	inMenuInflater.inflate(com.nullcognition.udacitydevelopingandriodapps.R.menu.forecastfragment, inMenu);
  }

  @Override
  public boolean onOptionsItemSelected(android.view.MenuItem inMenuItem){
	switch(inMenuItem.getItemId()){
	  case com.nullcognition.udacitydevelopingandriodapps.R.id.action_refresh:
		FetchWeatherTask weatherTask = new FetchWeatherTask(getActivity());
		weatherTask.execute("94043"); // executing asynctask here
		return true;
	  default:
		assert false;
	}
	return super.onOptionsItemSelected(inMenuItem);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

//		String[] days = new com.nullcognition.udacitydevelopingandriodapps.ForecastFragment.FetchWeatherTask.execute();
//		java.util.List<String> daysList = new java.util.ArrayList<String>(java.util.Arrays.asList(days));
//
//		android.widget.ArrayAdapter<String> arrayAdapter = new android.widget.ArrayAdapter<String>(getActivity(), com.nullcognition.udacitydevelopingandriodapps.R.layout.list_item_forcast, com.nullcognition.udacitydevelopingandriodapps.R.id.list_item_forecast_textview, daysList);

	View rootView = inflater.inflate(R.layout.fragment_my_activity2, container, false);
//		android.widget.ListView listView = (android.widget.ListView)rootView.findViewById(com.nullcognition.udacitydevelopingandriodapps.R.id.listview_forecast);
//		listView.setAdapter(arrayAdapter);

	// see fatchweathertask for ^ code

	 android.widget.ListView listView = (android.widget.ListView)getActivity()
	  .findViewById(com.nullcognition.udacitydevelopingandriodapps.R.id.listview_forecast);

	final android.widget.ListView listViewFinal = listView;

	listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

	  @Override
	  public void onItemClick(android.widget.AdapterView<?> parent, android.view.View view, int position, long id){
		String string=(String)listViewFinal.getAdapter().getItem(position);
		android.widget.Toast.makeText(getActivity(), "you clicked on " + string , android.widget.Toast.LENGTH_SHORT).show();

	  }
	});

	return rootView;
  }


}
