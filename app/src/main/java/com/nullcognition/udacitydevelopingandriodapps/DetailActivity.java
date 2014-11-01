package com.nullcognition.udacitydevelopingandriodapps;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


public class DetailActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState){
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_detail);
	if(savedInstanceState == null){
	  getFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
	}

  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu){
	// Inflate the menu; this adds items to the action bar if it is present.

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
	  startActivity(new android.content.Intent(this, SettingsActivity.class));

	  return true;
	}
	if(id == com.nullcognition.udacitydevelopingandriodapps.R.id.action_share){

	}

	return super.onOptionsItemSelected(item);
  }

  /**
   * A placeholder fragment containing a simple view.
   */
  public static class PlaceholderFragment extends Fragment {

	String forecastData;

	public PlaceholderFragment(){
	  setHasOptionsMenu(true);
	}

	@Override
	public void onCreateOptionsMenu(android.view.Menu menu, android.view.MenuInflater inflater){
	  super.onCreateOptionsMenu(menu, inflater);

	  inflater.inflate(R.menu.menu_detail, menu);
	  MenuItem menuItem = menu.findItem(com.nullcognition.udacitydevelopingandriodapps.R.id.action_share);

	  android.widget.ShareActionProvider shareActionProvider = (android.widget.ShareActionProvider)menuItem.getActionProvider();

	  if(shareActionProvider != null){
		shareActionProvider.setShareIntent(createShareIntent());
	  }

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
	  View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

	  try{
		forecastData = getActivity().getIntent().getExtras().getString(ForecastFragment.startDetailsIntentKey, "default");
		android.widget.TextView textView = (android.widget.TextView)rootView
		  .findViewById(com.nullcognition.udacitydevelopingandriodapps.R.id.textView);
		textView.setText(forecastData);
	  }
	  catch(Exception e){
		android.util.Log.e(getClass().getSimpleName(), "forecastdata did not load in textview");

	  }

	  return rootView;
	}

	private android.content.Intent createShareIntent(){
	  android.content.Intent shareIntent = new android.content.Intent(android.content.Intent.ACTION_SEND);

	  shareIntent.addFlags(android.content.Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
	  shareIntent.setType("text/plain");
	  shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, forecastData);

	  return shareIntent;
	}

  }
}
