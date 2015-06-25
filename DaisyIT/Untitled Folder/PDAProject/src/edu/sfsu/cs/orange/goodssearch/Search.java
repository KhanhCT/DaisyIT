package edu.sfsu.cs.orange.goodssearch;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.sfsu.cs.orange.database.DatabaseHandle;
import edu.sfsu.cs.orange.ocr.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;


public class Search extends Activity implements OnItemClickListener{
	public Button btn_search, btn_back;
	public GridView gd_display;
	public Bundle myBackup;
	public int flag = 0;
	public ImageView img;
	public DatabaseHandle database_handle;
	public TextView tv;
	public EditText ed_id_ware, ed_id_group,  ed_id_mer;
	public TextView tv_id_mer, tv_name_mer, tv_sold, tv_reserver,tv_stock, tv_liq;
	public TextView  tv_id_group, tv_name_group,tv_id_ware, tv_name_ware;
	public String id_ware =null, name_ware=null, id_group=null, name_group=null, id_mer=null, name_mer=null;
	String[] arr = new String[10000];
	String list_item[][];
	List<HashMap<String, String>> list_map = new ArrayList<HashMap<String,String>>();
	ArrayAdapter<String> array_adapter;
	List<String> list_ = new ArrayList<String>();
	WareGoods ware_goods;
	WareHouse ware_house;
	Groups group;
	ArrayList<WareGoods> list;
	Goods goods ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);
		ware_goods = new WareGoods();
		goods = new Goods();
		group = new Groups();
		ware_house = new WareHouse();
		btn_search = (Button)findViewById(R.id.btn_find);
		ed_id_ware = (EditText)findViewById(R.id.id_ware_search);
		ed_id_group = (EditText)findViewById(R.id.id_group_search);
		ed_id_mer = (EditText)findViewById(R.id.id_goods_search);
//		gd_display.setOnItemClickListener(this);
//		myBackup = savedInstanceState;
//		array_adapter=new ArrayAdapter<String>
//		 (
//		 this,
//		 android.R.layout.simple_list_item_1
//		 );
//		gd_display.setAdapter(array_adapter);
//		btn_search.setOnClickListener(new OnClickListener() {		
//			@Override
//			public void onClick(View arg0) {
//				setContentView(R.layout.productdetail);
//				 //TODO Auto-generated method stub
//				id_ware = ed_id_ware.getText().toString().trim();
//				id_group = ed_id_group.getText().toString().trim();
//				id_mer = ed_id_mer.getText().toString().trim();;
//				if(!id_ware.matches("")&&!id_group.matches("")&&id_mer.matches("")){
//				list =  database_handle.getWareGoodsGroup(id_ware, id_group);	
//				ware_house = database_handle.getWareHouse(id_ware);
//				group = database_handle.getGroup(id_group);
//				list_item = new String[list.size()][10];
//				for(int i = 0; i<list.size(); i++){
//					goods = list.get(i).getGoods();
//					list_item[i][0] = ware_house.getId_ware();
//					list_item[i][1] = ware_house.getName_ware();
//					list_item[i][2] = group.getId_group();
//					list_item[i][3] = group.getName_group();
//					list_item[i][4] = goods.getId_good();
//					list_item[i][5] = goods.getName_good();
//					list_item[i][6] = String.valueOf(list.get(i).getSold());
//					list_item[i][7] = String.valueOf(list.get(i).getReserver());
//					list_item[i][8] = String.valueOf(list.get(i).getStock_tranfer());
//					list_item[i][9] = String.valueOf(list.get(i).getLiq());
//					arr[i] = goods.getId_good();
//					array_adapter.add(arr[i]);					
//					flag = 1;
//				  }
//				}
//				Log.i("Products",list_.toString());
//			}
//		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
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

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		//ShowDetail(arg2);
		
	}
	/*public void ShowDetail(int position){
		setContentView(R.layout.productdetail);
//		tv_id_ware = (TextView)findViewById(R.id.id_list_ware);
//		tv_name_ware = (TextView)findViewById(R.id.name_list_name_ware);
//		tv_id_group = (TextView)findViewById(R.id.id_list_group);
//		tv_name_group = (TextView)findViewById(R.id.name_list_name_group);
//		tv_id_mer = (TextView)findViewById(R.id.id_list_goods);
//		tv_name_mer = (TextView)findViewById(R.id.name_list_name_goods);
//		tv_sold = (TextView)findViewById(R.id.value_list_sold);
//		tv_reserver = (TextView)findViewById(R.id.value_list_reserver);
//		tv_stock = (TextView)findViewById(R.id.value_list_stock);
//		tv_liq = (TextView)findViewById(R.id.value_list_liq);
//		btn_back = (Button)findViewById(R.id.btn_list_back);	
//		tv_id_ware.setText(list_item[position][0]);	
//		tv_name_ware.setText(list_item[position][1]);
//		tv_id_group.setText(list_item[position][2]);	
//		tv_name_group.setText(list_item[position][3]);
//		tv_id_mer.setText(list_item[position][4]);	
//		tv_name_mer.setText(list_item[position][5]);	
//		tv_sold.setText(list_item[position][6]);
//		tv_reserver.setText(list_item[position][7]);
//		tv_stock.setText(list_item[position][8]);
//		tv_liq.setText(list_item[position][9]);
//		switch (flag) {
//		case 1:
//			
//			flag = 0;
//			break;
//		default:
//			break;
//		}
		btn_back.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onCreate(myBackup);
			}
		});
	}*/
}
