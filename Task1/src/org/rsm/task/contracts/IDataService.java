package org.rsm.task.contracts;

import java.util.Map;

public interface IDataService {

    public Map<String,String> getInfoByStudentId(long studentId);
    public String getVarByStudentId(long studentId, String varName);
}
