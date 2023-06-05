package org.rsm.task.service;

import org.rsm.task.contracts.IDataService;

import java.util.HashMap;
import java.util.Map;

public class SchoolService implements IDataService {
    @Override
    public Map<String, String> getInfoByStudentId(long studentId) {
        Map<String, String> result = new HashMap<String, String>();
        result.put("schoolAddress", "schoolAddress_" + studentId);
        result.put("schoolEmail", "schoolEmail_" + studentId);
        result.put("schoolName", "schoolName_" + studentId);
        return result;
    }

    @Override
    public String getVarByStudentId(long studentId, String varName) {
        return varName + "_" + studentId;
    }
}
