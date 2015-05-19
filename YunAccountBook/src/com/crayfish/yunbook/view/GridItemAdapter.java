package com.crayfish.yunbook.view;

import java.util.ArrayList;
import java.util.HashMap;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.crayfish.yunbook.R;

public class GridItemAdapter extends BaseAdapter{

	private Context context;
	private ArrayList<HashMap<String, Object>> gridItems;
	
	private ImageView item_image;
	private TextView item_text;
	
	private int mFLAG = 0;
	
	public GridItemAdapter(Context context,ArrayList<HashMap<String, Object>> gridItems){
		this.context = context;
		this.gridItems = gridItems;
	}
	
	public void setGridItems(ArrayList<HashMap<String, Object>> gridItems){
		this.gridItems = gridItems;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return gridItems.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return gridItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@SuppressLint("NewApi")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		RelativeLayout labelItem_layout = null;
		if(convertView == null){
			labelItem_layout = new RelativeLayout(context);
			String inflater = Context.LAYOUT_INFLATER_SERVICE;
			LayoutInflater li = (LayoutInflater)context.getSystemService(inflater);
			li.inflate(R.layout.label_item, labelItem_layout,true);
		}else{
			labelItem_layout = (RelativeLayout)convertView;
		}
		item_text = (TextView)labelItem_layout.findViewById(R.id.item_text);
		item_image = (ImageView)labelItem_layout.findViewById(R.id.item_image);
		
		if(position == mFLAG){
			item_image.setImageResource((Integer)gridItems.get(position).get("ItemImage"));
			item_image.setBackgroundColor(context.getResources().getColor(R.color.blue));
			item_text.setText((String)gridItems.get(position).get("ItemText"));
			item_text.setTextColor(context.getResources().getColor(R.color.blue));
		}else if(position == gridItems.size()-1){
			item_image.setImageResource((Integer)gridItems.get(position).get("ItemImage"));
			item_image.setBackground(context.getResources().getDrawable(R.drawable.bg_add_view));
			item_text.setText((String)gridItems.get(position).get("ItemText"));
			item_text.setTextColor(context.getResources().getColor(R.color.black));
		}else{
			item_image.setImageResource((Integer)gridItems.get(position).get("ItemImage"));
			item_image.setBackgroundColor(Color.TRANSPARENT);
			item_image.setBackground(context.getResources().getDrawable(R.drawable.bg_view));
			item_text.setText((String)gridItems.get(position).get("ItemText"));
			item_text.setTextColor(context.getResources().getColor(R.color.black));
		}
		return labelItem_layout;
	}

	public void SetSeclection(int posotion){
		mFLAG = posotion;
	}
}
