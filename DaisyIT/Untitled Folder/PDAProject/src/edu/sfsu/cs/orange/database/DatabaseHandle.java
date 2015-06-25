package edu.sfsu.cs.orange.database;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import edu.sfsu.cs.orange.goodssearch.Goods;
import edu.sfsu.cs.orange.goodssearch.Groups;
import edu.sfsu.cs.orange.goodssearch.JSONParser;
import edu.sfsu.cs.orange.goodssearch.Product;
import edu.sfsu.cs.orange.goodssearch.WareGoods;
import edu.sfsu.cs.orange.goodssearch.WareHouse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandle extends SQLiteOpenHelper{
	JSONParser json_parser = new JSONParser();
	ArrayList<HashMap<String, String>> products_list;
	private static final String TAG_URL = "http://trongkhanhbkhn.meximas.com/Server/Model/getAllTable.php";
	// tag database
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "saletool";
	private static final String DATABASE_TABLE = "products";
	// tag table
	private static final String TABLE_GOODS = "Goods";
	private static final String TABLE_WARE_HOUSE = "WareHouse";
	private static final String TABLE_GROUP = "Groups";
	private static final String TABLE_WARE_GOODS = "WareGoods";
	// JSON Node names
	private static final String TAG_ID = "id";
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_PRODUCTS = "products";
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
	private static final String TAG_CREATE_AT = "created_at";
	private static final String TAG_WARE_HOUSE_ID = "id_ware_house";
	private static final String TAG_GROUP_ID = "id_group_mer";
	private static final String TAG_UPDATE_AT = "updated_at";
	private static final String TAG_FOREIGN_ACTION = "ON UPDATE CASCADE ON DELETE CASCADE";
	private static final String TAG = "Product";
	
	 
	/*private static final String TAG_ON_FOREIGN = "PRAGMA foreign_keys=ON;";
	private static final String CREATE_TABLE_GROUP = "CREATE TABLE " + TABLE_GROUP + "("+TAG_ID_GROUP + 
			" VARCHAR(20)," +TAG_NAME_GROUP +" TEXT NOT NULL," +TAG_WARE_HOUSE_ID +" VARCHAR(20) NOT NULL,"+ "PRIMARY KEY ("+TAG_ID_GROUP +"),"+
			"FOREIGN KEY ("+ TAG_WARE_HOUSE_ID +")"+" REFERENCES "+TABLE_WARE_HOUSE +"("+TAG_ID_WARE +") "+
			TAG_FOREIGN_ACTION +")";
	private static final String CREATE_TABLE_WARE_HOUSE = "CREATE TABLE "+ TABLE_WARE_HOUSE +
			"(" + TAG_ID_WARE + " VARCHAR(20) NOT NULL," +TAG_NAME_WARE +" TEXT NOT NULL," +"PRIMARY KEY ("+TAG_ID_WARE +")"+")";
	private static final String CREATE_TABLE_GOODS = "CREATE TABLE " + TABLE_GOODS +"(" +TAG_ID_MER+
			" VARCHAR(20) NOT NULL ," + TAG_NAME_MER + " TEXT NOT NULL," + TAG_SOLD +" INTEGER NOT NULL," +
			TAG_RESERVER +" INTEGER NOT NULL," + TAG_STOCK_TRAN +" INTEGER NOT NULL," + TAG_LIQUIDATED +
			" INTEGER NOT NULL," + TAG_ICON +" BLOB NOT NULL," +TAG_CREATE_AT +" DATETIME NOT NULL," +
			 TAG_UPDATE_AT +" DATETIME ," + TAG_GROUP_ID + " VARCHAR(20) NOT NULL,"+"PRIMARY KEY ("+TAG_ID_MER +"),"+"FOREIGN KEY (" +TAG_GROUP_ID +")" +" REFERENCES " + TABLE_GROUP + "(" +TAG_ID_GROUP+
			") "+ TAG_FOREIGN_ACTION  +")";*/
	private static final String TAG_ON_FOREIGN = "PRAGMA foreign_keys=ON;";
	private static final String CREATE_TABLE_GROUP = "CREATE TABLE " + TABLE_GROUP + "("+TAG_ID_GROUP + 
			" VARCHAR(20)," +TAG_NAME_GROUP +" TEXT NOT NULL," + "PRIMARY KEY ("+TAG_ID_GROUP +")" +")";
	private static final String CREATE_TABLE_WARE_HOUSE = "CREATE TABLE "+ TABLE_WARE_HOUSE +
			"(" + TAG_ID_WARE + " VARCHAR(20) NOT NULL," +TAG_NAME_WARE +" TEXT NOT NULL," +"PRIMARY KEY ("+TAG_ID_WARE +")"+")";
	private static final String CREATE_TABLE_WARE_GOODS = "CREATE TABLE " + TABLE_WARE_GOODS +"(" +TAG_ID_MER+
			" VARCHAR(20) NOT NULL ," + TAG_WARE_HOUSE_ID + " VARCHAR(20) NOT NULL," + TAG_SOLD +" INTEGER NOT NULL," +
			TAG_RESERVER +" INTEGER NOT NULL," + TAG_STOCK_TRAN +" INTEGER NOT NULL," + TAG_LIQUIDATED +
			" INTEGER NOT NULL," + TAG_ICON +" BLOB NOT NULL," +TAG_CREATE_AT +" DATETIME NOT NULL," +
			 TAG_UPDATE_AT +" DATETIME ," + TAG_GROUP_ID + " VARCHAR(20) NOT NULL,"+"PRIMARY KEY ("+TAG_ID_MER +","+TAG_WARE_HOUSE_ID+"),"+"FOREIGN KEY (" +TAG_GROUP_ID +")" +" REFERENCES " + TABLE_GROUP + "(" +TAG_ID_GROUP+
			") "+ TAG_FOREIGN_ACTION+","+ "FOREIGN KEY (" + TAG_WARE_HOUSE_ID +")"+" REFERENCES " +TABLE_WARE_HOUSE +"(" +TAG_ID_WARE + ") "+TAG_FOREIGN_ACTION +","+"FOREIGN KEY ("+ TAG_ID_MER +")"+" REFERENCES "+TABLE_GOODS+"("+
			 TAG_ID_MER +")"+ TAG_FOREIGN_ACTION +")";
	private static final String CREATE_TABLE_GOODS = "CREATE TABLE " + TABLE_GOODS + "("+TAG_ID_MER + 
			" VARCHAR(20)," +TAG_NAME_MER +" TEXT NOT NULL," + "PRIMARY KEY ("+TAG_ID_MER +")" +")";
	
	//SQLiteDatabase database;
	public DatabaseHandle(Context context) {
		/**
		 * create database
		 * database is save in sdcard @path:/data/data/app_name/databases
		 */
		super(context, DATABASE_NAME,null, DATABASE_VERSION);
		
	}
	/***
	 * this function use update datbase sqlite from sql server
	 * @param context
	 */
	
	public void UpdateProducts(){
		List<NameValuePair> params = new ArrayList<NameValuePair>();	
		//database = SQLiteDatabase.openDatabase(TAG_DATABASE, null, SQLiteDatabase.CREATE_IF_NECESSARY);
		JSONObject json_result = json_parser.makeHttpRequest(TAG_URL, "GET", params);
		Log.i(TAG, json_result.toString());
		try{
			int success = json_result.getInt(TAG_SUCCESS);
			if(success == 1){
				JSONArray products = json_result.getJSONArray(TAG_PRODUCTS);
				for(int i = 0; i<=json_result.length(); i++){
					JSONObject json_object = products.getJSONObject(i);
					String id_ware = json_object.getString(TAG_ID_WARE);
					String name_ware = json_object.getString(TAG_NAME_WARE);
					String id_group = json_object.getString(TAG_ID_GROUP);
					String name_group = json_object.getString(TAG_NAME_GROUP);
					String id_goods = json_object.getString(TAG_ID_MER);
					String name_goods = json_object.getString(TAG_NAME_MER);
					String sold = json_object.getString(TAG_SOLD);
					String reserver = json_object.getString(TAG_RESERVER);
					String stock_tranfer = json_object.getString(TAG_STOCK_TRAN);
					String liquidated = json_object.getString(TAG_LIQUIDATED);
					String icon = json_object.getString(TAG_ICON);
					String created_at = json_object.getString(TAG_CREATE_AT);
					String updated_at = json_object.getString(TAG_UPDATE_AT);													
					WareHouse ware_house = new WareHouse(id_ware, name_ware);
					addWareHouse(ware_house);
					Groups group = new Groups(id_group, name_group);
					addGroup(group);
					Goods goods = new Goods(id_goods, name_goods);
					addGoods(goods);
					WareGoods ware_goods = new WareGoods(goods, Integer.parseInt(sold), Integer.parseInt(reserver), Integer.parseInt(stock_tranfer), Integer.parseInt(liquidated), icon, created_at, updated_at, ware_house, group);
					addWareGoods(ware_goods);
				}
			}	
		}catch (JSONException e){
			e.printStackTrace();
			
		}
	}
	@Override
	public void onOpen(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		super.onOpen(db);
		if (!db.isReadOnly()) {
	        // Enable foreign key constraints
	        db.execSQL(TAG_ON_FOREIGN);
	    }
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_TABLE_WARE_HOUSE);
		db.execSQL(CREATE_TABLE_GROUP);
		db.execSQL(CREATE_TABLE_GOODS);
		db.execSQL(CREATE_TABLE_WARE_GOODS);
		
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_GOODS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROUP);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_WARE_HOUSE);
		onCreate(db);
	}
	private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
	}
	
	/**
	 * this function add goods in goods table database
	 * @param good
	 * @return
	 */
	public int addGoods(Goods goods){
		int result = 0;
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(TAG_ID_MER, goods.getId_good());
		values.put(TAG_NAME_MER, goods.getName_good());
		String msg;
		if(db.insert(TABLE_GOODS, null, values) == -1){
			msg = "Error Insert ";
			result = 0;
		}else{
			msg = "Success Insert";
			result =1;
		}
		Log.i(TAG,msg);
		closeDatabase(db);
		return result;
	}
	public int addWareHouse(WareHouse ware_house){
		int result;
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(TAG_ID_WARE, ware_house.getId_ware());
		values.put(TAG_NAME_WARE, ware_house.getName_ware());
		String msg;
		if(db.insert(TABLE_WARE_HOUSE, null, values) ==-1){
			msg = "Error Insert ";
			result = 0;
		}else{
			msg = "Success Insert";
			result =1;
		}
		Log.i(TAG,msg);
		closeDatabase(db);
		return result;
	}
	public int addGroup(Groups group){
		int result;
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(TAG_ID_GROUP, group.getId_group());
		values.put(TAG_NAME_GROUP, group.getName_group());
		String msg;
		if(db.insert(TABLE_GROUP, null, values) ==-1){
			msg = "Error Insert ";
			result = 0;
		}
		else{
			msg = "Success Insert";
			result =1;
		}
		Log.i(TAG,msg);
		closeDatabase(db);
		return result;
	}
	public int addWareGoods(WareGoods ware_goods){
		int result;
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(TAG_ID_MER, ware_goods.getGoods().getId_good());
		values.put(TAG_ID_WARE,ware_goods.getWare_house().getId_ware());
		values.put(TAG_SOLD, ware_goods.getSold());
		values.put(TAG_RESERVER, ware_goods.getReserver());
		values.put(TAG_STOCK_TRAN, ware_goods.getStock_tranfer());
		values.put(TAG_LIQUIDATED, ware_goods.getLiq());
		values.put(TAG_ICON, ware_goods.getIcon());
		values.put(TAG_CREATE_AT,  ware_goods.getCreate_at());
		values.put(TAG_UPDATE_AT, ware_goods.getUpdate_at());
		
		values.put(TAG_ID_GROUP, ware_goods.getGroups().getId_group());
		
		String msg;
		if(db.insert(TABLE_WARE_GOODS,null, values) ==-1){
			msg = "Error Insert ";
			result = 0;
		}
		else{
			msg = "Success Insert";
			result =1;
		}
		Log.i(TAG,msg);
		closeDatabase(db);
		return result;
	}
	

	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Goods getGoods(String id){
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.query(TABLE_GOODS, null, TAG_ID_MER +"=?", new String[]{String.valueOf(id)}, null, null, null, null);
		
		if(cursor !=null){
			cursor.moveToFirst();
		}
		Goods goods = new Goods();
		goods.setId_good(cursor.getString(cursor.getColumnIndex(TAG_ID_MER)));
		goods.setName_goods(cursor.getString(cursor.getColumnIndex(TAG_NAME_MER)));
		cursor.close();
		closeDatabase(db);
		return goods;
		
	}
	public WareHouse getWareHouse(String id){
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.query(TABLE_WARE_HOUSE, null, TAG_ID_WARE +"=?", new String[]{String.valueOf(id)}, null, null, null, null);
		if(cursor != null){
			cursor.moveToFirst();
		}
		WareHouse ware_house = new WareHouse();
		ware_house.setId_ware(cursor.getString(cursor.getColumnIndex(TAG_ID_WARE)));
		ware_house.setName_ware(cursor.getString(cursor.getColumnIndex(TAG_NAME_WARE)));
		cursor.close();
		closeDatabase(db);
		return ware_house;
	}
	public Groups getGroup(String id){
		SQLiteDatabase db =  this.getWritableDatabase();
		Cursor cursor = db.query(TABLE_GROUP, null, TAG_ID_GROUP +"=?", new String[]{String.valueOf(id)}, null, null, null, null);
		if(cursor !=null){
			cursor.moveToFirst();
		}
		Groups group = new Groups();
		group.setId_group(cursor.getString(cursor.getColumnIndex(TAG_ID_GROUP)));
		group.setName_group(cursor.getString(cursor.getColumnIndex(TAG_NAME_GROUP)));
		cursor.close();
		closeDatabase(db);
		return group;
	}
	public WareGoods getWareGoods(String id_goods, String id_ware){
		SQLiteDatabase db = this.getWritableDatabase();
		String[] whereArgs = new String[]{id_goods, id_ware};
		Cursor cursor =  db.query(TABLE_WARE_GOODS, null,TAG_ID_MER +"? OR"+ TAG_ID_WARE +"=?",whereArgs , null, null, null, null);
		if(cursor !=null){
			cursor.moveToFirst();
		}
		WareGoods ware_goods = new WareGoods();
		Goods goods = new Goods();
		Groups group = new Groups();
		WareHouse ware_house = new WareHouse();
		ware_goods.setSold(cursor.getInt(cursor.getColumnIndex(TAG_SOLD)));
		ware_goods.setReserver(cursor.getInt(cursor.getColumnIndex(TAG_RESERVER)));
		ware_goods.setStock_tranfer(cursor.getInt(cursor.getColumnIndex(TAG_STOCK_TRAN)));
		ware_goods.setLiq(cursor.getInt(cursor.getColumnIndex(TAG_LIQUIDATED)));
		ware_goods.setIcon(cursor.getString(cursor.getColumnIndex(TAG_ICON)));
		ware_goods.setCreate_at(cursor.getString(cursor.getColumnIndex(TAG_CREATE_AT)));
		ware_goods.setUpdate_at(cursor.getString(cursor.getColumnIndex(TAG_UPDATE_AT)));
		goods = getGoods(id_goods);
		ware_goods.setGoods(goods);
		group = getGroup(cursor.getString(cursor.getColumnIndex(TAG_ID_GROUP)));
		ware_goods.setGroups(group);
		ware_house = getWareHouse(id_ware);
		ware_goods.setWare_house(ware_house);
		cursor.close();
		closeDatabase(db);
		return ware_goods;
		
	}
	
	public WareGoods getWareGoodsGroups(String id_goods, String id_ware, String id_group){
		SQLiteDatabase db = this.getWritableDatabase();
		String[] whereArgs = new String[]{String.valueOf(id_goods), String.valueOf(id_ware), String.valueOf(id_group)};
		Cursor cursor =  db.query(TABLE_WARE_GOODS, null,TAG_ID_MER +"=? AND "+ TAG_ID_WARE +"=? AND " + TAG_ID_GROUP +"=?",whereArgs , null, null, null, null);
		if(cursor !=null){
			cursor.moveToFirst();
		}
		WareGoods ware_goods = new WareGoods();
		Goods goods = new Goods();
		Groups group = new Groups();
		WareHouse ware_house = new WareHouse();
		ware_goods.setSold(cursor.getInt(cursor.getColumnIndex(TAG_SOLD)));
		ware_goods.setReserver(cursor.getInt(cursor.getColumnIndex(TAG_RESERVER)));
		ware_goods.setStock_tranfer(cursor.getInt(cursor.getColumnIndex(TAG_STOCK_TRAN)));
		ware_goods.setLiq(cursor.getInt(cursor.getColumnIndex(TAG_LIQUIDATED)));
		ware_goods.setIcon(cursor.getString(cursor.getColumnIndex(TAG_ICON)));
		ware_goods.setCreate_at(cursor.getString(cursor.getColumnIndex(TAG_CREATE_AT)));
		ware_goods.setUpdate_at(cursor.getString(cursor.getColumnIndex(TAG_UPDATE_AT)));
		goods = getGoods(id_goods);
		ware_goods.setGoods(goods);
		group = getGroup(id_group);
		ware_goods.setGroups(group);
		ware_house = getWareHouse(id_ware);
		ware_goods.setWare_house(ware_house);
		cursor.close();
		closeDatabase(db);
		return ware_goods;
		
	}
	
	public ArrayList<WareGoods> getWareGoodsGroup(String id_ware, String id_group){
		 ArrayList<WareGoods> list = new ArrayList<WareGoods>();
		SQLiteDatabase db = this.getWritableDatabase();
		String[] whereArgs = new String[]{String.valueOf(id_ware), String.valueOf(id_group)};
		//String selectQuery = "SELECT * FROM "+ TABLE_GROUP +" WHERE " +TAG_ID_WARE + "=?"+id_ware.toString() ;
		Cursor cursor = db.query(TABLE_WARE_GOODS, null, TAG_ID_WARE +"=? AND "+ TAG_ID_GROUP + "=?", whereArgs, null, null, null, null);	
		if(cursor !=null){
			cursor.moveToFirst();
			do{
				WareGoods ware_goods = new WareGoods();
				Goods goods = new Goods();
				Groups group = new Groups();
				WareHouse ware_house = new WareHouse();
				ware_goods.setSold(cursor.getInt(cursor.getColumnIndex(TAG_SOLD)));
				ware_goods.setReserver(cursor.getInt(cursor.getColumnIndex(TAG_RESERVER)));
				ware_goods.setStock_tranfer(cursor.getInt(cursor.getColumnIndex(TAG_STOCK_TRAN)));
				ware_goods.setLiq(cursor.getInt(cursor.getColumnIndex(TAG_LIQUIDATED)));
				ware_goods.setIcon(cursor.getString(cursor.getColumnIndex(TAG_ICON)));
				ware_goods.setCreate_at(cursor.getString(cursor.getColumnIndex(TAG_CREATE_AT)));
				ware_goods.setUpdate_at(cursor.getString(cursor.getColumnIndex(TAG_UPDATE_AT)));
				goods = getGoods(cursor.getString(cursor.getColumnIndex(TAG_ID_MER)));
				ware_goods.setGoods(goods);
				group = getGroup(id_group);
				ware_goods.setGroups(group);
				ware_house = getWareHouse(id_ware);
				ware_goods.setWare_house(ware_house);
				list.add(ware_goods);
			}while(cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return list;
	}
	/*public ArrayList<WareGoods> getWareGoodsGroup(String id_ware, String id_group, String id_goods){
		ArrayList<WareGoods> list = new ArrayList<WareGoods>();
		SQLiteDatabase db = this.getWritableDatabase();
		String[] whereArgs = new String[]{id_ware, id_group, id_goods};
		//String selectQuery = "SELECT * FROM "+ TABLE_GROUP +" WHERE " +TAG_ID_WARE + "=?"+id_ware.toString() ;
		Cursor cursor = db.query(TABLE_WARE_GOODS, null, TAG_ID_WARE +"=? OR "+ TAG_ID_GROUP + "=? OR"+ TAG_ID_MER +"=?", whereArgs, null, null, null, null);	
		if(cursor !=null){
			cursor.moveToFirst();
			do{
				WareGoods ware_goods = new WareGoods();
				Goods goods = new Goods();
				Groups group = new Groups();
				WareHouse ware_house = new WareHouse();
				ware_goods.setSold(cursor.getInt(cursor.getColumnIndex(TAG_SOLD)));
				ware_goods.setReserver(cursor.getInt(cursor.getColumnIndex(TAG_RESERVER)));
				ware_goods.setStock_tranfer(cursor.getInt(cursor.getColumnIndex(TAG_STOCK_TRAN)));
				ware_goods.setLiq(cursor.getInt(cursor.getColumnIndex(TAG_LIQUIDATED)));
				ware_goods.setIcon(cursor.getString(cursor.getColumnIndex(TAG_ICON)));
				ware_goods.setCreate_at(cursor.getString(cursor.getColumnIndex(TAG_CREATE_AT)));
				ware_goods.setUpdate_at(cursor.getString(cursor.getColumnIndex(TAG_UPDATE_AT)));
				goods = getGoods(cursor.getString(cursor.getColumnIndex(TAG_ID_MER)));
				ware_goods.setGoods(goods);
				group = getGroup(id_group);
				ware_goods.setGroups(group);
				ware_house = getWareHouse(id_ware);
				ware_goods.setWare_house(ware_house);
				list.add(ware_goods);
			}while(cursor.moveToNext());
		}
		cursor.close();
		db.close();
		
		return list;
	}
	*/
	/**
	 * this function get product from database with command sql
	 * @param id: name id of product want find
	 * @return Product contain all colum
	 */
	public Product getProduct(int id){
		//Create and/or open a database that will be used for reading and writing. 
		SQLiteDatabase db = this.getReadableDatabase();
		// create product 
		Product product = null;
		// cursor contain data when query
		Cursor cursor = db.query(DATABASE_TABLE, null, TAG_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);
		// first cursor null not point other. 
		// moveToFirst to cusor point first line
		cursor.moveToFirst();
		// loop all line database
		while(cursor.isAfterLast() == false){
			product = new Product(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), Integer.parseInt(cursor.getString(7)), Integer.parseInt(cursor.getString(8)), Integer.parseInt(cursor.getString(9)), Integer.parseInt(cursor.getString(10)), cursor.getString(11));
			// next pointer
			cursor.moveToNext();
		}
		cursor.close();
		closeDatabase(db);
		// return
		return product;
	}
	public ArrayList<WareHouse> getAllWareHouses(){
		ArrayList<WareHouse> list = new ArrayList<WareHouse>();
		String selectQuery = "SELECT * FROM "+ TABLE_WARE_HOUSE;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		if(cursor !=null){
			cursor.moveToFirst();
			do{
				WareHouse ware_house = new WareHouse();
				ware_house.setId_ware(cursor.getString(cursor.getColumnIndex(TAG_ID_WARE)));
				ware_house.setName_ware(cursor.getString(cursor.getColumnIndex(TAG_NAME_WARE)));
				list.add(ware_house);
			}
			while(cursor.moveToNext());
		}
		cursor.close();
		closeDatabase(db);
		return list;
	}
	public ArrayList<Groups> getAllGroups(){
		ArrayList<Groups> list = new ArrayList<Groups>();
		String selectQuery = "SELECT * FROM "+ TABLE_GROUP;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		if(cursor !=null){
			cursor.moveToFirst();
			do{
				Groups groups = new Groups();
				groups.setId_group(cursor.getString(cursor.getColumnIndex(TAG_ID_GROUP)));
				groups.setName_group(cursor.getString(cursor.getColumnIndex(TAG_NAME_GROUP)));
				list.add(groups);
			}
			while(cursor.moveToNext());	
		}
		cursor.close();
		closeDatabase(db);
		return list;
	}
	public ArrayList<Goods> getAllGoods(){
		ArrayList<Goods> list = new ArrayList<Goods>();
		String selectQuery = "SELECT * FROM "+ TABLE_GOODS;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		if(cursor !=null){
			cursor.moveToFirst();
			do{
				Goods goods = new Goods();
				goods.setId_good(cursor.getString(cursor.getColumnIndex(TAG_ID_MER)));
				goods.setName_goods(cursor.getString(cursor.getColumnIndex(TAG_NAME_MER)));
				list.add(goods);
			}while(cursor.moveToNext());
		}
		cursor.close();
		closeDatabase(db); 
		return list;
	}
	public ArrayList<WareGoods> getAllWareGoods(){
		ArrayList<WareGoods> list = new ArrayList<WareGoods>();
		String slectQuery  = "SELECT * FROM " + TABLE_WARE_GOODS;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(slectQuery, null);
		if(cursor !=null){
			cursor.moveToFirst();
			do{
				WareGoods ware_goods = new WareGoods();
				ware_goods.setSold(cursor.getInt(cursor.getColumnIndex(TAG_SOLD)));
				ware_goods.setReserver(cursor.getInt(cursor.getColumnIndex(TAG_RESERVER)));
				ware_goods.setStock_tranfer(cursor.getInt(cursor.getColumnIndex(TAG_STOCK_TRAN)));
				ware_goods.setLiq(cursor.getInt(cursor.getColumnIndex(TAG_LIQUIDATED)));
				ware_goods.setIcon(cursor.getString(cursor.getColumnIndex(TAG_ICON)));
				ware_goods.setCreate_at(cursor.getString(cursor.getColumnIndex(TAG_CREATE_AT)));
				ware_goods.setUpdate_at(cursor.getString(cursor.getColumnIndex(TAG_UPDATE_AT)));
				Goods goods = getGoods(TAG_ID_MER);	
				goods = getGoods(cursor.getString(cursor.getColumnIndex(TAG_ID_MER)));
				ware_goods.setGoods(goods);
				Groups group = new Groups();
				group = getGroup(cursor.getString(cursor.getColumnIndex(TAG_ID_GROUP)));
				ware_goods.setGroups(group);
				WareHouse ware_house = new WareHouse();
				ware_house = getWareHouse(cursor.getString(cursor.getColumnIndex(TAG_ID_WARE)));
				ware_goods.setWare_house(ware_house);
				list.add(ware_goods);
			}while(cursor.moveToNext());
		}
		cursor.close();
		closeDatabase(db);
		return list;
	}
	/**
	 * this function use get all product from database 
	 */
	public List<Product> getAllProducts(){
		List<Product> list_products = new ArrayList<Product>();
		String selectQuery = "SELECT  * FROM " + DATABASE_NAME;
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		if(cursor.moveToFirst()){
			do{
				Product product = new Product();
				product.setId(Integer.parseInt(cursor.getString(0)));
				product.setId_ware(cursor.getString(1));
				product.setName_ware(cursor.getString(2));
				product.setId_group(cursor.getString(3));
				product.setName_group(cursor.getString(4));
				product.setId_mer(cursor.getString(5));
				product.setName_mer(cursor.getString(6));
				product.setSold(Integer.parseInt(cursor.getString(7)));
				product.setReserver(Integer.parseInt(cursor.getString(8)));
				product.setStock_tranfer(Integer.parseInt(cursor.getString(9)));
				product.setLiq(Integer.parseInt(cursor.getString(10)));
				list_products.add(product);							
			}while(cursor.moveToNext());
		}
		cursor.close();
		closeDatabase(db);
		return list_products;
	}
	public int deleteWarehouse(int id){
		int result;
		SQLiteDatabase db = getWritableDatabase();
		result = db.delete(TABLE_WARE_HOUSE, TAG_ID_WARE +"=?", new String[]{String.valueOf(id)});
		closeDatabase(db);
		return result;
	}
	public int deleteGroup(int id){
		int result;
		SQLiteDatabase db = getWritableDatabase();
		result = db.delete(TABLE_GROUP, TAG_ID_GROUP +"=?", new String[]{String.valueOf(id)});
		closeDatabase(db);
		return result;
	}
	public int deleteGoods(int id){
		int result;
		SQLiteDatabase db = getWritableDatabase();
		result = db.delete(TABLE_GOODS, TAG_ID_MER +"=?", new String[]{String.valueOf(id)});
		closeDatabase(db);
		return result;
	}
	/**
	 * this function use delete a product
	 * @param id: id product
	 */
	public int deleteProduct(String id, String table){
		int result;
		SQLiteDatabase db = getWritableDatabase();
		result = db.delete(table, TAG_ID_GROUP +"=?", new String[]{String.valueOf(id)});
		closeDatabase(db);
		return result;
	}
	public int deleteTable(String table){
		int result;
		SQLiteDatabase db = getReadableDatabase();
		result = db.delete(table, null, null);
		closeDatabase(db);
		return result;
	}
	public int updateWareHouse(WareHouse ware_house){
		int result =0;
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(TAG_ID_WARE, ware_house.getId_ware());
		values.put(TAG_NAME_WARE, ware_house.getName_ware());
		result = db.update(TABLE_WARE_HOUSE, values, TAG_ID_WARE +"=?",new String[]{String.valueOf(ware_house.getId_ware())});
		return result;
	}
	public int updateGroup(Groups group){
		int result = 0;
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(TAG_ID_GROUP, group.getId_group());
		values.put(TAG_NAME_GROUP, group.getName_group());
		result = db.update(TABLE_GROUP, values, TAG_ID_GROUP + "=?", new String[]{String.valueOf(group.getId_group())});
		return result;	
	}
	public int updateGoods(Goods goods){
		int result;
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(TAG_ID_MER, goods.getId_good());
		values.put(TAG_NAME_MER, goods.getName_good());
		result = db.update(TABLE_GOODS, values, TAG_ID_MER + "=?", new String[]{String.valueOf(goods.getId_good())});
		return result;
	}
	/**
	 * this function use to get count entry
	 * @return: count entry
	 */
	public int getGoodsCount(){
		String selectQuery = "SELECT * FROM " + TABLE_GOODS;
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		cursor.close();
		closeDatabase(db);
		return cursor.getCount();
	}
	public int getWareHouseCount(){
		String selectQuery = "SELECT * FROM " + TABLE_WARE_HOUSE;
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		cursor.close();
		closeDatabase(db);
		return cursor.getCount();
	}
	public int getGroupsCount(){
		String selectQuery = "SELECT * FROM " + TABLE_GROUP;
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		cursor.close();
		closeDatabase(db);
		return cursor.getCount();
	}
	
	public Cursor selectQuery(String selectQuery) {
		 SQLiteDatabase db= this.getWritableDatabase();
		 return db.rawQuery(selectQuery, null);
	}
	public void closeDatabase(SQLiteDatabase db) {
		 if(db!=null && db.isOpen()) {
		 db.close();
		 }
	}
}
