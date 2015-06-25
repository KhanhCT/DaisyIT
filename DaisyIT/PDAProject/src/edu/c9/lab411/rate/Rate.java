package edu.c9.lab411.rate;
import java.util.ArrayList;
import java.util.HashMap;
import edu.c9.lab411.database.DatabaseHandle;
import edu.c9.lab411.ocr.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Rate extends Activity {
ListView lv_all_rate;
EfficientAdapter eAdapter;
private DatabaseHandle database_handle;
public ArrayList<HashMap<String, String>> list;
public ArrayList<HashMap<String, String>> list_rate;
private ArrayList<String> arr;
public static final String FIRST_COLUMN = "First";
public static final String SECOND_COLUMN = "Second";
public static final String THIRD_COLUMN = "Third";
public static final String FOR_COLUMN = "For";
public static final String FIVE_COLUMN = "Five";
public static final String SIX_COLUMN = "Six";
public static final String SEVENT_COLUMN = "Sevent";
public static final String EIGHT_COLUMN = "Eight";
public static final String NEIGH_COLUMN = "Neigh";
public static final String ID_RATE = "id";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rate);
		database_handle = new DatabaseHandle(this);
		list = new ArrayList<HashMap<String,String>>();
		list_rate = new ArrayList<HashMap<String,String>>();
		arr = new ArrayList<String>();
		lv_all_rate = (ListView)findViewById(R.id.lv_all_rate);
		int result = database_handle.getRateCount();
		if(result !=0){
			ArrayList<Examined> s = database_handle.getAllRate();
		for(int i = 0; i<s.size(); i++){
				HashMap<String, String> map = new HashMap<String, String>();
				
				map.put(FIRST_COLUMN, "0");
				map.put(SECOND_COLUMN, "0");
				map.put(THIRD_COLUMN, "0");
				map.put(FOR_COLUMN, "0");
				map.put(FIVE_COLUMN, "0");
				map.put(SIX_COLUMN , s.get(i).getName());
				arr.add(s.get(i).getId());
				list.add(map);
			}
			
		}else{
			
		}		
		eAdapter = new EfficientAdapter(this, list);
		lv_all_rate.setAdapter(eAdapter);
	}
	public void submitRate(View view){
		AlertDialog.Builder dialog_submit = new AlertDialog.Builder(Rate.this);
		dialog_submit.setTitle("Question");
		dialog_submit.setMessage("Are you make sure all check?");
		dialog_submit.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {	
					
					
				}
			});
		dialog_submit.setNegativeButton("No", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int arg1) {
					// TODO Auto-generated method stub
					dialog.cancel();
				}
			});
		dialog_submit.create().show();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rate, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Toast.makeText(this, list_rate.get(0).get("1") +list_rate.get(1).get("2"), Toast.LENGTH_LONG).show();
		}
		return super.onOptionsItemSelected(item);
	}
	
	@SuppressLint("InflateParams")
	public class EfficientAdapter extends BaseAdapter{
		private Context context;
		public ArrayList<HashMap<String, String>> list_;
		private LayoutInflater mInflater;
		private ViewHolder holder;

		public Context getContext() {
			return context;
		}
		public void setContext(Context context) {
			this.context = context;
		}
		public EfficientAdapter(Context context,  ArrayList<HashMap<String, String>> list) {
			// TODO Auto-generated constructor stub
			this.context = context;
			mInflater = LayoutInflater.from(context);
			this.list_ = list;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list_.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return list_.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public View getView(final int position, View convertView, ViewGroup arg2) {
			if(convertView == null){
				convertView = mInflater.inflate(R.layout.listview_colum_examin,null);
				holder = new ViewHolder();
				holder.txFirst = (CheckBox)convertView.findViewById(R.id.t61);
				holder.txSecond = (CheckBox)convertView.findViewById(R.id.t71);
				holder.txThird = (CheckBox)convertView.findViewById(R.id.t81);
				holder.txFor = (CheckBox)convertView.findViewById(R.id.t91);
				holder.txFive = (CheckBox)convertView.findViewById(R.id.t101);	
				holder.nameRate = (TextView)convertView.findViewById(R.id.nameRate);	
				holder.nameRate.setTextColor(Color.YELLOW);
				//holder.layout = (RelativeLayout)convertView.findViewById(R.id.layout_rate);
				convertView.setTag(holder);
				final HashMap<String, String> map = new HashMap<String, String>();
				holder.txFirst.setOnClickListener(new OnClickListener() {
					int pos = position;
					@Override
					public void onClick(View arg0) {
						map.put(arr.get(pos), FIRST_COLUMN);
						list_rate.add(map);
					}
				});
				
				
				holder.txSecond.setOnClickListener(new OnClickListener() {
					int pos = position;
					@Override
					public void onClick(View arg0) {
						map.put(arr.get(pos), SECOND_COLUMN);
						list_rate.add(map);
					}
				});
				
				holder.txThird.setOnClickListener(new OnClickListener() {
					int pos = position;
					@Override
					public void onClick(View arg0) {
						map.put(arr.get(pos), THIRD_COLUMN);
						list_rate.add(map);
					}
				});
				
				holder.txFor.setOnClickListener(new OnClickListener() {
					int pos = position;
					@Override
					public void onClick(View arg0) {
						map.put(arr.get(pos), FOR_COLUMN);
						list_rate.add(map);
					}
				});
				
				holder.txFive.setOnClickListener(new OnClickListener() {
					int pos = position;
					@Override
					public void onClick(View arg0) {
						map.put(arr.get(pos), FIVE_COLUMN);
						list_rate.add(map);
					}
				});
			}else{
				holder = (ViewHolder) convertView.getTag();
			}
			HashMap<String, String> map = list.get(position);
			if(map.get(FIRST_COLUMN).matches("1")){
				holder.txFirst.setChecked(true);
			}else{
				holder.txFirst.setChecked(false);
			}
			if(map.get(SECOND_COLUMN).matches("1")){
				holder.txSecond.setChecked(true);
			}else{
				holder.txSecond.setChecked(false);
			}
			if(map.get(THIRD_COLUMN).matches("1")){
				holder.txThird.setChecked(true);
			}else{
				holder.txThird.setChecked(false);
			}
			if(map.get(FOR_COLUMN).matches("1")){
				holder.txFor.setChecked(true);
			}else{
				holder.txFor.setChecked(false);
			}
			if(map.get(FIVE_COLUMN).matches("1")){
				holder.txFive.setChecked(true);
			}else{
				holder.txFive.setChecked(false);
			}
			holder.nameRate.setText(map.get(SIX_COLUMN));
			return convertView;
		}

		private class ViewHolder{
			TextView nameRate;
			CheckBox txFirst;
			CheckBox txSecond;
			CheckBox txThird;
			CheckBox txFor;
			CheckBox txFive;
		//	RelativeLayout layout;
		}
		
	}
}
