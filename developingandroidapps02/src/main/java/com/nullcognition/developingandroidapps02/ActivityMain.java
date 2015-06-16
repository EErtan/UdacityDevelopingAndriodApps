package com.nullcognition.developingandroidapps02;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ShareActionProvider;

public class ActivityMain extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		createLocationEntryDatabaseTable();
	}
	private void createLocationEntryDatabaseTable(){

	}

// Mini-Lesson: Broadcast receivers can be set in the activity, thus lasts for the the lifetime of the activity
	// or in the manifest which will trigger even if your app is not running
	// Broadcast receivers are triggered by their intent filter, that allows the broadcast to get through if of the type

	@Override
	public boolean onCreateOptionsMenu(final Menu menu){
		MenuItem item = menu.findItem(R.id.menu_item_share);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(final MenuItem item){
		if(item.getItemId() == R.id.action_implicit){

			String location = "Toronto";
			Uri geoLocation = Uri.parse("geo:0,0?").buildUpon().appendQueryParameter("q", location).build();
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setData(geoLocation);
			if(intent.resolveActivity(getPackageManager()) != null){
				startActivity(intent);
				/*
				resolves because of google maps
				<intent-filter>
					<action:name="android.intent.action.View"/>
					<data android:scheme="geo"/>
				</intent-filter>
				*/

			}
			else{
				Log.e("logErr", "location did not work or intent did not reslove");
			}
			//startActivity(new Intent(this, SettingsActivity.class));
		}
		return super.onOptionsItemSelected(item);
	}
}
