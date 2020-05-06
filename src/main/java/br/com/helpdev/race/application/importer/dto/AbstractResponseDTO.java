package br.com.helpdev.race.application.importer.dto;

import br.com.helpdev.race.commons.objects.Notifiable;

public abstract class AbstractResponseDTO extends Notifiable {

    public enum Status {
        SUCCESS, ERROR
    }

    private final Status status;

    public AbstractResponseDTO(final Status status) {
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
