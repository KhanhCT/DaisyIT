package edu.c9.lab411.ocr;

import java.util.Timer;
import java.util.TimerTask;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import edu.c9.lab411.account.Login;
import edu.c9.lab411.account.Registy;
import edu.c9.lab411.barcode.BarcodeDemoActivity;
import edu.c9.lab411.controller.Bussiness;
import edu.c9.lab411.controller.Puckshop;
import edu.c9.lab411.database.DatabaseHandle;
import edu.c9.lab411.database.LoadDatabases;
import edu.c9.lab411.googlemap.GoogleMap;
import edu.c9.lab411.positionConduct.CurrentPosition;
import edu.c9.lab411.positionConduct.sms;
import edu.c9.lab411.ocr.R;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class Interface extends Activity {
	JSONObject jSonObject;
	sms s;
	TextView cartitemscount;
	CurrentPosition pos;
	Timer timerSendSMS;
	private Bundle myBackup;
	public static final String TAG = "SMS";
	DatabaseHandle databaseHandle;
	private static final String TABLE_CART = "Cart";
	private static final String TABLE_LOGIN = "Login";
	private static final String TABLE_WISH_LIST = "Wishlist";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// Timer timer = new Timer();
		// timer.schedule(new BeginLayout(), 5000);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		myBackup = savedInstanceState;
		databaseHandle = new DatabaseHandle(this);
		cartitemscount = (TextView) findViewById(R.id.cartitemscount);
		cartitemscount.setText(String.valueOf(databaseHandle.getCartCount()));
		jSonObject = new JSONObject();
		s = new sms();
		pos = new CurrentPosition(Interface.this);
		timerSendSMS = new Timer();
		AutoSendSMS autoSendSms = new AutoSendSMS();
		timerSendSMS.schedule(autoSendSms, 1000, 5000);
	}

	public void Map(View view) {
		final Intent int_map = new Intent(getApplicationContext(),
				GoogleMap.class);
		startActivity(int_map);

	}

	public void Ocr(View view) {
		final Intent ocr = new Intent(getApplicationContext(),
				CaptureActivity.class);
		startActivity(ocr);
	}

	public void Barcode(View view) {
		final Intent int_barcode = new Intent(getApplicationContext(),
				BarcodeDemoActivity.class);
		startActivity(int_barcode);
	}

	public void Cart(View view) {
		if (databaseHandle.getLoginCount() == 0) {
			AlertDialog.Builder dialog_login = new AlertDialog.Builder(
					Interface.this);
			dialog_login.setTitle("Question");
			dialog_login
					.setMessage("You are not Login. Are You Want Login Or Create Account");
			dialog_login.setPositiveButton("Login",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							Intent int_login = new Intent(
									getApplicationContext(), Login.class);
							startActivity(int_login);
						}
					});
			dialog_login.setNegativeButton("Register",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int arg1) {
							// TODO Auto-generated method stub
							Intent int_create_account = new Intent(
									getApplicationContext(), Registy.class);
							startActivity(int_create_account);
						}
					});
			dialog_login.create().show();
		}

	}

	public void Database(View view) {
		if (databaseHandle.getLoginCount() == 0) {
			AlertDialog.Builder dialog_login = new AlertDialog.Builder(
					Interface.this);
			dialog_login.setTitle("Question");
			dialog_login
					.setMessage("You are not Login. Are You Want Login Or Create Account");
			dialog_login.setPositiveButton("Login",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							Intent int_login = new Intent(
									getApplicationContext(), Login.class);
							startActivity(int_login);

						}
					});
			dialog_login.setNegativeButton("Register",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int arg1) {
							// TODO Auto-generated method stub
							Intent int_create_account = new Intent(
									getApplicationContext(), Registy.class);
							startActivity(int_create_account);
						}
					});
			dialog_login.create().show();
		} else {
			final Intent int_product = new Intent(getApplicationContext(),
					LoadDatabases.class);
			startActivity(int_product);
		}
	}

	public void Product(View view) {
		if (databaseHandle.getLoginCount() == 0) {
			AlertDialog.Builder dialog_login = new AlertDialog.Builder(
					Interface.this);
			dialog_login.setTitle("Question");
			dialog_login
					.setMessage("You are not Login. Are You Want Login Or Create Account");
			dialog_login.setPositiveButton("Login",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							Intent int_login = new Intent(
									getApplicationContext(), Login.class);
							startActivity(int_login);

						}
					});
			dialog_login.setNegativeButton("Register",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int arg1) {
							// TODO Auto-generated method stub
							Intent int_create_account = new Intent(
									getApplicationContext(), Registy.class);
							startActivity(int_create_account);
						}
					});
			dialog_login.create().show();
		} else {
			if (databaseHandle.getPartnerLoGoCount() != 0) {
				final Intent int_product = new Intent(getApplicationContext(),
						Puckshop.class);
				startActivity(int_product);
			} else {
				final Intent int_product = new Intent(getApplicationContext(),
						Bussiness.class);
				startActivity(int_product);
			}
		}

	}

	public void WishList(View view) {

		// if(databaseHandle.getLoginCount() ==0){
		// AlertDialog.Builder dialog_login = new
		// AlertDialog.Builder(Interface.this);
		// dialog_login.setTitle("Question");
		// dialog_login.setMessage("You are not Login. Are You Want Login Or Create Account");
		// dialog_login.setPositiveButton("Login", new
		// DialogInterface.OnClickListener() {
		//
		// @Override
		// public void onClick(DialogInterface arg0, int arg1) {
		// Intent int_login = new Intent(getApplicationContext(), Login.class);
		// startActivity(int_login);
		// }
		// });
		// dialog_login.setNegativeButton("Register", new
		// DialogInterface.OnClickListener() {
		//
		// @Override
		// public void onClick(DialogInterface dialog, int arg1) {
		// // TODO Auto-generated method stub
		// Intent int_create_account = new Intent(getApplicationContext(),
		// Registy.class);
		// startActivity(int_create_account);
		// }
		// });
		// dialog_login.create().show();
		// }else{
		// if(databaseHandle.getWishListCount() !=0){
		// setContentView(R.layout.mycartitems);
		// ListView lv = (ListView)findViewById(R.id.detail_item);
		// ArrayList<edu.c9.lab411.Cart.WishList> list_item =
		// databaseHandle.getAllWishList();
		// ArrayAdapter<String> array_adapter = new ArrayAdapter<String>(this,
		// android.R.layout.simple_list_item_1);
		// lv.setAdapter(array_adapter);
		// for (edu.c9.lab411.Cart.WishList wishList : list_item) {
		// array_adapter.add(wishList.getName_product());
		// }
		// }else{
		// AlertDialog.Builder dialog_wishlist = new
		// AlertDialog.Builder(Interface.this);
		// dialog_wishlist.setTitle("Notice");
		// dialog_wishlist.setMessage("No Item In WishList!");
		// dialog_wishlist.setPositiveButton("OK", new
		// DialogInterface.OnClickListener() {
		//
		// @Override
		// public void onClick(DialogInterface arg0, int arg1) {
		//
		// }
		// });
		// dialog_wishlist.create().show();
		// }
		// }
	}

	public void MyAccount(View view) {
		Intent int_create_account = new Intent(getApplicationContext(),
				Registy.class);
		startActivity(int_create_account);
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (timerSendSMS != null) {
			timerSendSMS.cancel();
			timerSendSMS = null;
		}
		databaseHandle.deleteTable(TABLE_CART);
		databaseHandle.deleteTable(TABLE_LOGIN);
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		super.onDestroy();
		if (timerSendSMS != null) {
			timerSendSMS.cancel();
			timerSendSMS = null;
		}
		Log.i(TAG, "Stop");
	}

	public class AutoSendSMS extends TimerTask {
		@Override
		public void run() {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					android.location.Location gpsLocation = pos
							.getLocation(LocationManager.GPS_PROVIDER);
					if (gpsLocation != null) {
						double lat = gpsLocation.getLatitude();
						double log = gpsLocation.getLongitude();
						jSonObject.put("Latitude", new Double(lat));
						jSonObject.put("Longitude", new Double(log));
						String jsonString = JSONValue.toJSONString(jSonObject);
						Log.v(TAG, "Toa do" + jsonString);
						// s.SendSMS("01675211874", jsonString);
					} else {
						Log.i("Error", "Cannot GPS");
					}
				}
			});
		}
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		int id = item.getItemId();
		if (id == R.id.refresh) {
			cartitemscount
					.setText(String.valueOf(databaseHandle.getCartCount()));
		}
		if (id == R.id.clearcart) {
			AlertDialog.Builder dialog_clear_cart = new AlertDialog.Builder(
					Interface.this);
			dialog_clear_cart.setTitle("Question");
			dialog_clear_cart.setMessage("Are You Ready Clear Cart?");
			dialog_clear_cart.setPositiveButton("Yes",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							databaseHandle.deleteTable(TABLE_CART);
							onCreate(myBackup);
						}
					});
			dialog_clear_cart.setNegativeButton("No",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int arg1) {
							// TODO Auto-generated method stub
							dialog.cancel();
						}
					});
			dialog_clear_cart.create().show();
		}
		if (id == R.id.clearwishlist) {
			AlertDialog.Builder dialog_clear_wishlist = new AlertDialog.Builder(
					Interface.this);
			dialog_clear_wishlist.setTitle("Question");
			dialog_clear_wishlist.setMessage("Are You Ready Clear WishList?");
			dialog_clear_wishlist.setPositiveButton("Yes",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							databaseHandle.deleteTable(TABLE_WISH_LIST);
						}
					});
			dialog_clear_wishlist.setNegativeButton("No",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int arg1) {
							// TODO Auto-generated method stub
							dialog.cancel();
						}
					});
			dialog_clear_wishlist.create().show();

		}
		if (id == R.id.signin) {
			Intent int_signin = new Intent(getApplicationContext(),
					Login.class);
			startActivity(int_signin);
		}
		if (id == R.id.signout) {
			if (databaseHandle.getLoginCount() == 0) {
				AlertDialog.Builder dialog_warning = new AlertDialog.Builder(
						Interface.this);
				dialog_warning.setTitle("Warning");
				dialog_warning.setMessage("You are not signin");
				dialog_warning.setPositiveButton("OK",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface arg0, int arg1) {

							}
						});
				dialog_warning.create().show();
			} else {
				if (databaseHandle.deleteTable(TABLE_LOGIN) != 0) {
					AlertDialog.Builder dialog_success = new AlertDialog.Builder(
							Interface.this);
					dialog_success.setTitle("Warning");
					dialog_success.setMessage("Sign in Successfully!");
					dialog_success.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface arg0, int arg1) {

								}
							});
					dialog_success.create().show();
				}
			}
		}
		return super.onOptionsItemSelected(item);
	}

	class BeginLayout extends TimerTask {

		@Override
		public void run() {
			setContentView(R.layout.splash);

		}

	}

}
