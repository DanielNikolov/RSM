package org.rsm.task.service;

import org.rsm.task.contracts.IDataService;

import java.util.HashMap;
import java.util.Map;

public class FamilyService implements IDataService {
    @Override
    public Map<String, String> getInfoByStudentId(long studentId) {
        Map<String, String> result = new HashMap<String, String>();
        result.put("fatherFirstName", "fatherFirstName_" + studentId);
        result.put("fatherLastName", "fatherLastName_" + studentId);
        result.put("motherFirstName", "motherFirstName_" + studentId);
        result.put("motherLastName", "motherLastName_" + studentId);
        result.put("numberOfSiblingsEnrolled", "numberOfSiblingsEnrolled_" + studentId);
        return result;
    }

    @Override
    public String getVarByStudentId(long studentId, String varName) {
        return varName + "_" + studentId;
    }
}
