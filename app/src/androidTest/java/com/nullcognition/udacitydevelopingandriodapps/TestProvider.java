package com.nullcognition.udacitydevelopingandriodapps;
/**
 * Created by ersin on 06/11/14 at 9:28 PM
 *
 */

import data.WeatherContract.LocationEntry;
import data.WeatherContract.WeatherEntry;
import data.WeatherDbOpenHelper;

public class TestProvider extends android.test.AndroidTestCase {

  public static final String LOG_TAG = TestProvider.class.getSimpleName();

  public void testDeleteDb() throws Throwable{
	mContext.deleteDatabase(WeatherDbOpenHelper.DATABASE_NAME);
  }

  public void testInsertReadDb(){

	// If there's an error in those massive SQL table creation Strings,
	// errors will be thrown here when you try to get a writable database.
	WeatherDbOpenHelper dbHelper = new WeatherDbOpenHelper(mContext);
	android.database.sqlite.SQLiteDatabase db = dbHelper.getWritableDatabase();

	android.content.ContentValues testValues = TestDb3.createNorthPoleLocationValues();

	long locationRowId;
	locationRowId = db.insert(LocationEntry.TABLE_NAME, null, testValues);

	// Verify we got a row back.
	assertTrue(locationRowId != - 1);
	android.util.Log.d(LOG_TAG, "New row id: " + locationRowId);

	// Data's inserted.  IN THEORY.  Now pull some out to stare at it and verify it made
	// the round trip.

	// A cursor is your primary interface to the query results.
	android.database.Cursor cursor = db.query(LocationEntry.TABLE_NAME,  // Table to Query
											  null, // all columns
											  null, // Columns for the "where" clause
											  null, // Values for the "where" clause
											  null, // columns to group by
											  null, // columns to filter by row groups
											  null // sort order
											 );

	TestDb3.validateCursor(cursor, testValues);

	// Fantastic.  Now that we have a location, add some weather!
	android.content.ContentValues weatherValues = TestDb3.createWeatherValues(locationRowId);

	long weatherRowId = db.insert(WeatherEntry.TABLE_NAME, null, weatherValues);
	assertTrue(weatherRowId != - 1);

	// A cursor is your primary interface to the query results.
	android.database.Cursor weatherCursor = db.query(WeatherEntry.TABLE_NAME,  // Table to Query
													 null, // leaving "columns" null just returns all the columns.
													 null, // cols for "where" clause
													 null, // values for "where" clause
													 null, // columns to group by
													 null, // columns to filter by row groups
													 null  // sort order
													);

	TestDb3.validateCursor(weatherCursor, weatherValues);

	dbHelper.close();
  }
}
