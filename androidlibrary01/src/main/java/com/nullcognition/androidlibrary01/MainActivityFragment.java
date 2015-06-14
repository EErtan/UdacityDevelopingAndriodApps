package com.nullcognition.androidlibrary01;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivityFragment extends Fragment{

	public MainActivityFragment(){}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View rootview = inflater.inflate(R.layout.fragment_main, container, false);
		initListView(rootview);
		return rootview;
	}

	private void initListView(final View rootview){
		ArrayAdapter arrayAdapter = createListDataInArrayAdapter();
		ListView listView = (ListView) rootview.findViewById(R.id.listView); // does the cast from array to list incur a large cost
		listView.setAdapter(arrayAdapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
			@Override
			public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id){
				Toast.makeText(getActivity(), String.valueOf(view.getId()), Toast.LENGTH_SHORT).show();

//				String s = arrayadapter.getItem(position); // to get the item
			}
		});
	}

	private ArrayAdapter<String> createListDataInArrayAdapter(){
		String[] strings = {"String 1", "String 2", "String 3", "String 4", "String 5", "String 6"};
		ArrayList<String> arrayList = (ArrayList<String>) Arrays.asList(strings);
		return new ArrayAdapter<String>(getActivity(), R.layout.listview_text_view, R.id.textView, arrayList);
	}
}
