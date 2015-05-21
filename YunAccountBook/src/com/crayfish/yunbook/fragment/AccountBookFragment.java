/**
 * RecordFragment.java [V 1.0.0]
 * classes :　com.crayfish.yunbook.RecordFragment
 * crayfish create at 2015-5-11 下午2:34:49
 */
package com.crayfish.yunbook.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;
import android.widget.Toast;

import com.crayfish.yunbook.R;
import com.crayfish.yunbook.view.PinnedSectionListView;
import com.crayfish.yunbook.view.PinnedSectionListView.PinnedSectionListAdapter;

/**
 * com.crayfish.yunbook.RecordFragment
 * @author crayfish   <br/>
 * create at 2015-5-11 下午2:34:49
 */
public class AccountBookFragment extends Fragment implements OnClickListener{

	static class SimpleAdapter extends ArrayAdapter<Item> implements PinnedSectionListAdapter {

		private LayoutInflater inflater;
		
        private static final int[] COLORS = new int[] {
        	R.color.green_light, R.color.orange_light,
            R.color.blue_light, R.color.red_light};

        public SimpleAdapter(Context context, int resource, int textViewResourceId) {
            super(context, resource, textViewResourceId);
            this.inflater = LayoutInflater.from(context);
            generateDataset('A', 'Z', false);
        }

        public void generateDataset(char from, char to, boolean clear) {
        	
        	if (clear) clear();
        	
            final int sectionsNumber = to - from + 1;
            prepareSections(sectionsNumber);

            int sectionPosition = 0, listPosition = 0;
            for (char i=0; i<sectionsNumber; i++) {
                Item section = new Item(Item.SECTION, "1","2","3","4");
                section.sectionPosition = sectionPosition;
                section.listPosition = listPosition++;
                onSectionAdded(section, sectionPosition);
                add(section);

                final int itemsNumber = (int) Math.abs((Math.cos(2f*Math.PI/3f * sectionsNumber / (i+1f)) * 25f));
                for (int j=0;j<itemsNumber;j++) {
                    Item item = new Item(Item.ITEM, "a","b","d","e");
                    item.sectionPosition = sectionPosition;
                    item.listPosition = listPosition++;
                    add(item);
                }

                sectionPosition++;
            }
        }
        
        protected void prepareSections(int sectionsNumber) { }
        protected void onSectionAdded(Item section, int sectionPosition) { }

        @Override public View getView(int position, View convertView, ViewGroup parent) {
//            TextView view = (TextView) super.getView(position, convertView, parent);
//            view.setTextColor(Color.DKGRAY);
//            view.setTag("" + position);
//            Item item = getItem(position);
//            if (item.type == Item.SECTION) {
//                //view.setOnClickListener(PinnedSectionListActivity.this);
//                view.setBackgroundColor(parent.getResources().getColor(COLORS[item.sectionPosition % COLORS.length]));
//            }
        	Item item = getItem(position);
        	if(item.type == Item.SECTION){
        		ViewHolderTitle titleHolder;
	        	if(convertView ==null){
	        		convertView = inflater.inflate(R.layout.accountbook_title_item, null);
	        		titleHolder = new ViewHolderTitle();
	        		titleHolder.date_textview = (TextView)convertView.findViewById(R.id.date);
	        		titleHolder.expend_total_textview = (TextView)convertView.findViewById(R.id.expend_total);
	        		titleHolder.income_total_textview = (TextView)convertView.findViewById(R.id.income_total);
	        		titleHolder.record_count_textview = (TextView)convertView.findViewById(R.id.record_count);
	        		convertView.setTag(titleHolder);//绑定对象
	        	}else{
	        		titleHolder = (ViewHolderTitle)convertView.getTag();
	        	}
//	        	titleHolder.date_textview.setText(item.text1);
//	        	titleHolder.expend_total_textview.setText(item.text2);
//	        	titleHolder.income_total_textview.setText(item.text3);
//	        	titleHolder.record_count_textview.setText(item.text4);
	        	convertView.setBackgroundColor(parent.getResources().getColor(COLORS[item.sectionPosition % COLORS.length]));
        	}else{
        		ViewHolderItem itemHolder;
        		if(convertView == null){
        			convertView = inflater.inflate(R.layout.accountbook_item, null);
	        		itemHolder = new ViewHolderItem();
	        		itemHolder.item_icon_Image = (ImageView)convertView.findViewById(R.id.item_icon);
	        		itemHolder.timeandtype_textview = (TextView)convertView.findViewById(R.id.timeandtype);
	        		itemHolder.type_name_textview = (TextView)convertView.findViewById(R.id.type_name);
	        		itemHolder.money_textview = (TextView)convertView.findViewById(R.id.money);
	        		convertView.setTag(itemHolder);//绑定对象
	        	}else{
	        		itemHolder = (ViewHolderItem)convertView.getTag();
	        	}
//        		itemHolder.item_icon_Image.setImageResource(R.drawable.ic_launcher);
//        		itemHolder.timeandtype_textview.setText(item.text2);
//        		itemHolder.type_name_textview.setText(item.text3);
//        		itemHolder.money_textview.setText(item.text4);
        	}
            return convertView;
        }

