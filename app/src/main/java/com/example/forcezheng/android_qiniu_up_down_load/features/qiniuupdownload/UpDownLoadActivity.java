package com.example.forcezheng.android_qiniu_up_down_load.features.qiniuupdownload;

import android.app.Activity;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import com.example.forcezheng.android_qiniu_up_down_load.R;
import com.example.forcezheng.android_qiniu_up_down_load.features.recorder.Mp3Recorder;
import com.example.forcezheng.android_qiniu_up_down_load.utils.DensityUtils;
import com.example.forcezheng.android_qiniu_up_down_load.view.RoundProgressBar;

import java.io.File;
import java.io.IOException;

/**
 * @author zhengwang
 * @email zhengwang043@foxmail.com
 * @date 2016/10/28
 */
public class UpDownLoadActivity extends Activity implements View.OnClickListener {
    private static final String SOUND_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/test.amr";
    private static final String TAG = "UpDownLoadActivity";
    private Button btnRecord;
    private Button btnUpload;
    private File soundFile;
    MediaRecorder mediaRecorder;
    private RoundProgressBar roundPb;
    int count = 0;

    private Chronometer time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_down_load);
        findView();
    }

    private void findView() {

        time = (Chronometer) findViewById(R.id.time);
        time.start();

        btnRecord = (Button) findViewById(R.id.btnRecord);
        btnRecord.setOnClickListener(this);
        btnUpload = (Button) findViewById(R.id.btnUpLoad);
        btnUpload.setOnClickListener(this);
        roundPb = (RoundProgressBar) findViewById(R.id.roundPb);
        roundPb.setRoundWidth(DensityUtils.dp2px(this, (15 / 2)));
//        roundPb.setMax((float) (2 * roundPb.getRadius() * Math.PI));
//        final Timer timer = new Timer(true);
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                if (count == 1200) {
////                    roundPb.refresh(true);
//                    long base = time.getBase();
//                    Log.i(TAG, count + "--------------------"+base);
//                    timer.cancel();
//                    time.stop();
//
//                } else {
//                    roundPb.refresh();
//                    Log.i(TAG, count + "--------");
//                }
//                count += 1;
//            }
//        }, 100, 100);
    }

    @Override
    public void onClick(View v) {
        Mp3Recorder recorder = new Mp3Recorder();
        switch (v.getId()) {
            case R.id.btnRecord:
//                MediaRecorderOP.getInstance().createMediaRecord();
//                recorder();
                try {
                    recorder.startRecording();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.btnUpLoad:
                try {
                    recorder.stopRecording();
                } catch(IOException e) {
                    Log.d("MainActivity", "Stop error");
                }
                break;
        }
    }

    private void recorder() {

        // 创建录制音频的对象
        mediaRecorder = new MediaRecorder();
        // 设置录音的声音来源，一般来自于麦克风
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        // 设置录制的声音输出格式（必须在设置声音编码格式之前设置）
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        // 设置声音编码格式
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        // 设置声音的输出路径
        mediaRecorder.setOutputFile(SOUND_PATH);
        // 准备录音
        try {
            mediaRecorder.prepare();
            // 开始录音
            mediaRecorder.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

//    private class TimeHandler extends Handler {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            if (msg.what == 1) {
//                roundPb.setProgress((roundPb.getProgress() + 1));
//
//            }
//        }
//    }
}
