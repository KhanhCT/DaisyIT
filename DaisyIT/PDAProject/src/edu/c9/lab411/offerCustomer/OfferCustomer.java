package edu.c9.lab411.offerCustomer;

import java.util.ArrayList;
import java.util.HashMap;
import edu.c9.lab411.database.DatabaseHandle;

import edu.c9.lab411.goods.SKUItems;
import edu.c9.lab411.goodsissue.GoodsIssue;
import edu.c9.lab411.ocr.R;
import edu.c9.lab411.order.Order;
import edu.c9.lab411.recieve.barcode.Barcode;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import static edu.c9.lab411.goodsissue.Constant.FIRST_COLUMN;
import static edu.c9.lab411.goodsissue.Constant.SECOND_COLUMN;
import static edu.c9.lab411.goodsissue.Constant.THIRD_COLUMN;
import static edu.c9.lab411.goodsissue.Constant.FOR_COLUMN;
import static edu.c9.lab411.goodsissue.Constant.FIVE_COLUMN;
import static edu.c9.lab411.goodsissue.Constant.SIX_COLUMN;

public class OfferCustomer extends Activity {
	public static final String DETAIL = "Detail: ";
	public static final String SKUCODE = "Sku Code: ";
	public static final String NAME = "Name: ";
	public static final String ADD = "Address: ";
	public static final String PHONE = "Phone Number: ";
	public static final String QUANTITY = "Quantity: ";
	public static final String PRODUCTNAME = "Product Name: ";
	private EditText txName, txAdd, txPhone, txquantity;
	private String ACTION_CONTENT_NOTIFY = "android.intent.action.CONTENT_NOTIFY";
	private TextView etBarcode;
	private AutoCompleteTextView  sku_code_auto;
	private DataReceiver dataReceiver = null;
	private String[] arrAdapter;
	String autoText = "";
	TabHost tabHost;
	private ListView lv_detail_offer, lv_all_offer;
	private EfficientAdapterOffer eAdapter;
	private ArrayList<HashMap<String, String>> list, list_all;
	private ListviewAdapter lv_adapter;
	DatabaseHandle databases_handle = new DatabaseHandle(this);
	String content = "", result = "";
	private String sku_code_temp = "";
	private String name_temp = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.offer_customer);
		loadTab();
		loadListView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.offer_customer, menu);
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
	class DataReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub	
			
			Bundle bundle = new Bundle();
			bundle = intent.getExtras();
			txquantity.setText("");
			sku_code_auto.setText("");
			autoText ="";
			content = bundle.getString("CONTENT").trim();
			result = bundle.getString("RESULT");
			etBarcode.setText(content);
			if(!content.matches("")){
				txquantity.setText("");
				sku_code_auto.setText("");
				autoText ="";
			}
		}
	}

	private void registerReceiver() {
		if (dataReceiver != null)
			return;
		dataReceiver = new DataReceiver();
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(ACTION_CONTENT_NOTIFY);
		registerReceiver(dataReceiver, intentFilter);
	}

	/**
	 * this function unregister
	 */
	private void unregisterReceiver() {
		if (dataReceiver != null)
			unregisterReceiver(dataReceiver);
	}
	public void loadTab(){
		tabHost = (TabHost) findViewById(R.id.tabhost);
		tabHost.setup();
		TabHost.TabSpec tabSpec;
		tabSpec = tabHost.newTabSpec("tab1");
		tabSpec.setIndicator("Item");
		tabSpec.setContent(R.id.tab1);
		tabHost.addTab(tabSpec);
		tabSpec = tabHost.newTabSpec("tab2");
		tabSpec.setContent(R.id.tab2);
		tabSpec.setIndicator("All Items");
		tabHost.addTab(tabSpec);
		tabHost.setCurrentTab(0);
		tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener(){
			@Override
			public void onTabChanged(String arg0) {
				list_all.clear();
				lv_adapter.notifyDataSetChanged();
				list.clear();
				eAdapter.notifyDataSetChanged();
				if (tabHost.getCurrentTab() == 0){
					if (sku_code_temp.matches("")||name_temp.matches("")){
						AlertDialog.Builder dialog_warning = new AlertDialog.Builder(
								OfferCustomer.this);
						dialog_warning.setTitle("Warning");
						dialog_warning.setMessage("No Item is select");
						dialog_warning.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int arg1) {
										dialog.cancel();
									}
								});
						dialog_warning.create().show();
					}else{
						Offer offer = databases_handle.getOffer(sku_code_temp, name_temp);
						HashMap<String , String> map = new HashMap<String, String>();
						map.put(FIRST_COLUMN, SKUCODE + sku_code_temp);
						map.put(SECOND_COLUMN, PRODUCTNAME + offer.getProductName());
						map.put(THIRD_COLUMN, QUANTITY + String.valueOf(offer.getQuantity()));
						map.put(FOR_COLUMN, NAME + offer.getName());
						map.put(FIVE_COLUMN, ADD + offer.getAddress());
						map.put(SIX_COLUMN, PHONE + String.valueOf(offer.getPhone()));
						map.put("STATUS", "0");
						list.add(map);
						eAdapter.notifyDataSetChanged();
					}
				}else{
					if(databases_handle.getOfferCount() !=0){
						list_all.clear();
						lv_adapter.notifyDataSetChanged();
						ArrayList<Offer> listOffer = databases_handle.getAllOffer();
						for(int i=0;i<listOffer.size(); i++){
							HashMap<String, String> temp = new HashMap<String, String>();
							temp.put(FIRST_COLUMN,   listOffer.get(i).getSkuCode());
							temp.put(SECOND_COLUMN,  listOffer.get(i).getName());
							temp.put(THIRD_COLUMN,  String.valueOf(listOffer.get(i).getQuantity()));						
							list_all.add(temp);
						}
						lv_adapter.notifyDataSetChanged();
					}else {
						AlertDialog.Builder dialog_warning = new AlertDialog.Builder(
								OfferCustomer.this);
						dialog_warning.setTitle("Warning");
						dialog_warning.setMessage("No Item");
						dialog_warning.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int arg1) {
										dialog.cancel();
									}
								});
						dialog_warning.create().show();
					}
					sku_code_temp = "";
					name_temp = "";
				}
			}
			
		});	
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		registerReceiver();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		unregisterReceiver();
	}

	public void loadListView(){
		list = new ArrayList<HashMap<String,String>>();
		HashMap<String , String> map = new HashMap<String, String>();
		map.put(FIRST_COLUMN, SKUCODE);
		map.put(SECOND_COLUMN, PRODUCTNAME);
		map.put(THIRD_COLUMN, QUANTITY);
		map.put(FOR_COLUMN, NAME);
		map.put(FIVE_COLUMN, ADD);
		map.put(SIX_COLUMN, PHONE);
		map.put("STATUS", "0");
		list.add(map);
		lv_detail_offer = (ListView)findViewById(R.id.lv_detail_offer);
		eAdapter = new EfficientAdapterOffer(this, list);
		lv_detail_offer.setAdapter(eAdapter);
		lv_detail_offer.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {			
				
			}
		});
		txAdd = (EditText)findViewById(R.id.add_customer);
		txName = (EditText)findViewById(R.id.name_customer);
		txPhone = (EditText)findViewById(R.id.phone_customer);
		etBarcode = (TextView)findViewById(R.id.barcode);
		txquantity = (EditText)findViewById(R.id.quantity_goods);
		arrAdapter = new String[500];
		if(databases_handle.getSKUCodeCount() !=0){
			arrAdapter = databases_handle.getAllSKUCode();
		}else{
			AlertDialog.Builder dialog_warning = new AlertDialog.Builder(
					OfferCustomer.this);
			dialog_warning.setTitle("Warning");
			dialog_warning.setMessage("Table SKU Item not Items");
			dialog_warning.setPositiveButton("OK",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog,
								int arg1) {
							dialog.cancel();
						}
					});
			dialog_warning.create().show();
		}
		sku_code_auto = (AutoCompleteTextView)findViewById(R.id.sku_code);
		sku_code_auto.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, arrAdapter));
		sku_code_auto.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				autoText = sku_code_auto.getText().toString();
			}
		});	
		lv_all_offer = (ListView)findViewById(R.id.lv_all_offer);
		HashMap<String, String> temp = new HashMap<String, String>();
		list_all = new ArrayList<HashMap<String, String>>();
		temp.put(FIRST_COLUMN, SKUCODE+ SKUCODE);
		temp.put(SECOND_COLUMN, NAME + NAME );
		temp.put(THIRD_COLUMN, QUANTITY + QUANTITY);
		list_all.add(temp);
		lv_all_offer = (ListView) findViewById(R.id.lv_all_offer);
		lv_adapter = new ListviewAdapter(OfferCustomer.this, list_all);
		lv_all_offer.setAdapter(lv_adapter);
		lv_all_offer.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				sku_code_temp = list_all.get(position).get(FIRST_COLUMN);
				name_temp = list_all.get(position).get(SECOND_COLUMN);
				tabHost.setCurrentTab(0);
				
			}
		});
		lv_all_offer.setLongClickable(true);
		lv_all_offer.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					final int pos, long arg3) {
				 AlertDialog.Builder dialog_login = new AlertDialog.Builder(OfferCustomer.this);
				   dialog_login.setTitle("Question");
				   dialog_login.setMessage("Are Your Delete Item?");
				   dialog_login.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface arg0, int arg1) {	
							databases_handle.deleteOffer(list_all.get(pos).get(FIRST_COLUMN), list_all.get(pos).get(SECOND_COLUMN));
							list_all.remove(pos);
							lv_adapter.notifyDataSetChanged();
							tabHost.setCurrentTab(1);
						}
					});
				  dialog_login.setNegativeButton("No", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int arg1) {
							// TODO Auto-generated method stub
							dialog.cancel();
						}
					});
				  dialog_login.create().show();
				
				return true;
			}
		});
	}
	public void deleteGood(View view){
		String name = txName.getText().toString();
		if(autoText.matches("")|| name.matches("")){
			
		}else{
			int result  = databases_handle.deleteOffer(autoText, name);
			if(result ==0){
				AlertDialog.Builder dialog_warning = new AlertDialog.Builder(
						OfferCustomer.this);
				dialog_warning.setTitle("Warning");
				dialog_warning.setMessage("Delete " + autoText +" " +name+ " Error");
				dialog_warning.setPositiveButton("OK",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int arg1) {
								dialog.cancel();
							}
						});
				dialog_warning.create().show();
			}else{
				AlertDialog.Builder dialog_warning = new AlertDialog.Builder(
						OfferCustomer.this);
				dialog_warning.setTitle("Success");
				dialog_warning.setMessage("Delete " + autoText +" " +name+  "Successfully");
				dialog_warning.setPositiveButton("OK",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int arg1) {
								dialog.cancel();
							}
						});
				dialog_warning.create().show();
			}
		}
	}
	public void updateGood(View view){
		
	}
	public void findGood(View view){
		Offer offer = new Offer();
		list.clear();
		eAdapter.notifyDataSetChanged();
		String name = txName.getText().toString();
		String add = txAdd.getText().toString();
		String phone = txPhone.getText().toString();
		String barcode = etBarcode.getText().toString();
		String quantity = txquantity.getText().toString();
		if(!barcode.matches("")){
			autoText = "";
			sku_code_auto.setText("");
		}
		if(name.matches("")|| add.matches("") || phone.matches("")||quantity.matches("")){
			AlertDialog.Builder dialog_warning = new AlertDialog.Builder(
			OfferCustomer.this);
			dialog_warning.setTitle("Question");
			dialog_warning.setMessage("Please, Name or ");
			dialog_warning.setPositiveButton("OK",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int arg1) {
							dialog.cancel();
						}
			});
			dialog_warning.create().show();
		}else if(!autoText.matches("") && barcode.matches("")){
			
			if (databases_handle.getOfferCount(name.trim(), autoText) != 0){
				
			}else{
				
				if (databases_handle.getSkuItemsCount(autoText) != 0) {
					SKUItems sku = databases_handle.getAllSKUCode(autoText);
					offer.setSkuCode(autoText);
					offer.setProductName(sku.getProductName());
					offer.setName(name);
					offer.setAddress(add);
					offer.setPhone(Integer.parseInt(phone));
					offer.setQuantity(Integer.parseInt(quantity));
					int result = databases_handle.addOffer(offer);
					HashMap<String , String> map = new HashMap<String, String>();
					map.put(FIRST_COLUMN, SKUCODE + autoText);
					map.put(SECOND_COLUMN, PRODUCTNAME + sku.getProductName());
					map.put(THIRD_COLUMN, QUANTITY + quantity);
					map.put(FOR_COLUMN, NAME + name);
					map.put(FIVE_COLUMN, ADD + add);
					map.put(SIX_COLUMN, PHONE + phone);
					if (result == 0) {
						map.put("STATUS", "0");
					} else {
						map.put("STATUS", "1");
					}
					list.add(map);
					eAdapter.notifyDataSetChanged();
				}else{
					AlertDialog.Builder dialog_warning = new AlertDialog.Builder(
							OfferCustomer.this);
					dialog_warning.setTitle("Warning");
					dialog_warning.setMessage("Sku Item not exits SKUCode: " + autoText);
					dialog_warning.setPositiveButton("Cancel",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog, int arg1) {
									dialog.cancel();
								}
							});
					dialog_warning.create().show();
				}
			}
			
		}else if(autoText.matches("") && !barcode.matches("")){
			if (databases_handle.getBarcodeCountID(barcode) == 0) {
				AlertDialog.Builder dialog_warning = new AlertDialog.Builder(
						OfferCustomer.this);
				dialog_warning.setTitle("Warning");
				dialog_warning.setMessage("Barcode not Exits in Databases");
				dialog_warning.setPositiveButton("Ok",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int arg1) {
								dialog.cancel();
							}
						});
				dialog_warning.create().show();
			}else{
				Barcode bar = databases_handle.getBarcodeId(barcode);
				if(databases_handle.getOfferCount(name.trim(), bar.getSkuCode()) != 0){
					
				}else{
					if (databases_handle.getSkuItemsCount(bar.getSkuCode()) !=0){
						SKUItems sku = databases_handle.getAllSKUCode(bar
								.getSkuCode());
						offer.setSkuCode(bar.getSkuCode());
						offer.setProductName(sku.getProductName());
						offer.setName(name);
						offer.setAddress(add);
						offer.setPhone(Integer.parseInt(phone));
						offer.setQuantity(Integer.parseInt(quantity));
						int result = databases_handle.addOffer(offer);
						HashMap<String , String> map = new HashMap<String, String>();
						map.put(FIRST_COLUMN, SKUCODE + bar.getSkuCode());
						map.put(SECOND_COLUMN, PRODUCTNAME + sku.getProductName());
						map.put(THIRD_COLUMN, QUANTITY + quantity);
						map.put(FOR_COLUMN, NAME + name);
						map.put(FIVE_COLUMN, ADD + add);
						map.put(SIX_COLUMN, PHONE + phone);
						if (result == 0) {
							map.put("STATUS", "0");
						} else {
							map.put("STATUS", "1");
						}
						list.add(map);
						eAdapter.notifyDataSetChanged();
						
					}
				}
			}
		}
		else{
			AlertDialog.Builder dialog_warning = new AlertDialog.Builder(
					OfferCustomer.this);
			dialog_warning.setTitle("Warning");
			dialog_warning.setMessage("Please, Input Barcode or SKU Code ");
			dialog_warning.setPositiveButton("Cancel",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int arg1) {
							dialog.cancel();
						}
					});
			dialog_warning.create().show();
		}
	}
	public class ListviewAdapter extends BaseAdapter {
		public ArrayList<HashMap<String, String>> list_;
		Activity activity;

		public ListviewAdapter(Activity activity,
				ArrayList<HashMap<String, String>> list) {
			// TODO Auto-generated constructor stub
			super();
			this.activity = activity;
			this.list_ = list;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list_.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list_.get(position);
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		private class ViewHolder {
			TextView txtFirst;
			TextView txtSecond;
			TextView txtThird;
		}

		@SuppressLint("InflateParams")
		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			final ViewHolder holder;
			LayoutInflater inflater = activity.getLayoutInflater();
			if (convertView == null) {
				convertView = inflater.inflate(
						R.layout.listview_colum_offer, null);
				holder = new ViewHolder();
				holder.txtFirst = (TextView) convertView
						.findViewById(R.id.tv_sku_code);
				holder.txtFirst.setTextColor(Color.YELLOW);
				holder.txtFirst.setTextSize(16);
				holder.txtSecond = (TextView) convertView
						.findViewById(R.id.tv_name);
				holder.txtSecond.setTextColor(Color.YELLOW);
				holder.txtSecond.setTextSize(16);
				
				holder.txtThird = (TextView) convertView
						.findViewById(R.id.tv_quantity);
				holder.txtThird.setTextColor(Color.YELLOW);
				holder.txtThird.setTextSize(16);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			HashMap<String, String> map = list_.get(position);
			holder.txtFirst.setText(map.get(FIRST_COLUMN));
			holder.txtSecond.setText(map.get(SECOND_COLUMN));
			holder.txtThird.setText(map.get(THIRD_COLUMN));
			return convertView;
		}
	}
}
