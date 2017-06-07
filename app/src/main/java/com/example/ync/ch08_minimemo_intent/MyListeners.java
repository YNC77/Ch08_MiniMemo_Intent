package com.example.ync.ch08_minimemo_intent;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.GregorianCalendar;

/*
 * Created by YNC on 2017/5/19.
 */

public class MyListeners implements View.OnClickListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    Main2Activity activity;
    GregorianCalendar calendar = new GregorianCalendar();
    //AppCompatActivity activity;
    public MyListeners(Main2Activity act){
        this.activity = act;
    }

    @Override
    public void onClick(View v) {
        if(v == activity.dateS) {
            new DatePickerDialog(activity,this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
        }else if (v == activity.timeS){
            new TimePickerDialog(activity,this, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
        }

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        activity.dateS.setText(year+" / "+(month+1)+" / "+dayOfMonth);

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        activity.timeS.setText(hourOfDay+" : "+minute);

    }
}
