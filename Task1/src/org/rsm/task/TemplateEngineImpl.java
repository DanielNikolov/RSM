package org.rsm.task;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateEngineImpl extends TemplateEngine{
    @Override
    public String applyTemplate(String templateSource, VariableResolver resolver) {
        if (resolver == null) {
            return templateSource;
        }
        Map<String, String> placeHoldersMap = new HashMap<String, String>();
        String placeHolderRegEx = "\\[\\w+\\]";
        Pattern placeHolderPattern = Pattern.compile(placeHolderRegEx);
        Matcher placeHolderMatcher = placeHolderPattern.matcher(templateSource);
        String placeHolder;
        String placeHolderValue;
        while(placeHolderMatcher.find()) {
            try {
                placeHolder = placeHolderMatcher.group();
                placeHolder = placeHolder.substring(1, placeHolder.length() - 1);
                placeHolderValue = resolver.get(placeHolder);
                if (placeHolderValue != null && placeHolderValue.strip().length() > 0) {
                    placeHoldersMap.put(placeHolder, placeHolderValue.strip());
                }
            } catch (Exception e) {
                System.out.println("Error processing placeholders:" + e.getMessage());
            }
        }

        String inflatedTemplate = placeHoldersMap.entrySet().stream().reduce(templateSource, (s, e) -> s.replace("[" + e.getKey() + "]", e.getValue()),
                (s, s2) -> s);
        return inflatedTemplate;
    }
}
