package edu.c9.lab411.controller;

import java.util.ArrayList;
import edu.c9.lab411.database.DatabaseHandle;
import edu.c9.lab411.ocr.R;
import edu.c9.lab411.partner.Partner;
import edu.c9.lab411.partner.PartnerLocGo;
import android.text.Editable;
import android.text.TextWatcher;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

public class Puckshop extends Activity implements TextWatcher {

	DatabaseHandle database_handle = new DatabaseHandle(this);
	AutoCompleteTextView idShopAuto;
	String[] arr = new String[100];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.puckshop);
		getAllPart();
		idShopAuto = (AutoCompleteTextView) findViewById(R.id.idShopAuto);
		idShopAuto.addTextChangedListener(this);
		idShopAuto.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, arr));
	}

	public ArrayList<Partner> getAllPart() {
		ArrayList<Partner> list = new ArrayList<Partner>();
		Partner partner = new Partner();
		if(database_handle.getPartnerLoGoCount() ==0){
			return list;
		}else{
		ArrayList<PartnerLocGo> list_ = database_handle.getAllPartnerLocGo();
		
		for (int i = 0; i < list_.size(); i++) {
			partner = database_handle.getPartnerId(list_.get(i).getPartnerID());
			arr[i] = String.valueOf(partner.getPartnerID());
			list.add(partner);
		}
		return list;
		}
	}

	public String nameShop(String idShop) {
		ArrayList<Partner> list = getAllPart();
		String name = null;
		for (int i = 0; i < list.size(); i++) {
			int id = list.get(i).getPartnerID();
			if (id == Integer.parseInt(idShop)) {
				name = list.get(i).getFullName();
				break;
			}
		}
		return name;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.puckshop, menu);
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

	public void ok(View view) {
		final Intent int_business = new Intent(getApplicationContext(),
				Bussiness.class);
		startActivity( int_business);
	}

	@Override
	public void afterTextChanged(Editable arg0) {
	}

	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			int arg3) {

	}

	@Override
	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
		String a = nameShop(idShopAuto.getText().toString().trim());
		Toast.makeText(this, a, Toast.LENGTH_LONG).show();
	}

}
