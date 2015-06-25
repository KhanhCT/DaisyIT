package edu.sfsu.cs.orange.barcode;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class NetTool {
	final static String DebugTag = "NetTool";

	public static String sendPostFileRequest(String uriPath, String FilePath)
			throws Exception {
		String end = "\r\n";
		String twoHyphens = "--";
		String boundary = "*****";

		try {
			URL url = new URL(uriPath);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			/* ���\Input�BOutput�A���ϥ�Cache */
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setUseCaches(false);
			/* �]�w�ǰe��method=POST */
			con.setRequestMethod("POST");
			/* setRequestProperty */
			con.setRequestProperty("Connection", "Keep-Alive");
			con.setRequestProperty("Charset", "UTF-8");
			con.setRequestProperty(
					"Accept",
					"image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, application/x-shockwave-flash, application/x-quickviewplus, */*");
			con.setRequestProperty("Content-Type",
					"multipart/form-data;boundary=" + boundary);
			File f = new File(FilePath);

			/* �]�wDataOutputStream */
			DataOutputStream ds = new DataOutputStream(con.getOutputStream());
			ds.writeBytes(twoHyphens + boundary + end);
			ds.writeBytes("Content-Disposition: form-data; "
					+ "name=\"file1\";filename=\"" + f.getName() + "\"" + end);
			ds.writeBytes(end);

			/* ��o�ɮת�FileInputStream */
			FileInputStream fStream = new FileInputStream(f);
			/* �]�w�C���g�J1024bytes */
			int bufferSize = 1024;
			byte[] buffer = new byte[bufferSize];

			int length = -1;
			/* �q�ɮ�Ū���Ʀܽw�İ� */
			while ((length = fStream.read(buffer)) != -1) {
				/* �N��Ƽg�JDataOutputStream�� */
				ds.write(buffer, 0, length);
			}
			ds.writeBytes(end);
			ds.writeBytes(twoHyphens + boundary + twoHyphens + end);

			/* close streams */
			fStream.close();
			ds.flush();

			/* ��oResponse���e */
			InputStream is = con.getInputStream();
			int ch;
			StringBuffer b = new StringBuffer();
			while ((ch = is.read()) != -1) {
				b.append((char) ch);
			}

			/* ����DataOutputStream */
			ds.close();
			/* �NResponse��ܩ�Dialog */
			return b.toString().trim();
		} catch (Exception e) {
			return "Error:" + e.toString();
		}
	}

	public static String sendPostRequest(String uriPath,
			Map<String, String> params, String encoding) throws Exception {
		// String param="id=67&name"+URLEncoder.encode("�ѱ�","UTF-8");//�n�o�e�����
		StringBuilder sb = new StringBuilder();

		// �i��ʸ�
		for (Map.Entry<String, String> entry : params.entrySet()) {
			sb.append(entry.getKey()).append("=")
					.append(URLEncoder.encode(entry.getValue(), encoding));
			sb.append('&');
		}

		sb.deleteCharAt(sb.length() - 1);

		byte[] data = sb.toString().getBytes();
		URL url = new URL(uriPath);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(6 * 1000);
		conn.setDoOutput(true);// �o�ePOST�ܦ�A��
		conn.setUseCaches(false);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Connection", "Keep-Alive");
		conn.setRequestProperty("Charset", "UTF-8");
		conn.setRequestProperty("Content-Length", String.valueOf(data.length));
		conn.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		DataOutputStream dataOutStream = new DataOutputStream(
				conn.getOutputStream());// �o���X�y
		dataOutStream.write(data);
		dataOutStream.flush();
		dataOutStream.close();

		System.out.println(conn.getResponseCode());

		if (conn.getResponseCode() == 200) {
			/* ��oResponse���e */
			InputStream is = conn.getInputStream();
			int ch;
			StringBuffer b = new StringBuffer();
			while ((ch = is.read()) != -1) {
				b.append((char) ch);
			}

			/* ����DataOutputStream */
			dataOutStream.close();
			/* �NResponse��ܩ�Dialog */
			return b.toString().trim();
		}
		return null;
	}

	/**
	 * ���URL���|��w�����e
	 * 
	 * 
	 * ��o��w���e
	 * */
	public static String getTextContent(String path) throws Exception {
		return getTextContent(path, "ISO-8859-1");
	}

	/**
	 * ���URL���|��w�����e
	 * 
	 * 
	 * ��o��w���e
	 * */
	public static String getTextContent(String path, String encoding)
			throws Exception {
		try {
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(6 * 1000);
			// �O�W�L10��C
			System.out.println(conn.getResponseCode());
			if (conn.getResponseCode() == 200) {
				/* ��oResponse���e */
				InputStream inStream = conn.getInputStream();
				byte[] data = readStream(inStream);

				return new String(data, encoding);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * �����i�H�o��奻�P�y�����
	 * */
	public static InputStream getContent(String uriPath) throws Exception {
		URL url = new URL(uriPath);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(6 * 1000);
		// �O�W�L10��C
		System.out.println(conn.getResponseCode());
		if (conn.getResponseCode() == 200) {
			return conn.getInputStream();
		}
		return null;
	}

	/**
	 * �����i�H�o��奻�P�y�����
	 * */
	public static InputStream getContentImputStream(String path, String encoding)
			throws Exception {
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(6 * 1000);
		// �O�W�L10��C
		System.out.println(conn.getResponseCode());
		if (conn.getResponseCode() == 200) {
			return conn.getInputStream();
		}
		return null;
	}

	/**
	 * ����w���|�A����ơC
	 * 
	 * **/
	public static byte[] getImage(String urlpath) throws Exception {
		URL url = new URL(urlpath);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(6 * 1000);
		// �O�W�L10��C
		if (conn.getResponseCode() == 200) {
			InputStream inputStream = conn.getInputStream();
			return readStream(inputStream);
		}
		return null;
	}

	/**
	 * 
	 * 
	 * */
	public static byte[] readStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outstream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = -1;
		while ((len = inStream.read(buffer)) != -1) {
			outstream.write(buffer, 0, len);
		}
		outstream.close();
		inStream.close();

		return outstream.toByteArray();
	}

	/**
	 * Ū��ƾ�
	 * 
	 * 
	 * */
	public static String getContentFromUrl(String url,
			List<NameValuePair> params) throws Exception {
		String result = "";

		try {
			HttpPost httpRequest = new HttpPost(url);
			if (params != null)
				httpRequest.setEntity(new UrlEncodedFormEntity(params,
						HTTP.UTF_8));
			/* ��oHTTP response */
			HttpResponse httpResponse = new DefaultHttpClient()
					.execute(httpRequest);
			/* �Y���A�X��200 ok */
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				/* ��X�^���r�� */
				result = EntityUtils.toString(httpResponse.getEntity());
			}
			return result;
		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection " + e.toString());
			return "";
		}
	}

	/**
	 * Ū��ƾ�
	 * 
	 * 
	 * */
	public static String getIP() throws Exception {
		InetAddress ia;
		try {
			ia = InetAddress.getLocalHost();
			return ia.getHostAddress();
		} catch (UnknownHostException e) {
			Log.i("Err. get my IP failed.", e.toString());
		}
		return "err";
	}

	/**
	 * �U���ɮ�
	 * 
	 * 
	 * */
	public static boolean DownloadFromUrl(String Url, String filePath)
			throws Exception {
		try {
			URL url = new URL(Url);
			File file = new File(filePath);

			long startTime = System.currentTimeMillis();
			Log.d(DebugTag, "download begining");
			Log.d(DebugTag, "download url:" + url);
			Log.d(DebugTag, "downloaded file name:" + filePath);
			/* Open a connection to that URL. */
			URLConnection ucon = url.openConnection();

			/*
			 * Define InputStreams to read from the URLConnection.
			 */
			InputStream is = ucon.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(is);

			/*
			 * Read bytes to the Buffer until there is nothing more to read(-1).
			 */
			ByteArrayBuffer baf = new ByteArrayBuffer(50);
			int current = 0;
			while ((current = bis.read()) != -1) {
				baf.append((byte) current);
			}

			/* Convert the Bytes read to a String. */
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(baf.toByteArray());
			fos.close();
			Log.d(DebugTag, "download ready in"
					+ ((System.currentTimeMillis() - startTime) / 1000)
					+ " sec");
			return true;

		} catch (IOException e) {
			Log.d(DebugTag, "Error: " + e);
			return false;
		}
	}
}
