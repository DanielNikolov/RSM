package org.rsm.task;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        String testString = "Dear Mr [fatherLastName] and Mrs [motherLastName] your son [studentFirstName] is very smart boy and definitely the best in his class [className]";
        TemplateProcessor processor = new TemplateProcessor();
        System.out.println("Processed string: " + processor.inflateTemplate(testString, 1L));
        testString = "Dear Mr [fatherLastName] and Mrs [motherLastName] your son [studentFirstName] is very smart boy and definitely the best in his class [%className]";
        System.out.println("Processed string: " + processor.inflateTemplate(testString, 1L));
    }
}