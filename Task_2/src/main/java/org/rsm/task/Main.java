package org.rsm.task;

import org.rsm.task.thread.DownloadThread;
import org.rsm.task.thread.UploadThread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int count = 5;
        int threadSize = 2;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(threadSize);
        for (int i = 0; i < count; i++) {
            threadPoolExecutor.execute(new DownloadThread(countDownLatch));
            threadPoolExecutor.execute(new UploadThread(countDownLatch));
        }
        countDownLatch.await();
        OperationsContainer.printReport();
        threadPoolExecutor.shutdown();


    }
}