package ru.pimalex1978.refactoring.solution2;

import java.util.HashMap;
import java.util.Map;

public class TemplateHandler {
    public Map<Integer, MailTemplate> templates;
    
    public TemplateHandler() {
        templates = new HashMap<Integer, MailTemplate>();
        templates.put(0, new BusinessMailTemplate());
        templates.put(1, new BusinessMailTemplate());
    }

    public void addTemplate(int templateCode, MailTemplate mailGenerator) {
        templates.put(templateCode, mailGenerator);
    }

    public MailTemplate getTemplate(int templateCode) {
        return templates.get(templateCode);
    }
}
