package org.rsm.task;

import org.rsm.task.thread.DownloadThread;
import org.rsm.task.thread.UploadThread;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {
    public static void main(String[] args) {
        int threadSize = 2;
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(threadSize);
        while (threadPoolExecutor.getTaskCount() < threadSize) {
            threadPoolExecutor.execute(new DownloadThread());
            threadPoolExecutor.execute(new UploadThread());
        }

        OperationsContainer.printReport();
    }
}