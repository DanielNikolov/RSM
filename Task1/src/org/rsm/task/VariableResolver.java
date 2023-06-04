package org.rsm.task;

import org.rsm.task.contracts.IDataService;
import org.rsm.task.contracts.IVariableResolver;
import org.rsm.task.service.*;

import java.util.HashMap;
import java.util.Map;

public class VariableResolver implements IVariableResolver {
    private static Map<Long, Map<String, String>> placeHolderCache = new HashMap<Long, Map<String, String>>();

    private IDataService familyService;
    private IDataService studentService;
    private IDataService schoolService;
    private IDataService teacherService;
    private IDataService classService;
    private Long studentId;

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public VariableResolver() {
        super();
        this.classService = new ClassService();
        this.familyService = new FamilyService();
        this.schoolService = new SchoolService();
        this.studentService = new StudentService();
        this.teacherService = new TeacherService();
    }

    /**
     * Updates cache with new date retrieved from service
     * @param serviceData
     */
    private void updateCache(Map<String, String> serviceData) {
        if (this.studentId == null || serviceData == null || serviceData.isEmpty()) {
            return;
        }
        Map<String, String> studentCache = placeHolderCache.get(this.studentId);
        if (studentCache == null) {
            studentCache = new HashMap<String, String>();
        }
        for (Map.Entry<String, String> dataEntry : serviceData.entrySet()) {
            studentCache.put(dataEntry.getKey(), dataEntry.getValue());
        }
        placeHolderCache.put(this.studentId, studentCache);
    }

    /**
     * Get variable value from data cache per student id
     * @param varName
     * @return variable value or null
     */
    private String getDataFromCache(String varName) {
        System.out.println("Get data from cache. Variable name: " + varName);
        String result = null;
        if (this.studentId == null || varName == null) {
            return result;
        }
        Map<String, String> studentData = VariableResolver.placeHolderCache.get(this.studentId);
        result = studentData == null || studentData.isEmpty() ? null : studentData.get(varName);
        return result;
    }

    /**
     * Get data from service based on variable name
     * @param varName
     * @return Set of student related data
     */
    private Map<String, String> getDataFromService(String varName) {
        System.out.println("Get data from service. Variable name: " + varName);
        return switch (varName) {
            case "fatherFirstName", "fatherLastName", "motherFirstName", "motherLastName",
                "numberOfSiblingsEnrolled" -> this.familyService.getInfoByStudentId(this.studentId);
            case "studentFirstName", "studentLastName", "gender", "age" -> this.studentService.getInfoByStudentId(this.studentId);
            case "className", "classLevel", "classGrade", "classDayOfWeek", "classRoomNumber" -> this.classService.getInfoByStudentId(this.studentId);
            case "teacherFirstName", "teacherLastName", "teacherEmail" -> this.teacherService.getInfoByStudentId(this.studentId);
            case "schoolName", "schoolAddress", "schoolEmail" -> this.schoolService.getInfoByStudentId(this.studentId);
            default -> new HashMap<String, String>();
        };
    }

    @Override
    public String get(String varName) {
        String varData = getDataFromCache(varName);
        if (varData != null) {
            return varData;
        }
        Map<String, String> serviceData = getDataFromService(varName);
        if (serviceData != null && !serviceData.isEmpty()) {
            this.updateCache(serviceData);
            varData = serviceData.get(varName);
        }
        return varData != null ? varData : "";
    }
}
