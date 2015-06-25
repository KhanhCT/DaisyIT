package edu.sfsu.cs.orange.database;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import edu.sfsu.cs.orange.goodssearch.JSONParser;
import edu.sfsu.cs.orange.ocr.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class DeleteTable extends Activity {
	Button btn_delete;
	EditText ed_table, ed_id, ed_count;
	TextView lb_status;
	String table, id;
	DatabaseHandle database_handle;
	JSONParser json_parser;
	private static final String TABLE_GOODS = "goods";
	private static final String TABLE_WARE_HOUSE = "ware_houses";
	private static final String TABLE_GROUP = "groups";
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_ID = "id";
	private static final String TAG_ID_WARE = "id_ware_house";
	private static final String TAG_ID_GROUP = "id_group_mer";
	private static final String TAG_ID_MER = "id_mer";
	private static final String DATABASE_TABLE = "table";
	private static final String TAG_URL = "http://trongkhanhbkhn.meximas.com/Server/Model/delete_product.php";
	private static final String TAG_URL1 = "http://trongkhanhbkhn.meximas.com/Server/Model/delete_database.php";
	int result, success;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.delete_table);
		json_parser = new JSONParser();
		database_handle = new DatabaseHandle(this);
		btn_delete = (Button)findViewById(R.id.btn_dele_delete);
		ed_table = (EditText)findViewById(R.id.delete_table);
		ed_count = (EditText)findViewById(R.id.delete_id);
		btn_delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				deleteProduct();
			}
		});
	}

	/**
	 * this Function delete product in sqlite an sql server
	 */
	public void deleteProduct(){
		int result;
		// create array list is contain id an table name 
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		table = ed_table.getText().toString().trim();
		id = ed_id.getText().toString().trim();
		if(!table.matches("") && id.matches("")){
			// clear ArrayList
			params.clear();
			// call deleteTable and return row number delete
			result = database_handle.deleteTable(table);
			
			params.add(new BasicNameValuePair(DATABASE_TABLE, table));
			// use json to tranfer query to sql server
			// create request to sql server
			JSONObject json_object1 = json_parser.makeHttpRequest(TAG_URL1, "POST", params);
			
			ed_count.setText(String.valueOf(result));	
			try{
				success = json_object1.getInt(TAG_SUCCESS);			
			}catch(JSONException e){
				e.printStackTrace();
			}
			if(success ==1){
				ed_count.setText(String.valueOf(result) + "-success");
			}
			else{
				ed_count.setText(String.valueOf(result) + "-" + "Error:" +String.valueOf(success));
			}
				
		}
		else if(!table.matches("") && !id.matches("")){
			params.clear();
			if(!table.matches(TABLE_GOODS)){
				result = database_handle.deleteProduct(TAG_ID_MER, table);			
				params.add(new BasicNameValuePair(DATABASE_TABLE, table));
				params.add(new BasicNameValuePair(TAG_ID, id));
				JSONObject json_object = json_parser.makeHttpRequest(TAG_URL, "POST", params);
				try{
					success = json_object.getInt(TAG_SUCCESS);			
				}catch(JSONException e){
					e.printStackTrace();
				}
				if(success ==1){
					ed_count.setText(String.valueOf(result) + "-success");
				}
				else{
					ed_count.setText(String.valueOf(result) + "-" + "Error:" +String.valueOf(success));
				}			
			}else if(!table.matches(TABLE_GROUP)){
				result = database_handle.deleteProduct(TAG_ID_GROUP, table);			
				params.add(new BasicNameValuePair(DATABASE_TABLE, table));
				params.add(new BasicNameValuePair(TAG_ID, id));
				JSONObject json_object = json_parser.makeHttpRequest(TAG_URL, "POST", params);
				try{
					success = json_object.getInt(TAG_SUCCESS);			
				}catch(JSONException e){
					e.printStackTrace();
				}
				if(success ==1){
					ed_count.setText(String.valueOf(result) + "-success");
				}
				else{
					ed_count.setText(String.valueOf(result) + "-" + "Error:" +String.valueOf(success));
				}		
			}else if(!table.matches(TABLE_WARE_HOUSE)){
				result = database_handle.deleteProduct(TAG_ID_WARE, table);			
				params.add(new BasicNameValuePair(DATABASE_TABLE, table));
				params.add(new BasicNameValuePair(TAG_ID, id));
				JSONObject json_object = json_parser.makeHttpRequest(TAG_URL, "POST", params);
				try{
					success = json_object.getInt(TAG_SUCCESS);			
				}catch(JSONException e){
					e.printStackTrace();
				}
				if(success ==1){
					ed_count.setText(String.valueOf(result) + "-success");
				}
				else{
					ed_count.setText(String.valueOf(result) + "-" + "Error:" +String.valueOf(success));
				}	
			}else{
				ed_count.setText("Input Table");
			}		
		}
		else{
			ed_count.setText("Input Table");
		}
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.delete_table, menu);
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