        @Override public int getViewTypeCount() {
            return 2;
        }

        @Override public int getItemViewType(int position) {
            return getItem(position).type;
        }

        @Override
        public boolean isItemViewTypePinned(int viewType) {
            return viewType == Item.SECTION;
        }
        class ViewHolderTitle{
        	TextView date_textview;
        	TextView expend_total_textview;
        	TextView income_total_textview;
        	TextView record_count_textview;
        }
        class ViewHolderItem{
        	ImageView item_icon_Image;
        	TextView type_name_textview;
        	TextView timeandtype_textview;
        	TextView money_textview;
        }
    }
	
	static class FastScrollAdapter extends SimpleAdapter implements SectionIndexer {

        private Item[] sections;

        public FastScrollAdapter(Context context, int resource, int textViewResourceId) {
            super(context, resource, textViewResourceId);
        }

        @Override protected void prepareSections(int sectionsNumber) {
            sections = new Item[sectionsNumber];
        }

        @Override protected void onSectionAdded(Item section, int sectionPosition) {
            sections[sectionPosition] = section;
        }

        @Override public Item[] getSections() {
            return sections;
        }

        @Override public int getPositionForSection(int section) {
            if (section >= sections.length) {
                section = sections.length - 1;
            }
            return sections[section].listPosition;
        }

        @Override public int getSectionForPosition(int position) {
            if (position >= getCount()) {
                position = getCount() - 1;
            }
            return getItem(position).sectionPosition;
        }

    }
	
	static class Item {

		public static final int ITEM = 0;
		public static final int SECTION = 1;

		public final int type;
		public final String text1;
		public final String text2;
		public final String text3;
		public final String text4;

		public int sectionPosition;
		public int listPosition;

		public Item(int type, String text1,String text2,String text3,String text4) {
		    this.type = type;
		    this.text1 = text1;
		    this.text2 = text2;
		    this.text3 = text3;
		    this.text4 = text4;
		}

		@Override public String toString() {
			return text1;
		}

	}
	
	private View rootview;
	private PinnedSectionListView accountbookListview;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
//		return super.onCreateView(inflater, container, savedInstanceState);
		rootview = inflater.inflate(R.layout.fragment_accountbook, container, false);
		initializeAdapter();
		return rootview;
	}

	/**
	 * 初始化适配器
	 */
	@SuppressLint("NewApi")
	private void initializeAdapter() {
		// TODO Auto-generated method stub
		accountbookListview = (PinnedSectionListView)rootview.findViewById(R.id.accountbookList);
		accountbookListview.setFastScrollEnabled(true);//有无滑动条
		accountbookListview.setAdapter(new SimpleAdapter(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1));
		accountbookListview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				Item item = (Item) accountbookListview.getAdapter().getItem(position);
			    if (item != null) {
			        Toast.makeText(getActivity(), "Item " + position + ": " + item.text1, Toast.LENGTH_SHORT).show();
			    } else {
			        Toast.makeText(getActivity(), "Item " + position, Toast.LENGTH_SHORT).show();
			    }
			}
		});
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Toast.makeText(getActivity(), "Item: " + v.getTag() , Toast.LENGTH_SHORT).show();
	}

}
