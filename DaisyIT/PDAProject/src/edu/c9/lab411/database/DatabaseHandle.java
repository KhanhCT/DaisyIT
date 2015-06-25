package edu.c9.lab411.database;

/**
 * THIS CLASS IS PROCESS INIT AND QUETY WIHT SQLITE DATABASES
 * IT IS PROCESS TRANSMITION DATA ON SQL SERVER
 */
import java.util.ArrayList;
import java.util.HashMap;

import edu.c9.lab411.Algorithm.Encryption;
import edu.c9.lab411.Cart.Cart;
import edu.c9.lab411.Cart.JSONParser;
import edu.c9.lab411.Cart.WishList;
import edu.c9.lab411.account.Account;
import edu.c9.lab411.goods.Goods;
import edu.c9.lab411.goods.SKUItems;
import edu.c9.lab411.goods.SKUOrder;
import edu.c9.lab411.offerCustomer.Offer;
import edu.c9.lab411.partner.Partner;
import edu.c9.lab411.partner.PartnerLocGo;
import edu.c9.lab411.partner.PartnerLocation;
import edu.c9.lab411.rate.Examined;
import edu.c9.lab411.recieve.barcode.Barcode;
import edu.c9.lab411.stock.Stock;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

// use extends class SQLiteOpenHelper to create or read/write databases
public class DatabaseHandle extends SQLiteOpenHelper {

	JSONParser json_parser = new JSONParser();
	ArrayList<HashMap<String, String>> products_list;
	// private static final String TAG_URL =
	// "http://trongkhanhbkhn.meximas.com/Server/Model/getAllTable.php";
	// tag database
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "saletool";
	// tag table
	private static final String TABLE_CART = "Cart";
	private static final String TABLE_WISH_LIST = "Wishlist";
	private static final String TABLE_ACCOUNT = "Account";
	private static final String TABLE_LOGIN = "Login";
	private static final String TABLE_PARTNER_LOC = "Partner_Loc";
	private static final String TABLE_STOCK = "Stock";
	private static final String TABLE_PARTNER = "Partner";
	private static final String TABLE_GoodGrps = "GoodGrps";
	private static final String TABLE_Units = "Units";
	private static final String TABLE_GOODS = "Goods";
	private static final String TABLE_SKU_Items = "SKU_Items";
	private static final String TABLE_Barcode = "Barcode";
	private static final String TABLE_MercUnit = "MercUnit";
	private static final String TABLE_Goods_Receipt = "GoodsReceipt";
	private static final String TABLE_PARTNER_LOC_GO = "Partner_Go";
	private static final String TABLE_SKU_ORDER = "SKU_Order";
	private static final String TABLE_RATE = "rate";
	private static final String TABLE_Order = "Order";
	private static final String TABLE_OFFER = "Offer";
	// Tag
	/***************************************************************/
	private static final String TAG_ID = "id";
	private static final String TAG_BARCODE = "barcode";
	private static final String TAG_ID_WARE = "id_ware_house";
	private static final String TAG_ID_GROUP = "id_group_mer";
	private static final String TAG_ID_MER = "id_mer";
	private static final String TAG_NAME_MER = "name_mer";
	private static final String TAG_QUANTITY = "quantity";
	/***************************************************************/
	// private static final String TAG_FOREIGN_ACTION =
	// "ON UPDATE CASCADE ON DELETE CASCADE";
	private static final String TAG = "Product";
	private static final String TAG_USER = "account";
	private static final String TAG_USER_NAME = "user_name";
	private static final String TAG_PASS_WORD = "password";
	private static final String TAG_FISRT_NAME = "fisrt_name";
	private static final String TAG_LAST_NAME = "last_name";
	private static final String TAG_ADDRESS = "address";
	private static final String TAG_PHONE_NUMBER = "phone_number";

	private static final String TAG_ZIP = "zip_postal_code";
	/***************************************************************/
	private static final String TAG_PLOCA_CODE = "PLoca_Code";
	private static final String TAG_PARTNER_ID = "Partner_ID";
	private static final String TAG_PLOCA_NAME = "PLoca_Name";
	private static final String TAG_PLOCA_ADDRESS = "PLoca_Address";
	private static final String TAG_PLACE_ID = "Place_ID";
	private static final String TAG_PLOCA_ID = "PLoca_ID";
	private static final String TAG_LONGITUDE = "Longitude";
	private static final String TAG_LATITUDE = "Latitude";
	private static final String TAG_STATUS = "Status";
	private static final String TAG_PLOCA_TYPE = "PLoca_Type";

	/*****************************************************************/
	private static final String TAG_STK_ID = "STK_ID";
	private static final String TAG_STK_Code = "STK_Code";
	private static final String TAG_STK_TYPE = "STK_TYPE";
	private static final String TAG_Node_ID = "Node_ID";
	private static final String TAG_Owner_Type = "Owner_Type";
	private static final String TAG_Owner_ID = "Owner_ID";
	private static final String TAG_STK_SYMB = "STK_SYMB";
	private static final String TAG_STK_CAP = "STK_CAP";
	private static final String TAG_STK_Name = "STK_Name";
	private static final String TAG_RItem_ID = "RItem_ID";
	private static final String TAG_STK_Addr = "STK_Addr";
	private static final String TAG_ACCOUNT_ID = "ACCOUNT_ID";
	private static final String TAG_ACC_CYS = "ACC_CYS";
	private static final String TAG_Phone = "Phone";
	private static final String TAG_Fax = "Fax";
	private static final String TAG_EMAIL = "Email";
	private static final String TAG_Con_Person = "Con_Person";
	private static final String TAG_DIMENSION = "DIMENSION";
	private static final String TAG_LOCATION = "LOCATION";
	private static final String TAG_IsPhysical = "IsPhysical";
	private static final String TAG_IsBase = "IsBase";
	private static final String TAG_IsDummy = "IsDummy";
	private static final String TAG_isPOS = "isPOS";
	private static final String TAG_isPOD = "isPOD";
	private static final String TAG_Modi_Date = "Modi_Date";
	/*************************************************************/
	/****************************************************************/
	private static final String TAG_Partner_Code = "Partner_Code";
	private static final String TAG_Dept_Code = "Dept_Code";
	private static final String TAG_Partner_Type = "Partner_Type";
	private static final String TAG_PartCtg_ID = "PartCtg_ID";
	private static final String TAG_Full_Name = "Full_Name";
	private static final String TAG_Short_Name = "Short_Name";
	private static final String TAG_Bill_Address = "Bill_Address";
	private static final String TAG_Country_ID = "Country_ID";
	private static final String TAG_Business_No = "Business_No";
	private static final String TAG_Tax_No = "Tax_No";
	/*****************************************************************/
	/*****************************************************************/
	private static final String TAG_GGrp_ID = "GGrp_ID";
	private static final String TAG_GGrp_Code = "GGrp_Code";
	private static final String TAG_GGrp_Type = "GGrp_Type";
	private static final String TAG_GoodCls_Desc = "GoodCls_Desc";
	private static final String TAG_GS_Div_ID = "GS_Div_ID";
	private static final String TAG_GS_Class_ID = "GS_Class_ID";
	private static final String TAG_GS_Grp_ID = "GS_Grp_ID";
	private static final String TAG_GS_AccCls_ID = "GS_AccCls_ID";
	private static final String TAG_Parent_ID = "Parent_ID";
	private static final String TAG_Grp_Level = "Grp_Level";
	private static final String TAG_Last_Level = "Last_Level";
	private static final String TAG_Tax_ID = "Tax_ID";
	private static final String TAG_Image = "Image";
	private static final String TAG_GProp_ID = "GProp_ID";
	private static final String TAG_GAGrp_ID = "GAGrp_ID";

