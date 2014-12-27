package com.nullcognition.udacitydevelopingandriodapps;// .cancel(booleanValue);
// .execute(Values);

class FetchWeatherTask extends android.os.AsyncTask<String, Void, String[]> {

   android.app.Activity activity;

   public FetchWeatherTask(android.app.Activity inActivity){ activity = inActivity;}

   String LOG_TAG = this.getClass().getSimpleName();

   java.net.HttpURLConnection urlConnection = null;
   java.io.BufferedReader     reader        = null;
   private String postalCode = "n2n1w3";

   @Override
   protected String[] doInBackground(String... params){
	  if(params.length == 0){ return null; }

	  postalCode = params[0];

	  return httpRequestBufferedReader();
   }

   private String[] httpRequestBufferedReader(){
	  // Will contain the raw JSON response as a string.

	  String forecastJsonStr = null;

	  // URI builder values
	  String format = "json";
	  String units = "metric";
	  int numDays = 7;

	  try{
		 // Construct the URL for the OpenWeatherMap query
		 // Possible parameters are available at OWM's forecast API page, at
		 // http://openweathermap.org/API#forecast

		 final String FORECAST_BASE_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?";
		 final String OUERY_PARAM = "q";
		 final String FORMAT_PARAM = "mode";
		 final String UNITS_PARAM = "units";
		 final String DAYS_PARAM = "cnt";

		 android.net.Uri builtUri = android.net.Uri.parse(FORECAST_BASE_URL).buildUpon().
		   appendQueryParameter(OUERY_PARAM, postalCode).appendQueryParameter(FORMAT_PARAM, format).appendQueryParameter(UNITS_PARAM, units).appendQueryParameter(DAYS_PARAM, Integer.toString(numDays)).build();

		 java.net.URL url = new java.net.URL(builtUri.toString());

		 // built uri varification
		 android.util.Log.v(LOG_TAG, "Built uri " + builtUri.toString());

		 // Create the request to OpenWeatherMap, and open the connection
		 urlConnection = (java.net.HttpURLConnection)url.openConnection();
		 urlConnection.setRequestMethod("GET");
		 urlConnection.connect();

		 // Read the input stream into a String
		 java.io.InputStream inputStream = urlConnection.getInputStream();
		 StringBuffer buffer = new StringBuffer();
		 if(inputStream == null){
			// Nothing to do.
			forecastJsonStr = null;
			return null;
		 }
		 reader = new java.io.BufferedReader(new java.io.InputStreamReader(inputStream));

		 String line;
		 while((line = reader.readLine()) != null){
			// Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
			// But it does make debugging a *lot* easier if you print out the completed
			// buffer for debugging.
			buffer.append(line + "\n");
		 }

		 if(buffer.length() == 0){
			// Stream was empty.  No point in parsing.
			forecastJsonStr = null;
			return null;
		 }
		 forecastJsonStr = buffer.toString();

		 android.util.Log.v(LOG_TAG, "Forecast JSON STRING:" + forecastJsonStr);
	  }
	  catch(java.io.IOException e){
		 android.util.Log.e("PlaceholderFragment", "Error  code did not get weather data", e);
		 // If the code didn't successfully get the weather data, there's no point in attempting
		 // to parse it.
		 forecastJsonStr = null;
		 return null;
	  }
	  finally{
		 if(urlConnection != null){
			urlConnection.disconnect();
		 }
		 if(reader != null){
			try{
			   reader.close();
			}
			catch(final java.io.IOException e){
			   android.util.Log.e("PlaceholderFragment", "Error closing stream", e);
			}
		 }
	  }

	  try{
		 return getWeatherDataFromJson(forecastJsonStr, numDays);
	  }
	  catch(org.json.JSONException e){
		 android.util.Log.e(LOG_TAG, e.getMessage(), e);
		 e.printStackTrace();
	  }
	  return null;
   }


