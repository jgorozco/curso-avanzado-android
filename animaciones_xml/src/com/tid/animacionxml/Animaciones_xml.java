package com.tid.animacionxml;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RadioGroup;

public class Animaciones_xml extends Activity {
    private static final String TID_EX = "TID_EX";
    private RadioGroup radioGroup;
	/** Called when the activity is first created. */
	ImageView image;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        image=(ImageView) findViewById(R.id.image);
        radioGroup=(RadioGroup) findViewById(R.id.radioGroup1);
    }
    
    
    
    public void onClickAnim(View target)
    {
    	int radioGrp=radioGroup.getCheckedRadioButtonId();
    	Log.d(TID_EX, "radioPushed:"+String.valueOf(radioGrp));
    	Animation a=null;
    	switch (radioGrp) {
		case 0+2131099651:
			a= AnimationUtils.loadAnimation(this, R.anim.rotation);
			break;
		case 1+2131099651:
			a= AnimationUtils.loadAnimation(this, R.anim.fadein);
			break;
		case 2+2131099651:
			a= AnimationUtils.loadAnimation(this, R.anim.fadeout);
			break;
		case 3+2131099651:
			a= AnimationUtils.loadAnimation(this, R.anim.propia);
			break;
		case 4+2131099651:
			a= AnimationUtils.loadAnimation(this, R.anim.push_left_in);
			break;
		case 5+2131099651:
			a= AnimationUtils.loadAnimation(this, R.anim.push_left_out);
			break;
		case 6+2131099651:
			a= AnimationUtils.loadAnimation(this, R.anim.push_right_in);
			break;
		case 7+2131099651:
			a= AnimationUtils.loadAnimation(this, R.anim.push_right_out);
			break;
		default:
			a= AnimationUtils.loadAnimation(this, R.anim.propia);
			break;
		}
    	 
    	image.startAnimation(a);
    	
    	
    }
}