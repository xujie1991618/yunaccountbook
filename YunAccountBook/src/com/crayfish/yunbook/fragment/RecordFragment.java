/**
 * RecordFragment.java [V 1.0.0]
 * classes :　com.crayfish.yunbook.RecordFragment
 * crayfish create at 2015-5-11 下午2:34:49
 */
package com.crayfish.yunbook.fragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.crayfish.yunbook.R;
import com.crayfish.yunbook.view.GridItemAdapter;

/**
 * com.crayfish.yunbook.RecordFragment
 * 
 * @author crayfish <br/>
 *         create at 2015-5-11 下午2:34:49
 */
public class RecordFragment extends Fragment {

	/** 金额输入框 **/
	private EditText edittext;
	private View rootview;
	/** 收支选择器 **/
	private LinearLayout InOrOut_layout;
	private TextView income_textview;
	private TextView expend_textview;
	private boolean mFLAG = false;

	/** 日历控件布局 **/
	private LinearLayout my_calendar_layout;
	/** 时钟控件布局 **/
	private RelativeLayout my_clock_layout;
	private TextView my_calendar_text;
	private TextView my_clock_text;
	/** 标签布局 **/
	private GridView label_gridview;
	private int[] incomeimagelist = { R.drawable.cart, R.drawable.game,
			R.drawable.house, R.drawable.learn, R.drawable.metro,
			R.drawable.recreation, R.drawable.tel, R.drawable.travel,
			R.drawable.video };
	private String[] incomestringlist = { "购物", "游戏","租房","学习","地铁", 
			"运动", "话费", "旅游","电影" };
	
	private int[] expendimagelist = {R.drawable.game,R.drawable.house,
			R.drawable.recreation};
	private String[] expendstringlist = {"游戏","出租","运动"};

