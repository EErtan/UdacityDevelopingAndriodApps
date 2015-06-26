package com.nullcognition.lesson04.pract;// Created by ersin on 25/06/15

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import com.nullcognition.lesson04.data.TestUtilities;
import com.nullcognition.lesson04.data.WeatherContract;
import com.nullcognition.lesson04.data.WeatherDbHelper;

import java.util.HashSet;

public class LC extends AndroidTestCase{
	// mContext variable is provided

	private static final String LOG_TAG = LC.class.getSimpleName();

	void deleteDb(){ mContext.deleteDatabase(WeatherDbHelper.DATABASE_NAME);}

	public void setUp(){ deleteDb();}

	public void testCreateDb() throws Throwable{
		final HashSet<String> tableNameHashSet = new HashSet<String>();
		tableNameHashSet.add(WeatherContract.LocationEntry.TABLE_NAME);
		tableNameHashSet.add(WeatherContract.WeatherEntry.TABLE_NAME);

		mContext.deleteDatabase(WeatherDbHelper.DATABASE_NAME);
		SQLiteDatabase db = new WeatherDbHelper(this.mContext).getWritableDatabase();
		assertEquals(true, db.isOpen());

		Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
		assertTrue("Error: Db creation fail", c.moveToFirst());

		tableNameHashSet.clear(); // hack, see TestDb, with additional tables of:android_metadata, and sqlite_sequence in the database


		// verifies that the tables have been created
		do{ tableNameHashSet.remove(c.getString(0));} while(c.moveToNext());
		assertTrue("db created without location and  weather entry tabbles", tableNameHashSet.isEmpty());

		c = db.rawQuery("PRAGMA table_info(" + WeatherContract.LocationEntry.TABLE_NAME + ")", null);
		assertTrue("Unable to query the db for table info", c.moveToFirst());

		final HashSet<String> locationColumnHashSet = new HashSet<>();
		locationColumnHashSet.add(WeatherContract.LocationEntry._ID);
		locationColumnHashSet.add(WeatherContract.LocationEntry.COLUMN_CITY_NAME);
		locationColumnHashSet.add(WeatherContract.LocationEntry.COLUMN_COORD_LAT);
		locationColumnHashSet.add(WeatherContract.LocationEntry.COLUMN_COORD_LONG);
		locationColumnHashSet.add(WeatherContract.LocationEntry.COLUMN_LOCATION_SETTING);

		int columnNameIndex = c.getColumnIndex("name");
		do{
			String columnName = c.getString(columnNameIndex);
			locationColumnHashSet.remove(columnName);
		} while(c.moveToNext());
		assertTrue("db doesn't contain all the required location entry columns", locationColumnHashSet.isEmpty());

		db.close();
		// what is going on here in the testing is there are certain properties in the db that must be initialized
		// thus checking the values exist in the testing phase will ensure that creation is successful
	}


	static String testLocationSetting = "99705";
	static String testCityName = "North Pole";
	static double testLatitude = 64.7488;
	static double testLongitude = -147.353;


	public long testLocationTable(){
		SQLiteDatabase db = new WeatherDbHelper(this.mContext).getWritableDatabase();

		ContentValues testValues = TestUtilities.createNorthPoleLocationValues();

		long locationRowId = db.insert(WeatherContract.LocationEntry.TABLE_NAME, null, testValues);
		assertTrue(locationRowId != -1);

		Cursor cursor = db.query(WeatherContract.LocationEntry.TABLE_NAME, null, null, null, null, null, null);
		assertTrue("no records returned from location query", cursor.moveToFirst());

		TestUtilities.validateCurrentRecord("location query validation failed", cursor, testValues);

		assertFalse("more than one record returned from location query", cursor.moveToNext());

		cursor.close();
		db.close();

		return locationRowId;
	}

	public void testWeatherTable(){}

	public long insertLocation(){return -1L;} // -1 if the column doesn't exist, normally use getColumnIndexOrThrow

	// int getColumnIndex (String columnName) Returns the zero-based index for the given column name,
	// or -1 if the column doesn't exist. If you expect the column to exist use getColumnIndexOrThrow(String)
	// instead, which will make the error more clear.
}
