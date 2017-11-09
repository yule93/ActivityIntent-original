package com.jyheo.activityintent;

/**
 * Created by BaekByoungSoo on 2017. 11. 9..
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ActivityLifeCycle";
    private static final int request_code = 0;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //res/layout의 activity_first.xml 뷰에 세팅
        setContentView(R.layout.activity_first);

        mListView = (ListView)findViewById(R.id.listView);

        /* 아이템 추가 및 어댑터 등록 */
        dataSetting();

        Log.i(TAG, getLocalClassName() + ".onCreate");

        Button btn;

        btn = (Button)findViewById(R.id.buttonThirdActivity);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ThirdActivity의 "UseDefinedExtra"에 "Hello"를 싣고, 액티비티 실행
                Intent intent = new Intent(getApplicationContext(), ThirdActivity.class);
                intent.putExtra("UserDefinedExtra", "Hello");
                startActivityForResult(intent, request_code);
            }
        });
    }

    private void dataSetting(){

        MyAdapter mMyAdapter = new MyAdapter();

        for (int i=0; i<10; i++) {
            mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.pic1), "name_" + i, "contents_" + i);
        }

        mListView.setAdapter(mMyAdapter);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.new_menu ,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //호출된 activity서 넘어온 request_code가 이곳에서 넘겨준 request_code와 같은지 확인
        if (requestCode != request_code || data == null)
            return;
        String msg = data.getStringExtra("ResultString");
        Log.i(TAG, "ActivityResult:" + resultCode + " " + msg);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, getLocalClassName() + ".onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, getLocalClassName() + ".onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, getLocalClassName() + ".onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, getLocalClassName() + ".onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, getLocalClassName() + ".onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, getLocalClassName() + ".onDestroy");
    }
}
