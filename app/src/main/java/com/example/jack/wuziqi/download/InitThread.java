package com.example.jack.wuziqi.download;

import android.os.Handler;
import android.os.Message;
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
 * Created by 72408 on 2016/12/21.
 */

class  InitThread extends Thread{

    private FileInfo mFileInfo = null;
    private String DOWNLOAD_PATH ;
    private Handler mHandler;

    public static final int MSG_INIT = 0;

    public InitThread(FileInfo mFileInfo , String DOWNLOAD_PATH ,Handler mHandler) {
        this.mFileInfo = mFileInfo;
        this.DOWNLOAD_PATH = DOWNLOAD_PATH;
        this.mHandler = mHandler;

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