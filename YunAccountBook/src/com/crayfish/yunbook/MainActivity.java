package com.crayfish.yunbook;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

import com.crayfish.yunbook.fragment.AccountBookFragment;
import com.crayfish.yunbook.fragment.AccountLableFragment;
import com.crayfish.yunbook.fragment.MoreFragment;
import com.crayfish.yunbook.fragment.PagerSlidingTabStrip;
import com.crayfish.yunbook.fragment.RecordFragment;

public class MainActivity extends FragmentActivity implements OnClickListener {

	/**记一笔**/
	private RecordFragment recordFragment;
	/**账本**/
	private AccountBookFragment accountBookFragment;
	/**标签**/
	private AccountLableFragment accountLableFragment;
	/**更多**/
	private MoreFragment moreFragment;
	
	/**PagerSlidingTabStrip实例**/
	private PagerSlidingTabStrip tabs;
	/**当前屏幕的密度**/
	public DisplayMetrics dm;
	/**内容页**/
	private ViewPager pager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		initView();
		initData();
	}

	/**
	 * 初始化设置
	 */
	private void initView() {
		dm = getResources().getDisplayMetrics();
		tabs = (PagerSlidingTabStrip)this.findViewById(R.id.tabs);
		pager = (ViewPager)this.findViewById(R.id.pager);
	}

	/**
	 * UI操作
	 */
	private void initData(){
		pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
		tabs.setViewPager(pager);
		setTabsValue();
	}

	/**
	 * 对PagerSlidingTabStrip各项属性进行设置
	 */
	@SuppressLint("NewApi")
	private void setTabsValue(){
		//自动填充满屏
		tabs.setShouldExpand(true);
		//分割线
		tabs.setDividerColor(Color.TRANSPARENT);
		// 设置Tab底部线的高度
		tabs.setUnderlineHeight((int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 2, dm));
		// 设置Tab Indicator的高度
		tabs.setIndicatorHeight((int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 6, dm));
		// 设置Tab标题文字的大小
		tabs.setTextSize((int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_SP, 16, dm));
		// 设置Tab Indicator的颜色
		tabs.setIndicatorColor(getResources().getColor(R.color.blue));
		// 设置选中Tab文字的颜色 (这是我自定义的一个方法)
		tabs.setSelectedTextColor(getResources().getColor(R.color.blue));
		tabs.setBackgroundResource(R.color.background_tab_pressed);
		// 取消点击Tab时的背景色
		tabs.setTabBackground(0);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	}

	public class MyPagerAdapter extends FragmentPagerAdapter{

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		private final String[] titles = {"记一笔","账本","标签","更多"}; 

		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
			return titles[position];
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return titles.length;
		}
		
		@Override
		public Fragment getItem(int position) {
			// TODO Auto-generated method stub
			switch (position) {
			case 0:
				if(recordFragment == null){
					recordFragment = new RecordFragment();
				}
				return recordFragment;
			case 1:
				if(accountBookFragment == null){
					accountBookFragment = new AccountBookFragment();
				}
				return accountBookFragment;
			case 2:
				if(accountLableFragment == null){
					accountLableFragment = new AccountLableFragment();
				}
				return accountLableFragment;
			case 3:
				if(moreFragment == null){
					moreFragment = new MoreFragment();
				}
				return moreFragment;

			default:
				return null;
			}
		}

	}
}
