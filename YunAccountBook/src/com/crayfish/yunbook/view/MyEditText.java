/**
 * MyEditText.java [V 1.0.0]
 * classes :　com.crayfish.yunbook.view.MyEditText
 * crayfish create at 2015-5-13 下午5:10:45
 */
package com.crayfish.yunbook.view;

import com.crayfish.yunbook.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Editable;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.UnderlineSpan;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * com.crayfish.yunbook.view.MyEditText
 * @author crayfish   <br/>
 * create at 2015-5-13 下午5:10:45
 */
public class MyEditText extends EditText implements TextWatcher{

	private Paint myTextPaint;
	
	private String label = "";
	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public MyEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context,attrs);
	}
	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public MyEditText(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context,null);
	}
	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public MyEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init(context, attrs);
	}
	/**
	 * 初始化设置
	 */
	private void init(Context context,AttributeSet attrs){
		myTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		myTextPaint.setColor(getResources().getColor(R.color.blue));
		addTextChangedListener(this);
	}
	
	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.draw(canvas);
	}
	
	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub
		if(s.toString().equals("")){
			setTextSize(15);
			setTextColor(getResources().getColor(R.color.gray));
		}else{
			setTextSize(40);
			setTextColor(getResources().getColor(R.color.gray));
			getPaint().setFakeBoldText(true);
		}
	}
	
	@Override
	public void onTextChanged(CharSequence text, int start,
			int lengthBefore, int lengthAfter) {
		// TODO Auto-generated method stub
		super.onTextChanged(text, start, lengthBefore, lengthAfter);
	}

}
