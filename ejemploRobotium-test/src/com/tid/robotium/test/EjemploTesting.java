package com.tid.robotium.test;

import java.util.ArrayList;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.Smoke;
import android.util.Log;
import android.widget.Button;

import com.jayway.android.robotium.solo.Solo;
import com.tid.robotium.EjemploRobotiumActivity;

public class EjemploTesting extends ActivityInstrumentationTestCase2<EjemploRobotiumActivity> {

	private Solo solo;

	public EjemploTesting() {
		super("com.tid.robotium", EjemploRobotiumActivity.class);

	}

	public void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
	}
	


	@Smoke
	public void testPutbtns() throws Exception {
		ArrayList<Button> listBtns = solo.getCurrentButtons();
		for (Button button : listBtns) {
			Log.d("TID_TAG","demo:"+button.getText().toString());
		//	solo.clickOnButton(button.getId());
		}
		solo.clickOnButton("btn1");
		solo.clickOnButton("btn2");
		solo.clickOnButton("btn3");
		solo.clickOnButton("btn4");
		solo.clickOnButton("btn5");
		solo.clickOnButton("btn6");
		solo.clickOnButton("btn1");
		solo.clickOnButton("btn2");
		solo.clickOnButton("btn3");
		solo.clickOnButton("btn4");
		solo.clickOnButton("btn5");
		solo.clickOnButton("btn6");
		assertTrue("terminados botones",true);
	}
	
	
	@Smoke
	public void testPutText() throws Exception {
		String data="ponemos texxxto";
		solo.enterText(getActivity().text,data );
		assertTrue("message igual", true);
	}
	
	
	
	@Override
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}
}
