package edu.c9.lab411.controller;

import edu.c9.lab411.database.DatabaseHandle;
import edu.c9.lab411.goodreceipt.GoodsReceipt;
import edu.c9.lab411.goodsissue.GoodsIssue;
import edu.c9.lab411.ocr.R;
import edu.c9.lab411.offerCustomer.OfferCustomer;
import edu.c9.lab411.order.Order;
import edu.c9.lab411.rate.Rate;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Bussiness extends Activity {
DatabaseHandle db = new DatabaseHandle(this);
private static final String TABLE_SKU_ORDER = "SKU_Order";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bussiness);
		db.deleteCart(TABLE_SKU_ORDER);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bussiness, menu);
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
	public void goodIssue(View view){
		final Intent int_barcode = new Intent(getApplicationContext(),GoodsIssue.class);
		startActivity(int_barcode);
	}
	public void rate(View view){
		final Intent int_barcode = new Intent(getApplicationContext(),Rate.class);
		startActivity(int_barcode);
	}
	public void goodReceipt(View view){
		final Intent int_goodsReceipt = new Intent(getApplicationContext(), GoodsReceipt.class);
		startActivity(int_goodsReceipt);
	}
	public void offerGoods(View view){
		final Intent int_goodsReceipt = new Intent(getApplicationContext(), OfferCustomer.class);
		startActivity(int_goodsReceipt);
	}
	public void goodOrder(View view){
		final Intent int_goodsReceipt = new Intent(getApplicationContext(), Order.class);
		startActivity(int_goodsReceipt);
	}
	public void inventory(View view){
		
	}
	
}
