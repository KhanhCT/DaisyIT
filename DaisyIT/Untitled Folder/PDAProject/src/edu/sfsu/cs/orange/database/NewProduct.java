package edu.sfsu.cs.orange.database;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import edu.sfsu.cs.orange.goodssearch.Goods;
import edu.sfsu.cs.orange.goodssearch.Groups;
import edu.sfsu.cs.orange.goodssearch.JSONParser;
import edu.sfsu.cs.orange.goodssearch.Product;
import edu.sfsu.cs.orange.goodssearch.WareGoods;
import edu.sfsu.cs.orange.goodssearch.WareHouse;
import edu.sfsu.cs.orange.ocr.R;
import edu.sfsu.cs.orange.ocr.R.layout;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;


public class NewProduct extends Activity {
    DatabaseHandle database_handle;
	// JSON Node names
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_ID_WARE = "id_ware_house";
	private static final String TAG_NAME_WARE = "name_ware_house";
	private static final String TAG_ID_GROUP = "id_group_mer";
	private static final String TAG_NAME_GROUP = "name_group_mer";
	private static final String TAG_ID_MER = "id_mer";
	private static final String TAG_NAME_MER = "name_mer";
	private static final String TAG_SOLD = "sold";
	private static final String TAG_RESERVER = "reserver";
	private static final String TAG_STOCK_TRAN = "stock_tranfer";
	private static final String TAG_LIQUIDATED = "liquidated";
	private static final String TAG_ICON = "icon";
	private static final String TAG_CREATE_AT = "create_at";
	private static final String TAG_UPDATE_AT = "updated_at";
	private static final String TAG_URL = "http://trongkhanhbkhn.meximas.com/Server/Model/create_product.php";
    Product product;
    Groups group;
    WareHouse ware_house;
    Goods goods;
    WareGoods ware_goods;
	String id_ware, name_ware, id_group, name_group, id_goods, name_goods, icon ="wait", date;
	String sold, reserver, stock_tranfer, liq;
	EditText ed_id_ware, ed_name_ware, ed_id_group, ed_name_group, ed_id_mer, ed_name_mer, ed_icon;
	EditText ed_sold, ed_reserver, ed_stock_tranfer, ed_liq;
	Button btn_create_product;
	JSONParser json_parser;
	int result=0, success=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_product);
		database_handle = new DatabaseHandle(this);	
		ed_id_ware = (EditText)findViewById(R.id.id_ware);
		ed_name_ware = (EditText)findViewById(R.id.name_ware);
		ed_id_group = (EditText)findViewById(R.id.id_groups);
		ed_name_group = (EditText)findViewById(R.id.name_groups);
		ed_id_mer = (EditText)findViewById(R.id.id_goods);
		ed_name_mer = (EditText)findViewById(R.id.name_goods);
		ed_sold = (EditText)findViewById(R.id.sold);
		ed_reserver = (EditText)findViewById(R.id.reserver);
		ed_stock_tranfer = (EditText)findViewById(R.id.stock_tranfer);
		ed_liq = (EditText)findViewById(R.id.liq);
		btn_create_product = (Button)findViewById(R.id.btn_add);
		json_parser = new JSONParser();	
		btn_create_product.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//CreateNewProduct();
				CreateNewProduct();
			}
		});
		
	}

	private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
	}
	
	/**
	 * this function create a product new in sqlite and sql server
	 */
	
	public void CreateNewProduct(){
		List<NameValuePair> params = new ArrayList<NameValuePair>();

		// get data from edittext
		id_ware = ed_id_ware.getText().toString().trim();
		name_ware = ed_name_ware.getText().toString().trim();
		id_group = ed_id_group.getText().toString().trim();
		name_group = ed_name_group.getText().toString().trim();
		id_goods = ed_id_mer.getText().toString().trim();
		name_goods = ed_name_mer.getText().toString().trim();
		sold = ed_sold.getText().toString().trim();//Integer.parseInt(ed_sold.getText().toString());
		reserver = ed_reserver.getText().toString().trim();//Integer.parseInt(ed_reserver.getText().toString());
		stock_tranfer =ed_stock_tranfer.getText().toString().trim(); //Integer.parseInt(ed_stock_tranfer.getText().toString());
		liq = ed_liq.getText().toString().trim();//Integer.parseInt(ed_liq.getText().toString());
		// check data in edittext null
		if((id_ware.matches(""))||(name_ware.matches("")) || (id_group.matches(""))||(name_group.matches(""))||(id_goods.matches(""))||(name_goods.matches(""))||(sold.matches(""))||(reserver.matches(""))||(stock_tranfer.matches(""))||(liq.matches(""))){
			
		}
		else{
			// create Product 
			//product = new Product(id_ware, name_ware, id_group, name_group, id_mer, name_mer, Integer.parseInt(sold), Integer.parseInt(reserver), Integer.parseInt(stock_tranfer), Integer.parseInt(liq), icon, );
			ware_house = new WareHouse(id_ware, name_ware);
			group = new Groups(id_group, name_group);
			goods = new Goods(id_goods, name_goods);
			ware_goods = new WareGoods(goods, Integer.parseInt(sold), Integer.parseInt(reserver), Integer.parseInt(stock_tranfer), Integer.parseInt(liq),icon, getDateTime(),"wating update", ware_house, group);
			result +=database_handle.addWareHouse(ware_house);
			result +=database_handle.addGroup(group);
			result += database_handle.addGoods(goods);
			result +=database_handle.addWareGoods(ware_goods);
			params.add(new BasicNameValuePair(TAG_ID_WARE, id_ware));
			params.add(new BasicNameValuePair(TAG_NAME_WARE	, name_ware));
			params.add(new BasicNameValuePair(TAG_ID_GROUP, id_group));
			params.add(new BasicNameValuePair(TAG_NAME_GROUP, name_group));
			params.add(new BasicNameValuePair(TAG_ID_MER, id_goods));
			params.add(new BasicNameValuePair(TAG_NAME_MER, name_goods));
			params.add(new BasicNameValuePair(TAG_SOLD, sold));
			params.add(new BasicNameValuePair(TAG_RESERVER, reserver));
			params.add(new BasicNameValuePair(TAG_STOCK_TRAN, stock_tranfer));
			params.add(new BasicNameValuePair(TAG_LIQUIDATED, liq));
			params.add(new BasicNameValuePair(TAG_ICON, icon));
			params.add(new BasicNameValuePair(TAG_CREATE_AT, getDateTime()));
			params.add(new BasicNameValuePair(TAG_UPDATE_AT, "waiting update"));
			JSONObject json_object = json_parser.makeHttpRequest(TAG_URL, "POST", params);
			try{
				success = json_object.getInt(TAG_SUCCESS);				
			}catch(JSONException e){
				e.printStackTrace();
			}
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_product, menu);
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
