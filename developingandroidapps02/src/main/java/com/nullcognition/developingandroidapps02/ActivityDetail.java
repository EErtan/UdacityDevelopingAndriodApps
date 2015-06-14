package com.nullcognition.developingandroidapps02;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class ActivityDetail extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		if(savedInstanceState == null){

			getFragmentManager().beginTransaction()
			                    .add(R.id.container, new PlaceholderFragment())
			                    .commit();
		}
		String forecast = (String) getIntent().getExtras().get(Intent.EXTRA_TEXT);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_activity_detail, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if(id == R.id.action_settings){
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	/**
	 A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment{

		public PlaceholderFragment(){
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
		                         Bundle savedInstanceState){

			View rootView = inflater.inflate(R.layout.activity_fragment_detail, container, false);
			return rootView;
		}
	}
}
