package edu.c9.lab411.barcode;

import java.io.File;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

public class IOTool {
	final static String DebugTag = "IOTool";

	/* method */
	public static void openFile(Context c, String filePath) throws Exception {
		File f = new File(filePath);
		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(android.content.Intent.ACTION_VIEW);

		String type = getMIMEType(f);

		intent.setDataAndType(Uri.fromFile(f), type);
		c.startActivity(intent);
	}

	public static void delFile(String filePath) throws Exception {
		Log.i(DebugTag, "The TempFile(" + filePath + ") was deleted.");
		File myFile = new File(filePath);
		if (myFile.exists()) {
			myFile.delete();
		}
	}

	/* �P�_�ɮ�MimeType��method */
	public static String getMIMEType(File f) throws Exception {
		String type = "";
		String fName = f.getName();
		/* ��o���ɦW */
		String end = fName
				.substring(fName.lastIndexOf(".") + 1, fName.length())
				.toLowerCase();

		/* �̪��ɦW�������M�wMimeType */
		if (end.equals("m4a") || end.equals("mp3") || end.equals("mid")
				|| end.equals("xmf") || end.equals("ogg") || end.equals("wav")) {
			type = "audio";
		} else if (end.equals("3gp") || end.equals("mp4")) {
			type = "video";
		} else if (end.equals("jpg") || end.equals("gif") || end.equals("png")
				|| end.equals("jpeg") || end.equals("bmp")) {
			type = "image";
		} else if (end.equals("apk")) {
			/* android.permission.INSTALL_PACKAGES */
			type = "application/vnd.android.package-archive";
		} else {
			type = "*";
		}
		/* �p�G�L�k�����}�ҡA�N���X�n��M�浹�ϥΪ̿�� */

		if (end.equals("apk")) {

		} else {
			type += "/*";
		}
		return type;
	}

}
