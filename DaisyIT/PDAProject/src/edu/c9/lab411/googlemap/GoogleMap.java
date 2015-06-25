package edu.c9.lab411.googlemap;

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

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.c9.lab411.Cart.JSONParser;
import edu.c9.lab411.database.DatabaseHandle;
import edu.c9.lab411.ocr.R;
import edu.c9.lab411.partner.PartnerLocGo;
import edu.c9.lab411.partner.PartnerLocation;
import android.support.v4.app.FragmentActivity;
import android.telephony.SmsMessage;
import android.app.Dialog;
import android.app.ProgressDialog;
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
	private static final String TABLE = "googleMap";
	private static final String TABLE_PARTNER_LOC = "Partner_Loc";
	private static final String TABLE_ID = "id";
	private static final String TABLE_NAME = "name";
	private static final String TABLE_ADD = "address";
	private static final String TABLE_DES= "description";
	private static final String TABLE_IMAGE = "image";
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_PLOCA_CODE = "PLoca_Code";
	private static final String TAG_PARTNER_ID = "Partner_ID";
	private static final String TAG_PLOCA_NAME = "PLoca_Name";
	private static final String TAG_PLOCA_ADDRESS = "PLoca_Address";
	private static final String TAG_PLACE_ID = "Place_ID";
	private static final String TAG_PLOCA_ID = "PLoca_ID";
	private static final String TAG_LONGITUDE = "Longitude";
	private static final String TAG_LATITUDE = "Latitude";
	private static final String TAG_STATUS = "Status";
	private static final String TAG_PLOCA_TYPE = "PLoca_Type";
			
	private static final String TAG_URL = "http://trongkhanhbkhn.meximas.com/Server/Model/get_google_map.php";
	JSONParser json_parser;
	ArrayList<String> list;
	double mLatitude=0;
	double mLongitude=0;
	ProgressDialog myProgress;
	ArrayList<Address> address;
	Geocoder geogle;
	String Mylocation="";
	int success;
	public static final int REQUEST_CODE_ROUTER = 17;
	private int model=0;
	@SuppressWarnings("unused")
	private String TYPE=null;
	TSCActivity  TscDll = new TSCActivity();
	BroadcastReceiver receiver=null;
	Timer timerClearGoogleMap;
	ArrayList<HashMap<String, String>> list_partner_location;
	DatabaseHandle databases_handle;
	String id_ ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.googlemap);
		databases_handle = new DatabaseHandle(this);
		Log.v(TAG, "Google Map");
		json_parser = new JSONParser();
		list =new ArrayList<String>();
		 list_partner_location = new ArrayList<HashMap<String,String>>();
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
	        timerClearGoogleMap.schedule(clearMap, 1000, 120000);
	        
	}
	public ArrayList<String> getGoogleMapAddress(String id) throws JSONException{
		// create array list is contain id an table name 		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		ArrayList<String> list = new ArrayList<String>();
		params.add(new BasicNameValuePair(TABLE_ID, id));
		final JSONObject json = json_parser.makeHttpRequest(TAG_URL, "GET", params);
		try{
			success = json.getInt(TAG_SUCCESS)	;
		}catch(JSONException e){
			e.printStackTrace();
		}
		if(success == 1){
			JSONArray products = json.getJSONArray(TABLE);
			JSONObject product = products.getJSONObject(0);
			list.add(product.getString(TABLE_NAME));
			list.add(product.getString(TABLE_ADD));
			list.add(product.getString(TABLE_DES));
			list.add(product.getString(TABLE_IMAGE));
		}
		return list;
	}
	public ArrayList<HashMap<String, String>> getPartnerLocation(String longitude , String latitude){
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		PartnerLocation pLocation = new PartnerLocation();
		databases_handle.deleteTable(TABLE_PARTNER_LOC);
		params.add(new BasicNameValuePair(TAG_LONGITUDE, longitude));
		params.add(new BasicNameValuePair(TAG_LATITUDE, latitude));
		ArrayList<HashMap<String, String>> list_partner_location_ = new ArrayList<HashMap<String,String>>();
		final JSONObject json = json_parser.makeHttpRequest(TAG_URL, "GET", params);
		try {
			success = json.getInt(TAG_SUCCESS);
			if(success ==1){
				JSONArray products = json.getJSONArray(TABLE_PARTNER_LOC);
				for(int i = 0; i<products.length(); i++){
					JSONObject item = products.getJSONObject(i);
					String PLoca_ID = item.getString(TAG_PLOCA_ID);
					String PLoca_Code = item.getString(TAG_PLOCA_CODE);
					String Partner_ID = item.getString(TAG_PARTNER_ID);
					String PLoca_Name = item.getString(TAG_PLOCA_NAME);
					String PLoca_Address = item.getString(TAG_PLOCA_ADDRESS);
					String Place_ID = item.getString(TAG_PLACE_ID);
					String Ploca_Type = item.getString(TAG_PLOCA_TYPE);
					String Longitude = item.getString(TAG_LONGITUDE);
					String Latitude = item.getString(TAG_LATITUDE);
					String Status = item.getString(TAG_STATUS);
					pLocation.setPLoca_ID(Integer.parseInt(PLoca_ID));
					pLocation.setPLoca_Code(PLoca_Code);
					pLocation.setPartner_ID(Integer.parseInt(Partner_ID));
					pLocation.setPLoca_Name(PLoca_Name);
					pLocation.setPLoca_Address(PLoca_Address);
					pLocation.setPlace_ID(Integer.parseInt(Place_ID));
					pLocation.setPLoca_Type(Ploca_Type);
					pLocation.setLongitude(Double.parseDouble(Longitude));
					pLocation.setLatitude(Double.parseDouble(Latitude));
					pLocation.setStatus(Boolean.parseBoolean(Status));
					databases_handle.addPartnerLocation(pLocation);
					HashMap<String, String> map = new HashMap<String, String>();
					map.put(TAG_PLOCA_ID, PLoca_ID);
					map.put(TAG_PLOCA_CODE, PLoca_Code);
					map.put(TAG_PARTNER_ID, Partner_ID);
					map.put(TAG_PLOCA_NAME, PLoca_Name);
					map.put(TAG_PLOCA_ADDRESS, PLoca_Address);
					map.put(TAG_PLACE_ID, Place_ID);
					map.put(TAG_PLOCA_TYPE, Ploca_Type);
					map.put(TAG_LONGITUDE, Longitude);
					map.put(TAG_LATITUDE, Latitude);
					map.put(TAG_STATUS, Status);
					list_partner_location_.add(map);			
				}
				
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return list_partner_location_;
		
	}
	private void ProcessSms(Context context, Intent intent) throws JSONException {
		String smsExtra = "pdus";
		//pdus để lấy gói tin nhắn
		Bundle bundle = intent.getExtras();
		Object []objArr= (Object[]) bundle.get(smsExtra);
		//bundle trả về tập các tin nhắn gửi về cùng lúc
		//duyệt vòng lặp để đọc từng tin nhắn
		for(int i=0; i<objArr.length;i++){
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
						Toast.makeText(getApplicationContext(), phoneNum, Toast.LENGTH_SHORT).show();
						String myLocation = item1;
						MarkerOptions marker=new MarkerOptions();
				    	marker.position(new LatLng(lat, log));
				    	marker.title(myLocation);
				    	marker.snippet(phoneNum);
				    	marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));			    	
				    	try {
							list = getGoogleMapAddress(phoneNum);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
//					    Marker currentMarker= googleMap.addMarker(marker);
//					    ImageLoadTask imgTask=new ImageLoadTask(this,list.get(3),googleMap,currentMarker, list.get(0), list.get(1), list.get(2));
//				    	imgTask.execute();
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
		case R.id.partner_location:{
			double Longitude =0 ;
			double Latitude = 0;
			ArrayList<PartnerLocation> list = databases_handle.getAllPartnerLocation();
			PartnerLocGo partner = new PartnerLocGo();
			for(int i = 0; i<list.size(); i++){
				String PLoca_ID  = String.valueOf(list.get(i).getPLoca_ID());
				Longitude = list.get(i).getLongitude();
				Latitude = list.get(i).getLatitude();
				String PLoca_Code = list.get(i).getPLoca_Code();
				String PLoca_Name = list.get(i).getPLoca_Name();
				String Partner_ID =String.valueOf(list.get(i).getPartner_ID());
				String PLoca_Address = list.get(i).getPLoca_Address();
				String Place_ID = String.valueOf(list.get(i).getPlace_ID());
				String PLoca_Type = list.get(i).getPLoca_Type();
				partner.setPartnerID(list.get(i).getPartner_ID());
				partner.setPloco_id(list.get(i).getPLoca_ID());
				partner.setPlace_id(list.get(i).getPlace_ID());
				databases_handle.addPartnerLoGo(partner);
				MarkerOptions option=new MarkerOptions();				
				option.title("Partner Location");
				option.snippet("Partner Location");
				option.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
				LatLng latLng = new LatLng(Latitude, Longitude);
				option.position(latLng);
				Marker currentMarker= googleMap.addMarker(option);
				String Url =null;
				if(i ==1){
					Url= "http://www.exelisvis.com/docs/html/images/iimage_example2.gif";
				}else{
					Url ="http://www.physics.nyu.edu/grierlab/idl_html_help/images/iimage_example321.gif"; 
				}			
				ImageLoadTask imageTask = new ImageLoadTask(this, Url, googleMap, currentMarker, PLoca_ID, PLoca_Code, Partner_ID, PLoca_Type, PLoca_Name, PLoca_Address, Place_ID);
				imageTask.execute();				
			}
			Toast.makeText(this, String.valueOf(Longitude) + "   "+String.valueOf(Latitude), Toast.LENGTH_LONG).show();
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
		case R.id.clearmap:{
			googleMap.clear();
			
		}break;
		case R.id.viewLocation:{
			try {
				address = (ArrayList<Address>) geogle.getFromLocation(mLatitude, mLatitude, 3);
				if(address == null||address.isEmpty()){
					Log.i(TAG,"Empty");
				}
				String item1 =null;
				item1 = address.get(0).getAddressLine(0);
				if(item1.equals(null))
					Toast.makeText(getApplicationContext(), "Cannot Find My Location", Toast.LENGTH_SHORT).show();
				else
					Toast.makeText(getApplicationContext(), item1, Toast.LENGTH_SHORT).show();
				String myLocation = item1;
				MarkerOptions marker=new MarkerOptions();
			    marker.position(new LatLng(mLatitude, mLongitude));
			    marker.title(myLocation);
			    marker.snippet("Chu Trong Khanh");
			    marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));			    	
		    	try {
					list = getGoogleMapAddress("1");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    googleMap.addMarker(marker);

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
		//    googleMap.clear();
			mLatitude = location.getLatitude();
			mLongitude = location.getLongitude();
//			MarkerOptions marker=new MarkerOptions();
//	    	marker.position(new LatLng(mLatitude, mLongitude));
//	    	marker.title(title);
//	    	marker.snippet("My Location");
//	    	marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
//	    	Marker currentMarker= googleMap.addMarker(marker);
//	    	
//	    	list_partner_location = getPartnerLocation();
//
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
					googleMap.clear();
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
