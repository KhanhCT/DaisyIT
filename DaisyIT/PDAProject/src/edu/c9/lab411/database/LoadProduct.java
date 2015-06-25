package edu.c9.lab411.database;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import edu.c9.lab411.goods.SKUItems;
import edu.c9.lab411.recieve.barcode.Barcode;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
public class LoadProduct {
	private static final String TABLE_Barcode = "Barcode";
	private static final String TABLE_SKU_Items = "SKU_Items";
	private Context context;
	private DatabaseHandle db;
	private ProgressDialog pDialog;
	private static final String TAG_URL = "http://trongkhanhbkhn.meximas.com/Server/Model/get_all_products.php";
	private static final String TAG_URL1= "http://trongkhanhbkhn.meximas.com/Server/Model/get_all_barcode.php";
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_SKUItems = "skuitems";
	private static final String TAG_Barcode = "Barcode";
	private static final String TAG_SKU_Code = "SKU_Code";
	private static final String TAG_Product_Name = "Product_Name";
	private static final String TAG_Good_ID = "Good_ID";
	private JSONArray products = null;

	JSONParser jParser = new JSONParser();
	ArrayList<HashMap<String, String>> productsList;
	public LoadProduct(Context context) {
		this.context = context;
		db  = new DatabaseHandle(context);	

	}
	public void loadSkuCode(){
		new LoadSkuItems().execute();
	}
	public void loadBarcode(){
		new LoadBarcode().execute();
	}
	class LoadBarcode extends AsyncTask<String, String, String>{
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(context);
			pDialog.setMessage("Loading SKU Items. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}
		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			db.deleteTable(TABLE_Barcode);
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			JSONObject json = jParser.makeHttpRequest(TAG_URL1, "GET", params);
			try {
				int success = json.getInt(TAG_SUCCESS);
				if(success ==1){
					products = json.getJSONArray("barcode");
					for(int i = 0; i<products.length(); i++){
						JSONObject jsonObj = products.getJSONObject(i);
						String barcode = jsonObj.getString(TAG_Barcode);
						int goodId = jsonObj.getInt(TAG_Good_ID);
						String skucode = jsonObj.getString(TAG_SKU_Code);
						Barcode b = new Barcode();
						b.setSkuCode(skucode);
						b.setGoodID(goodId);
						b.setBarcode(barcode);
						db.addBarcode(b);					
					}
				}else{
					
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pDialog.dismiss();
		}
		
	}
	
	class LoadSkuItems extends AsyncTask<String, String, String>{
		
		
		@Override
		protected void onCancelled() {
			// TODO Auto-generated method stub
			super.onCancelled();
		}

		@Override
		protected void onCancelled(String result) {
			// TODO Auto-generated method stub
			super.onCancelled(result);
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pDialog.dismiss();
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(context);
			pDialog.setMessage("Loading SKU Items. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		@Override
		protected void onProgressUpdate(String... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		}

		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			
			db.deleteTable(TABLE_SKU_Items);
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			JSONObject json = jParser.makeHttpRequest(TAG_URL, "GET", params);
			try {
				int success = json.getInt(TAG_SUCCESS);
				if(success ==1){
					products = json.getJSONArray(TAG_SKUItems);
					for(int i = 0; i<products.length(); i++){
						JSONObject jsonObj = products.getJSONObject(i);
						String skuCode = jsonObj.getString(TAG_SKU_Code);
						int goodId = jsonObj.getInt(TAG_Good_ID);
						String productName = jsonObj.getString(TAG_Product_Name);
						SKUItems sku = new SKUItems();
						sku.setSkuCode(skuCode);
						sku.setGoodID(goodId);
						sku.setProductName(productName);
						db.addSKUItems(sku);					
					}
				}else{
					
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
	}
}
