package com.tid.custom_element;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

public class CustomTextView extends TextView {

	public CustomTextView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public CustomTextView(Context context, AttributeSet attrs) {
	    super(context, attrs);
	    try {
			setCustomFont(context, attrs);
		} catch (Exception e) {
			Log.d("CUSTOM_TEXT","PRoblem");
		}
	}
	
	public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        try {
			setCustomFont(context, attrs);
		} catch (Exception e) {
			Log.d("CUSTOM_TEXT","PRoblem");
		}
    }
	
	
	

    public void setCustomFont(Context ctx, AttributeSet attrs) throws Exception {
        TypedArray a = ctx.obtainStyledAttributes(attrs, R.styleable.CustomTextParams);
        String customFont = a.getString(R.styleable.CustomTextParams_custom_font);
        setCustomFont(ctx, customFont);
        a.recycle();
    }

    public boolean setCustomFont(Context ctx, String asset) {
        Typeface tf = null;
        try {
        tf = Typeface.createFromAsset(ctx.getAssets(), asset);  
        } catch (Exception e) {
            Log.e("TAG", "Could not get typeface: "+e.getMessage());
            return false;
        }

        setTypeface(tf);  
        return true;
    }
}
