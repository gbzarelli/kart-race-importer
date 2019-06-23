package br.com.helpdev.race.shared.dto;

import br.com.helpdev.race.shared.notification.Notifiable;

public abstract class OutputDTO implements Notifiable {

    public enum Status {
        SUCCESS, ERROR
    }

    private final Status status;

    public OutputDTO(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "OutputDTO{" +
                "status=" + status +
                '}';
    }
}
