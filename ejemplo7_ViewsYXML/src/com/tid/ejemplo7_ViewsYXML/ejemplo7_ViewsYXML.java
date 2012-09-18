package com.tid.ejemplo7_ViewsYXML;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TimePicker;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TimePicker.OnTimeChangedListener;

public class ejemplo7_ViewsYXML extends Activity implements OnTimeChangedListener {
    
	Button boton1;
	ImageButton botonImagen;
	EditText edittext1;
	CheckBox checkbox;
	SeekBar myseekbar;
	DatePicker datePicker;
	TimePicker timePicker;
	
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        boton1=(Button) findViewById(R.id.boton1);
        botonImagen=(ImageButton) findViewById(R.id.image_button);
        edittext1=(EditText) findViewById(R.id.edittext1);
        checkbox=(CheckBox) findViewById(R.id.check1);
        myseekbar=(SeekBar) findViewById(R.id.seek_bar);
        datePicker=(DatePicker) findViewById(R.id.date_picker);
        timePicker=(TimePicker) findViewById(R.id.time_picker);
        
        boton1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				edittext1.setText("hemos clickeado en el boton1");
				
			}
		});
        botonImagen.setOnClickListener(new OnClickListener() {	
			public void onClick(View v) {
				edittext1.setText("hemos clickeado en el boton con imagen");
				
			}
		});
        botonImagen.setOnLongClickListener(new OnLongClickListener() {
			
			public boolean onLongClick(View v) {
				edittext1.setText("hemos hecho un longclick en el boton con imagen");
				return false;
			}
		});
        checkbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				edittext1.setText("click en ckeck["+String.valueOf(isChecked)+"]");
				
			}
		});
        myseekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			public void onStopTrackingTouch(SeekBar seekBar) {
				edittext1.setText("onStopTrackingTouch["+String.valueOf(seekBar.getProgress())+"]");
				
			}
			
			public void onStartTrackingTouch(SeekBar seekBar) {
				edittext1.setText("onStartTrackingTouch["+String.valueOf(seekBar.getProgress())+"]");
				
			}
			
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				edittext1.setText("onProgressChanged["+String.valueOf(seekBar.getProgress())+"]");
				
			}
		});
        
        timePicker.setOnTimeChangedListener( this);
       /* datePicker.setOnTouchListener(new OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				int dia=datePicker.getDayOfMonth();
				int mes=datePicker.getMonth();
				int anio=datePicker.getYear();
				edittext1.setText("..date["+String.valueOf(dia)+"/"+String.valueOf(mes)+"/"+String.valueOf(anio)+"]");
				return true;
			}
		});*/
    }


	public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
		edittext1.setText("..onTimeChanged["+String.valueOf(hourOfDay)+":"+String.valueOf(minute)+"]");
		
	}

/*
	public void onClick(View v) {
		int dia=datePicker.getDayOfMonth();
		int mes=datePicker.getMonth();
		int anio=datePicker.getYear();
		edittext1.setText("..date["+String.valueOf(dia)+"/"+String.valueOf(mes)+"/"+String.valueOf(anio)+"]");
		
	}*/
}