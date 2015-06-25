package edu.c9.lab411.order;

import static edu.c9.lab411.goodsissue.Constant.FIRST_COLUMN;
import static edu.c9.lab411.goodsissue.Constant.FOR_COLUMN;
import static edu.c9.lab411.goodsissue.Constant.SECOND_COLUMN;
import static edu.c9.lab411.goodsissue.Constant.THIRD_COLUMN;

import java.util.ArrayList;
import java.util.HashMap;
import edu.c9.lab411.ocr.R;
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
import android.util.Log;
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
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import edu.c9.lab411.database.DatabaseHandle;
import edu.c9.lab411.goods.Goods;
import edu.c9.lab411.goods.SKUItems;
import edu.c9.lab411.goods.SKUOrder;
import edu.c9.lab411.goodsissue.EfficientAdapter;
import edu.c9.lab411.goodsissue.GoodsIssue;
public class Order extends Activity  {
	public static final String DETAIL = "Detail: ";
	public static final String SKUCODE = "Sku Code: ";
	public static final String GOODID = "Good Id: ";
	public static final String QUANTITY = "Quantity: ";
	public static final String PRODUCTNAME = "Product Name: ";
	private static final String TABLE_SKU_ORDER = "SKU_Order";
	private ArrayList<HashMap<String, String>> list;
	ArrayList<String> list_skucode = new ArrayList<String>();
	private ArrayList<HashMap<String, String>> list_all = new ArrayList<HashMap<String, String>>();
	private String[] arrAdapter;
	private String ACTION_CONTENT_NOTIFY = "android.intent.action.CONTENT_NOTIFY";
	private EfficientAdapter eAdapter;
	private EditText quantity_goods;
	private ListView lv_detail_goods, lv_all_goods;
	private ListviewAdapter lv_adapter;
	private AutoCompleteTextView sku_code_auto;
	private TextView tv_barcode;
	String autoText;
	private DataReceiver dataReceiver = null;
	DatabaseHandle databases_handle = new DatabaseHandle(this);
	TabHost tabHost;
	String content = "", result = "";
	String skuCode_temp = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order);
		databases_handle.deleteTable(TABLE_SKU_ORDER);
		loadTab();
		populateList();
		loadListView();
		doFormWidget();
		tv_barcode.setText(null);
		sku_code_auto.setText(null);
	}

	public void loadTab() {
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
		tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
			@Override
			public void onTabChanged(String arg0) {
				String s = "Tab tag =" + arg0 + "; index ="
						+ tabHost.getCurrentTab();
				list_all.clear();
				lv_adapter.notifyDataSetChanged();
				list.clear();
				eAdapter.notifyDataSetChanged();
				if (tabHost.getCurrentTab() == 0) {
					if (skuCode_temp.matches("")) {
						AlertDialog.Builder dialog_warning = new AlertDialog.Builder(
								Order.this);
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
					} else {
						SKUOrder skuOder = databases_handle
								.getSkuOrderId(skuCode_temp);
						HashMap<String, String> temp = new HashMap<String, String>();
						temp.put(FIRST_COLUMN, DETAIL);
						temp.put(SECOND_COLUMN, SKUCODE + skuCode_temp);
						temp.put(THIRD_COLUMN,
								SKUCODE + String.valueOf(skuOder.getSkuCode()));
						temp.put(
								FOR_COLUMN,
								QUANTITY
										+ String.valueOf(skuOder.getQuantity()));
						temp.put("STATUS", String.valueOf("1"));
						list.add(temp);
						eAdapter.notifyDataSetChanged();
					}
				}
				if (tabHost.getCurrentTab() == 1) {
					if (databases_handle.getSkuOrderCount() != 0) {
						list_all.clear();
						lv_adapter.notifyDataSetChanged();
						ArrayList<SKUOrder> skuOrder = databases_handle
								.getAllSkuOder();
						for (int i = 0; i < skuOrder.size(); i++) {
							HashMap<String, String> map = new HashMap<String, String>();
							map.put(FIRST_COLUMN, skuOrder.get(i).getSkuCode());
							map.put(SECOND_COLUMN, String.valueOf(skuOrder.get(
									i).getQuantity()));
							list_all.add(map);
						}
						lv_adapter.notifyDataSetChanged();
					} else {
						AlertDialog.Builder dialog_warning = new AlertDialog.Builder(
								Order.this);
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
					skuCode_temp = "";
				}
				Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG)
						.show();
			}

		});
	}

	private void populateList() {
		list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> temp = new HashMap<String, String>();
		temp.put(FIRST_COLUMN, DETAIL);
		temp.put(SECOND_COLUMN, SKUCODE);
		temp.put(THIRD_COLUMN, PRODUCTNAME);
		temp.put(FOR_COLUMN, QUANTITY);
		temp.put("STATUS", "0");
		list.add(temp);
	}

	public void loadListView() {
		tv_barcode = (TextView) findViewById(R.id.barcode);
		quantity_goods = (EditText) findViewById(R.id.quantity_goods);
		tv_barcode.setText(null);
		eAdapter = new EfficientAdapter(this, list);
		lv_detail_goods = (ListView) findViewById(R.id.lv_detail_goods);
		lv_detail_goods.setAdapter(eAdapter);

		tv_barcode.setText(null);
		HashMap<String, String> temp = new HashMap<String, String>();
		temp.put(FIRST_COLUMN, SKUCODE);
		temp.put(SECOND_COLUMN, QUANTITY);
		list_all.add(temp);
		lv_all_goods = (ListView) findViewById(R.id.lv_all_goods);
		lv_adapter = new ListviewAdapter(Order.this, list_all);
		lv_all_goods.setAdapter(lv_adapter);
		lv_all_goods.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				skuCode_temp = list_all.get(position).get(FIRST_COLUMN);
				tabHost.setCurrentTab(0);
			}
		});
		lv_all_goods.setLongClickable(true);
		lv_all_goods.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					final int pos, long arg3) {
				 AlertDialog.Builder dialog_login = new AlertDialog.Builder(Order.this);
				   dialog_login.setTitle("Question");
				   dialog_login.setMessage("Are Your Delete Item?");
				   dialog_login.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface arg0, int arg1) {	
							databases_handle.deleteSkuOrder(list_all.get(pos).get(FIRST_COLUMN));
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

	public void doFormWidget() {
		arrAdapter = new String[500];
		if(databases_handle.getSKUCodeCount() !=0){
			arrAdapter = databases_handle.getAllSKUCode();
		}else{
			AlertDialog.Builder dialog_warning = new AlertDialog.Builder(
				Order.this);
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
		sku_code_auto = (AutoCompleteTextView) findViewById(R.id.sku_code);
		sku_code_auto.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, arrAdapter));
		sku_code_auto.setText(null);
		sku_code_auto.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				autoText = sku_code_auto.getText().toString();
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
		databases_handle.deleteTable(TABLE_SKU_ORDER);
		unregisterReceiver();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.order, menu);
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
	public void updateGood(View view) {
		String quantity = quantity_goods.getText().toString();
		if (quantity.matches("")) {
			AlertDialog.Builder dialog_warning = new AlertDialog.Builder(
					Order.this);
			dialog_warning.setTitle("Warning");
			dialog_warning.setMessage("Please, Input Qantity");
			dialog_warning.setPositiveButton("Cancel",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int arg1) {
							dialog.cancel();
						}
					});
			dialog_warning.create().show();
		} else {
			if (!autoText.matches("")) {
				if (databases_handle.getSkuOrderCount(autoText) != 0) {
					SKUOrder skuOrder = new SKUOrder();
					skuOrder = databases_handle.getSkuOrderId(autoText);
					skuOrder.setQuantity(skuOrder.getQuantity()
							+ Integer.parseInt(quantity));
					int result = databases_handle.updateSkuOrder(skuOrder);
					if (result == 0) {
						AlertDialog.Builder dialog_warning = new AlertDialog.Builder(
								Order.this);
						dialog_warning.setTitle("Warning");
						dialog_warning.setMessage("Please, Update Error!");
						dialog_warning.setPositiveButton("Cancel",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int arg1) {
										dialog.cancel();
									}
								});
						dialog_warning.create().show();
					} else {
						AlertDialog.Builder dialog_warning = new AlertDialog.Builder(
								Order.this);
						dialog_warning.setTitle("Success");
						dialog_warning.setMessage("Update Successfully!!");
						dialog_warning.setPositiveButton("Cancel",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int arg1) {
										dialog.cancel();
									}
								});
						dialog_warning.create().show();
					}
				} else {
					AlertDialog.Builder dialog_warning = new AlertDialog.Builder(
							Order.this);
					dialog_warning.setTitle("Warning");
					dialog_warning.setMessage("Update Error SkuCode not exits");
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
			} else {
				AlertDialog.Builder dialog_warning = new AlertDialog.Builder(
						Order.this);
				dialog_warning.setTitle("Warning");
				dialog_warning.setMessage("Please, Input SKUCode");
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
		tv_barcode.setText("");
		sku_code_auto.setText("");
		autoText = "";
	}
	public void deleteGood(View view){
		if(autoText.matches("")){
			AlertDialog.Builder dialog_warning = new AlertDialog.Builder(
					Order.this);
			dialog_warning.setTitle("Warning");
			dialog_warning.setMessage("Please, Input SKU Code");
			dialog_warning.setPositiveButton("Cancel",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int arg1) {
							dialog.cancel();
						}
					});
			dialog_warning.create().show();
		}else{
			int result = databases_handle.deleteSkuOrder(autoText);
			if(result ==0){
				AlertDialog.Builder dialog_warning = new AlertDialog.Builder(
						Order.this);
				dialog_warning.setTitle("Warning");
				dialog_warning.setMessage("Delete " + autoText + " Error");
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
						Order.this);
				dialog_warning.setTitle("Success");
				dialog_warning.setMessage("Delete " + autoText + "Successfully");
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

	public void findGood(View view) {
		list.clear();
		eAdapter.notifyDataSetChanged();
		String quantity = quantity_goods.getText().toString();
		String barcode = tv_barcode.getText().toString();
		if (!barcode.matches("")) {
			autoText = "";
			sku_code_auto.setText("");

		}
		if (quantity.matches("")) {
			AlertDialog.Builder dialog_warning = new AlertDialog.Builder(
					Order.this);
			dialog_warning.setTitle("Question");
			dialog_warning.setMessage("Please, Input Quantity");
			dialog_warning.setPositiveButton("Cancel",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int arg1) {
							dialog.cancel();
						}
					});
			dialog_warning.create().show();
		} else if (!autoText.matches("") && barcode.matches("")) {
			tv_barcode.setText(null);
			if (databases_handle.getSkuOrderCount(autoText) != 0) {
				SKUOrder skuOrder = new SKUOrder();
				skuOrder = databases_handle.getSkuOrderId(autoText);
				skuOrder.setQuantity(Integer.parseInt(quantity)
						+ skuOrder.getQuantity());
				HashMap<String, String> temp = new HashMap<String, String>();
				temp.put(FIRST_COLUMN, DETAIL);
				temp.put(SECOND_COLUMN, SKUCODE + autoText);
				temp.put(THIRD_COLUMN,
						PRODUCTNAME + String.valueOf(skuOrder.getProductName()));
				temp.put(FOR_COLUMN, QUANTITY + String.valueOf(quantity));
				int result = databases_handle.updateSkuOrder(skuOrder);
				if (result == 0) {
					temp.put("STATUS", "0");
				} else {
					temp.put("STATUS", "1");
				}
				list.add(temp);
				eAdapter.notifyDataSetChanged();
			} else {
				if (databases_handle.getSkuItemsCount(autoText) != 0) {
					SKUItems sku = databases_handle.getAllSKUCode(autoText);
					list_skucode.add(autoText);
					Goods goods = new Goods(sku.getGoodID(), 0, 0, 0, 0, 0, 0,
							0, Integer.parseInt(quantity), null, null, null,
							null, null, null, null, null, null, null, null,
							true);
					int result = databases_handle.addGood(goods);
					HashMap<String, String> temp = new HashMap<String, String>();
					temp.put(FIRST_COLUMN, DETAIL);
					temp.put(SECOND_COLUMN, SKUCODE + autoText);
					temp.put(THIRD_COLUMN,
							PRODUCTNAME + String.valueOf(sku.getProductName()));
					temp.put(FOR_COLUMN, QUANTITY + String.valueOf(quantity));
					if (result == 0) {
						temp.put("STATUS", "0");
					} else {
						temp.put("STATUS", "1");
					}
					list.add(temp);
					eAdapter.notifyDataSetChanged();
					SKUOrder skuOrder = new SKUOrder();
					skuOrder.setSkuCode(autoText);
					skuOrder.setProductName(sku.getProductName());
					skuOrder.setQuantity(Integer.parseInt(quantity));
					if (databases_handle.addSKUOrder(skuOrder) == 0) {
						Log.i("Insert", "Insert Item in SKUOrder Error");
					} else {
						Log.i("Insert", "Insert Item in SKUOrder Success");
					}
				} else {
					AlertDialog.Builder dialog_warning = new AlertDialog.Builder(
							Order.this);
					dialog_warning.setTitle("Warning");
					dialog_warning.setMessage("Sku Item not exits SKUCode: "
							+ autoText);
					dialog_warning.setPositiveButton("Cancel",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int arg1) {
									dialog.cancel();
								}
							});
					dialog_warning.create().show();
				}
			}
		} else if (autoText.matches("") && !barcode.matches("")) {
			autoText = null;
			if (databases_handle.getBarcodeCountID(barcode) == 0) {
				AlertDialog.Builder dialog_warning = new AlertDialog.Builder(
						Order.this);
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
			} else {
				Barcode bar = databases_handle.getBarcodeId(barcode);
				if (databases_handle.getSkuOrderCount(bar.getSkuCode()) != 0) {
					SKUOrder skuOder = new SKUOrder();
					skuOder = databases_handle.getSkuOrderId(bar.getSkuCode());
					skuOder.setQuantity(skuOder.getQuantity()
							+ Integer.parseInt(quantity));
					int result = databases_handle.updateSkuOrder(skuOder);
					HashMap<String, String> temp = new HashMap<String, String>();
					temp.put(FIRST_COLUMN, DETAIL);
					temp.put(SECOND_COLUMN, SKUCODE + bar.getSkuCode());
					temp.put(
							THIRD_COLUMN,
							PRODUCTNAME
									+ String.valueOf(skuOder.getProductName()));
					temp.put(FOR_COLUMN, QUANTITY + String.valueOf(quantity));
					if (result == 0) {
						temp.put("STATUS", "0");
					} else {
						temp.put("STATUS", "1");
					}
					list.add(temp);
					eAdapter.notifyDataSetChanged();

				} else {
					SKUItems sku = databases_handle.getAllSKUCode(bar
							.getSkuCode());
					SKUOrder skuOder = new SKUOrder(bar.getSkuCode(),
							sku.getProductName(), Integer.parseInt(quantity));
					int result = databases_handle.addSKUOrder(skuOder);
					HashMap<String, String> temp = new HashMap<String, String>();
					temp.put(FIRST_COLUMN, DETAIL);
					temp.put(SECOND_COLUMN, SKUCODE + sku.getSkuCode());
					temp.put(THIRD_COLUMN,
							PRODUCTNAME + String.valueOf(sku.getProductName()));
					temp.put(FOR_COLUMN, QUANTITY + String.valueOf(quantity));
					if (result == 0) {
						temp.put("STATUS", "0");
					} else {
						temp.put("STATUS", "1");
					}
					list.add(temp);
					eAdapter.notifyDataSetChanged();
					list_skucode.add(bar.getSkuCode());
				}
			}
		} else {
			autoText = null;
			tv_barcode.setText(null);
			AlertDialog.Builder dialog_warning = new AlertDialog.Builder(
					Order.this);
			dialog_warning.setTitle("Warning");
			dialog_warning.setMessage("Please, Input Barcode or SKU Code");
			dialog_warning.setPositiveButton("Cancel",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int arg1) {
							dialog.cancel();
						}
					});
			dialog_warning.create().show();
		}
		quantity_goods.setText(null);
		tv_barcode.setText(null);

	}




	class DataReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			tv_barcode.setText(null);
			Bundle bundle = new Bundle();
			bundle = intent.getExtras();
			content = bundle.getString("CONTENT").trim();
			result = bundle.getString("RESULT");
			if (!content.matches("")) {
				tv_barcode.setText(content);
				quantity_goods.setText("");
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
		}
		@SuppressLint("InflateParams")
		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			
			final ViewHolder holder;
			LayoutInflater inflater = activity.getLayoutInflater();
			if (convertView == null) {
				convertView = inflater.inflate(
						R.layout.listview_show_all_goods, null);
				holder = new ViewHolder();
				holder.txtFirst = (TextView) convertView
						.findViewById(R.id.tv_sku_code);
				holder.txtFirst.setTextColor(Color.YELLOW);
				holder.txtFirst.setTextSize(16);
				holder.txtSecond = (TextView) convertView
						.findViewById(R.id.tv_quantity);
				holder.txtSecond.setTextColor(Color.YELLOW);
				holder.txtSecond.setTextSize(16);
				
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			HashMap<String, String> map = list_.get(position);
			holder.txtFirst.setText(map.get(FIRST_COLUMN));
			holder.txtSecond.setText(map.get(SECOND_COLUMN));
			return convertView;
		}
	}

}
