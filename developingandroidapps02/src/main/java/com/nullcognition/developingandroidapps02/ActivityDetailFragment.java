package com.nullcognition.developingandroidapps02;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 A placeholder fragment containing a simple view.
 */
public class ActivityDetailFragment extends Fragment{

	public ActivityDetailFragment(){ }

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState){
		return inflater.inflate(R.layout.activity_fragment_detail, container, false);
	}
}
