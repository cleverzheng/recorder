package com.example.forcezheng.android_qiniu_up_down_load;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.forcezheng.android_qiniu_up_down_load.features.qiniuupdownload.UpDownLoadActivity;

public class MainActivity extends Activity {

    private Button btnRecorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRecorder = (Button) findViewById(R.id.btnRecorder);
        btnRecorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UpDownLoadActivity.class);
                startActivity(intent);
            }
        });
    }
}
