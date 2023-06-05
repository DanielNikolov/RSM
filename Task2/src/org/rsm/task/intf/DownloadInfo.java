package org.rsm.task.intf;

public interface DownloadInfo {
    public int getSize();
    public String getOriginalFileName();
    public String getFileKey();
    public String getDownloadURL();
}
