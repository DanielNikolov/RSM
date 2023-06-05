package org.rsm.task.thread;

import org.rsm.task.OperationsContainer;
import org.rsm.task.service.DownloadService;

public class DownloadThread implements Runnable{
    @Override
    public void run() {
        DownloadService downloadService = new DownloadService();
        OperationsContainer.addFileData(downloadService.getDownloadInfos(1L));
    }
}
