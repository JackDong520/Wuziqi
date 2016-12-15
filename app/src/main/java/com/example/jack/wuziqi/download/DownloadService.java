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

    public static final String DOWNLOAD_PATH =
            Environment.getExternalStorageDirectory().getAbsolutePath()+
                    "/download";

    public static final int MSG_INIT = 0;

    private DownloadTask mTask = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(ACTION_START.equals(intent.getAction())){
            FileInfo fileInfo = (FileInfo) intent.getSerializableExtra("fileInfo");
            Log.i("test" ,"start:"+fileInfo.toString());
            //启动初始化线程
            new InitThread(fileInfo).start();

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

    class  InitThread extends Thread{
        private FileInfo mFileInfo = null;

        public InitThread(FileInfo mFileInfo) {
            this.mFileInfo = mFileInfo;
        }

        @Override
        public void run() {
            super.run();
            HttpURLConnection conn =null;
            RandomAccessFile raf = null;

            try {
                //连接网络文件
                URL url = new URL(mFileInfo.getUrl());
                conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(3000);
                conn.setRequestMethod("GET");
                int length = -1 ;
                //获得文件的长度
                if(conn.getResponseCode() == 200)
                {
                    length = conn.getContentLength();
                }
            if(length<0){
                return;
            }

                File dir = new File(DOWNLOAD_PATH);
                if(!dir.exists()){
                    dir.mkdir();
                }
                File file = new File(dir , mFileInfo.getFileName());

                raf = new RandomAccessFile(file , "rwd");
                raf.setLength(length);

                mFileInfo.setLength(length);
                mHandler.obtainMessage(MSG_INIT , mFileInfo).sendToTarget();
                //在本地创建文件
                //设置文件长度
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                conn.disconnect();
                try {
                    raf.close();
                } catch (IOException e) {
                    e.printStackTrace();

                }


            }

        }
    }

}
