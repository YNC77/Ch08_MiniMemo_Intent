package com.example.ync.ch08_minimemo_intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    Intent memoIt;
    String[] aMemo = {"1. click to edit it!","2. long-click to delete text.","3. "};
    ListView lv;
    ArrayAdapter<String> aa ; // aMemo和ListView的橋樑

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ListView
        lv = (ListView) findViewById(R.id.listView);
        aa = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, aMemo); //aa讀取aMemo字串資料
        lv.setAdapter(aa); //設定listView的adapter為aa
        lv.setOnItemClickListener(MyItemClick);
        lv.setOnItemLongClickListener(MyLongClick);

    }

    AdapterView.OnItemClickListener MyItemClick = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            memoIt = new Intent(MainActivity.this, Main3Activity.class); //connect memoIt and Main3Activity
            memoIt.putExtra("Number", position+1);
            memoIt.putExtra("tittle", aMemo[position]);
            startActivityForResult(memoIt, position); // 啟動,有回傳資料, 2nd parameter is RequestCode
        }
    };

    AdapterView.OnItemLongClickListener MyLongClick = new AdapterView.OnItemLongClickListener(){

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            aMemo[position] = (position+1)+". ";
            aa.notifyDataSetChanged(); //通知aa已修改內容
            return true; //回傳true表示長按事件已處理
        }
    };

    protected void onActivityResult(int requestCode, int resultCode, Intent it){
        if(resultCode == RESULT_OK){
            aMemo[requestCode] = it.getStringExtra("tittle");
            aa.notifyDataSetChanged();
        }
    }

}

