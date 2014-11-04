package data;
/**
 * Created by ersin on 03/11/14 at 11:27 PM
 */
import data.WeatherContract.WeatherEntry;
import data.WeatherContract.LocationEntry;

public class WeatherDbOpenHelper extends android.database.sqlite.SQLiteOpenHelper {

  public static final  String DATABASE_NAME    = "weather.db";
  private static final int    DATABASE_VERSION = 1;

  public WeatherDbOpenHelper(android.content.Context context, String name, android.database.sqlite.SQLiteDatabase.CursorFactory factory, int version){
	super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(android.database.sqlite.SQLiteDatabase db){

	final String SQL_CREATE_WEATHER_TABLE = "CREATE TABLE " +
	WeatherEntry.TABLE_NAME + " (" + data.WeatherContract.WeatherEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
	WeatherEntry.COLUMN_LOC_KEY + " INTEGER NOT NULL," +
	data.WeatherContract.WeatherEntry.COLUMN_DATETEXT + " TEXT NOT NULL," +
	WeatherEntry.COLUMN_SHORT_DESC + " TEXT NOT NULL, " +
	WeatherEntry.COLUMN_WEATHER_ID + " INTEGER NOT NULL," +

	WeatherEntry.COLUMN_MIN_TEMP + " REAL NOT NULL, " +
	WeatherEntry.COLUMN_MAX_TEMP + " REAL NOT NULL, " +

	WeatherEntry.COLUMN_HUMIDITY + " REAL NOT NULL, " +
	WeatherEntry.COLUMN_PRESSURE + " REAL NOT NULL, " +
	WeatherEntry.COLUMN_WIND_SPEED + " REAL NOT NULL, " +
	WeatherEntry.COLUMN_DEGREES + " REAL NOT NULL, " +

	// Set up the location column as a foreign key to location table.
	" FOREIGN KEY (" + WeatherEntry.COLUMN_LOC_KEY + ") REFERENCES " +
	LocationEntry.TABLE_NAME + " (" + LocationEntry._ID + "), " +

	// To assure the application have just one weather entry per day
	// per location, it's created a UNIQUE constraint with REPLACE strategy
	" UNIQUE (" + WeatherEntry.COLUMN_DATETEXT + ", " +
	WeatherEntry.COLUMN_LOC_KEY + ") ON CONFLICT REPLACE);";


  }

  @Override
  public void onUpgrade(android.database.sqlite.SQLiteDatabase db, int oldVersion, int newVersion){

  }
}
