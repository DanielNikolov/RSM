package org.rsm.task.service;

import org.rsm.task.impl.DownloadInfoImpl;
import org.rsm.task.intf.DownloadInfo;

import java.util.ArrayList;
import java.util.List;

public class DownloadService {

    /**
     * Get dummy filtered list of DownloadInfo object
     * @param packageId
     * @return
     */
    public List<DownloadInfo> getDownloadInfos(long packageId) {
        List<DownloadInfo> result = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            result.add(new DownloadInfoImpl());
        }
        result.removeIf(s -> s.getOriginalFileName().endsWith(".cmd") ||
                s.getOriginalFileName().endsWith(".com") ||
                s.getOriginalFileName().endsWith(".dll") ||
                s.getOriginalFileName().endsWith(".dmg") ||
                s.getOriginalFileName().endsWith(".exe") ||
                s.getOriginalFileName().endsWith(".iso") ||
                s.getOriginalFileName().endsWith(".jar") ||
                s.getOriginalFileName().endsWith(".js")
        );
        return result;
    }
}