	/*****************************************************************/
	/*****************************************************************/
	private static final String TAG_Unit_ID = "Unit_ID";
	private static final String TAG_Unit_Symb = "Unit_Symb";
	private static final String TAG_Unit_Name = "Unit_Name";
	/*****************************************************************/
	/*****************************************************************/
	private static final String TAG_Good_ID = "Good_ID";
	private static final String TAG_Good_Code = "Good_Code";
	private static final String TAG_Family_ID = "Family_ID";
	private static final String TAG_Base_Unit = "Base_Unit";
	private static final String TAG_GS_Img_Icon = "GS_Img_Icon";
	private static final String TAG_GS_Img_01 = "GS_Img_01";
	private static final String TAG_GS_Img_02 = "GS_Img_02";
	private static final String TAG_GS_Img_03 = "GS_Img_03";
	private static final String TAG_GS_Img_04 = "GS_Img_04";
	private static final String TAG_GS_Img_05 = "GS_Img_05";
	private static final String TAG_GS_Img_06 = "GS_Img_06";
	private static final String TAG_GS_Description = "GS_Description";
	/*****************************************************************/
	/*****************************************************************/
	private static final String TAG_SKU_Code = "SKU_Code";
	private static final String TAG_Product_Code = "Product_Code";
	private static final String TAG_Product_Name = "Product_Name";
	private static final String TAG_Rank = "Rank";
	private static final String TAG_Sales_Block = "Sales_Block";
	private static final String TAG_OPL_Limit = "OPL_Limit";
	private static final String TAG_ORD_Right = "IORD_Right";
	private static final String TAG_Purc_Price = "Purc_Price";
	/*****************************************************************/
	/*****************************************************************/
	private static final String TAG_Barcode = "Barcode";
	private static final String TAG_Unit_Idx = "Unit_Idx";
	/*****************************************************************/
	/*****************************************************************/
	private static final String TAG_Pack_Unit = "Pack_Unit";
	private static final String TAG_Unit_Desc = "Unit_Desc";
	private static final String TAG_Unit_Conv = "Unit_Conv";
	private static final String TAG_Rt_Unit = "Rt_Unit";
	private static final String TAG_Wh_Unit = "Wh_Unit";
	private static final String TAG_PC_Unit = "PC_Unit";
	/*****************************************************************/
	/*****************************************************************/
	private static final String TAG_NAME = "name";
	private static final String TAG_ID_RATE = "id_rate";
	/*****************************************************************/
	// this tag use enable foriegn key
	private static final String TAG_ON_FOREIGN = "PRAGMA foreign_keys=ON;";
	// Tag command use create table in databases
	private static final String CREATE_TABLE_CART = "CREATE TABLE "
			+ TABLE_CART + "(" + TAG_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + TAG_ID_MER
			+ " VARCHAR(20)," + TAG_NAME_MER + " TEXT NOT NULL," + TAG_ID_WARE
			+ " VARCHAR(20) NOT NULL," + TAG_QUANTITY + " INTEGER NOT NULL"
			+ ")";
	private static final String CREATE_TABLE_WISHLIST = "CREATE TABLE "
			+ TABLE_WISH_LIST + "(" + TAG_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + TAG_ID_MER
			+ " VARCHAR(20)," + TAG_NAME_MER + " TEXT NOT NULL" + ")";
	private static final String CREATE_TABLE_ACCOUNT = "CREATE TABLE "
			+ TABLE_ACCOUNT + "(" + TAG_PHONE_NUMBER + " VARCHAR(20) NOT NULL,"
			+ TAG_USER_NAME + " VARCHAR(45) NOT NULL," + TAG_PASS_WORD
			+ " VARCHAR(45) NOT NULL," + TAG_FISRT_NAME
			+ " VARCHAR(45) NOT NULL," + TAG_LAST_NAME
			+ " VARCHAR(45) NOT NULL," + TAG_ADDRESS + " TEXT NOT NULL,"
			+ TAG_EMAIL + " TEXT NOT NULL," + TAG_ZIP
			+ " VARCHAR(20) NOT NULL," + " PRIMARY KEY (" + TAG_PHONE_NUMBER
			+ ")" + ")";
	private static final String CREATE_TABLE_LOGIN = "CREATE TABLE "
			+ TABLE_LOGIN + "(" + TAG_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + TAG_USER_NAME
			+ " VARCHAR(45) NOT NULL" + ")";
	private static final String CREATE_TABLE_PARTNER_LOC = "CREATE TABLE "
			+ TABLE_PARTNER_LOC + "(" + TAG_PLOCA_ID + " INTEGER NOT NULL,"
			+ TAG_PLOCA_CODE + " VARCHAR(8)," + TAG_PARTNER_ID
			+ " INTEGER NOT NULL," + TAG_PLOCA_TYPE + " CHAR(2) NOT NULL,"
			+ TAG_PLOCA_NAME + " VARCHAR(100)," + TAG_PLOCA_ADDRESS
			+ " VARCHAR(100)," + TAG_PLACE_ID + " INTEGER," + TAG_LONGITUDE
			+ " DOUBLE," + TAG_LATITUDE + " DOUBLE," + TAG_STATUS + " BIT(1),"
			+ "PRIMARY KEY (" + TAG_PLOCA_ID + "," + TAG_PLOCA_CODE + ")" + ")";
	private static final String CREATE_TABLE_STOCK = "CREATE TABLE "
			+ TABLE_STOCK + "(" + TAG_STK_ID + " INTEGER NOT NULL,"
			+ TAG_STK_Code + " CHAR(12) NOT NULL," + TAG_STK_TYPE + " CHAR(2),"
			+ TAG_Node_ID + " INTEGER NOT NULL," + TAG_Owner_Type + " CHAR(1),"
			+ TAG_Owner_ID + " INTEGER," + TAG_STK_SYMB + " CHAR(25),"
			+ TAG_STK_CAP + " CHAR(10)," + TAG_STK_Name + " VARCHAR(60),"
			+ TAG_RItem_ID + " INTEGER," + TAG_STK_Addr + " VARCHAR(60),"
			+ TAG_PLACE_ID + " INTEGER," + TAG_ACCOUNT_ID + " CHAR(12),"
			+ TAG_ACC_CYS + " CHAR(3)," + TAG_Phone + " CHAR(24)," + TAG_Fax
			+ " CHAR(24)," + TAG_EMAIL + " CHAR(40)," + TAG_Con_Person
			+ " VARCHAR(50)," + TAG_DIMENSION + " INTEGER," + TAG_LOCATION
			+ " VARCHAR(60)," + TAG_IsPhysical + " BIT(1)," + TAG_IsDummy
			+ " BIT(1)," + TAG_IsBase + " BIT(1)," + TAG_isPOS + " BIT(1),"
			+ TAG_isPOD + " BIT(1)," + TAG_Modi_Date + " DATETIME,"
			+ TAG_LONGITUDE + " DOUBLE," + TAG_LATITUDE + " DOUBLE,"
			+ TAG_STATUS + " BIT(1)," + "PRIMARY KEY (" + TAG_STK_ID + ","
			+ TAG_STK_Code + ")" + ")";
	private static final String CREATE_TABLE_PARTNER = "CREATE TABLE "
			+ TABLE_PARTNER + "(" + TAG_PARTNER_ID + " INTEGER NOT NULL,"
			+ TAG_Partner_Code + " VARCHAR(8)," + TAG_Dept_Code + " CHAR(1),"
			+ TAG_Partner_Type + "  CHAR(2) NOT NULL," + TAG_PartCtg_ID
			+ " INTEGER," + TAG_Full_Name + " VARCHAR(100)," + TAG_Short_Name
			+ " VARCHAR(50)," + TAG_Bill_Address + " VARCHAR(100),"
			+ TAG_PLACE_ID + " INTEGER," + TAG_Country_ID + " INTEGER,"
			+ TAG_Business_No + " VARCHAR(20)," + TAG_Tax_No + " VARCHAR(20),"
			+ TAG_STATUS + " BIT(1)," + " PRIMARY KEY (" + TAG_PARTNER_ID + ","
			+ TAG_Partner_Code + ")" + ")";
	private static final String CREATE_TABLE_GOODGRPS = "CREATE TABLE "
			+ TABLE_GoodGrps + "(" + TAG_GGrp_ID + " INTEGER NOT NULL,"
			+ TAG_GGrp_Code + " VARCHAR(6) NOT NULL," + TAG_GGrp_Type
			+ " CHAR(2)," + TAG_GoodCls_Desc + " VARCHAR(50)," + TAG_GS_Div_ID
			+ " INTEGER," + TAG_GS_Class_ID + " INTEGER," + TAG_GS_Grp_ID
			+ " INTEGER," + TAG_GS_AccCls_ID + " INTEGER," + TAG_Parent_ID
			+ " INTEGER," + TAG_Grp_Level + " INTEGER," + TAG_Last_Level
			+ " BIT(1)," + TAG_Tax_ID + " INTEGER," + TAG_Image
			+ " VARCHAR(256)," + TAG_GProp_ID + " INTEGER," + TAG_GAGrp_ID
			+ " INTEGER," + TAG_STATUS + " BIT(1)," + " PRIMARY KEY ("
			+ TAG_GGrp_ID + "," + TAG_GGrp_Code + ")" + ")";
	private static final String CREATE_TABLE_UNITS = "CREATE TABLE "
			+ TABLE_Units + "(" + TAG_Unit_ID + " INTEGER NOT NULL,"
			+ TAG_Unit_Symb + " VARCHAR(6)," + TAG_Unit_Name + " VARCHAR(20),"
			+ TAG_STATUS + " BIT(2)," + " PRIMARY KEY (" + TAG_Unit_ID + ","
			+ TAG_Unit_Symb + ")" + ")";
	private static final String CREATE_TABLE_GOODS = "CREATE TABLE "
			+ TABLE_GOODS + "(" + TAG_Good_ID + " INTEGER NOT NULL,"
			+ TAG_Good_Code + " VARCHAR(20)," + TAG_Short_Name
			+ " VARCHAR(20)," + TAG_Full_Name + " VARCHAR(50)," + TAG_GS_Div_ID
			+ " INTEGER," + TAG_GS_Class_ID + " INTEGER," + TAG_GS_AccCls_ID
			+ " INTEGER," + TAG_Family_ID + " INTEGER," + TAG_GProp_ID
			+ " INTEGER," + TAG_GAGrp_ID + " INTEGER," + TAG_Base_Unit
			+ " INTEGER," + TAG_GS_Img_Icon + " VARCHAR(256)," + TAG_GS_Img_01
			+ " VARCHAR(256)," + TAG_GS_Img_02 + " VARCHAR(256),"
			+ TAG_GS_Img_03 + " VARCHAR(256)," + TAG_GS_Img_04
			+ " VARCHAR(256)," + TAG_GS_Img_05 + " VARCHAR(256),"
			+ TAG_GS_Img_06 + " VARCHAR(256)," + TAG_GS_Description
			+ " VARCHAR(256)," + TAG_STATUS + " BIT(1)," + " PRIMARY KEY ("
			+ TAG_Good_ID + "," + TAG_Good_Code + ")" + ")";
	private static final String CREATE_TABLE_SKU_Items = "CREATE TABLE "
			+ TABLE_SKU_Items + "(" + TAG_SKU_Code + " CHAR(12)," + TAG_Good_ID
			+ " INTEGER NOT NULL," + TAG_Product_Code + " VARCHAR(30),"
			+ TAG_Product_Name + " VARCHAR(50)," + TAG_Rank + " INTEGER,"
			+ TAG_Sales_Block + " CAHR(2)," + TAG_OPL_Limit + " CHAR(2),"
			+ TAG_ORD_Right + " CHAR(2)," + TAG_Purc_Price + " DOUBLE,"
			+ TAG_STATUS + " BIT(1)," + " PRIMARY KEY (" + TAG_SKU_Code + ")"
			+ ")";
	private static final String CREATE_TABLE_Barcode = " CREATE TABLE "
			+ TAG_Barcode + "(" + TAG_Barcode + " CHAR(18) NOT NULL,"
			+ TAG_SKU_Code + " CHAR(12) NOT NULL," + TAG_Good_ID
			+ " INTEGER NOT NULL," + TAG_Unit_Idx + " INTEGER," + TAG_STATUS
			+ " BIT(1)," + " PRIMARY KEY(" + TAG_BARCODE + ")" + ")";
	private static final String CREATE_TABLE_MercUnit = "CREATE TABLE "
			+ TABLE_MercUnit + "(" + TAG_SKU_Code + " CHAR(12) NOT NULL,"
			+ TAG_Good_ID + " INTEGER NOT NULL," + TAG_Unit_Idx + " INTEGER,"
			+ TAG_Pack_Unit + " CHAR(6)," + TAG_Unit_Desc + " VARCHAR(20),"
			+ TAG_Unit_Conv + " NUMERIC(4)," + TAG_Rt_Unit + " BIT(1),"
			+ TAG_Wh_Unit + " BIT(1)," + TAG_PC_Unit + " BIT(1)," + TAG_STATUS
			+ " BIT(1), " + "PRIMARY KEY (" + TAG_SKU_Code + ","
			+ TAG_Pack_Unit + ")" + ")";
	private static final String CREATE_TABLE_GOODS_RECEIPT = "CREATE TABLE "
			+ TABLE_Goods_Receipt + "(" + TAG_Good_ID + " INTEGER NOT NULL,"
			+ TAG_Good_Code + " VARCHAR(20)," + TAG_Barcode
			+ " CHAR(18) NOT NULL," + TAG_QUANTITY + " INTEGER NOT NULL,"
			+ "PRIMARY KEY (" + TAG_BARCODE + ")" + ")";
	private static final String CREATE_TABLE_PARTNER_LOC_GO = "CREATE TABLE "
			+ TABLE_PARTNER_LOC_GO + "(" + TAG_PARTNER_ID
			+ " INTEGER NOT NULL," + TAG_PLOCA_ID + " INTEGER NOT NULL,"
			+ TAG_PLACE_ID + " INTEGER NOT NULL," + "PRIMARY KEY ("
			+ TAG_PLOCA_ID + ")" + ")";
	private static final String CREATE_TABLE_SKU_ORDER = "CREATE TABLE "
			+ TABLE_SKU_ORDER + "(" + TAG_SKU_Code + " CHAR(12),"
			+ TAG_Product_Name + " VARCHAR(50)," + TAG_QUANTITY
			+ " INTEGER NOT NULL," + "PRIMARY KEY (" + TAG_SKU_Code + ")" + ")";
	private static final String CREATE_TABLE_RATE = "CREATE TABLE "
			+ TABLE_RATE + "(" + TAG_ID_RATE + " VARCHAR(50) NOT NULL,"
			+ TAG_NAME + " VARCHAR(256) NOT NULL," + " PRIMARY KEY ("
			+ TAG_ID_RATE + ")" + ")";
	private static final String CREATE_TABLE_OFFER = "CREATE TABLE "
			+ TABLE_OFFER + "(" + TAG_SKU_Code + " CHAR(12) NOT NULL,"
			+ TAG_Product_Name + " VARCHAR(50)," + TAG_NAME + " VARCHAR(50), "
			+ TAG_ADDRESS + " VARCHAR(256)," + TAG_Phone + " VARCHAR(14),"
			+ TAG_QUANTITY + " INTEGER NOT NULL," + "PRIMARY KEY ("
			+ TAG_SKU_Code + "," + TAG_NAME + ")" + ")";

