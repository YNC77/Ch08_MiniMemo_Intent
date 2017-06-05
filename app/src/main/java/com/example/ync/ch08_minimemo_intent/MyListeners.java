package com.example.ync.ch08_minimemo_intent;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

/**
 * Created by YNC on 2017/5/19.
 */

public class MyListeners implements View.OnClickListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    Main2Activity activity;
    //AppCompatActivity activity;
    public MyListeners(Main2Activity act){
        this.activity = act;
    }

    @Override
    public void onClick(View v) {
        if(v == activity.dateS) {
            new DatePickerDialog(activity,this,activity.calendar.get(Calendar.YEAR), activity.calendar.get(Calendar.MONTH), activity.calendar.get(Calendar.DAY_OF_MONTH)).show();
        }else if (v == activity.timeS){
            new TimePickerDialog(activity, this, activity.calendar.get(Calendar.HOUR_OF_DAY), activity.calendar.get(Calendar.MINUTE), true).show();
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
