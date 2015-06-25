package edu.c9.lab411.googlemap;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import edu.c9.lab411.Cart.JSONParser;
import edu.c9.lab411.ocr.R;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.Marker;

public class MyInfoWindowAdapter implements InfoWindowAdapter {

	private Activity context;
	private Bitmap btmp;
	private String PLoca_ID_, PLoca_Code_, Partner_ID_, PLoca_Type_,
			PLoca_Name_, PLoca_Address_, Place_ID_;
	JSONParser json_parser;
	int success;
	ArrayList<String> list;

	public MyInfoWindowAdapter(Activity context, Bitmap btmp, String PLoca_ID,
			String PLoca_Code, String Partner_ID, String PLoca_Type,
			String PLoca_Name, String PLoca_Address, String Place_ID) {
		this.context = context;
		this.btmp = btmp;
		this.PLoca_ID_ = PLoca_ID;
		this.PLoca_Code_ = PLoca_Code;
		this.Partner_ID_ = Partner_ID;
		this.PLoca_Type_ = PLoca_Type;
		this.PLoca_Name_ = PLoca_Name;
		this.PLoca_Address_ = PLoca_Address;
		this.Place_ID_ = Place_ID;
	}

	@Override
	public View getInfoContents(Marker arg0) {
		// Getting view from the layout file info_window_layout
		View v = this.context.getLayoutInflater().inflate(R.layout.custom_info,
				null);
		// Getting the position from the marker
		// LatLng latLng = arg0.getPosition();
		TextView tvPLoca_ID = (TextView) v.findViewById(R.id.PLoca_ID);
		TextView tvPLoca_Code = (TextView) v.findViewById(R.id.PLoca_Code);
		TextView tvPartner_ID = (TextView) v.findViewById(R.id.Partner_ID);
		TextView tvPLoca_Type = (TextView) v.findViewById(R.id.PLoca_Type);
		TextView tvPLoca_Name = (TextView) v.findViewById(R.id.PLoca_Name);
		TextView tvPLoca_Address = (TextView) v
				.findViewById(R.id.PLoca_Address);
		TextView tvPlace_ID = (TextView) v.findViewById(R.id.Place_ID);
		ImageView img = (ImageView) v.findViewById(R.id.img_view);

		tvPLoca_ID.setText(PLoca_ID_);
		tvPLoca_Code.setText(PLoca_Code_);
		tvPartner_ID.setText(Partner_ID_);
		tvPLoca_Type.setText(PLoca_Type_);
		tvPLoca_Name.setText(PLoca_Name_);
		tvPLoca_Address.setText(PLoca_Address_);
		tvPlace_ID.setText(Place_ID_);
		img.setImageBitmap(btmp);
		return v;
	}

	@Override
	public View getInfoWindow(Marker arg0) {

		return null;
	}

}
