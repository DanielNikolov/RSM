package org.rsm.task.service;

import org.rsm.task.contracts.IDataService;

import java.util.HashMap;
import java.util.Map;

public class ClassService implements IDataService {
    @Override
    public Map<String, String> getInfoByStudentId(long studentId) {
        Map<String, String> result = new HashMap<String, String>();
        result.put("classDayOfWeek", "classDayOfWeek_" + studentId);
        result.put("classGrade", "classGrade_" + studentId);
        result.put("classLevel", "classLevel_" + studentId);
        result.put("className", "className_" + studentId);
        result.put("classRoomNumber", "classRoomNumber_" + studentId);
        return result;
    }

    @Override
    public String getVarByStudentId(long studentId, String varName) {
        return varName + "_" + studentId;
    }
}
