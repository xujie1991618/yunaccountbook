/**
 * RecordFragment.java [V 1.0.0]
 * classes :　com.crayfish.yunbook.RecordFragment
 * crayfish create at 2015-5-11 下午2:34:49
 */
package com.crayfish.yunbook.fragment;

import java.util.Locale;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
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

        private static final int[] COLORS = new int[] {
            R.color.lightblue };

        public SimpleAdapter(Context context, int resource, int textViewResourceId) {
            super(context, resource, textViewResourceId);
            generateDataset('A', 'Z', false);
        }

        public void generateDataset(char from, char to, boolean clear) {
        	
        	if (clear) clear();
        	
            final int sectionsNumber = to - from + 1;
            prepareSections(sectionsNumber);

            int sectionPosition = 0, listPosition = 0;
            for (char i=0; i<sectionsNumber; i++) {
                Item section = new Item(Item.SECTION, String.valueOf((char)('A' + i)));
                section.sectionPosition = sectionPosition;
                section.listPosition = listPosition++;
                onSectionAdded(section, sectionPosition);
                add(section);

                final int itemsNumber = (int) Math.abs((Math.cos(2f*Math.PI/3f * sectionsNumber / (i+1f)) * 25f));
                for (int j=0;j<itemsNumber;j++) {
                    Item item = new Item(Item.ITEM, section.text.toUpperCase(Locale.ENGLISH) + " - " + j);
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
            TextView view = (TextView) super.getView(position, convertView, parent);
            view.setTextColor(Color.DKGRAY);
            view.setTag("" + position);
            Item item = getItem(position);
            if (item.type == Item.SECTION) {
                //view.setOnClickListener(PinnedSectionListActivity.this);
                view.setBackgroundColor(parent.getResources().getColor(COLORS[item.sectionPosition % COLORS.length]));
            }
            return view;
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
		public final String text;

		public int sectionPosition;
		public int listPosition;

		public Item(int type, String text) {
		    this.type = type;
		    this.text = text;
		}

		@Override public String toString() {
			return text;
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
			        Toast.makeText(getActivity(), "Item " + position + ": " + item.text, Toast.LENGTH_SHORT).show();
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
