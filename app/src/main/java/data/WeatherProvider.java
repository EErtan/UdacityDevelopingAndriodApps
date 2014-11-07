package data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

public class WeatherProvider extends ContentProvider {

  private static final int WEATHER                        = 100;
  private static final int WEATHER_WITH_LOCATION          = 101;
  private static final int WEATHER_WITH_LOCATION_AND_DATE = 102;
  private static final int LOCATION                       = 300;
  private static final int LOCATION_ID                    = 301;
  private WeatherDbOpenHelper mOpenHelper;

  // The URI Matcher used by this content provider.
  private static final android.content.UriMatcher sUriMatcher = buildUriMatcher();

  public WeatherProvider(){
  }

  private static android.content.UriMatcher buildUriMatcher(){
	final android.content.UriMatcher matcher = new android.content.UriMatcher(android.content.UriMatcher.NO_MATCH);
	final String authority = WeatherContract.CONTENT_AUTHORITY;

	// For each type of URI you want to add, create a corresponding code.
	matcher.addURI(authority, WeatherContract.PATH_WEATHER, WEATHER);
	matcher.addURI(authority, WeatherContract.PATH_WEATHER + "/*", WEATHER_WITH_LOCATION);
	matcher.addURI(authority, WeatherContract.PATH_WEATHER + "/*/*", WEATHER_WITH_LOCATION_AND_DATE);

	matcher.addURI(authority, WeatherContract.PATH_LOCATION, LOCATION);
	matcher.addURI(authority, WeatherContract.PATH_LOCATION + "/#", LOCATION_ID);

	return matcher;
  }

  @Override
  public int delete(Uri uri, String selection, String[] selectionArgs){
	// Implement this to handle requests to delete one or more rows.
	throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public String getType(Uri uri){
	// TODO: Implement this to handle requests for the MIME type of the data
	// at the given URI.
	throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public Uri insert(Uri uri, ContentValues values){
	// TODO: Implement this to handle requests to insert a new row.
	throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public boolean onCreate(){
	mOpenHelper = new WeatherDbOpenHelper(getContext());
	return true;
  }

  @Override
  public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder){
	// TODO: Implement this to handle query requests from clients.
	throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs){
	// TODO: Implement this to handle requests to update one or more rows.
	throw new UnsupportedOperationException("Not yet implemented");
  }
}
