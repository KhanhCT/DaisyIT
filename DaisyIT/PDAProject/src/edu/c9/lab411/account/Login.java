package edu.c9.lab411.account;
import edu.c9.lab411.database.DatabaseHandle;
import edu.c9.lab411.ocr.Interface;
import edu.c9.lab411.ocr.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
public class Login extends Activity {
public EditText username_login, pass_login;
public DatabaseHandle database_handle;
private static final String TABLE_LOGIN = "Login";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		username_login = (EditText)findViewById(R.id.username_login);
		pass_login = (EditText)findViewById(R.id.password_login);
		database_handle = new DatabaseHandle(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	public void LoginAccount(View view){
		String username = username_login.getText().toString().trim();
		String pass = pass_login.getText().toString().trim();
		if(username.matches("")|| pass.matches("")){
			   AlertDialog.Builder dialog_login = new AlertDialog.Builder(Login.this);
			   dialog_login.setTitle("Warning");
			   dialog_login.setMessage("Username Or Password Error!");
			   dialog_login.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int arg1) {	
						dialog.cancel();
					}
				});
			   dialog_login.create().show();
		}else{
			if(database_handle.getLoginAccountCount(username) !=0){
				   AlertDialog.Builder dialog_login = new AlertDialog.Builder(Login.this);
				   dialog_login.setTitle("Warning");
				   dialog_login.setMessage("You Were Logined!");
				   dialog_login.setPositiveButton("OK", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int arg1) {	
							dialog.cancel();
						}
					});
				   dialog_login.create().show();
			}
			else{
				if(database_handle.getAccountCount(username)==0){
					 AlertDialog.Builder dialog_login_warning = new AlertDialog.Builder(Login.this);
					   dialog_login_warning.setTitle("Error");
					   dialog_login_warning.setMessage("Account Error!");
					   dialog_login_warning.setPositiveButton("OK", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int arg1) {	
								dialog.cancel();
							}
						});
					   dialog_login_warning.create().show();
				}else{
					database_handle.deleteTable(TABLE_LOGIN);
					if(database_handle.addAccountLogin(String.valueOf(username)) !=0){
						  AlertDialog.Builder dialog_login_warning = new AlertDialog.Builder(Login.this);
						   dialog_login_warning.setTitle("Notice");
						   dialog_login_warning.setMessage("You Are Login Successfully!");
						   dialog_login_warning.setPositiveButton("OK", new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int arg1) {	
									Intent int_interface = new Intent(getApplicationContext(), Interface.class);
									startActivity(int_interface);
								}
							});
						   dialog_login_warning.create().show();
					}
					else{
						   AlertDialog.Builder dialog_login_warning = new AlertDialog.Builder(Login.this);
						   dialog_login_warning.setTitle("Notice");
						   dialog_login_warning.setMessage("You Are Login Error!");
						   dialog_login_warning.setPositiveButton("OK", new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int arg1) {	
									dialog.cancel();
								}
							});
						   dialog_login_warning.create().show();
					}
				}
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
