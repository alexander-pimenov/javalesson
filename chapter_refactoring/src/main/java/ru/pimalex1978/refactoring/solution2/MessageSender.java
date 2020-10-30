package ru.pimalex1978.refactoring.solution2;

import ru.pimalex1978.refactoring.util.MessageSenderUtil;

public class MessageSender {
    private void sendMessage() {
        int templateCode = MessageSenderUtil.getTemplateTypeCodeFromDB();

        TemplateHandler templateHandler = new TemplateHandler();
        String templateAsString = templateHandler.getTemplate(templateCode).generateTemplate();

        MessageSenderUtil.sendMessage(templateAsString);
    }
}
