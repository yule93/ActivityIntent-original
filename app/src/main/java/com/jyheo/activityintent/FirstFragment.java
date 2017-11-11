package com.jyheo.activityintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by BaekByoungSoo on 2017. 11. 11..
 */

public class FirstFragment extends Fragment {

    private static final String TAG = "ActivityLifeCycle";

    private static final int request_code = 0;

    private ListView mListView;

    View view;
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        
        view = inflater.inflate(R.layout.fragment_first,container,false);
        
        return view;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //res/layout의 activity_first.xml 뷰에 세팅
        
//        setContentView(R.layout.activity_first);

        mListView = (ListView) view.findViewById(R.id.listView);

        /* 아이템 추가 및 어댑터 등록 */
        dataSetting();

        Log.i(TAG, this.getClass().getName() + ".onCreate");

        Button btn;

        btn = (Button) view.findViewById(R.id.buttonThirdActivity);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ThirdActivity의 "UseDefinedExtra"에 "Hello"를 싣고, 액티비티 실행
                Intent intent = new Intent(getContext(), ThirdActivity.class);
                intent.putExtra("UserDefinedExtra", "Hello");
                startActivityForResult(intent, request_code);
            }
        });
    }

    private void dataSetting(){

        MyAdapter mMyAdapter = new MyAdapter();

        for (int i=0; i<10; i++) {
            mMyAdapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.pic1), "name_" + i, "contents_" + i);
        }

        mListView.setAdapter(mMyAdapter);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.new_menu ,menu);
        return getActivity().onCreateOptionsMenu(menu);
    }


    /*
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
    void onStart() {
        onStart();
        Log.i(TAG, this.getClass().getName() + ".onStart");
    }

    @Override
    protected void onRestart() {
        onRestart();
        Log.i(TAG, this.getClass().getName() + ".onRestart");
    }

    @Override
    protected void onResume() {
        onResume();
        Log.i(TAG, this.getClass().getName() + ".onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, this.getClass().getName() + ".onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, this.getClass().getName() + ".onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, this.getClass().getName() + ".onDestroy");
    }*/
}
