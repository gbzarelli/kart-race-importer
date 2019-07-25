package br.com.helpdev.race.domain.race;

import java.util.Objects;
import java.util.UUID;

public class RaceId {
    private String id;

    private RaceId(String id) {
        this.id = id;
    }

    static RaceId getNewRaceID() {
        return new RaceId(UUID.randomUUID().toString());
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof RaceId && ((RaceId) obj).id.equals(this.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id;
    }
}
