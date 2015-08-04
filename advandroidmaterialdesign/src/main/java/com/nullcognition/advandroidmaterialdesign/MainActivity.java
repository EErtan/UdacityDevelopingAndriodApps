package com.nullcognition.advandroidmaterialdesign;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

// Material Design: uses 500 as the base color, pic a lighter color in the 100 range, and a darker
// color in the 700-800 range - after choose an A for accent color to harmonize with the first

public class MainActivity extends AppCompatActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayShowTitleEnabled(false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		int id = item.getItemId();
		if(id == R.id.action_settings){

			Toast.makeText(MainActivity.this, "Starting main2Activity", Toast.LENGTH_SHORT).show();
			startActivity(new Intent(this, ActivityDetail.class));
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
