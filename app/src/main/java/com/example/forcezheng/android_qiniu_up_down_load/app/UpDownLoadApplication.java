package com.example.forcezheng.android_qiniu_up_down_load.app;

import android.app.Application;
import android.content.Context;

import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONObject;

/**
 * @author zhengwang
 * @email zhengwang043@foxmail.com
 * @date 2016/10/28
 */
public class UpDownLoadApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        initQiniuUploadManager();
    }

    /**
     *
     */
    private void initQiniuUploadManager() {
        String token = "从服务端SDK获取";
        UploadManager uploadManager = new UploadManager();
        uploadManager.put("Hello, World!".getBytes(), "hello", token,
                new UpCompletionHandler() {
                    @Override
                    public void complete(String key, ResponseInfo info, JSONObject response) {
//                        Log.i("qiniu", info);
                    }
                }, null);
    }

    public static Context getContext() {
        return getContext();
    }
}
