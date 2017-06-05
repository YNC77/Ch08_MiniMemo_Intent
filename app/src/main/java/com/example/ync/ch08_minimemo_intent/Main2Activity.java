package com.example.ync.ch08_minimemo_intent;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.GregorianCalendar;



public class Main2Activity extends AppCompatActivity {

    TextView dateS, timeS;
    GregorianCalendar calendar = new GregorianCalendar();
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        dateS = (TextView) findViewById(R.id.txvDateS);
        timeS = (TextView) findViewById(R.id.txvTimeS);
        dateS.setOnClickListener(ml);
        timeS.setOnClickListener(ml);

        back = (Button) findViewById(R.id.btnBack);
        back.setOnClickListener(backListener);

    }

    MyListeners ml = new MyListeners(this);

    View.OnClickListener backListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent backIt = new Intent();
            backIt.putExtra("date", dateS.getText());
            backIt.putExtra("time", timeS.getText());
            setResult(RESULT_OK, backIt);
            finish();

        }
    };

}
