package com.example.ync.ch08_minimemo_intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    TextView number, dt;
    EditText content;
    Intent getIt, setIt;
    Button settingT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        number = (TextView) findViewById(R.id.txvNo);
        content = (EditText) findViewById(R.id.edtContent);
        show();

        //Setting button
        settingT = (Button) findViewById(R.id.btnSetting);
        settingT.setOnClickListener(MySetClick); //設定settingT的監聽器(MyListener) <<BUG
        setIt = new Intent(); //建立Intent物件
        setIt.setClass(Main3Activity.this, Main2Activity.class);//設定Class(在哪一個Activity啟動, 移至哪個class)

        dt = (TextView) findViewById(R.id.txvDT);
    }

    public void show() {
        //get data
        getIt = getIntent();
        int no = getIt.getIntExtra("Number", 0);
        String con = getIt.getStringExtra("tittle");

        //present
        number.setText(no+".");
        if(con.length()>3){
            content.setText(con.substring(3));
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
        setResult(RESULT_OK, saveIt);
        finish();
    }

    View.OnClickListener MySetClick = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            startActivityForResult(setIt, 123);
            //startActivity(setIt); //啟動,無回傳資料
            //shorter-way: startActivity(new Intent(this, Main2Activity.class);
            overridePendingTransition(R.anim.in_translate,R.anim.out_translate); //amim
        }
    };

    protected void onActivityResult(int requestCode, int resultCode, Intent it){
        if(requestCode == 123){
            if(resultCode == RESULT_OK){
                dt.setText(it.getStringExtra("date")+"/n"+it.getStringExtra("time"));
            }
        }
    }

    /*
    public void setClick(View v){
        setIt = new Intent(); //建立Intent物件
        setIt.setClass(this, Main2Activity.class);//設定Class(在哪一個Activity啟動, 移至哪個class)
        startActivityForResult(setIt, 123);
        overridePendingTransition(R.anim.in_translate,R.anim.out_translate); //amim
    }*/
}
