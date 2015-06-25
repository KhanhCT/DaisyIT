package edu.sfsu.cs.orange.googlemap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.example.tscdll.TSCActivity;

import org.json.JSONException;
import org.json.JSONObject;

import edu.sfsu.cs.orange.ocr.R;
import edu.sfsu.cs.orange.positionConduct.CurrentPosition;
import android.support.v4.app.FragmentActivity;
import android.telephony.SmsMessage;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import android.content.BroadcastReceiver;

public class GoogleMap extends FragmentActivity implements LocationListener{
	public static final String TAG="GoogleMAP";
	public com.google.android.gms.maps.GoogleMap googleMap;
	public static String title = "Location";
	public static final String macBluetooth="00:19:0E:A0:7A:78";
	double mLatitude=0;
	double mLongitude=0;
	ArrayList<Address> address;
	Geocoder geogle;
	String Mylocation="";
	public static final int REQUEST_CODE_ROUTER = 17;
	private int model=0;
	private String TYPE="";
	TSCActivity  TscDll = new TSCActivity();
	BroadcastReceiver receiver=null;
	Timer timerClearGoogleMap;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.googlemap);
		
		Log.v(TAG, "Google Map");
		 int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());       
	        if(status!=ConnectionResult.SUCCESS){ // Google Play Services are not available
	        	int requestCode = 10;
	            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
	            dialog.show();
	        }
	        else{
	        Log.i(TAG, "Starting....");
	    	SupportMapFragment fragment = ( SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.gMap);			
	    	// Getting Google Map
	    	final CurrentPosition currentPos = new CurrentPosition(GoogleMap.this);
	    	googleMap = fragment.getMap();
	    	geogle=new Geocoder(this);		
	        
	    	// Getting LocationManager object from System Service LOCATION_SERVICE
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            // Creating a criteria object to retrieve provider
            Criteria criteria = new Criteria();

            // Getting the name of the best provider
            String provider = locationManager.getBestProvider(criteria, true);

            // Getting Current Location From GPS
            Location location = locationManager.getLastKnownLocation(provider);

            if(location!=null){
                    onLocationChanged(location);
            }
            if(mLatitude!=0)
            {
            LatLng latLng = new LatLng(mLatitude, mLongitude);
	    	googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
			googleMap.animateCamera(CameraUpdateFactory.zoomTo(16));
            }
            googleMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
				
				@Override
				public void onInfoWindowClick(Marker marker) {
					// TODO Auto-generated method stub
					Intent i=new Intent(android.content.Intent.ACTION_SENDTO,Uri.parse("sms:"));
					i.putExtra("sms_body",marker.getTitle());
					startActivity(i);
				}
			});
            googleMap.setOnMapLongClickListener(new OnMapLongClickListener() {
				
				@Override
				public void onMapLongClick(LatLng point) {
					// TODO Auto-generated method stub
					googleMap.clear();
				}
			});
	        }
	        IntentFilter intentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
	        receiver = new BroadcastReceiver(){

				@Override
				public void onReceive(Context context, Intent intent) {
					// TODO Auto-generated method stub
					try {
						ProcessSms(context, intent);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}	        	
	        };
	        registerReceiver(receiver, intentFilter);
	        timerClearGoogleMap = new Timer();
	        ClearMap clearMap = new ClearMap();
	        timerClearGoogleMap.schedule(clearMap, 1000, 1000);
	        
	}

	private void ProcessSms(Context context, Intent intent) throws JSONException {
		String smsExtra = "pdus";
		//pdus để lấy gói tin nhắn
		Bundle bundle = intent.getExtras();
		Object []objArr= (Object[]) bundle.get(smsExtra);
		//bundle trả về tập các tin nhắn gửi về cùng lúc
		String sms="";
		//duyệt vòng lặp để đọc từng tin nhắn
		for(int i=0; i<objArr.length;i++){
			//lệnh chuyển đổi về tin nhắn createFromPdu
			 SmsMessage smsMsg=SmsMessage.createFromPdu((byte[]) objArr[i]);
			 String body=smsMsg.getMessageBody();
			 String phoneNum = smsMsg.getOriginatingAddress();
			 JSONObject json = new JSONObject(body);
			 double lat = json.getDouble("Latitude");
			 double log = json.getDouble("Longitude");
			 try {
					address = (ArrayList<Address>) geogle.getFromLocation(lat, log, 3);
					String item1 ="";
					item1 = address.get(0).getAddressLine(0);
					if(item1.equals("")){
						Toast.makeText(getApplicationContext(), "Cannot Find My Location", Toast.LENGTH_SHORT).show();
					}
					else
					{
					//use Marker
						Toast.makeText(getApplicationContext(), item1, Toast.LENGTH_SHORT).show();
						String myLocation = item1;
						MarkerOptions marker=new MarkerOptions();
				    	marker.position(new LatLng(lat, log));
				    	marker.title(myLocation);
				    	marker.snippet(phoneNum);
				    	marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));			    	
				    	//googleMap.clear();
				    	googleMap.addMarker(marker);
					}
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Log.i(TAG,"Not Address!!");
				}
		}
		
		
	}
	@Override
	protected void onResumeFragments() {
		// TODO Auto-generated method stub
		super.onResumeFragments();
	}

	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		// TODO Auto-generated method stub
		super.onActivityResult(arg0, arg1, arg2);
		if(arg0== REQUEST_CODE_ROUTER)
		{
			if(arg1==7)
			{
				double relat=0,relag=0,delat=0,delag=0;
				relat=arg2.getDoubleExtra("reLatitude", 0);
				relag=arg2.getDoubleExtra("reLongitude", 0);
				delat=arg2.getDoubleExtra("deLatitude", 0);
				delag=arg2.getDoubleExtra("deLongitude",0);
				TYPE=arg2.getStringExtra("Type_Vehicle");
				model=arg2.getIntExtra("colorPath",1);
				Log.d("toa do",relat+":"+relag+":"+delat+":"+delag);
				LatLng origin = new LatLng(relat, relag);
				LatLng dest = new LatLng(delat, delag);
				//mGoogleMap.clear();
				MarkerOptions mar=new MarkerOptions();
				mar.position(origin);
				googleMap.addMarker(mar);
				MarkerOptions mar1=new MarkerOptions();
				mar1.position(dest);
				
				googleMap.addMarker(mar1);
				
				// Getting URL to the Google Directions API
				String url = getDirectionsUrl(origin, dest);				
				
				DownloadTask downloadTask = new DownloadTask();
				
				// Start downloading json data from Google Directions API
				downloadTask.execute(url);
			}
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.google_map, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id) {
		case R.id.action_settings:{
			TscDll.closeport();
		}break;
		case R.id.router:{
			Log.i(TAG, "starting ....");
			Bundle bundle = new Bundle();
			bundle.putDouble("latitude", mLatitude);
			bundle.putDouble("longitude", mLongitude);
			Intent intent = new Intent(GoogleMap.this, Router.class);
			intent.putExtras(bundle);
			startActivityForResult(intent, REQUEST_CODE_ROUTER);
			Log.i(TAG, "start activity...");
		}break;
		case R.id.nearby:{
			
		}break;
		case R.id.viewLocation:{
			try {
				address = (ArrayList<Address>) geogle.getFromLocation(mLatitude, mLatitude, 3);
				String item1 ="";
				item1 = address.get(0).getAddressLine(0);
				if(item1.equals("")){
					Toast.makeText(getApplicationContext(), "Cannot Find My Location", Toast.LENGTH_SHORT).show();
				}
				else
				{
					//use Marker
					Toast.makeText(getApplicationContext(), item1, Toast.LENGTH_SHORT).show();
					String myLocation = item1;
					MarkerOptions marker=new MarkerOptions();
			    	marker.position(new LatLng(mLatitude, mLongitude));
			    	marker.title(myLocation);
			    	marker.snippet("Chu Trong Khanh");
			    	marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));			    	
			    	googleMap.clear();
			    	googleMap.addMarker(marker);
				}
				
			} catch (IOException e) {
				e.printStackTrace();
				Log.i(TAG,"Not Address!!");
			}
			
		}break;
		case R.id.printLocation:{
			String text = "Kinh do: "+Double.toString(mLatitude);
			String text1= "Vi do:"+ Double.toString(mLongitude);        	
	    	TscDll.openport("00:19:0E:A0:7A:78");	            	
	    	TscDll.setup(70, 110, 4, 4, 0, 0, 0);
        	TscDll.clearbuffer();
        	TscDll.sendcommand("SET TEAR ON\n");
        	TscDll.sendcommand("SET COUNTER @1 1\n");
        	TscDll.sendcommand("@1 = \"0001\"\n");
        	TscDll.sendcommand("TEXT 100,300,\"3\",0,1,1,@1\n");
        	TscDll.barcode(100, 100, "128", 100, 1, 0, 3, 3, "123456789");
        	//TscDll.printerfont(100, 250, "3", 0, 1, 1, "987654321");
        	TscDll.printerfont(100, 250, "3", 0, 1, 1, text);
        	TscDll.printerfont(100, 350, "3", 0, 1, 1, text1);
        	TscDll.printlabel(2, 1);	            	       	
		}break;
		case R.id.vehicles:{
			
		}break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
			mLatitude = location.getLatitude();
			mLongitude = location.getLongitude();
