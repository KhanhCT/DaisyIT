package edu.c9.lab411.offerCustomer;
import edu.c9.lab411.ocr.R;

import java.util.ArrayList;
import java.util.HashMap;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import static edu.c9.lab411.goodsissue.Constant.FIRST_COLUMN;
import static edu.c9.lab411.goodsissue.Constant.SECOND_COLUMN;
import static edu.c9.lab411.goodsissue.Constant.THIRD_COLUMN;
import static edu.c9.lab411.goodsissue.Constant.FOR_COLUMN;
import static edu.c9.lab411.goodsissue.Constant.FIVE_COLUMN;
import static edu.c9.lab411.goodsissue.Constant.SIX_COLUMN;

public class EfficientAdapterOffer extends BaseAdapter implements Filterable{
private Context context;
public ArrayList<HashMap<String, String>> list;
private LayoutInflater mInflater;
private ViewHolder holder;
	public EfficientAdapterOffer(Context context,  ArrayList<HashMap<String, String>> list) {
		// TODO Auto-generated constructor stub
		this.context = context;
		mInflater = LayoutInflater.from(context);
		this.list = list;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Context getContext() {
		return context;
	}
	public void setContext(Context context) {
		this.context = context;
	}
	@SuppressLint("InflateParams")
	@Override
	public View getView(final int position, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub

		if(convertView == null){
			convertView = mInflater.inflate(R.layout.lv_colum_offer, null);
			holder = new ViewHolder();
			holder.relativeLayout = (RelativeLayout) convertView.findViewById(R.id.lineItem);
			holder.txFirst = (TextView)convertView.findViewById(R.id.textLine);
			holder.txSecond = (TextView)convertView.findViewById(R.id.textLine2);
			holder.txThird = (TextView)convertView.findViewById(R.id.textLine3);
			holder.txFor = (TextView)convertView.findViewById(R.id.textLine4);
			holder.txFive = (TextView)convertView.findViewById(R.id.textLine5);
			holder.txSix= (TextView)convertView.findViewById(R.id.textLine6);
			holder.img = (ImageView)convertView.findViewById(R.id.image_view);
			holder.status = (CheckBox)convertView.findViewById(R.id.cb_status);	
			
			holder.txFirst.setTextColor(Color.YELLOW);
			holder.txSecond.setTextColor(Color.YELLOW);
			holder.txThird.setTextColor(Color.YELLOW);
			holder.txFor.setTextColor(Color.YELLOW);
			holder.txFive.setTextColor(Color.YELLOW);
			holder.txSix.setTextColor(Color.YELLOW);
			
			holder.txFirst.setTextSize(16);
			holder.txSecond.setTextSize(16);
			holder.txThird.setTextSize(16);
			holder.txFor.setTextSize(16);
			holder.txFive.setTextSize(16);
			holder.txSix.setTextSize(16);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		HashMap<String, String> map = list.get(position);
		holder.txFirst.setText(map.get(FIRST_COLUMN));
		holder.txSecond.setText(map.get(SECOND_COLUMN));
		holder.txThird.setText(map.get(THIRD_COLUMN));
		holder.txFor.setText(map.get(FOR_COLUMN));
		holder.txFive.setText(map.get(FIVE_COLUMN));
		holder.txSix.setText(map.get(SIX_COLUMN));
		if(map.get("STATUS").matches("0")){
			holder.status.setChecked(false);
		}else{
			holder.status.setChecked(true);
		}		
		return convertView;
	}
	public void removeLayout(){
		holder.relativeLayout.removeAllViews();
	}
	private class ViewHolder{
		RelativeLayout relativeLayout;
		TextView txFirst;
		TextView txSecond;
		TextView txThird;
		TextView txFor;
		TextView txFive;
		TextView txSix;
		@SuppressWarnings("unused")
		ImageView img;
		CheckBox status;
		
	}
	@Override
	public Filter getFilter() {
		// TODO Auto-generated method stub
		return null;
	}

}
