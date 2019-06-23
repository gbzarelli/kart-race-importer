package br.com.helpdev.race.shared.notification;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public interface Notifiable {

    Set<String> notifications = new HashSet<>();

    default void addNotification(String message) {
        notifications.add(message);
    }

    default void addNotifiable(Notifiable notifiable) {
        notifications.addAll(notifiable.getNotifications());
    }

    default boolean isValid() {
        return notifications.isEmpty();
    }

    default Set<String> getNotifications() {
        return Collections.unmodifiableSet(notifications);
    }
}
