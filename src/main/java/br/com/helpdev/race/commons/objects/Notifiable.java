package br.com.helpdev.race.commons.objects;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class Notifiable {

    private final Set<String> notifications = new HashSet<>();

    public void addNotification(final String message) {
        notifications.add(message);
    }

    public void addNotifiable(final Notifiable notifiable) {
        notifications.addAll(notifiable.getNotifications());
    }

    public boolean isValid() {
        return notifications.isEmpty();
    }

    public Set<String> getNotifications() {
        return Collections.unmodifiableSet(notifications);
    }
}
