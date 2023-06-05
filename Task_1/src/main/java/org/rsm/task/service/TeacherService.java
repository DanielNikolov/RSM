package org.rsm.task.service;

import org.rsm.task.contracts.IDataService;

import java.util.HashMap;
import java.util.Map;

public class TeacherService implements IDataService {
    @Override
    public Map<String, String> getInfoByStudentId(long studentId) {
        Map<String, String> result = new HashMap<String, String>();
        result.put("teacherEmail", "teacherEmail_" + studentId);
        result.put("teacherFirstName", "teacherFirstName_" + studentId);
        result.put("teacherLastName", "teacherLastName_" + studentId);
        return result;
    }

    @Override
    public String getVarByStudentId(long studentId, String varName) {
        return varName + "_" + studentId;
    }
}
