package org.rsm.task;

import org.rsm.task.intf.DownloadInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OperationsContainer {
    private static Map<String, FileOperationStatus> fileTrackingMap = new HashMap<>();

    public synchronized static void addFileData(List<DownloadInfo> downloadInfoList) {
        for (DownloadInfo downloadInfo : downloadInfoList) {
            fileTrackingMap.put(downloadInfo.getFileKey(),
                    new FileOperationStatus(downloadInfo.getSize(), downloadInfo.getFileKey(), downloadInfo.getOriginalFileName()));
        }
    }

    public synchronized static int getTotalUploadedSize() {
        int result = 0;
        for (Map.Entry<String, FileOperationStatus> uploadStatus : fileTrackingMap.entrySet()) {
            if (uploadStatus.getValue().getUploadStatus() > 0) {
                result += uploadStatus.getValue().getUploadSize();
            }
        }
        return result;
    }

    public synchronized static FileOperationStatus getFileToUpload() {
        FileOperationStatus result = null;
        for (Map.Entry<String, FileOperationStatus> uploadStatus : fileTrackingMap.entrySet()) {
            if (uploadStatus.getValue().getUploadStatus() < 0) {
                result = uploadStatus.getValue();
                break;
            }
        }
        return result;
    }

    public synchronized static void updateFileUploadData(String key, FileOperationStatus operationStatus) {
        fileTrackingMap.put(key, operationStatus);
    }

    public synchronized static void printReport() {
        long totalTime = 0L;
        int successCount = 0;
        int failCount = 0;
        for (Map.Entry<String, FileOperationStatus> fileTrackingInfo : fileTrackingMap.entrySet()) {
            FileOperationStatus fileData = fileTrackingInfo.getValue();
            System.out.println("File Key: " + fileData.getName() +
                    " Upload time (ms): " + fileData.getUploadTime() +
                    " Upload status: " + (fileData.getUploadStatus() > 0 ? "success" : "fail"));
            totalTime += fileData.getUploadTime();
            if (fileData.getUploadStatus() > 0) {
                successCount++;
            } else {
                failCount++;
            }
        }
        System.out.println("Total time (ms): " + totalTime + " Success count: " + successCount + " Fail count: " + failCount);
    }
}
