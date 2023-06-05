package org.rsm.task.service;

import org.rsm.task.contracts.IDataService;

import java.util.HashMap;
import java.util.Map;

public class StudentService implements IDataService {
    @Override
    public Map<String, String> getInfoByStudentId(long studentId) {
        Map<String, String> result = new HashMap<String, String>();
        result.put("studentFirstName", "studentFirstName_" + studentId);
        result.put("studentLastName", "studentLastName_" + studentId);
        result.put("gender", "gender_" + studentId);
        result.put("age", "age_" + studentId);
        return result;
    }

    @Override
    public String getVarByStudentId(long studentId, String varName) {
        return varName + "_" + studentId;
    }
}
