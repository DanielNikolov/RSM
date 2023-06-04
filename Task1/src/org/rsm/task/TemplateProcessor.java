package org.rsm.task;

public class TemplateProcessor {

    public String inflateTemplate(String templateSource, long studentId) {
        VariableResolver resolver = new VariableResolver();
        resolver.setStudentId(studentId);
        TemplateEngine templateEngine = new TemplateEngineImpl();
        return templateEngine.applyTemplate(templateSource, resolver);
    }
}
