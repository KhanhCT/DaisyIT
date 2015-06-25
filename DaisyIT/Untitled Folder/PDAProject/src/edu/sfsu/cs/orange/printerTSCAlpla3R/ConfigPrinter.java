package edu.sfsu.cs.orange.printerTSCAlpla3R;

import edu.sfsu.cs.orange.ocr.R;
import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ConfigPrinter extends Activity {
	Spinner sizeSpinner, speedSpinner, macSpinner, widthSpinner, heightSpinner, densitySpinner,sensorSpinner, distanceSpinner, offsetSpinner;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.config_printer);	
		final String arrMac[] = getResources().getStringArray(R.array.macArray);
		final String arrSize[] = getResources().getStringArray(R.array.sizeArray);
		final String arrSpeed[] = getResources().getStringArray(R.array.speedArray);
		final String arrWidth[] = getResources().getStringArray(R.array.widthArray);
		final String arrHeight[] = getResources().getStringArray(R.array.heightArray);
		final String arrDensity[] = getResources().getStringArray(R.array.densityArray);
		final String arrSensor[] = getResources().getStringArray(R.array.sensorArray);
		final String arrDistance[] = getResources().getStringArray(R.array.distanceArray);
		final String arrOffset[] = getResources().getStringArray(R.array.offsetArray);
		/**
		 * process macSpinner
		 */
		macSpinner = (Spinner)findViewById(R.id.macSp);
		ArrayAdapter<String> macAdapter = new ArrayAdapter<String>
		(
				this, android.R.layout.simple_spinner_item, arrMac
		);
		macAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		macSpinner.setAdapter(macAdapter);
		macSpinner.setOnItemSelectedListener(new MacProcessEvent());
		/**
		 * process sizeSpinner
		 */
		sizeSpinner = (Spinner)findViewById(R.id.sizeSp);
		ArrayAdapter<String> sizeAdapter = new ArrayAdapter<String>
		(
				this, android.R.layout.simple_spinner_item, arrSize
		);
		sizeAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		sizeSpinner.setAdapter(sizeAdapter);
		sizeSpinner.setOnItemSelectedListener(new SizeProcessEvent());	
		/**
		 * process speedSpinner
		 */
		speedSpinner = (Spinner)findViewById(R.id.speedSp);
		ArrayAdapter<String> speedAdapter = new ArrayAdapter<String>
		(
				this, android.R.layout.simple_spinner_item, arrSpeed
		);
		speedAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		speedSpinner.setAdapter(speedAdapter);
		speedSpinner.setOnItemSelectedListener(new SpeedProcessEvent());	
		/**
		 * process widthSpinner
		 */
		widthSpinner = (Spinner)findViewById(R.id.widthSp);
		ArrayAdapter<String> widthAdapter = new ArrayAdapter<String>
		(
				this, android.R.layout.simple_spinner_item, arrWidth
		);
		widthAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		widthSpinner.setAdapter(sizeAdapter);
		widthSpinner.setOnItemSelectedListener(new WidthProcessEvent());
		/**
		 * process heightSpinner
		 */
		heightSpinner = (Spinner)findViewById(R.id.heightSp);
		ArrayAdapter<String> heightAdapter = new ArrayAdapter<String>
		(
				this, android.R.layout.simple_spinner_item, arrHeight
		);
		heightAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		heightSpinner.setAdapter(heightAdapter);
		heightSpinner.setOnItemSelectedListener(new HeightProcessEvent());
		/**
		 * process densitySpinner
		 */
		densitySpinner = (Spinner)findViewById(R.id.densitySp);
		ArrayAdapter<String> densityAdapter = new ArrayAdapter<String>
		(
				this, android.R.layout.simple_spinner_item, arrDensity
		);
		densityAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		densitySpinner.setAdapter(densityAdapter);
		densitySpinner.setOnItemSelectedListener(new DensityProcessEvent());
		/**
		 * process sensorSpinner
		 */
		sensorSpinner = (Spinner)findViewById(R.id.sensorSp);
		ArrayAdapter<String> sensorAdapter = new ArrayAdapter<String>
		(
				this, android.R.layout.simple_spinner_item, arrSensor
		);
		sensorAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		sensorSpinner.setAdapter(sensorAdapter);
		sensorSpinner.setOnItemSelectedListener(new SensorProcessEvent());
		/**
		 * process distanceSpinner
		 */
		distanceSpinner = (Spinner)findViewById(R.id.distanceSp);
		ArrayAdapter<String> distanceAdapter = new ArrayAdapter<String>
		(
				this, android.R.layout.simple_spinner_item, arrDistance
		);
		distanceAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		distanceSpinner.setAdapter(distanceAdapter);
		distanceSpinner.setOnItemSelectedListener(new DistanceProcessEvent());
		/**
		 * process offsetSpinner
		 */
		offsetSpinner = (Spinner)findViewById(R.id.offsetSp);
		ArrayAdapter<String> offsetAdapter = new ArrayAdapter<String>
		(
				this, android.R.layout.simple_spinner_item, arrOffset
		);
		offsetAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		offsetSpinner.setAdapter(distanceAdapter);
		offsetSpinner.setOnItemSelectedListener(new OffsetProcessEvent());
	}
	private class SizeProcessEvent implements OnItemSelectedListener{

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1,
				int arg2, long arg3) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub			
		}		
	}
	
	private class MacProcessEvent implements OnItemSelectedListener{

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1,
				int arg2, long arg3) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub			
		}		
	}
	private class SpeedProcessEvent implements OnItemSelectedListener{

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1,
				int arg2, long arg3) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub			
		}		
	}
	private class WidthProcessEvent implements OnItemSelectedListener{

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1,
				int arg2, long arg3) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub			
		}		
	}
	private class HeightProcessEvent implements OnItemSelectedListener{

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1,
				int arg2, long arg3) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub			
		}		
	}
	private class DensityProcessEvent implements OnItemSelectedListener{

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1,
				int arg2, long arg3) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub			
		}		
	}
	private class SensorProcessEvent implements OnItemSelectedListener{

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1,
				int arg2, long arg3) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub			
		}		
	}
	private class DistanceProcessEvent implements OnItemSelectedListener{

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1,
				int arg2, long arg3) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub			
		}		
	}
	private class OffsetProcessEvent implements OnItemSelectedListener{

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1,
				int arg2, long arg3) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub			
		}		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.config_printer, menu);
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