	// construction function Open or create a databases
	// if It exits then open databases
	public DatabaseHandle(Context context) {
		/**
		 * create database database is save in sdcard
		 * 
		 * @path:/data/data/app_name/databases
		 */
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/**
	 * 
	 */
	@Override
	public void onOpen(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		super.onOpen(db);
		if (!db.isReadOnly()) {
			// Enable foreign key constraints
			db.execSQL(TAG_ON_FOREIGN);
		}
	}

	/**
	 * this function use create table in databases
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_TABLE_CART);
		db.execSQL(CREATE_TABLE_WISHLIST);
		db.execSQL(CREATE_TABLE_ACCOUNT);
		db.execSQL(CREATE_TABLE_LOGIN);
		db.execSQL(CREATE_TABLE_PARTNER);
		db.execSQL(CREATE_TABLE_PARTNER_LOC);
		db.execSQL(CREATE_TABLE_STOCK);
		db.execSQL(CREATE_TABLE_GOODGRPS);
		db.execSQL(CREATE_TABLE_UNITS);
		db.execSQL(CREATE_TABLE_GOODS);
		db.execSQL(CREATE_TABLE_SKU_Items);
		db.execSQL(CREATE_TABLE_Barcode);
		db.execSQL(CREATE_TABLE_MercUnit);
		db.execSQL(CREATE_TABLE_GOODS_RECEIPT);
		db.execSQL(CREATE_TABLE_PARTNER_LOC_GO);
		db.execSQL(CREATE_TABLE_SKU_ORDER);
		db.execSQL(CREATE_TABLE_RATE);
		db.execSQL(CREATE_TABLE_OFFER);
	}

	/**
	 * this function use update table in databases
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_WISH_LIST);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNT);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PARTNER);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PARTNER_LOC);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_STOCK);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_GoodGrps);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_Units);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_GOODS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SKU_Items);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_Barcode);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_MercUnit);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_Goods_Receipt);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PARTNER_LOC_GO);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SKU_ORDER);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_RATE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_OFFER);
		onCreate(db);
	}

	/************* Functions in this part use inset item in databases *******************/

	/**
	 * 
	 * @param cart
	 * @return
	 */
	public int addCart(edu.c9.lab411.Cart.Cart cart) {
		int result = 0;
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(TAG_ID_MER, cart.getId_product());
		values.put(TAG_NAME_MER, cart.getName_product());
		values.put(TAG_ID_WARE, cart.getId_ware());
		values.put(TAG_QUANTITY, cart.getQuantity());
		String msg;
		if (db.insert(TABLE_CART, null, values) == -1) {
			msg = "Error Insert ";
			result = 0;
		} else {
			msg = "Success Insert";
			result = 1;
		}
		Log.i(TAG, msg);
		closeDatabase(db);
		return result;
	}

	/**
	 * 
	 * @param wishlist
	 * @return
	 */
	public int addWishList(WishList wishlist) {
		int result = 0;
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(TAG_ID_MER, wishlist.getId_product());
		values.put(TAG_NAME_MER, wishlist.getName_product());
		String msg = "";
		if (db.insert(TABLE_WISH_LIST, null, values) == -1) {
			msg = "Insert Error";
			result = 0;
		} else {
			msg = "Insert Success";
			result = 1;
		}
		Log.i(TAG, msg);
		closeDatabase(db);
		return result;
	}

	public int addAccount(Account account) {
		int result = 0;
		Encryption en = new Encryption();
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(TAG_PHONE_NUMBER, account.getPhonenum());
		values.put(TAG_USER_NAME, account.getUsername());
		values.put(TAG_PASS_WORD, en.EncryptMD5(account.getPassword()));
		values.put(TAG_LAST_NAME, account.getLastname());
		values.put(TAG_FISRT_NAME, account.getFirstname());
		values.put(TAG_ADDRESS, account.getAddress());
		values.put(TAG_EMAIL, account.getEmail());
		values.put(TAG_ZIP, account.getZipcode());
		String msg = "";
		if (db.insert(TABLE_ACCOUNT, null, values) == -1) {
			msg = "Insert Error";
			result = 0;
		} else {
			msg = "Insert Success";
			result = 1;
		}
		Log.i(TAG, msg);
		closeDatabase(db);
		return result;
	}

	public int addAccountLogin(String account) {
		int result = 0;
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(TAG_USER_NAME, account);
		String msg = "";
		if (db.insert(TABLE_LOGIN, null, values) == -1) {
			msg = "Insert Error";
			result = 0;
		} else {
			msg = "Insert Success";
			result = 1;
		}
		Log.i(TAG, msg);
		closeDatabase(db);
		return result;
	}

	public int addPartnerLocation(PartnerLocation partnerLocation) {
		int result = 0;
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(TAG_PLOCA_ID, partnerLocation.getPLoca_ID());
		values.put(TAG_PLOCA_CODE, partnerLocation.getPLoca_Code());
		values.put(TAG_PARTNER_ID, partnerLocation.getPartner_ID());
		values.put(TAG_PLOCA_TYPE, partnerLocation.getPLoca_Type());
		values.put(TAG_PLOCA_NAME, partnerLocation.getPLoca_Name());
		values.put(TAG_PLOCA_ADDRESS, partnerLocation.getPLoca_Address());
		values.put(TAG_PLACE_ID, partnerLocation.getPlace_ID());
		values.put(TAG_LONGITUDE, partnerLocation.getLongitude());
		values.put(TAG_LATITUDE, partnerLocation.getLatitude());
		values.put(TAG_STATUS, partnerLocation.isStatus());
		String msg = "";
		if (db.insert(CREATE_TABLE_PARTNER_LOC, null, values) == -1) {
			msg = "Insert Error";
			result = 0;
		} else {
			msg = "Insert Success";
			result = 1;
		}
		Log.i("notice", msg);
		return result;

	}

	public int addBarcode(Barcode ba) {
		int result = 0;
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(TAG_Barcode, ba.getBarcode());
		values.put(TAG_SKU_Code, ba.getSkuCode());
		values.put(TAG_Good_ID, String.valueOf(ba.getGoodID()));
		String msg = "";
		if (db.insert(TABLE_Barcode, null, values) == -1) {
			msg = "Error Insert";
			result = 0;
		} else {
			msg = " Insert Success";
			result = 1;
		}
		Log.i("notice", msg);
		return result;
	}

	public int addPartner(Partner partner) {
		int result = 0;
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(TAG_Parent_ID, partner.getPartnerID());
		values.put(TAG_Partner_Code, String.valueOf(partner.getPartnerCode()));
		values.put(TAG_Dept_Code, String.valueOf(partner.getDeptCode()));
		values.put(TAG_Partner_Type, String.valueOf(partner.getPartnerType()));
		values.put(TAG_PartCtg_ID, partner.getPartCtgID());
		values.put(TAG_Full_Name, String.valueOf(partner.getFullName()));
		values.put(TAG_Short_Name, String.valueOf(partner.getShortName()));
		values.put(TAG_Bill_Address, String.valueOf(partner.getBillAddress()));
		values.put(TAG_PLACE_ID, partner.getPlaceID());
		values.put(TAG_Country_ID, partner.getCountryID());
		values.put(TAG_Business_No, String.valueOf(partner.getBusinessNo()));
		values.put(TAG_Tax_No, String.valueOf(partner.getTaxNo()));
		values.put(TAG_STATUS, partner.isStatus());
		String msg = "";
		if (db.insert(TABLE_PARTNER, null, values) == -1) {
			msg = "Error Insert";
			result = 0;
		} else {
			msg = " Insert Success";
			result = 1;
		}
		Log.i("notice", msg);
		return result;

	}

	public int addGood(Goods goods) {
		int result = 0;
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(TAG_Good_ID, String.valueOf(goods.getGoodID()));
		values.put(TAG_Base_Unit, String.valueOf(goods.getBaseUnit()));
		String msg = "";
		if (db.insert(TABLE_GOODS, null, values) == -1) {
			msg = "Error Insert";
			result = 0;
		} else {
			msg = " Insert Success";
			result = 1;
		}
		Log.i("notice", msg);
		return result;
	}

	public int addStock(Stock stock) {
		int result = 0;
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(TAG_STK_ID, stock.getStkID());
		values.put(TAG_STK_Code, String.valueOf(stock.getStkCode()));
		values.put(TAG_STK_TYPE, String.valueOf(stock.getStkType()));
		values.put(TAG_Node_ID, stock.getNodeID());
		values.put(TAG_Owner_Type, String.valueOf(stock.getOwnerType()));
		values.put(TAG_Owner_ID, String.valueOf(stock.getOwnerID()));
		values.put(TAG_STK_SYMB, String.valueOf(stock.getStkSymb()));
		values.put(TAG_STK_CAP, String.valueOf(stock.getStkCap()));
		values.put(TAG_STK_Name, String.valueOf(stock.getStkName()));
		values.put(TAG_RItem_ID, stock.getrItemID());
		values.put(TAG_STK_Addr, String.valueOf(stock.getStkAddr()));
		values.put(TAG_PLACE_ID, stock.getPlaceID());
		values.put(TAG_ACCOUNT_ID, String.valueOf(stock.getAccountID()));
		values.put(TAG_ACC_CYS, String.valueOf(stock.getAccCys()));
		values.put(TAG_Phone, String.valueOf(stock.getPhone()));
		values.put(TAG_Fax, String.valueOf(stock.getFax()));
		values.put(TAG_EMAIL, String.valueOf(stock.getEmail()));
		values.put(TAG_Con_Person, String.valueOf(stock.getConPersion()));
		values.put(TAG_DIMENSION, stock.getDimension());
		values.put(TAG_LOCATION, String.valueOf(stock.getLocation()));
		values.put(TAG_IsPhysical, stock.isPhysical());
		values.put(TAG_IsDummy, stock.isDummy());
		values.put(TAG_IsBase, stock.isBase());
		values.put(TAG_isPOS, stock.isPos());
		values.put(TAG_isPOD, stock.isIspod());
		values.put(TAG_Modi_Date, stock.getLongitude());
		values.put(TAG_LATITUDE, stock.getLatitude());
		values.put(TAG_STATUS, stock.isStatus());
		String msg = "";
		if (db.insert(TABLE_STOCK, null, values) == -1) {
			msg = "Error Insert";
			result = 0;
		} else {
			msg = " Insert Success";
			result = 1;
		}
		Log.i("notice", msg);
		return result;
	}

	public int addPartnerLoGo(PartnerLocGo partner) {
		int result = 0;
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(TAG_PARTNER_ID, partner.getPartnerID());
		values.put(TAG_PLOCA_ID, partner.getPloco_id());
		values.put(TAG_PLACE_ID, partner.getPlace_id());
		if (db.insert(TABLE_PARTNER_LOC_GO, null, values) == -1) {
			result = 0;
		} else {
			result = 1;
		}
		return result;
	}

	public int addRate(Examined exami) {
		int result = 0;
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(TAG_ID_RATE, exami.getId());
		values.put(TAG_NAME, exami.getName());
		if (db.insert(TABLE_RATE, null, values) == -1) {
			result = 0;
		} else {
			result = 1;
		}
		return result;
	}

	public int addSKUOrder(SKUOrder sku) {
		int result = 0;
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(TAG_SKU_Code, sku.getSkuCode());
		values.put(TAG_Product_Name, sku.getProductName());
		values.put(TAG_QUANTITY, String.valueOf(sku.getQuantity()));
		if (db.insert(TABLE_SKU_ORDER, null, values) == -1) {
			result = 0;
		} else {
			result = 1;
		}
		return result;
	}

	public int addOffer(edu.c9.lab411.offerCustomer.Offer offer) {
		int result = 0;
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(TAG_SKU_Code, offer.getSkuCode());
		values.put(TAG_Product_Name, offer.getProductName());
		values.put(TAG_NAME, offer.getName());
		values.put(TAG_ADDRESS, offer.getAddress());
		values.put(TAG_Phone, String.valueOf(offer.getPhone()));
		values.put(TAG_QUANTITY, String.valueOf(offer.getQuantity()));
		if (db.insert(TABLE_OFFER, null, values) == -1) {
			result = 0;
		} else {
			result = 1;
		}
		return result;
	}

	public int addSKUItems(SKUItems sku) {
		int result = 0;
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(TAG_SKU_Code, sku.getSkuCode());
		values.put(TAG_Good_ID, String.valueOf(sku.getGoodID()));
		values.put(TAG_Product_Name, sku.getProductName());
		if (db.insert(TABLE_SKU_Items, null, values) == -1) {
			result = 0;
		} else {
			result = 1;
		}
		return result;
	}

	/***************************** End **********************************************/
	/************* Functions in this part use return item in databases *************/

	public edu.c9.lab411.Cart.Cart getCart(String id_product) {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.query(TABLE_CART, null, TAG_ID_MER + "=?",
				new String[] { String.valueOf(id_product) }, null, null, null,
				null);
		if (cursor != null) {
			cursor.moveToFirst();
		}
		edu.c9.lab411.Cart.Cart cart = new edu.c9.lab411.Cart.Cart();
		cart.setId_product(cursor.getString(cursor.getColumnIndex(TAG_ID_MER)));
		cart.setName_product(cursor.getString(cursor
				.getColumnIndex(TAG_NAME_MER)));
		cart.setId_ware(cursor.getString(cursor.getColumnIndex(TAG_ID_WARE)));
		cart.setQuantity(cursor.getInt(cursor.getColumnIndex(TAG_QUANTITY)));
		closeDatabase(db);
		return cart;
	}

	public WishList getWishList(String id_product) {
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.query(TABLE_WISH_LIST, null, TAG_ID_MER + "=?",
				new String[] { String.valueOf(id_product) }, null, null, null,
				null);
		if (cursor != null) {
			cursor.moveToFirst();
		}
		WishList wishlist = new WishList();
		wishlist.setId_product(cursor.getString(cursor
				.getColumnIndex(TAG_ID_MER)));
		wishlist.setName_product(cursor.getString(cursor
				.getColumnIndex(TAG_NAME_MER)));
		cursor.close();
		closeDatabase(db);
		return wishlist;
	}

	public Account getAccount(int phone_number) {
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.query(TABLE_ACCOUNT, null, TAG_PHONE_NUMBER + "=?",
				new String[] { String.valueOf(phone_number) }, null, null,
				null, null);
		if (cursor != null) {
			cursor.moveToFirst();
		}
		Account account = new Account();
		account.setUsername(cursor.getString(cursor.getColumnIndex(TAG_USER)));
		account.setPassword(cursor.getString(cursor
				.getColumnIndex(TAG_PASS_WORD)));
		account.setFirstname(cursor.getString(cursor
				.getColumnIndex(TAG_FISRT_NAME)));
		account.setLastname(cursor.getString(cursor
				.getColumnIndex(TAG_LAST_NAME)));
		account.setAddress(cursor.getString(cursor.getColumnIndex(TAG_ADDRESS)));
		account.setPhonenum(cursor.getInt(cursor
				.getColumnIndex(TAG_PHONE_NUMBER)));
		account.setEmail(cursor.getString(cursor.getColumnIndex(TAG_EMAIL)));
		account.setZipcode(cursor.getInt(cursor.getColumnIndex(TAG_ZIP)));
		cursor.close();
		closeDatabase(db);
		return account;

	}

	public Partner getPartnerId(int partner_id) {
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.query(TABLE_PARTNER, null, TAG_PARTNER_ID + "=?",
				new String[] { String.valueOf(partner_id) }, null, null, null,
				null);
		if (cursor != null) {
			cursor.moveToFirst();
		}
		Partner partner = new Partner();
		partner.setPartnerID(cursor.getInt(cursor
				.getColumnIndex(TAG_PARTNER_ID)));
		partner.setPartCtgID(cursor.getInt(cursor
				.getColumnIndex(TAG_PartCtg_ID)));
		partner.setPlaceID(cursor.getInt(cursor.getColumnIndex(TAG_PLACE_ID)));
		partner.setCountryID(cursor.getInt(cursor
				.getColumnIndex(TAG_Country_ID)));
		partner.setFullName(cursor.getString(cursor
				.getColumnIndex(TAG_Full_Name)));
		partner.setShortName(cursor.getString(cursor
				.getColumnIndex(TAG_Short_Name)));
		partner.setStatus(Boolean.parseBoolean(cursor.getString(cursor
				.getColumnIndex(TAG_Short_Name))));
		cursor.close();
		closeDatabase(db);
		return partner;
	}

	public Barcode getBarcodeId(String barcode) {
		Barcode bcode = new Barcode();
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.query(TABLE_Barcode, null, TAG_Barcode + "=?",
				new String[] { String.valueOf(barcode) }, null, null, null,
				null);
		if (cursor != null) {
			cursor.moveToFirst();
		}
		bcode.setBarcode(barcode);
		bcode.setGoodID(Integer.parseInt(cursor.getString(cursor
				.getColumnIndex(TAG_Good_ID))));
		bcode.setSkuCode(cursor.getString(cursor.getColumnIndex(TAG_SKU_Code)));
		bcode.setUnitIdx(Integer.parseInt(cursor.getString(cursor
				.getColumnIndex(TAG_Unit_Idx))));
		if (cursor.getInt(cursor.getColumnIndex(TAG_STATUS)) == 1) {
			bcode.setStatus(true);
		} else {
			bcode.setStatus(false);
		}
		cursor.close();
		closeDatabase(db);
		return bcode;
	}

	public ArrayList<PartnerLocation> getAllPartnerLocation() {
		ArrayList<PartnerLocation> list = new ArrayList<PartnerLocation>();
		String selectQuery = "SELECT * FROM " + TABLE_PARTNER_LOC;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor != null) {
			cursor.moveToFirst();
			do {
				PartnerLocation p = new PartnerLocation();
				p.setPLoca_ID(cursor.getInt(cursor.getColumnIndex(TAG_PLOCA_ID)));
				p.setPLoca_Code(cursor.getString(cursor
						.getColumnIndex(TAG_PLOCA_CODE)));
				p.setPartner_ID(cursor.getInt(cursor
						.getColumnIndex(TAG_PARTNER_ID)));
				p.setPLoca_Type(cursor.getString(cursor
						.getColumnIndex(TAG_PLOCA_TYPE)));
				p.setPLoca_Name(cursor.getString(cursor
						.getColumnIndex(TAG_PLOCA_NAME)));
				p.setPLoca_Address(cursor.getString(cursor
						.getColumnIndex(TAG_PLOCA_ADDRESS)));
				p.setPlace_ID(cursor.getInt(cursor.getColumnIndex(TAG_PLACE_ID)));
				p.setLongitude(cursor.getDouble(cursor
						.getColumnIndex(TAG_LONGITUDE)));
				p.setLatitude(cursor.getDouble(cursor
						.getColumnIndex(TAG_LATITUDE)));
				p.setStatus(Boolean.parseBoolean(cursor.getString(cursor
						.getColumnIndex(TAG_STATUS))));
				list.add(p);

			} while (cursor.moveToNext());

		}
		cursor.close();
		closeDatabase(db);
		return list;
	}

