package com.example.jack.wuziqi.download.download.db;

import com.example.jack.wuziqi.download.javabean.ThreadInfo;

import java.util.List;

/**
 * Created by 72408 on 2016/12/14.
 */

public interface ThreadDAO   {
    /**
     *
     * @param threadInfo
     */
    public void insertThread (ThreadInfo threadInfo);

    /**
     *
     * @param url
     * @param thread_id
     */
    public void deleteThread (String url , int thread_id);

    /**
     *
     * @param url
     * @param thread_id
     * @param finished
     */
    public void updateThread (String url , int thread_id , int finished);

    /**
     * 查询文件的线程信息
     * @param url
     * @return
     */
    public List<ThreadInfo>getThreads(String url);

    /**
     *
     * @param url
     * @param thread_id
     * @return
     */
    public boolean isExists(String url ,int thread_id);

}
