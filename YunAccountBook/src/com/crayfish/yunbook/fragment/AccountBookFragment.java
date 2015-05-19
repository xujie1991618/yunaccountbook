/**
 * RecordFragment.java [V 1.0.0]
 * classes :　com.crayfish.yunbook.RecordFragment
 * crayfish create at 2015-5-11 下午2:34:49
 */
package com.crayfish.yunbook.fragment;

import com.crayfish.yunbook.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * com.crayfish.yunbook.RecordFragment
 * @author crayfish   <br/>
 * create at 2015-5-11 下午2:34:49
 */
public class AccountBookFragment extends Fragment{

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
		return inflater.inflate(R.layout.fragment_accountbook, container,false);
	}

}