	/**
	 * this function get product from database with command sql
	 * 
	 * @param id
	 *            : name id of product want find
	 * @return Product contain all colum
	 */

	public ArrayList<edu.c9.lab411.Cart.Cart> getAllCart() {
		ArrayList<edu.c9.lab411.Cart.Cart> list = new ArrayList<edu.c9.lab411.Cart.Cart>();
		String selectQuery = "SELECT * FROM " + TABLE_CART;
		SQLiteDatabase db = getWritableDatabase();
		edu.c9.lab411.Cart.Cart cart = new edu.c9.lab411.Cart.Cart();
		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor != null) {
			cursor.moveToFirst();
			do {
				cart.setId_product(cursor.getString(cursor
						.getColumnIndex(TAG_ID_MER)));
				cart.setName_product(cursor.getString(cursor
						.getColumnIndex(TAG_NAME_MER)));
				cart.setId_ware(cursor.getString(cursor
						.getColumnIndex(TAG_ID_WARE)));
				cart.setQuantity(cursor.getInt(cursor
						.getColumnIndex(TAG_QUANTITY)));
				list.add(cart);
			} while (cursor.moveToNext());
		}
		cursor.close();
		closeDatabase(db);
		return list;
	}

	public ArrayList<Examined> getAllExamined() {
		SQLiteDatabase db = getWritableDatabase();
		ArrayList<edu.c9.lab411.rate.Examined> list = new ArrayList<edu.c9.lab411.rate.Examined>();
		String selectQuery = "SELECT * FROM " + TABLE_RATE;

		Cursor cursor = db.rawQuery(selectQuery, null);
		edu.c9.lab411.rate.Examined examined = new edu.c9.lab411.rate.Examined();
		if (cursor != null) {
			cursor.moveToFirst();
			do {
				examined.setId(cursor.getString(cursor
						.getColumnIndex(TAG_ID_RATE)));
				examined.setName(cursor.getString(cursor
						.getColumnIndex(TAG_NAME)));
				list.add(examined);
			} while (cursor.moveToNext());
		}

		cursor.close();
		closeDatabase(db);
		return list;
	}

	public Examined getAllExamined(String id) {
		SQLiteDatabase db = getWritableDatabase();
		edu.c9.lab411.rate.Examined list = new edu.c9.lab411.rate.Examined();
		Cursor cursor = db.query(TABLE_RATE, null, TAG_ID_RATE + "=?",
				new String[] { id }, null, null, null, null);
		edu.c9.lab411.rate.Examined examined = new edu.c9.lab411.rate.Examined();
		if (cursor != null) {
			cursor.moveToFirst();
		}
		examined.setId(cursor.getString(cursor.getColumnIndex(TAG_ID_RATE)));
		examined.setName(cursor.getString(cursor.getColumnIndex(TAG_NAME)));
		cursor.close();
		closeDatabase(db);
		return list;
	}

	public ArrayList<WishList> getAllWishList() {
		ArrayList<WishList> list = new ArrayList<WishList>();
		String slectQuery = "SELECT * FROM " + TABLE_WISH_LIST;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(slectQuery, null);
		if (cursor != null) {
			cursor.moveToFirst();
			do {
				WishList wishlist = new WishList();
				wishlist.setId_product(cursor.getString(cursor
						.getColumnIndex(TAG_ID_MER)));
				wishlist.setName_product(cursor.getString(cursor
						.getColumnIndex(TAG_NAME_MER)));
				list.add(wishlist);
			} while (cursor.moveToNext());
		}
		cursor.close();
		closeDatabase(db);
		return list;
	}

	public ArrayList<PartnerLocGo> getAllPartnerLocGo() {
		ArrayList<PartnerLocGo> list = new ArrayList<PartnerLocGo>();
		String slectQuery = "SELECT * FROM " + TABLE_PARTNER_LOC_GO;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(slectQuery, null);
		if (cursor != null) {
			cursor.moveToFirst();
			do {
				PartnerLocGo partner = new PartnerLocGo();
				partner.setPartnerID(cursor.getInt(cursor
						.getColumnIndex(TAG_PARTNER_ID)));
				partner.setPloco_id(cursor.getInt(cursor
						.getColumnIndex(TAG_PLOCA_ID)));
				partner.setPlace_id(cursor.getInt(cursor
						.getColumnIndex(TAG_PLACE_ID)));
				list.add(partner);
			} while (cursor.moveToNext());
		}
		cursor.close();
		closeDatabase(db);
		return list;
	}

	public ArrayList<SKUOrder> getAllSkuOder() {
		ArrayList<SKUOrder> list = new ArrayList<SKUOrder>();
		String slectQuery = "SELECT * FROM " + TABLE_SKU_ORDER;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(slectQuery, null);
		if (cursor != null) {
			cursor.moveToFirst();
			do {
				SKUOrder sku = new SKUOrder();
				sku.setSkuCode(cursor.getString(cursor
						.getColumnIndex(TAG_SKU_Code)));
				sku.setProductName(cursor.getString(cursor
						.getColumnIndex(TAG_Product_Name)));
				sku.setQuantity(cursor.getInt(cursor
						.getColumnIndex(TAG_QUANTITY)));
				list.add(sku);
			} while (cursor.moveToNext());
		}
		cursor.close();
		closeDatabase(db);
		return list;
	}

	public String[] getAllSKUCode() {
		int size = this.getSKUCodeCount();
		String[] arr = new String[size];
		int i = 0;
		String slectQuery = "SELECT * FROM " + TABLE_SKU_Items;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(slectQuery, null);
		if (cursor != null) {
			cursor.moveToFirst();
			do {
				arr[i] = cursor.getString(cursor.getColumnIndex(TAG_SKU_Code));
				i++;
			} while (cursor.moveToNext());
		}
		cursor.close();
		closeDatabase(db);
		return arr;
	}

	public SKUItems getAllSKUCode(String sku_code) {
		SQLiteDatabase db = this.getWritableDatabase();
		SKUItems sku = new SKUItems();

		Cursor cursor = db.query(TABLE_SKU_Items, null, TAG_SKU_Code + "=?",
				new String[] { sku_code }, null, null, null, null);
		if (cursor != null) {
			cursor.moveToFirst();
		}

		sku.setSkuCode(cursor.getString(cursor.getColumnIndex(TAG_SKU_Code)));
		sku.setProductCode(cursor.getString(cursor
				.getColumnIndex(TAG_Product_Code)));
		sku.setProductName(cursor.getString(cursor
				.getColumnIndex(TAG_Product_Name)));
		sku.setSaleBlock(cursor.getString(cursor
				.getColumnIndex(TAG_Sales_Block)));
		sku.setOplLimit(cursor.getString(cursor.getColumnIndex(TAG_OPL_Limit)));
		sku.setOdrRight(cursor.getString(cursor.getColumnIndex(TAG_ORD_Right)));
		sku.setGoodID(cursor.getInt(cursor.getColumnIndex(TAG_Good_ID)));
		sku.setRank(cursor.getInt(cursor.getColumnIndex(TAG_Rank)));
		sku.setPurcPrice(cursor.getDouble(cursor.getColumnIndex(TAG_Purc_Price)));
		sku.setStatus(Boolean.parseBoolean(cursor.getString(cursor
				.getColumnIndex(TAG_STATUS))));

		closeDatabase(db);
		cursor.close();
		return sku;
	}

	public Goods getGoodsId(String goods_id) {
		SQLiteDatabase db = this.getWritableDatabase();
		Goods goods = new Goods();
		Cursor cursor = db.query(TABLE_GOODS, null, TAG_Good_ID + "=?",
				new String[] { goods_id }, null, null, null, null);
		if (cursor != null) {
			cursor.moveToFirst();
		}
		goods.setGoodID(cursor.getInt(cursor.getColumnIndex(TAG_Good_ID)));
		goods.setBaseUnit(cursor.getInt(cursor.getColumnIndex(TAG_Base_Unit)));
		goods.setStatus(Boolean.parseBoolean(cursor.getString(cursor
				.getColumnIndex(TAG_STATUS))));
		goods.setGsDivID(0);
		goods.setGsClassID(0);
		goods.setGsGrpID(0);
		goods.setGsAccClsID(0);
		goods.setFamailyID(0);
		goods.setgPropID(0);
		goods.setgAGrpID(0);
		goods.setFullName(null);
		goods.setShortName(null);
		goods.setGsImgIcon(null);
		goods.setGsImg01(null);
		goods.setGsImg02(null);
		goods.setGsImg03(null);
		goods.setGsImg04(null);
		goods.setGsImg05(null);
		goods.setGsImg06(null);
		goods.setStatus(true);
		closeDatabase(db);
		cursor.close();
		return goods;
	}

	public SKUOrder getSkuOrderId(String skuCode) {
		SQLiteDatabase db = this.getWritableDatabase();
		SKUOrder sku = new SKUOrder();
		Cursor cursor = db.query(TABLE_SKU_ORDER, null, TAG_SKU_Code + "=?",
				new String[] { skuCode }, null, null, null, null);
		if (cursor != null) {
			cursor.moveToFirst();
		}
		sku.setSkuCode(cursor.getString(cursor.getColumnIndex(TAG_SKU_Code)));
		sku.setProductName(cursor.getString(cursor
				.getColumnIndex(TAG_Product_Name)));
		sku.setQuantity(cursor.getInt(cursor.getColumnIndex(TAG_QUANTITY)));
		closeDatabase(db);
		cursor.close();
		return sku;
	}

	public ArrayList<Examined> getAllRate() {
		ArrayList<Examined> list = new ArrayList<Examined>();
		String slectQuery = "SELECT * FROM " + TABLE_RATE;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(slectQuery, null);
		if (cursor != null) {
			cursor.moveToFirst();
			do {
				Examined sku = new Examined();
				sku.setId(cursor.getString(cursor.getColumnIndex(TAG_ID_RATE)));
				sku.setName(cursor.getString(cursor.getColumnIndex(TAG_NAME)));
				list.add(sku);
			} while (cursor.moveToNext());
		}
		cursor.close();
		closeDatabase(db);
		return list;
	}

	public ArrayList<Offer> getAllOffer() {
		ArrayList<Offer> list = new ArrayList<Offer>();
		String slectQuery = "SELECT * FROM " + TABLE_OFFER;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(slectQuery, null);
		Offer offer = new Offer();
		if (cursor != null) {
			cursor.moveToFirst();
			do {
				offer.setSkuCode(cursor.getString(cursor
						.getColumnIndex(TAG_SKU_Code)));
				offer.setProductName(cursor.getString(cursor
						.getColumnIndex(TAG_Product_Name)));
				offer.setName(cursor.getString(cursor.getColumnIndex(TAG_NAME)));
				offer.setAddress(cursor.getString(cursor
						.getColumnIndex(TAG_ADDRESS)));
				offer.setPhone(cursor.getInt(cursor.getColumnIndex(TAG_Phone)));
				offer.setQuantity(cursor.getInt(cursor
						.getColumnIndex(TAG_QUANTITY)));
				list.add(offer);
			} while (cursor.moveToNext());
		}
		closeDatabase(db);
		cursor.close();
		return list;
	}

	public Offer getOffer(String sku_code, String name) {
		SQLiteDatabase db = this.getWritableDatabase();
		Offer offer = new Offer();
		Cursor cursor = db.query(TABLE_OFFER, null, TAG_SKU_Code + "=? AND "
				+ TAG_NAME + "=?", new String[] { sku_code, name }, null, null,
				null, null);
		if (cursor != null) {
			cursor.moveToFirst();
		}
		offer.setSkuCode(cursor.getString(cursor.getColumnIndex(TAG_SKU_Code)));
		offer.setProductName(cursor.getString(cursor
				.getColumnIndex(TAG_Product_Name)));
		offer.setName(cursor.getString(cursor.getColumnIndex(TAG_NAME)));
		offer.setAddress(cursor.getString(cursor.getColumnIndex(TAG_ADDRESS)));
		offer.setPhone(cursor.getInt(cursor.getColumnIndex(TAG_Phone)));
		offer.setQuantity(cursor.getInt(cursor.getColumnIndex(TAG_QUANTITY)));
		closeDatabase(db);
		cursor.close();
		return offer;
	}

	/********************************************* End *************************************/
	public int deleteGoods(int id) {
		int result;
		SQLiteDatabase db = getWritableDatabase();
		result = db.delete(TABLE_GOODS, TAG_ID_MER + "=?",
				new String[] { String.valueOf(id) });
		closeDatabase(db);
		return result;
	}

	/**
	 * this function use delete a product
	 * 
	 * @param id
	 *            : id product
	 */
	public int deleteProduct(String id, String table) {
		int result;
		SQLiteDatabase db = getWritableDatabase();
		result = db.delete(table, TAG_ID_GROUP + "=?",
				new String[] { String.valueOf(id) });
		closeDatabase(db);
		return result;
	}

	public int deleteCart(String id_product) {
		int result;
		SQLiteDatabase db = getWritableDatabase();
		result = db.delete(TABLE_CART, TAG_ID_MER + "=?",
				new String[] { String.valueOf(id_product) });
		closeDatabase(db);
		return result;
	}

	public int deleteTable(String table) {
		int result;
		SQLiteDatabase db = getReadableDatabase();
		result = db.delete(table, null, null);
		closeDatabase(db);
		return result;
	}

	public int deleteSkuOrder(String skuCode) {
		int result;
		SQLiteDatabase db = getWritableDatabase();
		result = db.delete(TABLE_SKU_ORDER, TAG_SKU_Code + "=?",
				new String[] { String.valueOf(skuCode) });
		closeDatabase(db);
		return result;
	}

	public int deleteOffer(String skuCode, String name) {
		int result;
		SQLiteDatabase db = getWritableDatabase();
		result = db.delete(TABLE_OFFER, TAG_SKU_Code + "=? AND " + TAG_NAME
				+ "=?",
				new String[] { String.valueOf(skuCode), String.valueOf(name) });
		closeDatabase(db);
		return result;
	}

	public int deleteOrder(String skuCode) {
		int result;
		SQLiteDatabase db = getWritableDatabase();
		result = db.delete(TABLE_Order, TAG_SKU_Code + "=?",
				new String[] { String.valueOf(skuCode) });
		closeDatabase(db);
		return result;
	}

	/******************************* End **************************************************/
	/*********** Functions in this part use update item in table databases ***************/

	public int updateCart(Cart cart) {
		int result = 0;
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(TAG_ID_MER, cart.getId_product());
		values.put(TAG_NAME_MER, cart.getName_product());
		values.put(TAG_ID_WARE, cart.getId_ware());
		values.put(TAG_QUANTITY, String.valueOf(cart.getQuantity()));
		result = db.update(TABLE_CART, values, TAG_ID_MER + "=?",
				new String[] { String.valueOf(cart.getId_product()) });
		return result;
	}

	public int updateGoods(Goods goods) {
		int result = 0;
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(TAG_Good_ID, String.valueOf(goods.getGoodID()));
		values.put(TAG_Base_Unit, String.valueOf(goods.getBaseUnit()));
		result = db.update(TABLE_GOODS, values, TAG_Good_ID + "=?",
				new String[] { String.valueOf(goods.getGoodID()) });
		closeDatabase(db);
		return result;
	}

	public int updateSkuOrder(SKUOrder sku) {
		int result = 0;
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(TAG_SKU_Code, sku.getSkuCode());
		values.put(TAG_Product_Name, sku.getProductName());
		values.put(TAG_QUANTITY, String.valueOf(sku.getQuantity()));
		result = db.update(TABLE_SKU_ORDER, values, TAG_SKU_Code + "=?",
				new String[] { String.valueOf(sku.getSkuCode()) });
		closeDatabase(db);
		return result;
	}

	public int updateOffer(Offer offer) {
		int result = 0;
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(TAG_SKU_Code, offer.getSkuCode());
		values.put(TAG_Product_Name, offer.getProductName());
		values.put(TAG_NAME, offer.getName());
		values.put(TAG_ADDRESS, offer.getAddress());
		values.put(TAG_PHONE_NUMBER, String.valueOf(offer.getPhone()));
		values.put(TAG_QUANTITY, String.valueOf(offer.getQuantity()));
		result = db.update(
				TABLE_OFFER,
				values,
				TAG_SKU_Code + "=? AND " + TAG_NAME + "=?",
				new String[] { String.valueOf(offer.getSkuCode()),
						String.valueOf(offer.getName()) });
		closeDatabase(db);
		return result;
	}

	/********************************************* End ************************************/
	/***** Functions in this part use return number item in table ************************/
	/**
	 * this function use to get count entry
	 * 
	 * @return: count entry
	 */
	public int getGoodsCount() {
		String selectQuery = "SELECT * FROM " + TABLE_GOODS;
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		cursor.close();
		closeDatabase(db);
		return cursor.getCount();
	}

	public int getCartCount() {
		String selectQuery = "SELECT * FROM " + TABLE_CART;
		int result = 0;
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// Cursor cursor = db.query(TABLE_CART, null, null, null, null, null,
		// null, null);
		if (cursor != null) {
			cursor.moveToFirst();
			result = cursor.getCount();
		}
		cursor.close();
		closeDatabase(db);
		return result;
	}

	public int getWishListCount() {
		String selectQuery = "SELECT * FROM " + TABLE_WISH_LIST;
		int result = 0;
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// Cursor cursor = db.query(TABLE_CART, null, null, null, null, null,
		// null, null);
		if (cursor != null) {
			cursor.moveToFirst();
			result = cursor.getCount();
		}
		cursor.close();
		closeDatabase(db);
		return result;
	}

	public int getLoginCount() {
		String selectQuery = "SELECT * FROM " + TABLE_LOGIN;
		int result = 0;
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// Cursor cursor = db.query(TABLE_CART, null, null, null, null, null,
		// null, null);
		if (cursor != null) {
			cursor.moveToFirst();
			result = cursor.getCount();
		}
		cursor.close();
		closeDatabase(db);
		return result;
	}

	public int getRateCount() {
		String selectQuery = "SELECT * FROM " + TABLE_RATE;
		int result = 0;
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor != null) {
			cursor.moveToFirst();
			result = cursor.getCount();
		}
		cursor.close();
		closeDatabase(db);
		return result;
	}

	public int getSKUCodeCount() {
		String selectQuery = "SELECT * FROM " + TABLE_SKU_Items;
		int result = 0;
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor != null) {
			cursor.moveToFirst();
			result = cursor.getCount();
		}
		cursor.close();
		closeDatabase(db);
		return result;
	}

	public int getCartCount(String id) {
		int result = 0;
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.query(TABLE_CART, null, TAG_ID_MER + "=?",
				new String[] { id }, null, null, null, null);
		if (cursor != null) {
			cursor.moveToFirst();
			result = cursor.getCount();
		}
		cursor.close();
		closeDatabase(db);
		return result;
	}

	public int getLoginAccountCount(String username) {
		// String selectQuery = "SELECT * FROM " + TABLE_CART + " ";
		int result = 0;
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.query(TABLE_LOGIN, null, TAG_USER_NAME + "=?",
				new String[] { String.valueOf(username) }, null, null, null,
				null);
		// Cursor cursor = db.query(TABLE_CART, null, null, null, null, null,
		// null, null);
		if (cursor != null) {
			cursor.moveToFirst();
			result = cursor.getCount();
		}
		cursor.close();
		closeDatabase(db);
		return result;
	}

	public int getAccountCount(String username, String pass) {
		// String selectQuery = "SELECT * FROM " + TABLE_CART + " ";
		int result = 0;
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.query(TABLE_ACCOUNT, null, TAG_USER_NAME + "=? AND "
				+ TAG_PASS_WORD + "=?", new String[] {
				String.valueOf(username), String.valueOf(pass) }, null, null,
				null, null);
		// Cursor cursor = db.query(TABLE_CART, null, null, null, null, null,
		// null, null);
		if (cursor != null) {
			cursor.moveToFirst();
			result = cursor.getCount();
		}
		cursor.close();
		closeDatabase(db);
		return result;
	}

	public int getAccountCount(String username) {
		// String selectQuery = "SELECT * FROM " + TABLE_CART + " ";
		int result = 0;
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.query(TABLE_ACCOUNT, null, TAG_USER_NAME + "=?",
				new String[] { String.valueOf(username) }, null, null, null,
				null);
		// Cursor cursor = db.query(TABLE_CART, null, null, null, null, null,
		// null, null);
		if (cursor != null) {
			cursor.moveToFirst();
			result = cursor.getCount();
		}
		cursor.close();
		closeDatabase(db);
		return result;
	}

	public int getGoodsCountID(String id) {
		// String selectQuery = "SELECT * FROM " + TABLE_CART + " ";
		int result = 0;
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.query(TABLE_GOODS, null, TAG_Good_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null) {
			cursor.moveToFirst();
			result = cursor.getCount();
		}
		cursor.close();
		closeDatabase(db);
		return result;
	}

	public int getSkuOrderCount() {
		String selectQuery = "SELECT * FROM " + TABLE_SKU_ORDER;
		int result = 0;
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// Cursor cursor = db.query(TABLE_CART, null, null, null, null, null,
		// null, null);
		if (cursor != null) {
			cursor.moveToFirst();
			result = cursor.getCount();
		}
		cursor.close();
		closeDatabase(db);
		return result;
	}

	public int getSkuOrderCount(String skuCode) {
		int result = 0;
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.query(TABLE_SKU_ORDER, null, TAG_SKU_Code + "=?",
				new String[] { String.valueOf(skuCode) }, null, null, null,
				null);
		if (cursor != null) {
			cursor.moveToFirst();
			result = cursor.getCount();
		}
		cursor.close();
		closeDatabase(db);
		return result;
	}

	public int getBarcodeCountID(String barcode) {
		int result = 0;
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.query(TABLE_Barcode, null, TAG_Barcode + "=?",
				new String[] { String.valueOf(barcode) }, null, null, null,
				null);
		if (cursor != null) {
			cursor.moveToFirst();
			result = cursor.getCount();
		}
		cursor.close();
		closeDatabase(db);
		return result;
	}

	public int getSkuItemsCount(String skuCode) {
		int result = 0;
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.query(TABLE_SKU_Items, null, TAG_SKU_Code + "=?",
				new String[] { String.valueOf(skuCode) }, null, null, null,
				null);
		if (cursor != null) {
			cursor.moveToFirst();
			result = cursor.getCount();
		}
		cursor.close();
		closeDatabase(db);
		return result;
	}

	public int getOfferCount(String name, String skuCode) {
		int result = 0;
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.query(TABLE_OFFER, null, TAG_NAME + "=? AND "
				+ TAG_SKU_Code + "=?", new String[] { String.valueOf(name),
				String.valueOf(skuCode) }, null, null, null, null);
		if (cursor != null) {
			cursor.moveToFirst();
			result = cursor.getCount();
		}
		cursor.close();
		closeDatabase(db);
		return result;
	}

	public int getOfferCount() {
		String selectQuery = "SELECT * FROM " + TABLE_OFFER;
		int result = 0;
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// Cursor cursor = db.query(TABLE_CART, null, null, null, null, null,
		// null, null);
		if (cursor != null) {
			cursor.moveToFirst();
			result = cursor.getCount();
		}
		cursor.close();
		closeDatabase(db);
		return result;
	}

	public int getPartnerLoGoCount() {
		String selectQuery = "SELECT * FROM " + TABLE_PARTNER_LOC_GO;
		int result = 0;
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor != null) {
			cursor.moveToFirst();
			result = cursor.getCount();
		}
		cursor.close();
		closeDatabase(db);
		return result;
	}

	/************************************ End ************************************************/
	/**
	 * this function use create a query command
	 * 
	 * @param selectQuery
	 * @return
	 */
	public Cursor selectQuery(String selectQuery) {
		SQLiteDatabase db = this.getWritableDatabase();
		return db.rawQuery(selectQuery, null);
	}

	/***
	 * this function use close databases
	 * 
	 * @param db
	 */
	public void closeDatabase(SQLiteDatabase db) {
		if (db != null && db.isOpen()) {
			db.close();
		}
	}

	/**
	 * this function use open a databases
	 * 
	 * @param path
	 */
	public void OpenDatabase(String path) {

		SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
	}

}
