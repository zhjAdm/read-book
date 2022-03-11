package com.zhjAdm.action;

import com.intellij.openapi.actionSystem.*;
import com.zhjAdm.main.ReadBook;
import com.zhjAdm.util.NotificationUtil;

public class ReadNextLineAction extends AnAction {


    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        NotificationUtil.notificationInformation(ReadBook.nextSentence());
    }
}
