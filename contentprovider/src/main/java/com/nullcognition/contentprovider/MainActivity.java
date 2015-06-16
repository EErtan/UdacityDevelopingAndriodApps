package com.nullcognition.contentprovider;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

// app <> contentResolver <> UserDictionary[ContentProvider for Dictionary <> Data source for dictionary]
// Alarms, contacts, calendar, etc, can have content providers. including other apps
// the contentResolver allows you to talk to other content providers

// read from data, add, change, delete
// query, insert, update,  delete reslover.query - the content resolver calls the methods on the content provider


public class MainActivity extends Activity{


	private static final String[] Columns_to_be_bound = new String[]{
			UserDictionary.Words.WORD,
			UserDictionary.Words.FREQUENCY
	};
	private static final int[] Layout_items_toFill = new int[]{
			android.R.id.text1,
			android.R.id.text2
	};

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ListView dictListView = (ListView) findViewById(R.id.dictionary_list_view);

		// Get the ContentResolver which will send a message to the ContentProvider
		ContentResolver resolver = getContentResolver();

		// Get a Cursor containing all of the rows in the Words table
		Cursor cursor = resolver.query(UserDictionary.Words.CONTENT_URI, null, null, null, null);
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(
				this, android.R.layout.two_line_list_item, cursor, Columns_to_be_bound, Layout_items_toFill, 0);

		dictListView.setAdapter(adapter);

		// Uri - uniform resource identifier to identify or give location
		// URL are a subset of uri

		// UserDictionary.Words.Content_URI = content://user_dictionary/words
		// content:// standard way to start content uris
		// user_dictionary is the content authority, what content provider the resolver is using
		// words is the list of words

		// Cursor is an iterator that gives access to the data in tabular form, the first three nulls specify
		// the rows and columns

		// common methods - .getCount(), .moveToNext() or while(cursor.moveToNext()) cursor.getInt() ...
		// cursor.getColumnIndex(nameOfColumn or get___()
		// try{}finally{c.close;} to prevent memory from leaking

// Populating a string
//		try{
//			dictTextView.setText("The UserDictionary contains " + cursor.getCount() + " words\n");
//			dictTextView.append("COLUMNS: " + UserDictionary.Words._ID + " - " + UserDictionary.Words.FREQUENCY + " - " + UserDictionary.Words.WORD);
//
//			// Get the index of the column containing the actual words, using
//			int idColumn = cursor.getColumnIndex(UserDictionary.Words._ID);
//			int frequencyColumn = cursor.getColumnIndex(UserDictionary.Words.FREQUENCY);
//			int wordColumn = cursor.getColumnIndex(UserDictionary.Words.WORD);
//
//			// Iterates through all returned rows in the cursor.
//			String[] words = new String[];
//			while(cursor.moveToNext()){
//				// Use that index to extract the String value of the word
//				// at the current row the cursor is on.
//				int id = cursor.getInt(idColumn);
//				int frequency = cursor.getInt(frequencyColumn);
//				String word = cursor.getString(wordColumn);
//
//				dictTextView.append(("\n" + id + " - " + frequency + " - " + word));
//			}
//		}
//		finally{
//			// Always close your cursor to avoid memory leaks
//			cursor.close();
//		}
	}

}
