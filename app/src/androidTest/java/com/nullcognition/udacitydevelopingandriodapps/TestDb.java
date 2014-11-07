package com.nullcognition.udacitydevelopingandriodapps;
/**
 * Created by ersin on 07/11/14 at 5:03 PM
 */
public class TestDb extends android.test.AndroidTestCase {

  public static final String LOG_TAG       = TestDb.class.getSimpleName();
  static final        String TEST_LOCATION = "99705";
  static final        String TEST_DATE     = "20141205";

  public void testCreateDb() throws Throwable{
	mContext.deleteDatabase(data.WeatherDbOpenHelper.DATABASE_NAME);
	android.database.sqlite.SQLiteDatabase db = new data.WeatherDbOpenHelper(this.mContext).getWritableDatabase();
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
	locationRowId = db.insert(data.WeatherContract.LocationEntry.TABLE_NAME, null, testValues);

	// Verify we got a row back.
	assertTrue(locationRowId != - 1);
	android.util.Log.d(LOG_TAG, "New row id: " + locationRowId);

	// Data's inserted.  IN THEORY.  Now pull some out to stare at it and verify it made
	// the round trip.

	// A cursor is your primary interface to the query results.
	android.database.Cursor cursor = db.query(data.WeatherContract.LocationEntry.TABLE_NAME,  // Table to Query
											  null, // all columns
											  null, // Columns for the "where" clause
											  null, // Values for the "where" clause
											  null, // columns to group by
											  null, // columns to filter by row groups
											  null // sort order
											 );

	validateCursor(cursor, testValues);

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

	validateCursor(weatherCursor, weatherValues);

	dbHelper.close();
  }

  static android.content.ContentValues createWeatherValues(long locationRowId){
	android.content.ContentValues weatherValues = new android.content.ContentValues();
	weatherValues.put(data.WeatherContract.WeatherEntry.COLUMN_LOC_KEY, locationRowId);
	weatherValues.put(data.WeatherContract.WeatherEntry.COLUMN_DATETEXT, TEST_DATE);
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
	testValues.put(data.WeatherContract.LocationEntry.COLUMN_LOCATION_SETTING, TEST_LOCATION);
	testValues.put(data.WeatherContract.LocationEntry.COLUMN_CITY_NAME, "North Pole");
	testValues.put(data.WeatherContract.LocationEntry.COLUMN_COORD_LAT, 64.7488);
	testValues.put(data.WeatherContract.LocationEntry.COLUMN_COORD_LONG, - 147.353);

	return testValues;
  }

  static void validateCursor(android.database.Cursor valueCursor, android.content.ContentValues expectedValues){

	assertTrue(valueCursor.moveToFirst());

	java.util.Set<java.util.Map.Entry<String, Object>> valueSet = expectedValues.valueSet();
	for(java.util.Map.Entry<String, Object> entry : valueSet){
	  String columnName = entry.getKey();
	  int idx = valueCursor.getColumnIndex(columnName);
	  assertFalse(idx == - 1);
	  String expectedValue = entry.getValue().toString();
	  assertEquals(expectedValue, valueCursor.getString(idx));
	}
	valueCursor.close();
  }
}
