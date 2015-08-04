package com.nullcognition.advandroidmaterialdesign;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityDetail extends AppCompatActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);

		if(savedInstanceState == null){
			FragmentDetail fragment = new FragmentDetail();
			getSupportFragmentManager().beginTransaction()
			                           .add(R.id.weather_detail_container, fragment)
			                           .commit();
		}
	}

	public static class FragmentDetail extends Fragment{


		private ImageView mIconView;
		private TextView  mDateView;
		private TextView  mDescriptionView;
		private TextView  mHighTempView;
		private TextView  mLowTempView;
		private TextView  mHumidityView;
		private TextView  mHumidityLabelView;
		private TextView  mWindView;
		private TextView  mWindLabelView;
		private TextView  mPressureView;
		private TextView  mPressureLabelView;

		public FragmentDetail(){
			setHasOptionsMenu(true);
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

			View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
			mIconView = (ImageView) rootView.findViewById(R.id.detail_icon);
			mDateView = (TextView) rootView.findViewById(R.id.detail_date_textview);
			mDescriptionView = (TextView) rootView.findViewById(R.id.detail_forecast_textview);
			mHighTempView = (TextView) rootView.findViewById(R.id.detail_high_textview);
			mLowTempView = (TextView) rootView.findViewById(R.id.detail_low_textview);
			mHumidityView = (TextView) rootView.findViewById(R.id.detail_humidity_textview);
			mHumidityLabelView = (TextView) rootView.findViewById(R.id.detail_humidity_label_textview);
			mWindView = (TextView) rootView.findViewById(R.id.detail_wind_textview);
			mWindLabelView = (TextView) rootView.findViewById(R.id.detail_wind_label_textview);
			mPressureView = (TextView) rootView.findViewById(R.id.detail_pressure_textview);
			mPressureLabelView = (TextView) rootView.findViewById(R.id.detail_pressure_label_textview);
			return rootView;
		}

		@Override
		public void onViewCreated(final View view, final Bundle savedInstanceState){
			super.onViewCreated(view, savedInstanceState);

			AppCompatActivity activity    = (AppCompatActivity) getActivity();
			Toolbar           toolbarView = (Toolbar) getView().findViewById(R.id.toolbar);

			// We need to start the enter transition after the data has loaded
			if(activity instanceof ActivityDetail){
				activity.supportStartPostponedEnterTransition();

				if(null != toolbarView){
					activity.setSupportActionBar(toolbarView);

					activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
					activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
				}
			}
			else{
				if(null != toolbarView){
					Menu menu = toolbarView.getMenu();
					if(null != menu){ menu.clear(); }
					toolbarView.inflateMenu(R.menu.fragment_detail);
					finishCreatingMenu(toolbarView.getMenu());
				}
			}
		}

		private void finishCreatingMenu(Menu menu){
			// Retrieve the share menu item
			MenuItem menuItem = menu.findItem(R.id.action_share);
			menuItem.setIntent(createShareForecastIntent());
		}

		private Intent createShareForecastIntent(){
			Intent shareIntent = new Intent(Intent.ACTION_SEND);
			shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
			shareIntent.setType("text/plain");
			shareIntent.putExtra(Intent.EXTRA_TEXT, "forecast");
			return shareIntent;
		}

		@Override
		public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
			if(getActivity() instanceof ActivityDetail){
				// Inflate the menu; this adds items to the action bar if it is present.
				inflater.inflate(R.menu.fragment_detail, menu);
				finishCreatingMenu(menu);
			}
		}

	}
}
