package org.rsm.task;

public class FileOperationStatus {
    private long uploadTime;
    private int uploadStatus;
    private int uploadSize;

    public String getName() {
        return name;
    }

    private String name;

    public String getKey() {
        return key;
    }

    private String key;

    public long getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(long uploadTime) {
        this.uploadTime = uploadTime;
    }

    public int getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(int uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    public int getUploadSize() {
        return uploadSize;
    }

    public void setUploadSize(int uploadSize) {
        this.uploadSize = uploadSize;
    }

    public FileOperationStatus(int fileSize, String fileKey, String name) {
        this.uploadStatus = 0;
        this.uploadSize = fileSize;
        this.uploadTime = 0;
        this.key = fileKey;
        this.name = name;
    }
}
