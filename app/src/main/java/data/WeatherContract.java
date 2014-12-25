package data;
import java.sql.Date;
/**
 * Created by ersin on 03/11/14 at 11:19 PM
 */
public class WeatherContract {

   public static final String          CONTENT_AUTHORITY = "com.nullcognition.udacitydevelopingandroidapps";
   public static final android.net.Uri BASE_CONTENT_URI  = android.net.Uri.parse("content://" + CONTENT_AUTHORITY);

   public static final String PATH_WEATHER  = "weather";
   public static final String PATH_LOCATION = "location";

   public static String getDbDateString(Date inDate){
	  return "some date";
   }


   /* Inner class that defines the table contents of the weather table */
   public static final class WeatherEntry implements android.provider.BaseColumns {

	  public static final android.net.Uri CONTENT_URI       = BASE_CONTENT_URI.buildUpon().appendPath(PATH_WEATHER).build();
	  public static final String          CONTENT_TYPE      = "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + PATH_WEATHER;
	  public static final String          CONTENT_ITEM_TYPE = "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + PATH_WEATHER;

	  public static final String TABLE_NAME = "weather";

	  // Column with the foreign key into the location table.
	  public static final String COLUMN_LOC_KEY    = "location_id";
	  // Date, stored as Text with format yyyy-MM-dd
	  public static final String COLUMN_DATETEXT   = "date";
	  // Weather id as returned by API, to identify the icon to be used
	  public static final String COLUMN_WEATHER_ID = "weather_id";

	  // Short description and long description of the weather, as provided by API.
	  // e.g "clear" vs "sky is clear".
	  public static final String COLUMN_SHORT_DESC = "short_desc";

	  // Min and max temperatures for the day (stored as floats)
	  public static final String COLUMN_MIN_TEMP = "min";
	  public static final String COLUMN_MAX_TEMP = "max";

	  // Humidity is stored as a float representing percentage
	  public static final String COLUMN_HUMIDITY = "humidity";

	  // Pressure is stored as a float representing percentage
	  public static final String COLUMN_PRESSURE = "pressure";

	  // Windspeed is stored as a float representing windspeed  mph
	  public static final String COLUMN_WIND_SPEED = "wind";

	  // Degrees are meteorological degrees (e.g, 0 is north, 180 is south).  Stored as floats.
	  public static final String COLUMN_DEGREES = "degrees";

	  public static android.net.Uri buildWeatherUri(long id){ // the difference between single item and list of items
		 return android.content.ContentUris.withAppendedId(CONTENT_URI, id);
	  }

	  public static android.net.Uri buildWeatherLocation(String locationSetting){
		 return CONTENT_URI.buildUpon().appendPath(locationSetting).build();
	  }

	  public static android.net.Uri buildWeatherLocationWithStartDate(String locationSetting, String startDate){
		 return CONTENT_URI.buildUpon().appendPath(locationSetting).appendQueryParameter(COLUMN_DATETEXT, startDate).build();
	  }

	  public static android.net.Uri buildWeatherLocationWithDate(String locationSetting, String date){
		 return CONTENT_URI.buildUpon().appendPath(locationSetting).appendPath(date).build();
	  }

	  public static String getLocationSettingFromUri(android.net.Uri uri){
		 return uri.getPathSegments().get(1);
	  }

	  public static String getDateFromUri(android.net.Uri uri){
		 return uri.getPathSegments().get(2);
	  }

	  public static String getStartDateFromUri(android.net.Uri uri){
		 return uri.getQueryParameter(COLUMN_DATETEXT);
	  }


   }

   public static final class LocationEntry implements android.provider.BaseColumns {


	  public static final android.net.Uri CONTENT_URI       = BASE_CONTENT_URI.buildUpon().appendPath(PATH_LOCATION).build();
	  public static final String          CONTENT_TYPE      = "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + PATH_LOCATION;
	  public static final String          CONTENT_ITEM_TYPE = "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + PATH_LOCATION;

	  // Table name
	  public static final String TABLE_NAME = "location";

	  // The location setting string is what will be sent to openweathermap
	  // as the location query.
	  public static final String COLUMN_LOCATION_SETTING = "location_setting";

	  // Human readable location string, provided by the API.  Because for styling,
	  // "Mountain View" is more recognizable than 94043.
	  public static final String COLUMN_CITY_NAME = "city_name";

	  // In order to uniquely pinpoint the location on the map when we launch the
	  // map intent, we store the latitude and longitude as returned by openweathermap.
	  public static final String COLUMN_COORD_LAT  = "coord_lat";
	  public static final String COLUMN_COORD_LONG = "coord_long";


	  public static android.net.Uri buildLocationUri(long id){ // the difference between single item and list of items
		 return android.content.ContentUris.withAppendedId(CONTENT_URI, id);
	  }

   }

}
