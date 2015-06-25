package edu.sfsu.cs.orange.positionConduct;
import android.telephony.SmsManager;
public class sms {
	 final SmsManager sms;
	public sms(){
		 sms = SmsManager.getDefault();
	}
	public void SendSMS(String phoneNumber, String message){
		sms.sendTextMessage(phoneNumber, null, message, null, null);
	}

}
