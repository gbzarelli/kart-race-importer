package br.com.helpdev.race.domain.race;

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
}
