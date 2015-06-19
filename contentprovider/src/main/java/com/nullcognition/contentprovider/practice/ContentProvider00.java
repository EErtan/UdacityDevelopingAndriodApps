package com.nullcognition.contentprovider.practice;// Created by ersin on 18/06/15

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.UserDictionary;

public class ContentProvider00{

	ContentProvider00(Context context){
		// provides applications access to the content model
		ContentResolver contentResolver = context.getContentResolver();

		// the uri will contain the schema, content authority and path to the dir as a private static final
		// Userdictionary.Words.CONTENT_URI = content://user_dictionary/words <- is a folder/grouping
		Cursor cursor = contentResolver.query(UserDictionary.Words.CONTENT_URI, null, null, null, null);
		/*
		* Uri	The URI, using the content:// scheme, for the content to retrieve.
		projection	A list of which columns to return. Passing null will return all columns, which
		is inefficient.

		selection	A filter declaring which rows to return, formatted as an SQL WHERE clause
		(excluding the WHERE itself). Passing null will return all rows for the given URI.

		selectionArgs	You may include ?s in selection, which will be replaced by the values from
		selectionArgs, in the order that they appear in the selection. The values will be bound as Strings.

		sortOrder	How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY
		itself). Passing null will use the default sort order, which may be unordered.

		cancellationSignal	A signal to cancel the operation in progress, or null if none. If the operation
		is canceled, then OperationCanceledException will be thrown when the query is executed.


Provide an explicit projection, to prevent reading data from storage that aren't going to be used.
Use question mark parameter markers such as 'phone=?' instead of explicit values in the selection parameter,
so that queries that differ only by those values will be recognized as the same for caching purposes.
		* */

		// with your cursor you may use an SimpleCursorAdapter to populate a list(then set adapter
		// on the listview)
	}
}