   /* The date/time conversion code is going to be moved outside the asynctask later,
 * so for convenience we're breaking it out into its own method now.
 */
   private String getReadableDateString(long time){
	  // Because the API returns a unix timestamp (measured in seconds),
	  // it must be converted to milliseconds in order to be converted to valid date.
	  java.util.Date date = new java.util.Date(time * 1000);
	  java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("E, MMM d");
	  return format.format(date).toString();
   }

   /**
	* Prepare the weather high/lows for presentation.
	*/
   private String formatHighLows(double high, double low){
	  // For presentation, assume the user doesn't care about tenths of a degree.
	  long roundedHigh = Math.round(high);
	  long roundedLow = Math.round(low);

	  String highLowStr = roundedHigh + "/" + roundedLow;
	  return highLowStr;
   }

   /**
	* Take the String representing the complete forecast in JSON Format and
	* pull out the data we need to construct the Strings needed for the wireframes.
	* <p/>
	* Fortunately parsing is easy:  constructor takes the JSON string and converts it
	* into an Object hierarchy for us.
	*/
   private String[] getWeatherDataFromJson(String forecastJsonStr, int numDays) throws org.json.JSONException{

	  // These are the names of the JSON objects that need to be extracted.
	  final String OWM_LIST = "list";
	  final String OWM_WEATHER = "weather";
	  final String OWM_TEMPERATURE = "temp";
	  final String OWM_MAX = "max";
	  final String OWM_MIN = "min";
	  final String OWM_DATETIME = "dt";
	  final String OWM_DESCRIPTION = "main";

	  org.json.JSONObject forecastJson = new org.json.JSONObject(forecastJsonStr);
	  org.json.JSONArray weatherArray = forecastJson.getJSONArray(OWM_LIST);

	  String[] resultStrs = new String[numDays];
	  for(int i = 0; i < weatherArray.length(); i++){
		 // For now, using the format "Day, description, hi/low"
		 String day;
		 String description;
		 String highAndLow;

		 // Get the JSON object representing the day
		 org.json.JSONObject dayForecast = weatherArray.getJSONObject(i);

		 // The date/time is returned as a long.  We need to convert that
		 // into something human-readable, since most people won't read "1400356800" as
		 // "this saturday".
		 long dateTime = dayForecast.getLong(OWM_DATETIME);
		 day = getReadableDateString(dateTime);

		 // description is in a child array called "weather", which is 1 element long.
		 org.json.JSONObject weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
		 description = weatherObject.getString(OWM_DESCRIPTION);

		 // Temperatures are in a child object called "temp".  Try not to name variables
		 // "temp" when working with temperature.  It confuses everybody.
		 org.json.JSONObject temperatureObject = dayForecast.getJSONObject(OWM_TEMPERATURE);
		 double high = temperatureObject.getDouble(OWM_MAX);
		 double low = temperatureObject.getDouble(OWM_MIN);

		 highAndLow = formatHighLows(high, low);
		 resultStrs[i] = day + " - " + description + " - " + highAndLow;
	  }

	  return resultStrs;
   }

   @Override
   protected void onPostExecute(String[] result){

	  // getActivity only needed if called from within a fragment
	  // see constructor, which takes in a reference to the calling activity

	  if(result != null){
		 // array adapter auto calls adapter.notifydatasetchanngged() on each insertion and deletion
		 android.widget.ArrayAdapter<String> arrayAdapter = new android.widget.ArrayAdapter<String>(activity, //getActivity(),
																									com.nullcognition.udacitydevelopingandriodapps.R.layout.list_item_forcast, com.nullcognition.udacitydevelopingandriodapps.R.id.list_item_forecast_textview);
		 arrayAdapter.clear();
		 arrayAdapter.addAll(result); // instead of using a for each loop to go through the loop

		 android.widget.ListView listView = (android.widget.ListView)activity//.getApplicationContext().getActivity()
		   .findViewById(com.nullcognition.udacitydevelopingandriodapps.R.id.listview_forecast);
		 listView.setAdapter(arrayAdapter);

	  }
   }
}
