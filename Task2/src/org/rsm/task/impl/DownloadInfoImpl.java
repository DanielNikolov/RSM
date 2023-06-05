package org.rsm.task.impl;

import org.rsm.task.intf.DownloadInfo;

import java.util.Date;
import java.util.Random;

public class DownloadInfoImpl implements DownloadInfo {
    /**
     * Get file size in bytes
     * @return
     */
    @Override
    public int getSize() {
        Random random = new Random();
        return random.nextInt(1000000, 2000000);
    }

    /**
     * Get dummy file name
     * @return
     */
    @Override
    public String getOriginalFileName() {
        long timeStamp = new Date().getTime();
        if (timeStamp % 3 == 0) {
            return "TestFile" + timeStamp + ".cmd";
        }
        return "TestFile" + timeStamp + ".txt";
    }

    /**
     * Get dummy file key
     * @return
     */
    @Override
    public String getFileKey() {
        long timeStamp = new Date().getTime();
        return "FileKey_" + timeStamp;
    }

    /**
     * Get dummy download url
     * @return
     */
    @Override
    public String getDownloadURL() {
        return "sftp://path/file/rsm";
    }
}
