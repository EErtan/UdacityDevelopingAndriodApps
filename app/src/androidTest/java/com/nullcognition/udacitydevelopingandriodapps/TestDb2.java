package com.nullcognition.udacitydevelopingandriodapps;
/**
 * Created by ersin on 04/11/14 at 1:17 AM
 */

import data.WeatherContract.LocationEntry;
import data.WeatherDbOpenHelper;

public class TestDb2 extends android.test.AndroidTestCase {

  public static final String LOG_TAG = TestDb4.class.getSimpleName();

  public void testCreateDb() throws Throwable{
	mContext.deleteDatabase(WeatherDbOpenHelper.DATABASE_NAME);
	android.database.sqlite.SQLiteDatabase db = new WeatherDbOpenHelper(this.mContext).getWritableDatabase();
	assertEquals(true, db.isOpen());
	db.close();
  }

  public void testInsertReadDb(){

	// If there's an error in those massive SQL table creation Strings,
	// errors will be thrown here when you try to get a writable database.
	data.WeatherDbOpenHelper dbHelper = new data.WeatherDbOpenHelper(mContext);
	android.database.sqlite.SQLiteDatabase db = dbHelper.getWritableDatabase();

	android.content.ContentValues testValues = createNorthPoleLocationValues();

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

	validateCursorAgainstContentValues(cursor, testValues);

	// Fantastic.  Now that we have a location, add some weather!
	android.content.ContentValues weatherValues = createWeatherValues(locationRowId);

	long weatherRowId = db.insert(data.WeatherContract.WeatherEntry.TABLE_NAME, null, weatherValues);
	assertTrue(weatherRowId != - 1);

	// A cursor is your primary interface to the query results.
	android.database.Cursor weatherCursor = db.query(data.WeatherContract.WeatherEntry.TABLE_NAME,  // Table to Query
													 null, // leaving "columns" null just returns all the columns.
													 null, // cols for "where" clause
													 null, // values for "where" clause
													 null, // columns to group by
													 null, // columns to filter by row groups
													 null  // sort order
													);

	validateCursorAgainstContentValues(weatherCursor, weatherValues);

	dbHelper.close();
  }

  static android.content.ContentValues createWeatherValues(long locationRowId){
	android.content.ContentValues weatherValues = new android.content.ContentValues();
	weatherValues.put(data.WeatherContract.WeatherEntry.COLUMN_LOC_KEY, locationRowId);
	weatherValues.put(data.WeatherContract.WeatherEntry.COLUMN_DATETEXT, "20141205");
	weatherValues.put(data.WeatherContract.WeatherEntry.COLUMN_DEGREES, 1.1);
	weatherValues.put(data.WeatherContract.WeatherEntry.COLUMN_HUMIDITY, 1.2);
	weatherValues.put(data.WeatherContract.WeatherEntry.COLUMN_PRESSURE, 1.3);
	weatherValues.put(data.WeatherContract.WeatherEntry.COLUMN_MAX_TEMP, 75);
	weatherValues.put(data.WeatherContract.WeatherEntry.COLUMN_MIN_TEMP, 65);
	weatherValues.put(data.WeatherContract.WeatherEntry.COLUMN_SHORT_DESC, "Asteroids");
	weatherValues.put(data.WeatherContract.WeatherEntry.COLUMN_WIND_SPEED, 5.5);
	weatherValues.put(data.WeatherContract.WeatherEntry.COLUMN_WEATHER_ID, 321);

	return weatherValues;
  }

  static android.content.ContentValues createNorthPoleLocationValues(){
	// Create a new map of values, where column names are the keys
	android.content.ContentValues testValues = new android.content.ContentValues();
	testValues.put(LocationEntry.COLUMN_LOCATION_SETTING, "99705");
	testValues.put(LocationEntry.COLUMN_CITY_NAME, "North Pole");
	testValues.put(LocationEntry.COLUMN_COORD_LAT, 64.7488);
	testValues.put(LocationEntry.COLUMN_COORD_LONG, - 147.353);

	return testValues;
  }

  static void validateCursorAgainstContentValues(android.database.Cursor valueCursor, android.content.ContentValues expectedValues){

	// If possible, move to the first row of the query results.
	assertTrue(valueCursor.moveToFirst());

	// get the content values out of the cursor at the current position
	android.content.ContentValues resultValues = new android.content.ContentValues();
	android.database.DatabaseUtils.cursorRowToContentValues(valueCursor, resultValues);

	// make sure the values match the ones we put in
	validateContentValues(resultValues, expectedValues);
	valueCursor.close();
  }

  // The target api annotation is needed for the call to keySet -- we wouldn't want
  // to use this in our app, but in a test it's fine to assume a higher target.
  @android.annotation.TargetApi(android.os.Build.VERSION_CODES.HONEYCOMB)
  static void validateContentValues(android.content.ContentValues output, android.content.ContentValues input){
	java.util.Set<String> inputKeys = input.keySet();
	for(String key : inputKeys){
	  assertTrue(output.containsKey(key));
	  assertEquals(output.getAsString(key), (input.getAsString(key)));
	}
  }
}
