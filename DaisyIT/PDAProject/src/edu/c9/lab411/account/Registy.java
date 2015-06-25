package edu.c9.lab411.account;

import edu.c9.lab411.database.DatabaseHandle;
import edu.c9.lab411.ocr.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class Registy extends Activity {

	EditText username, password, firstname, lastname, address, phonenumber, email, confirmemail, zipcode;
	DatabaseHandle database_handle;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.resgisty);
		username = (EditText)findViewById(R.id.username);
		password = (EditText)findViewById(R.id.password);
		firstname = (EditText)findViewById(R.id.firstname);
		lastname = (EditText)findViewById(R.id.lastname);
		address = (EditText)findViewById(R.id.address_);
		phonenumber = (EditText)findViewById(R.id.phonenumber);
		email = (EditText)findViewById(R.id.email_);
		confirmemail = (EditText)findViewById(R.id.confirm_email);
		zipcode = (EditText)findViewById(R.id.zip_postal_code);
		database_handle = new DatabaseHandle(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.resgisty, menu);
		return true;
	}
	public void CreateAccount(View view){
		String str_user, str_pass, str_first_name, str_lastname, str_address,str_phone_number,str_email, str_confirm, str_zipcode;
		str_user = username.getText().toString().trim();
		str_pass = password.getText().toString().trim();
		str_first_name = firstname.getText().toString().trim();
		str_lastname = lastname.getText().toString().trim();
		str_address = address.getText().toString().trim();
		str_phone_number = phonenumber.toString().trim();
		str_email = email.getText().toString().trim();
		str_confirm = confirmemail.getText().toString().trim();
		str_zipcode = zipcode.getText().toString().trim();
		if(str_user.matches("")||str_pass.matches("")||str_first_name.matches("")||str_lastname.matches("")||str_address.matches("")||str_phone_number.matches("")||str_email.matches("")||str_confirm.matches("")||str_zipcode.matches("")){
			AlertDialog.Builder dialog_registy = new AlertDialog.Builder(Registy.this);
			   dialog_registy.setTitle("Warning");
			   dialog_registy.setMessage("Other Field Error!");
			   dialog_registy.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int arg1) {	
						dialog.cancel();
					}
				});
			   dialog_registy.create().show();
		}else{
			if(str_email.matches(str_confirm)){
				int size = str_user.length();
				if(size <8){
					AlertDialog.Builder dialog_user = new AlertDialog.Builder(Registy.this);
					   dialog_user.setTitle("Warning");
					   dialog_user.setMessage("User Name Least 8 Character");
					   dialog_user.setPositiveButton("OK", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int arg1) {	
								dialog.cancel();
							}
						});
					   dialog_user.create().show();
				}
				else{
					if(str_email.endsWith("@gmail.com")||str_email.endsWith("@edu.vn")||str_email.endsWith("@yahoo.com")||str_email.endsWith("@hotmail.com")){
						Account account = new Account();
						account.setUsername(str_user);
						account.setPassword(str_pass);
						account.setFirstname(str_first_name);
						account.setLastname(str_lastname);
						account.setAddress(str_address);
						account.setPhonenum(Integer.parseInt("01675211874"));
						account.setEmail(str_email);
						account.setZipcode(Integer.parseInt(str_zipcode));
						if(database_handle.getAccountCount(str_user)==0){
							if(database_handle.addAccount(account) !=0){
								AlertDialog.Builder dialog_email = new AlertDialog.Builder(Registy.this);
								   dialog_email.setTitle("Success");
								   dialog_email.setMessage("Create Account Success!");
								   dialog_email.setPositiveButton("OK", new DialogInterface.OnClickListener() {
										
										@Override
										public void onClick(DialogInterface dialog, int arg1) {	
											dialog.cancel();
										}
									});
								   dialog_email.create().show();
							}else{
								AlertDialog.Builder dialog_email = new AlertDialog.Builder(Registy.this);
								   dialog_email.setTitle("Error");
								   dialog_email.setMessage("Create Account Error!");
								   dialog_email.setPositiveButton("OK", new DialogInterface.OnClickListener() {
										
										@Override
										public void onClick(DialogInterface dialog, int arg1) {	
											dialog.cancel();
										}
									});
								   dialog_email.create().show();
							}
						}else{
								AlertDialog.Builder dialog_email = new AlertDialog.Builder(Registy.this);
							   dialog_email.setTitle("Warning");
							   dialog_email.setMessage("User Name Exist!");
							   dialog_email.setPositiveButton("OK", new DialogInterface.OnClickListener() {
									
									@Override
									public void onClick(DialogInterface dialog, int arg1) {	
										dialog.cancel();
									}
								});
							   dialog_email.create().show();
						}
					
					}else{
						   AlertDialog.Builder dialog_email = new AlertDialog.Builder(Registy.this);
						   dialog_email.setTitle("Warning");
						   dialog_email.setMessage("Email Not Support Or Error!");
						   dialog_email.setPositiveButton("OK", new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int arg1) {	
									dialog.cancel();
								}
							});
						   dialog_email.create().show();
					}		
				}
			}else{
				   AlertDialog.Builder dialog_registy = new AlertDialog.Builder(Registy.this);
				   dialog_registy.setTitle("Warning");
				   dialog_registy.setMessage("Email and Confirm Email Not Same!");
				   dialog_registy.setPositiveButton("OK", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int arg1) {	
							dialog.cancel();
						}
					});
				   dialog_registy.create().show();
			}
		}
		
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
