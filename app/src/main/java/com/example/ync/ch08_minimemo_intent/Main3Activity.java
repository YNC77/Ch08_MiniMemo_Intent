package com.example.ync.ch08_minimemo_intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    TextView number, dateS, timeS;
    EditText content;
    Intent getIt, setIt;
    Button settingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        number = (TextView) findViewById(R.id.txvNo);
        content = (EditText) findViewById(R.id.edtContent);


        //Setting button
        settingBtn = (Button) findViewById(R.id.btnSetting);
        dateS = (TextView) findViewById(R.id.txvDate);
        timeS = (TextView) findViewById(R.id.txvTime);
        setIt = new Intent(); //建立Intent物件
        setIt.setClass(Main3Activity.this, Main2Activity.class);//設定Class(在哪一個Activity啟動, 移至哪個class)

        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(setIt, 9);
                //startActivity(setIt); //啟動,無回傳資料
                //shorter-way: startActivity(new Intent(this, Main2Activity.class);
                overridePendingTransition(R.anim.in_translate,R.anim.out_translate); //amim
            }
        });

        show();

    }

    public void show() {
        //get data
        getIt = getIntent();
        int no = getIt.getIntExtra("Number", 0);
        String con = getIt.getStringExtra("tittle");
        String date = getIt.getStringExtra("date");
        String time = getIt.getStringExtra("time");

        //present
        number.setText(no+".");
        if(con.length()>3){
            content.setText(con.substring(3));
        }
        dateS.setText(date);
        timeS.setText(time);
    }

    //從Main2傳回資料 Result from Main2
    protected void onActivityResult(int requestCode, int resultCode, Intent it){
        if(requestCode == 9){
            if(resultCode == RESULT_OK){
                if(!it.getStringExtra("date").equals("Please click to set Date")) dateS.setText(it.getStringExtra("date"));
                if(!it.getStringExtra("time").equals("Please click to set Time")) timeS.setText(it.getStringExtra("time"));
            }
        }
    }

    //Cancel Button
    public void onCancel(View v){
        setResult(RESULT_CANCELED);
        finish();
    }

    //Save Button
    public void onSave(View view){
        Intent saveIt = new Intent();
        saveIt.putExtra("tittle", number.getText()+" "+content.getText());
        saveIt.putExtra("date", dateS.getText());
        saveIt.putExtra("time", timeS.getText());
        setResult(RESULT_OK, saveIt);
        finish();
    }

}
