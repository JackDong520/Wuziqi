package com.example.jack.wuziqi.download.download.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jack.wuziqi.download.javabean.ThreadInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据访问接口实现
 * Created by 72408 on 2016/12/14.
 */

public class ThreadDAOImpl implements ThreadDAO {

    private DBHelper mHelper = null;

    public ThreadDAOImpl(Context context){
        mHelper = new DBHelper(context);

    }
    @Override
    public void insertThread(ThreadInfo threadInfo) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL("insert into thread_info(thread_id , url ,start , end ,finished)values(?,?,?,?,?)" ,
                new Object[]{threadInfo.getId() , threadInfo.getUrl() , threadInfo.getStart() , threadInfo.getEnd()
                ,threadInfo.getFinished()});
        db.close();
    }

    @Override
    public void deleteThread(String url, int thread_id) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL(
                "delete from thread_info where url =? and thread_id = ?",
                new Object[]{url , thread_id});
        db.close();

    }

    @Override
    public void updateThread(String url, int thread_id, int finished) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL(
                "update thread_info set finished =? where url =? and thread_id =?",
                new Object[]{ finished,url , thread_id});
        db.close();

    }

    @Override
    public List<ThreadInfo> getThreads(String url) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        List<ThreadInfo>list = new ArrayList<ThreadInfo>();
        Cursor cursor =
                db.rawQuery("select * from thread_info where url =?",
                new String[]{url});
        while(cursor.moveToNext()){
            ThreadInfo thread = new ThreadInfo();
            thread.setId(cursor.getInt(cursor.getColumnIndex("thread_id")));
            thread.setStart(cursor.getInt(cursor.getColumnIndex("start")));
            thread.setEnd(cursor.getInt(cursor.getColumnIndex("end")));
            thread.setFinished(cursor.getInt(cursor.getColumnIndex("finished")));
            thread.setUrl(cursor.getString(cursor.getColumnIndex("url")));
            list.add(thread);
        }
        cursor.close();
        db.close();
        return list;
    }

    @Override
    public boolean isExists(String url, int thread_id) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        List<ThreadInfo>list = new ArrayList<ThreadInfo>();
        Cursor cursor =
                db.rawQuery("select * from thread_info where url =? and thread_id =?",
                        new String[]{url , thread_id+""});
        boolean exists = cursor.moveToNext();
        cursor.close();
        db.close();
        return exists;
    }
}
