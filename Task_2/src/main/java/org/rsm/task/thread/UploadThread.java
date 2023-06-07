package org.rsm.task.thread;

import org.rsm.task.FileOperationStatus;
import org.rsm.task.OperationsContainer;
import org.rsm.task.service.UploadService;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

public class UploadThread implements Runnable{
    private CountDownLatch countDownLatch;

    @Override
    public void run() {
        this.countDownLatch.countDown();
        int maxFileSize = 100 * 1024 * 1024;
        int totalFileUploaded = OperationsContainer.getTotalUploadedSize();
        FileOperationStatus fileToUpload = OperationsContainer.getFileToUpload();
        if (fileToUpload != null) {
            if (totalFileUploaded + fileToUpload.getUploadSize() <= maxFileSize) {
                UploadService uploadService = new UploadService();
                long startTime = new Date().getTime();
                uploadService.doUpload(fileToUpload.getKey(), null, fileToUpload.getUploadSize());
                long difference = new Date().getTime() - startTime;
                fileToUpload.setUploadTime(difference);
                fileToUpload.setUploadStatus(difference % 3 == 0 ? 1 : 0);
                OperationsContainer.updateFileUploadData(fileToUpload.getKey(), fileToUpload);
            }
        }
    }

    public UploadThread(CountDownLatch latch) {
        this.countDownLatch = latch;
    }
}
