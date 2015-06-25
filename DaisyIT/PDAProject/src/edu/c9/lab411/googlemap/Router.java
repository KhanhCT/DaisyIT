package edu.c9.lab411.googlemap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import org.json.JSONObject;
import edu.c9.lab411.ocr.R;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class Router extends FragmentActivity {
	public static final String TAG = "Router";
	public AutoCompleteTextView addressFrom, addressTo;
	private Button route;
	private RadioGroup typeVehicle;
	private String Type_Vehicle = "driving";
	public final int PLACES=0;
	public final int PLACES_DETAILS=1;	
	private int color_Path = 1;
	private double reLatitude = 0.0, reLongitude = 0.0; 
	private double deLatitude = 0.0, deLongitude = 0.0; 
	DownloadTask placesDownloadTask;
	DownloadTask placeDetailsDownloadTask;
	ParserTask placesParserTask;
	ParserTask placeDetailsParserTask;
	List<HashMap<String, String>> reference;
	private int check=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.router);
		route = (Button)findViewById(R.id.find);
		typeVehicle = (RadioGroup)findViewById(R.id.groupVehicles);
		addressFrom = (AutoCompleteTextView)findViewById(R.id.fromAddress);
		addressFrom.setThreshold(1);
		typeVehicle.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int id) {
				// TODO Auto-generated method stub
				switch(id){
				case R.id.driving:{
					Type_Vehicle = "driving";
					color_Path = 1;
				}break;
				case R.id.walking:{
					Type_Vehicle = "walking";
					color_Path = 2;
				}break;
				case R.id.bus:{
					Type_Vehicle = "bus";
					color_Path = 3;					
				}break;
				}
			}
		});
		route.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent in = getIntent();
				Bundle bundle = in.getExtras();
				double latitude = bundle.getDouble("latitude");
				double longitude = bundle.getDouble("longitude");
				String myLocation = "My Location";
				String addressFromString = addressFrom.getText().toString().trim();
				if(addressFromString.equals(myLocation)){
					Log.i(TAG, "My Location");
					in.putExtra("reLatitude", latitude);
					in.putExtra("reLongitude", longitude);
				}
				else{
					in.putExtra("reLatitude", reLatitude);
					in.putExtra("reLongitude",reLongitude);
				}
				in.putExtra("deLatitude", deLatitude);
				in.putExtra("deLongitude",deLongitude);
				in.putExtra("typeVehicle", Type_Vehicle);
				in.putExtra("colorPath", color_Path);
				setResult(7, in);
				finish();
			}
		});
		addressFrom.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				// Creating a DownloadTask to download Google Places matching "s"
				placesDownloadTask = new DownloadTask(PLACES);
				
				// Getting url to the Google Places Autocomplete api
				String url = getAutoCompleteUrl(s.toString());
				
				// Start downloading Google Places
				// This causes to execute doInBackground() of DownloadTask class
				placesDownloadTask.execute(url);
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
		addressFrom.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int index,
					long id) {
				// TODO Auto-generated method stub
	       	    check=0;
               // ListView lv = (ListView) arg0;
                SimpleAdapter adapter = (SimpleAdapter) arg0.getAdapter();

                //@SuppressWarnings("unchecked")
				@SuppressWarnings("unchecked")
				HashMap<String, String> hm = (HashMap<String, String>) adapter.getItem(index);                    
                addressFrom.setText(hm.get("description"));
                // Creating a DownloadTask to download Places details of the selected place
                placeDetailsDownloadTask = new DownloadTask(PLACES_DETAILS);
                
                // Getting url to the Google Places details api
				String url = getPlaceDetailsUrl(hm.get("reference"));    				
              // Start downloading Google Place Details
				// This causes to execute doInBackground() of DownloadTask class
				placeDetailsDownloadTask.execute(url);
               // Toast.makeText(getApplicationContext(), hm.get("reference"), Toast.LENGTH_SHORT).show();
                
				
			}
		});
		// AutoComplet 2
		addressTo = (AutoCompleteTextView)findViewById(R.id.toAddress);
		addressTo.setThreshold(1);	
		addressTo.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				// Creating a DownloadTask to download Google Places matching "s"
				placesDownloadTask = new DownloadTask(PLACES);
				
				// Getting url to the Google Places Autocomplete api
				String url = getAutoCompleteUrl(s.toString());
				
				// Start downloading Google Places
				// This causes to execute doInBackground() of DownloadTask class
				placesDownloadTask.execute(url);
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
		addressTo.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int index,
					long id) {
				// TODO Auto-generated method stub
				check=1;
                ListView lv = (ListView) arg0;
                SimpleAdapter adapter = (SimpleAdapter) arg0.getAdapter();

                HashMap<String, String> hm = (HashMap<String, String>) adapter.getItem(index);                    
                addressTo.setText(hm.get("description"));
                // Creating a DownloadTask to download Places details of the selected place
                placeDetailsDownloadTask = new DownloadTask(PLACES_DETAILS);
                
                // Getting url to the Google Places details api
				String url = getPlaceDetailsUrl(hm.get("reference"));    				
                				// Start downloading Google Place Details
				// This causes to execute doInBackground() of DownloadTask class
				placeDetailsDownloadTask.execute(url);
               // Toast.makeText(getApplicationContext(), hm.get("reference"), Toast.LENGTH_SHORT).show();
				
			}
		});
		
	}
	
	
	private String getAutoCompleteUrl(String place){
					
		// Obtain browser key from https://code.google.com/apis/console
		String key = "key=AIzaSyD4zj1YJ05rklYk7XbxFPxZ6tay8Zp8AJ0";
					
		// place to be be searched
		String input = "input="+place;
					
		// place type to be searched
		String types = "types=geocode";
					
		// Sensor enabled
		String sensor = "sensor=false";			
					
		// Building the parameters to the web service
		String parameters = input+"&"+types+"&"+sensor+"&"+key;
					
		// Output format
		String output = "json";
					
		// Building the url to the web service
		String url = "https://maps.googleapis.com/maps/api/place/autocomplete/"+output+"?"+parameters;
		
		return url;
	}
	
	private String getPlaceDetailsUrl(String ref){
		
		// Obtain browser key from https://code.google.com/apis/console
		String key = "key=AIzaSyD4zj1YJ05rklYk7XbxFPxZ6tay8Zp8AJ0";
					
		// reference of place
		String reference = "reference="+ref;					
					
		// Sensor enabled
		String sensor = "sensor=false";			
					
		// Building the parameters to the web service
		String parameters = reference+"&"+sensor+"&"+key;
					
		// Output format
		String output = "json";
		
		// Building the url to the web service
		String url = "https://maps.googleapis.com/maps/api/place/details/"+output+"?"+parameters;
		
		return url;
	}
	/** A method to download json data from url */
    private String DownloadUrl(String strUrl) throws IOException{
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try{
                URL url = new URL(strUrl);

                // Creating an http connection to communicate with url 
                urlConnection = (HttpURLConnection) url.openConnection();

                // Connecting to url 
                urlConnection.connect();

                // Reading data from url 
                iStream = urlConnection.getInputStream();

                BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

                StringBuffer sb  = new StringBuffer();

                String line = "";
                while( ( line = br.readLine())  != null){
                        sb.append(line);
                }
                
                data = sb.toString();

                br.close();

        }catch(Exception e){
                Log.d("Exception while downloading url", e.toString());
        }finally{
                iStream.close();
                urlConnection.disconnect();
        }
        return data;
     }
    private class DownloadTask extends AsyncTask<String, Void, String>{
    	private int downloadType=0;
    	public DownloadTask(int type){
    		this.downloadType = type;
    	}
		@Override
		protected String doInBackground(String... url) {
			// TODO Auto-generated method stub
			
			// For storing data from web service
			String data = "";		
			try{
				// Fetching the data from web service
				data = DownloadUrl(url[0]);
			}catch(Exception e){
                Log.d("Background Task",e.toString());
			}
			return data;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			switch (downloadType) {
			case PLACES:{
				// Creating ParserTask for parsing Google Places
				placesParserTask = new ParserTask(PLACES);
				
				// Start parsing google places json data
				// This causes to execute doInBackground() of ParserTask class
				placesParserTask.execute(result);
			}break;
			case PLACES_DETAILS:{
				// Creating ParserTask for parsing Google Places
				placeDetailsParserTask = new ParserTask(PLACES_DETAILS);
				
				// Starting Parsing the JSON string
				// This causes to execute doInBackground() of ParserTask class
				placeDetailsParserTask.execute(result);	
			}//break;
			//default:
			//	break;
			}
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		@Override
		protected void onProgressUpdate(Void... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		}
    	
    }
    private class ParserTask extends AsyncTask<String, Integer, List<HashMap<String, String>>>{

    	int parserType = 0;
    	public ParserTask(int type){
    		this.parserType = type;
    	}
		@Override
		protected List<HashMap<String, String>> doInBackground(String... jsonData) {
			// TODO Auto-generated method stub
			
			JSONObject jObject;			
			List<HashMap<String, String>> list = null; 
			try{
            	jObject = new JSONObject(jsonData[0]);
            	
            	switch(parserType){
            	case PLACES :
            		PlaceJSONParser placeJsonParser = new PlaceJSONParser();
		            // Getting the parsed data as a List construct
		            list = placeJsonParser.parse(jObject);
		            break;
            	case PLACES_DETAILS :      	            	
	            	PlaceDetailsJSONParser placeDetailsJsonParser = new PlaceDetailsJSONParser();
	            	// Getting the parsed data as a List construct
	               	list = placeDetailsJsonParser.parse(jObject);
	               //	break;
            	}			
			}catch(Exception e){
				Log.d("Exception",e.toString());
			}
			return list;
		}
		@Override
		protected void onPostExecute(List<HashMap<String, String>> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			switch(parserType){
			case PLACES:{
				String[] from = new String[] { "description"};
				int[] to = new int[] { android.R.id.text1 };
				// Creating a SimpleAdapter for the AutoCompleteTextView
				SimpleAdapter adapter = new SimpleAdapter(Router.this, result, android.R.layout.simple_list_item_1, from, to);				
			   		
				// Setting the adapter
				addressFrom.setAdapter(adapter);		
				
				// Setting the adapter
				addressTo.setAdapter(adapter);
			}break;
			case PLACES_DETAILS:{
				HashMap<String, String> hm = result.get(0);
				// Getting latitude from the parsed data 
				double latitude = Double.parseDouble(hm.get("lat"));
				// Getting longitude from the parsed data
				double longitude = Double.parseDouble(hm.get("lng"));
				if(check==0)
				{
					reLatitude=latitude; 
					reLongitude=longitude;
				}
				if(check==1)
				{
					deLatitude=latitude;
					deLongitude=longitude;
				}
				//Toast.makeText(getApplicationContext(), longitude+"", Toast.LENGTH_SHORT).show();		
				
			}break;
			}
		}
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}
		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		}
		
    	
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.router, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
