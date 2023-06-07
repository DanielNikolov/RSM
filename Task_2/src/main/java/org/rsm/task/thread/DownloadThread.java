package org.rsm.task.thread;

import org.rsm.task.OperationsContainer;
import org.rsm.task.service.DownloadService;

import java.util.concurrent.CountDownLatch;

public class DownloadThread implements Runnable{
    private CountDownLatch countDownLatch;

    @Override
    public void run() {
        this.countDownLatch.countDown();
        DownloadService downloadService = new DownloadService();
        OperationsContainer.addFileData(downloadService.getDownloadInfos(1L));
    }

    public DownloadThread(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }
}
