package com.cnpc.gateway.utils;

import com.cnpc.common.vo.LogInfo;
import com.cnpc.gateway.feign.LogService;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 操作日志持久化
 *
 * @author billjiang 475572229@qq.com
 * @create 17-9-21
 */
@Slf4j
public class DBLog extends Thread {

    private static DBLog dbLog = null;
    private static BlockingQueue<LogInfo> logInfoQueue = new LinkedBlockingDeque<>(1024);

    private LogService logService;

    public LogService getLogService() {
        return logService;
    }

    public DBLog setLogService(LogService logService) {
        if (this.logService == null)
            this.logService = logService;
        return this;
    }

    private DBLog() {
        super("dblog thread");
    }

    public static synchronized DBLog getInstance() {
        if (dbLog == null)
            dbLog = new DBLog();
        return dbLog;
    }

    public void offerQueue(LogInfo logInfo) {
        logInfoQueue.offer(logInfo);
    }

    public void run() {
        List<LogInfo> bufferedLogList = new ArrayList<>();
        while (true) {
            try {
                bufferedLogList.add(logInfoQueue.take());
                logInfoQueue.drainTo(bufferedLogList);
                if (!bufferedLogList.isEmpty()) {
                    for (LogInfo logInfo : bufferedLogList) {
                        logService.saveLog(logInfo);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                try {
                    Thread.sleep(1000);
                } catch (Exception ee) {

                }
            } finally {
                if(!bufferedLogList.isEmpty()){
                    bufferedLogList.clear();
                }
            }
        }

    }


}
