package com.nullcognition.udacitydevelopingandriodapps;
/**
 * Created by ersin on 09/11/14 at 11:53 PM
 */

public class Utility {

   public static String getPreferredLocation(android.content.Context context){
	  android.content.SharedPreferences prefs = android.preference.PreferenceManager.getDefaultSharedPreferences(context);
	  return prefs.getString(context.getString(R.string.pref_location_key), context.getString(R.string.pref_location_default));
   }
}
