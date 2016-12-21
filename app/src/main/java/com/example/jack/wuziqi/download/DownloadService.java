package com.example.jack.wuziqi.download;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.jack.wuziqi.download.download.db.DownloadTask;
import com.example.jack.wuziqi.download.javabean.FileInfo;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by 72408 on 2016/12/14.
 */

public class DownloadService extends Service {
    @Nullable
    public static final String ACTION_START ="ACTION_START";
    public static final String ACTION_STOP ="ACTION_STOP";
    public static final String ACTION_UPDATE ="ACTION_UPDATE";
    public static final int MSG_INIT = 0;
    private DownloadTask mTask = null;

    public static final String DOWNLOAD_PATH =
            Environment.getExternalStorageDirectory().getAbsolutePath()+
                    "/download";
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MSG_INIT:
                    FileInfo fileInfo = (FileInfo) msg.obj;
                    Log.i("test" , "Init" +fileInfo);

                    //启动下载任务
                    mTask = new DownloadTask(DownloadService.this,fileInfo);
                    mTask.download();
                    break;
            }
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(ACTION_START.equals(intent.getAction())){
            FileInfo fileInfo = (FileInfo) intent.getSerializableExtra("fileInfo");
            Log.i("test" ,"start:"+fileInfo.toString());
            //启动初始化线程
            new InitThread(fileInfo , DOWNLOAD_PATH,mHandler).start();

        } else if (ACTION_STOP.equals(intent.getAction())) {
            FileInfo fileInfo = (FileInfo) intent.getSerializableExtra("fileInfo");
            Log.i("test" ,"dtop:"+fileInfo.toString());
            if(mTask!=null){
                mTask.isPause = true;
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

}
