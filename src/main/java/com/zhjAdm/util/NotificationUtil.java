package com.zhjAdm.util;

import com.intellij.notification.*;

public class NotificationUtil {
    public static void notificationInformation(String content){
        NotificationGroup notificationGroup = new NotificationGroup("read-book", NotificationDisplayType.NONE, true);
        Notification notification = notificationGroup.createNotification(content, NotificationType.INFORMATION);
        Notifications.Bus.notify(notification);
    }

    public static void error(String s) {
        NotificationGroup notificationGroup = new NotificationGroup("read-book", NotificationDisplayType.NONE, true);
        Notification notification = notificationGroup.createNotification(s, NotificationType.INFORMATION);
        Notifications.Bus.notify(notification);
    }
}