	private Calendar calendar;
	private int my_year, my_month, my_day, my_hour, my_minute;
	/**标签适配器**/
	private GridItemAdapter adapter;
	/**标签数据集**/
	private ArrayList<HashMap<String, Object>> gridItems;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		rootview = inflater.inflate(R.layout.fragment_record, container, false);
		calendar = Calendar.getInstance();
		my_year = calendar.get(Calendar.YEAR);
		my_month = calendar.get(Calendar.MONTH) + 1;
		my_day = calendar.get(Calendar.DAY_OF_MONTH);
		my_hour = calendar.get(Calendar.HOUR_OF_DAY);
		my_minute = calendar.get(Calendar.MINUTE);
		initView();
		initEvent();
		return rootview;
	}

	private void initView() {
		edittext = (EditText) rootview.findViewById(R.id.money);
		edittext.clearFocus();
		InOrOut_layout = (LinearLayout) rootview
				.findViewById(R.id.InOrOut_layout);
		income_textview = (TextView) rootview.findViewById(R.id.income_text);
		expend_textview = (TextView) rootview.findViewById(R.id.expend_text);
		income_textview.setEnabled(mFLAG);
		income_textview
				.setTextColor(getResources().getColor(R.color.lightgray));
		expend_textview.setEnabled(!mFLAG);
		expend_textview.setTextColor(getResources().getColor(R.color.blue));

		my_calendar_layout = (LinearLayout) rootview
				.findViewById(R.id.my_calendar_layout);
		my_clock_layout = (RelativeLayout) rootview
				.findViewById(R.id.my_clock_layout);
		my_calendar_text = (TextView) rootview
				.findViewById(R.id.my_calendar_text);
		my_calendar_text.setText(String.format("%tF", new Date()));
		my_clock_text = (TextView) rootview.findViewById(R.id.my_clock_text);
		my_clock_text.setText(my_hour + ":" + String.format("%02d", my_minute));
		label_gridview = (GridView) rootview.findViewById(R.id.label_gridview);
	}

	private void initEvent() {
		edittext.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				Log.w("onTextChanged", s.toString());
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				Log.w("beforeTextChanged", s.toString());
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				Log.w("afterTextChanged", s.toString());
				if (s.toString().equals("")) {
					edittext.setTextSize(15);
					edittext.setTextColor(getResources().getColor(R.color.gray));
					edittext.getPaint().setFakeBoldText(false);
				} else {
					edittext.setTextSize(40);
					edittext.setTextColor(getResources().getColor(R.color.blue));
					edittext.getPaint().setFakeBoldText(true);
				}
			}
		});
		gridItems = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < incomeimagelist.length; i++) {
			HashMap<String, Object> mapItem = new HashMap<String, Object>();
			mapItem.put("ItemImage", incomeimagelist[i]);// 添加图片资源
			mapItem.put("ItemText", incomestringlist[i]);// 添加Name资源
			gridItems.add(mapItem);
		}
		HashMap<String, Object> mapItem = new HashMap<String, Object>();
		mapItem.put("ItemImage", R.drawable.add);// 添加图片资源
		mapItem.put("ItemText", "添加");// 添加Name资源
		gridItems.add(mapItem);
		adapter = new GridItemAdapter(getActivity(), gridItems);
		InOrOut_layout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mFLAG = !mFLAG;
				income_textview.setEnabled(mFLAG);
				income_textview.setTextColor(mFLAG ? getResources().getColor(
						R.color.blue) : getResources().getColor(
						R.color.lightgray));
				expend_textview.setEnabled(!mFLAG);
				expend_textview.setTextColor(!mFLAG ? getResources().getColor(
						R.color.blue) : getResources().getColor(
						R.color.lightgray));
				gridItems = new ArrayList<HashMap<String, Object>>();
				if(!mFLAG){
					for (int i = 0; i < incomeimagelist.length; i++) {
						HashMap<String, Object> mapItem = new HashMap<String, Object>();
						mapItem.put("ItemImage", incomeimagelist[i]);// 添加图片资源
						mapItem.put("ItemText", incomestringlist[i]);// 添加Name资源
						gridItems.add(mapItem);
					}
					HashMap<String, Object> mapItem = new HashMap<String, Object>();
					mapItem.put("ItemImage", R.drawable.add);// 添加图片资源
					mapItem.put("ItemText", "添加");// 添加Name资源
					gridItems.add(mapItem);
					adapter.setGridItems(gridItems);
					adapter.notifyDataSetChanged();
				}else{
					for (int i = 0; i < expendimagelist.length; i++) {
						HashMap<String, Object> mapItem = new HashMap<String, Object>();
						mapItem.put("ItemImage", expendimagelist[i]);// 添加图片资源
						mapItem.put("ItemText", expendstringlist[i]);// 添加Name资源
						gridItems.add(mapItem);
					}
					HashMap<String, Object> mapItem = new HashMap<String, Object>();
					mapItem.put("ItemImage", R.drawable.add);// 添加图片资源
					mapItem.put("ItemText", "添加");// 添加Name资源
					gridItems.add(mapItem);
					adapter.setGridItems(gridItems);
					adapter.notifyDataSetChanged();
				}
			}
		});

		my_calendar_layout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DatePickerDialog datePickerDialog = new DatePickerDialog(
						getActivity(), new OnDateSetListener() {
							
							@Override
							public void onDateSet(DatePicker view, int year,
									int monthOfYear, int dayOfMonth) {
								// TODO Auto-generated method stub
								view.setFocusable(false);
								my_year = year;
								my_month = monthOfYear + 1;
								my_day = dayOfMonth;
								my_calendar_text.setText(year
										+ "-"
										+ String.format("%02d",
												(monthOfYear + 1)) + "-"
										+ String.format("%02d", dayOfMonth));
							}
						}, my_year, my_month - 1, my_day);
				datePickerDialog.setCancelable(false);
				datePickerDialog.show();
			}
		});
		my_clock_layout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TimePickerDialog timePickerDialog = new TimePickerDialog(
						getActivity(), new OnTimeSetListener() {

							@Override
							public void onTimeSet(TimePicker view,
									int hourOfDay, int minute) {
								// TODO Auto-generated method stub
								view.setFocusable(false);
								my_hour = hourOfDay;
								my_minute = minute;
								my_clock_text.setText(String.format("%02d",
										hourOfDay)
										+ ":"
										+ String.format("%02d", minute));
							}
						}, my_hour, my_minute, true);
				timePickerDialog.setCancelable(false);
				timePickerDialog.show();
			}
		});

		label_gridview.setAdapter(adapter);
		label_gridview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				if(position == gridItems.size()-1){
					Toast.makeText(getActivity(), "添加标签", Toast.LENGTH_SHORT).show();
				}else{
					adapter.SetSeclection(position);
				}
				adapter.notifyDataSetChanged();
			}
		});
	}

}
