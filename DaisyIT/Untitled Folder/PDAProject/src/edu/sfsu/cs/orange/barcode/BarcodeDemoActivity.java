package edu.sfsu.cs.orange.barcode;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebStorage.QuotaUpdater;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class BarcodeDemoActivity extends Activity {
	public static final String TAG = "KIM_DEBUG";
	private static ProgressDialog pDialog;
	private static String sContent = "";
	private static WebView MainWebView;
	private static Handler wHandler = new Handler();
	private static final String JSInterface = "android_js";
	private static final String WebPageUrl = "file:///android_asset/main.html";
	private static final String PostDataUrl = "http://dc.winmate.com.tw/WinmateMobileNet/WebService/data.ashx";
	private static final String UpdateUrl = "http://dc.winmate.com.tw/winmatemobile/updateAPK/BarcodeDemo.apk";
	private static final String VersionUrl = "http://dc.winmate.com.tw/winmatemobile/updateAPK/BarcodeDemoVer.txt";
	private static final int MENU_LIST1 = Menu.FIRST;
	private static final int MENU_LIST2 = Menu.FIRST + 1;
	private static final int MENU_LIST3 = Menu.FIRST + 2;
	private static final int MENU_LIST4 = Menu.FIRST + 3;
	private static MediaPlayer mpAudio;
	// private static int UpdateInteval = 24 * 60 * 60 * 1000;
	private static final int UpdateInteval = 0;
	private static Boolean isExit = false;
	private static long PressTime = 0, PrePressTime = 0;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {  
		super.onCreate(savedInstanceState);
		// ���ê��A�C
		final Window win = this.getWindow();
		win.setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN
				,  WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
		// ���ü��D�C
		requestWindowFeature(Window.FEATURE_CONTEXT_MENU);
		setContentView(R.layout.bar_code);

		if (isOnline() && getAutoUpdateSetting()) {
			AutoUpdate();
			Log.i(TAG, "online");
		} else
			Log.i(TAG, "offline");

		/* �T�w�a�V�s�� */
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		String databasePath = this.getApplicationContext()
				.getDir("database", Context.MODE_PRIVATE).getPath();
		Log.i(TAG, databasePath);

		mpAudio = new MediaPlayer();
		mpAudio = MediaPlayer.create(BarcodeDemoActivity.this, R.raw.bb);
		mpAudio.setVolume(100.0f, 100.0f);

		MainWebView = (WebView) findViewById(R.id.barcode);
		MainWebView.requestFocus(View.FOCUS_DOWN);
		MainWebView
				.addJavascriptInterface(new runCallJavaScript(), JSInterface);
	 
		WebSettings settings = MainWebView.getSettings();
		settings.setJavaScriptEnabled(true);
		settings.setDatabaseEnabled(true);
		settings.setDatabasePath(databasePath + File.separator + "BarcodeDemoDB");
		
		MainWebView.loadUrl(WebPageUrl+"?lang="+getLangCode(getLocale()));

		MainWebView.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onExceededDatabaseQuota(String url,
					String databaseIdentifier, long currentQuota,
					long estimatedSize, long totalUsedQuota,
					QuotaUpdater quotaUpdater) {
				Log.i(TAG, "estimatedSize=" + estimatedSize);
				quotaUpdater.updateQuota(estimatedSize * 2);
			}  
		});   
		//�B�z������JS
		MainWebView.setWebViewClient(new WebViewClient() {    
		    @Override  
		    public void onPageFinished(WebView webView, String url){   
		    	//String lang = getLangCode(getLocale()); 
		    	//MainWebView.loadUrl("javascript:setLanguage('"+lang+"');"); 
		    }  
		});   
	}
	  
	/* -------------�]�w�y�t����------------- */
	public void setLocale(Locale l){
		Resources resources = getResources(); 
		Configuration config = resources.getConfiguration();
		DisplayMetrics dm = resources.getDisplayMetrics();
		config.locale = l; 
		resources.updateConfiguration(config, dm);
	}
	public Locale getLocale(){
		Resources resources = getResources(); 
		Configuration config = resources.getConfiguration();
		return config.locale;
	} 
	public String getLangCode(Locale curLocale){
		String lang = "EN"; 
    	
    	if(curLocale.equals(Locale.SIMPLIFIED_CHINESE) || curLocale.equals(Locale.CHINA) 
    			|| curLocale.equals(Locale.CHINESE) || curLocale.equals(Locale.PRC))
    		lang = "CN";
    	else if(curLocale.equals(Locale.TRADITIONAL_CHINESE) || curLocale.equals(Locale.TAIWAN))
    		lang = "TW";
    	
    	return lang;
	} 
	private Runnable ChooseLanguage = new Runnable() {
			public void run() {
				Builder MyAlertDialog = new AlertDialog.Builder(BarcodeDemoActivity.this); 
				 MyAlertDialog.setIcon(R.drawable.ic_launcher);
				 MyAlertDialog.setTitle(R.string.lang_title); 
								 
				 //�إ߿�ܪ��ƥ�
				 DialogInterface.OnClickListener ListClick = new DialogInterface.OnClickListener()
				 {
					public void onClick(DialogInterface dialog, int which) {
						Locale sLocale;
						
						switch(which){
							case 0:
								sLocale=Locale.ENGLISH;
								break;
							case 1:
								sLocale=Locale.SIMPLIFIED_CHINESE;
								break;
							case 2:
								sLocale=Locale.TRADITIONAL_CHINESE;
								break;
							default:
								sLocale=Locale.ENGLISH;
								break;
						}
						
					 	setLocale(sLocale);
						restartActivity(BarcodeDemoActivity.this);
					}
				 };
				 //�إ߫��U����Ʊ����������ƥ�
				 DialogInterface.OnClickListener Cancel_Click = new DialogInterface.OnClickListener()
				 {
					public void onClick(DialogInterface dialog, int which) {
					}
				 };  
				 
				 final String[] items = getResources().getStringArray(R.array.lang_list);
				 MyAlertDialog.setItems(items, ListClick);
				 MyAlertDialog.setNeutralButton(R.string.cancel, Cancel_Click );
				 MyAlertDialog.show(); 
			}
		};
	/* -------------�]�w�y�t����------------- */
	
	// BACK�⦸���}�{��
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_SEARCH:
		case KeyEvent.KEYCODE_HOME:
			return true;
		case KeyEvent.KEYCODE_BACK:  
			PressTime = new Date().getTime();
			if ((PressTime - PrePressTime) >= 2000)
				isExit = false;

			if (!isExit) {
				isExit = true;
				PrePressTime = new Date().getTime();
				Toast.makeText(this, R.string.exit_confirm, Toast.LENGTH_SHORT)
						.show();
			} else {
				finish();
				System.exit(0);
			}
			return true;
			/*case KeyEvent.KEYCODE_MENU:
			PressTime = new Date().getTime();
			if ((PressTime - PrePressTime) >= 2000)
				isExit = false;

			if (!isExit) {
				isExit = true;
				PrePressTime = new Date().getTime();
				Toast.makeText(this, "Press again to pop menu", Toast.LENGTH_SHORT)
						.show();
			}
			else{
				return super.onKeyDown(keyCode, event);
			}
			return true;*/
		default:
			break;
		}
		
		return super.onKeyDown(keyCode, event);
	}

	// �إ߿��ƥ�
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		/* menu�s��ID */
		int idGroup1 = 0;

		/* The order position of the item */
		int orderMenuItem1 = Menu.NONE;
		int orderMenuItem2 = Menu.NONE + 1;
		int orderMenuItem3 = Menu.NONE + 2;
		int orderMenuItem4 = Menu.NONE + 3;
		int auto_update_res = getAutoUpdateSetting() ? R.string.auto_update_on
				: R.string.auto_update_off;
		String Version=Common.getResText(this, R.string.version)+":"+getVersionName();
		
		menu.add(idGroup1, MENU_LIST1, orderMenuItem1, auto_update_res);
		menu.add(idGroup1, MENU_LIST2, orderMenuItem2, R.string.lang);
		menu.add(idGroup1, MENU_LIST3, orderMenuItem3, Version);
		menu.add(idGroup1, MENU_LIST4, orderMenuItem4, R.string.exit);
		
		return super.onCreateOptionsMenu(menu);
	}

	// ����ܫ᪺�ƥ�
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		try {
			switch (item.getItemId()) {
			case (MENU_LIST1):
				Boolean AutoUpdate = !getAutoUpdateSetting();

				SharedPreferences.Editor editor = getPreferences(0).edit();
				editor.putBoolean("AutoUpdate", AutoUpdate);
				editor.commit();

				if (AutoUpdate)
					item.setTitle(R.string.auto_update_on);
				else
					item.setTitle(R.string.auto_update_off);
				break;
			case (MENU_LIST2): 
				wHandler.post(ChooseLanguage); 
				break;
			case (MENU_LIST4):
				finish();
				System.exit(0);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return super.onOptionsItemSelected(item);
	}
	
	/* -------------�۰ʧ�s����------------- */
	public boolean getAutoUpdateSetting() {
		SharedPreferences prefs = getPreferences(0);
		return prefs.getBoolean("AutoUpdate", true);
	}

	public void AutoUpdate() {
		/* Get Last Update Time from Preferences */
		SharedPreferences prefs = getPreferences(0);
		long lastUpdateTime = prefs.getLong("lastUpdateTime", 0);

		/* Should Activity Check for Updates Now? */
		if ((lastUpdateTime + UpdateInteval) < System.currentTimeMillis()) {
			/* Start Update */
			checkUpdate.start();

			/* Save current timestamp for next Check */
			lastUpdateTime = System.currentTimeMillis();
			SharedPreferences.Editor editor = getPreferences(0).edit();
			editor.putLong("lastUpdateTime", lastUpdateTime);
			editor.commit();
		}
	}

	/* ��o�ثe�{�������� */
	public int getVersion() {
		int v = 0;
		try {
			v = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return v;
	}
	public String getVersionName() {
		String v="";
		
		try {
			v = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return v;
	}
	/* This Thread checks for Updates in the Background */
	private Thread checkUpdate = new Thread() {
		public void run() {
			try {
				String s = NetTool.getTextContent(VersionUrl).toString();
				if (s == null)
					return;
				/* Get current Version Number */
				int curVersion = getVersion();
				int newVersion = Integer.valueOf(s);

				//Log.i("curVersion:", String.valueOf(curVersion));
				//Log.i("newVersion:", String.valueOf(newVersion));

				if (newVersion > curVersion) {
					wHandler.post(showUpdate);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};

	/* This Runnable creates a Dialog and asks the user to open the Market */
	private Runnable showUpdate = new Runnable() {
		public void run() {
			new AlertDialog.Builder(BarcodeDemoActivity.this)
					.setIcon(R.drawable.ic_launcher)
					.setTitle(R.string.update_title)
					.setMessage(R.string.update_tip)

					.setPositiveButton(R.string.yes,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									pDialog = ProgressDialog.show(
											BarcodeDemoActivity.this,
											Common.getResText(BarcodeDemoActivity.this, R.string.processing)+"..." , 
											Common.getResText(BarcodeDemoActivity.this, R.string.pls_wait)+"...",
											true);
									new UpdateTask().execute();
								}
							})

					.setNegativeButton(R.string.no,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									/* User clicked Cancel */
								}
							}).show();
		}
	};

	class UpdateTask extends AsyncTask<Void, Void, String> {
		@Override
		protected String doInBackground(Void... unsued) {
			try {
				if (!Common.checkSDCard())
					return Common.getResText(BarcodeDemoActivity.this,R.string.no_sdcard);

				String RootPath = android.os.Environment
						.getExternalStorageDirectory().toString();
				String filePath = RootPath + File.separator + "download"
						+ File.separator + "BarcodeDemo.apk";
				boolean Result = false;
				Result = NetTool.DownloadFromUrl(UpdateUrl, filePath);

				if (Result) {
					IOTool.openFile(BarcodeDemoActivity.this, filePath);
					// IOTool.delFile(filePath);
					return "Update sucess";
				} else
					return "Update fail!!";
			} catch (Exception e) {
				if (pDialog.isShowing())
					pDialog.dismiss();
				Toast.makeText(getApplicationContext(), e.getMessage(),
						Toast.LENGTH_LONG).show();
				Log.e(e.getClass().getName(), e.getMessage(), e);
				return null;
			}
		}

		@Override
		protected void onProgressUpdate(Void... unsued) {

		}

		@Override
		protected void onPostExecute(String sResponse) {
			try {
				if (pDialog.isShowing())
					pDialog.dismiss();

				if (sResponse != null) {
					Common.showDialog(BarcodeDemoActivity.this, sResponse);
				}
			} catch (Exception e) {
				Common.showDialog(BarcodeDemoActivity.this, e.getMessage());
				Log.e(e.getClass().getName(), e.getMessage(), e);
			}
		}
	}
	/* -------------�۰ʧ�s����------------- */
	
	final class runCallJavaScript {  
		public void PlayAudio(final String content) {
			wHandler.post(new Runnable() {
				// @Override
				public void run() {
					try {
						mpAudio.start();
					} catch (Exception e) {
						Toast.makeText(getApplicationContext(), e.getMessage(),
								Toast.LENGTH_LONG).show();
						Log.e(e.getClass().getName(), e.getMessage(), e);
					}
				}
			});
		}

		public void ProcData(final String content) {
			wHandler.post(new Runnable() {
				// @Override
				public void run() {
					try {
						new AlertDialog.Builder(BarcodeDemoActivity.this)
								.setIcon(R.drawable.ic_launcher)
								.setTitle(R.string.submit_title)
								.setMessage(R.string.submit_msg)

								.setPositiveButton(R.string.yes,
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface dialog,
													int whichButton) {
												pDialog = ProgressDialog
														.show(BarcodeDemoActivity.this,
																Common.getResText(BarcodeDemoActivity.this, R.string.processing)+"..." , 
																Common.getResText(BarcodeDemoActivity.this, R.string.pls_wait)+"...",
																true);
												sContent = content;
												new ProcessTask().execute();
											}
										})

								.setNegativeButton(R.string.no,
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface dialog,
													int whichButton) {
												/* User clicked Cancel */
											}
										}).show();
					} catch (Exception e) {
						Toast.makeText(getApplicationContext(), e.getMessage(),
								Toast.LENGTH_LONG).show();
						Log.e(e.getClass().getName(), e.getMessage(), e);
					}
				}
			});
		}
		public void MenuSwitch(final String IsLock) {
			wHandler.post(new Runnable() {
				// @Override
				public void run() {
					try {
						int iState = (IsLock.equals("Y")) ? 2 : 0; // �n�]�w��TOUCH_STATE,  0=Enable, 1=Disable, 2=VirtualKey Disable
											
						Intent intent = new Intent("android.intent.action.TOUCH_STATE_NOTIFY");
						Bundle bundle = new Bundle();
						 
						bundle.putInt("TOUCH_STATE", iState);  // key : "TOUCH_STATE" , value : iState ( 0 ~ 2 �����A )
						 
						intent.putExtras(bundle);
						 
						sendBroadcast(intent);
					} catch (Exception e) {
						Toast.makeText(getApplicationContext(), e.getMessage(),
								Toast.LENGTH_LONG).show();
						Log.e(e.getClass().getName(), e.getMessage(), e);
					}
				}
			});
		}
		class ProcessTask extends AsyncTask<Void, Void, String> {
			@Override
			protected String doInBackground(Void... unsued) {
				try {
					String response = "";
					String FileName = Common.getNowTime("yyyyMMddHHmmss") + ".csv";

					/*if (isOnline()) {
						//��oResponse���e
						Map<String, String> map = new HashMap<String, String>();
						map.put("data", sContent);
						response = NetTool.sendPostRequest(PostDataUrl, map,
								"UTF-8"); 
					} else {
						response = Common.getResText(BarcodeDemoActivity.this, R.string.save_msg) + FileName;
					}*/
					response = Common.getResText(BarcodeDemoActivity.this, R.string.save_msg) + " " + FileName;
					writeFile(sContent, FileName);
					return response;
				} catch (Exception e) {
					if (pDialog.isShowing())
						pDialog.dismiss();
					Toast.makeText(getApplicationContext(), e.getMessage(),
							Toast.LENGTH_LONG).show();
					Log.e(e.getClass().getName(), e.getMessage(), e);
					return null;
				}
			}

			@Override
			protected void onProgressUpdate(Void... unsued) {

			}

			@Override
			protected void onPostExecute(String sResponse) {
				try {
					if (pDialog.isShowing())
						pDialog.dismiss();

					if (sResponse != null) {
						Common.showDialog(BarcodeDemoActivity.this, sResponse);
					}
				} catch (Exception e) {
					Common.showDialog(BarcodeDemoActivity.this, e.getMessage());
					Log.e(e.getClass().getName(), e.getMessage(), e);
				}
			}
		}
	}

	private void writeFile(String data, String FileName) {
		if (!Common.checkSDCard()) {
			Common.showDialog(BarcodeDemoActivity.this, Common.getResText(this,R.string.no_sdcard));
			return;
		}

		File root = new File(android.os.Environment
				.getExternalStorageDirectory().toString()
				+ File.separator
				+ "WinmateBarcode" + File.separator);
		if (!root.exists())
			root.mkdirs();

		FileWriter filewriter = null;
		BufferedWriter out = null;

		try {
			if (!root.canWrite())
				Common.showDialog(BarcodeDemoActivity.this, Common.getResText(this,R.string.write_file_err));

			File file = new File(root, FileName);
			filewriter = new FileWriter(file);
			out = new BufferedWriter(filewriter);
			out.write(data);
			out.flush();
		} catch (Exception e) {
			Log.i(TAG, e.toString());
			e.printStackTrace();
		} finally {
			try {
				filewriter.close();
				out.close();
			} catch (IOException e) {
				Log.i(TAG, e.toString());
				e.printStackTrace();
			}
		}
	}

	/* -------------��L�\��------------- */
	public boolean isOnline() { 
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();

		if (netInfo != null && netInfo.isConnectedOrConnecting()
				&& netInfo.isAvailable())
			return true;
		else
			return false;
	}
	
	public static void restartActivity(Activity act){
		Intent intent = act.getIntent();
		act.finish();
		act.startActivity(intent);
	}
}