//			MarkerOptions marker=new MarkerOptions();
//	    	marker.position(new LatLng(mLatitude, mLongitude));
//	    	marker.title(title);
//	    	marker.snippet("My Location");
//	    	marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
//	    	googleMap.clear();
//	    	googleMap.addMarker(marker);    	
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}
private String getDirectionsUrl(LatLng origin,LatLng dest){
		
		// Origin of route
		String str_origin = "origin="+origin.latitude+","+origin.longitude;	
		// Destination of route
		String str_dest = "destination="+dest.latitude+","+dest.longitude;						
		// Sensor enabled
		String sensor = "sensor=false";					
		// Travelling Mode
		String mode = "mode=driving";			
		switch (model) {
		case 1:
			mode = "mode=driving";
			break;
		case 2:
			mode = "mode=walking";
			break;
		default:
			mode = "mode=bus";
			break;
		}				
		// Building the parameters to the web service
		String parameters = str_origin+"&"+str_dest+"&"+sensor+"&"+mode;				
		// Output format
		String output = "json";	
		// Building the url to the web service
		String url = "https://maps.googleapis.com/maps/api/directions/"+output+"?"+parameters;	
		return url;
	}
	
	
	/** A method to download json data from url */
    private String downloadUrl(String strUrl) throws IOException{
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

	
	
	// Fetches data from url passed
	private class DownloadTask extends AsyncTask<String, Void, String>{			
				
		// Downloading data in non-ui thread
		@Override
		protected String doInBackground(String... url) {
				
			// For storing data from web service
			String data = "";
					
			try{
				// Fetching the data from web service
				data = downloadUrl(url[0]);
				Log.d("url", data);
			}catch(Exception e){
				Log.d("Background Task",e.toString());
			}
			return data;		
		}
		
		// Executes in UI thread, after the execution of
		// doInBackground()
		@Override
		protected void onPostExecute(String result) {			
			super.onPostExecute(result);			
			
			ParserTask parserTask = new ParserTask();
			
			// Invokes the thread for parsing the JSON data
			parserTask.execute(result);
				
		}		
	}
	private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>>{

		@Override
		protected List<List<HashMap<String, String>>> doInBackground(
				String... jsonData) {
			JSONObject jObject;	
			List<List<HashMap<String, String>>> routes = null;			           
            
            try{
            	jObject = new JSONObject(jsonData[0]);
            	DirectionsJSONParser parser = new DirectionsJSONParser();
            	
            	// Starts parsing data
            	routes = parser.parse(jObject);    
            }catch(Exception e){
            	e.printStackTrace();
            }
            return routes;
		}

		@Override
		protected void onPostExecute(List<List<HashMap<String, String>>> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			ArrayList<LatLng> points = null;
			PolylineOptions lineOptions = null;
			MarkerOptions markerOptions = new MarkerOptions();
			
			// Traversing through all the routes
			for(int i=0;i<result.size();i++){
				points = new ArrayList<LatLng>();
				lineOptions = new PolylineOptions();				
				// Fetching i-th route
				List<HashMap<String, String>> path = result.get(i);				
				// Fetching all the points in i-th route
				for(int j=0;j<path.size();j++){
					HashMap<String,String> point = path.get(j);									
					double lat = Double.parseDouble(point.get("lat"));
					double lng = Double.parseDouble(point.get("lng"));
					LatLng position = new LatLng(lat, lng);						
					points.add(position);						
				}				
				// Adding all the points in the route to LineOptions
				lineOptions.addAll(points);
				lineOptions.width(3);
				// Changing the color polyline according to the mode
				if(model==1)
					lineOptions.color(Color.RED);
				else if(model==2)
					lineOptions.color(Color.GREEN);
				else if(model==3)
					lineOptions.color(Color.BLUE);				
			}
			
			if(result.size()<1){
				Toast.makeText(getBaseContext(), "No Points", Toast.LENGTH_SHORT).show();
				return;
			}
			
			// Drawing polyline in the Google Map for the i-th route
			googleMap.addPolyline(lineOptions);
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
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		//TscDll.closeport();
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		if(timerClearGoogleMap !=null){
			timerClearGoogleMap.cancel();
			timerClearGoogleMap =null;
		}
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
	}
	public class ClearMap extends TimerTask{

		@Override
		public void run() {
			runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					//googleMap.clear();
					try {
						address = (ArrayList<Address>) geogle.getFromLocation(mLatitude, mLatitude, 3);
						String item1 ="";
						item1 = address.get(0).getAddressLine(0);
						if(item1.equals("")){
							Toast.makeText(getApplicationContext(), "Cannot Find My Location", Toast.LENGTH_SHORT).show();
						}
						else
						{
							//use Marker
							Toast.makeText(getApplicationContext(), item1, Toast.LENGTH_SHORT).show();
							String myLocation = item1;
							MarkerOptions marker=new MarkerOptions();
					    	marker.position(new LatLng(mLatitude, mLongitude));
					    	marker.title(myLocation);
					    	marker.snippet("Chu Trong Khanh");
					    	marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));			    	
					    	googleMap.clear();
					    	googleMap.addMarker(marker);
						}
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						Log.i(TAG,"Not Address!!");
					}
				}
			});
		
		}
		
	}
	
}
