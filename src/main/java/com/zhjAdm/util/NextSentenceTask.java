package com.zhjAdm.util;

import com.zhjAdm.main.ReadBook;

public class NextSentenceTask implements Runnable {
    @Override
    public void run() {
        NotificationUtil.notificationInformation(ReadBook.nextSentence());
    }

} 