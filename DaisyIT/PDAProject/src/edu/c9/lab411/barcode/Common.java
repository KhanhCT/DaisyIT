package edu.c9.lab411.barcode;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;

public class Common {
	public Common(Context context) {
	}
	public static String getResText(Context c, int id) {
		return (String) c.getResources().getText(id);
	}
	public static String getNowTime() {
		return getNowTime("yyyy/MM/dd HH:mm:ss");
	}

	public static String getNowDate() {
		return getNowTime("yyyy/MM/dd");
	}

	public static String getNowTime(String format) {
		Calendar tmpCal = Calendar.getInstance();
		SimpleDateFormat tmpSDF = new SimpleDateFormat(format);
		return tmpSDF.format(tmpCal.getTime());
	}

	/* �}�Һ�������k */
	public static void goToUrl(Context c, String url) {
		Uri uri = Uri.parse(url);
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		c.startActivity(intent);
	}

	/* ���Dialog��method */
	public static void showDialog(Context c, String mess) {
		new AlertDialog.Builder(c).setTitle("Message").setMessage(mess)
				.setNegativeButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
					}
				}).show();
	}

	/* �P�_�O�Хd�O�_�s�b */
	public static boolean checkSDCard() {
		if (android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED))
			return true;
		else
			return false;
	}
}
