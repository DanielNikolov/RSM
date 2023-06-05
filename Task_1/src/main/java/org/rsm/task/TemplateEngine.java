package org.rsm.task;

public abstract class TemplateEngine {
    public abstract String applyTemplate(String templateSource, VariableResolver resolver);
}
