package edu.sfsu.cs.orange.database;

import edu.sfsu.cs.orange.ocr.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Database extends Activity {

	private Button btn_new_product, btn_update_product, btn_delete_product;
	private DatabaseHandle database_handle;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.database);
		database_handle = new DatabaseHandle(this);
		btn_new_product = (Button)findViewById(R.id.btn_database_new);
		btn_delete_product = (Button)findViewById(R.id.btn_database_delete);
		btn_update_product = (Button)findViewById(R.id.btn_database_update);
		btn_new_product.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent int_new_product = new Intent(getApplicationContext(), NewProduct.class);
				startActivity(int_new_product);
			}
		});
		btn_delete_product.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent int_new_delete = new Intent(getApplicationContext(), DeleteTable.class);
				startActivity(int_new_delete);
			}
		});
		btn_update_product.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {				
				database_handle.UpdateProducts();	
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.database, menu);
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
}
