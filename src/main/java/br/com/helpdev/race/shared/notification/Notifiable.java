package br.com.helpdev.race.shared.notification;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class Notifiable {

    private final Set<String> notifications = new HashSet<>();

    public void addNotification(String message) {
        notifications.add(message);
    }

    public void addNotifiable(Notifiable notifiable) {
        notifications.addAll(notifiable.getNotifications());
    }

    public boolean isValid() {
        return notifications.isEmpty();
    }

    public Set<String> getNotifications() {
        return Collections.unmodifiableSet(notifications);
    }
}
