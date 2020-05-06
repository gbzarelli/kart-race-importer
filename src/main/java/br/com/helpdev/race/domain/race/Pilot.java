package br.com.helpdev.race.domain.race;

import java.util.Objects;

public class Pilot {

    private final PilotId pilotId;
    private final String name;

    public Pilot(final PilotId pilotId,
                 final String name) {
        this.pilotId = pilotId;
        this.name = name;
    }

    public ToRace subscribe() {
        return race -> {
            PilotRace pr = new PilotRace(race.getRaceId(), this);
            race.addPilot(pr);
            return pr;
        };
    }

    @FunctionalInterface
    public interface ToRace {
        PilotRace toRace(Race race);
    }

    public PilotId getPilotId() {
        return pilotId;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Pilot && ((Pilot) obj).pilotId.equals(pilotId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pilotId, name);
    }

    @Override
    public String toString() {
        return "Pilot{" +
                "pilotId=" + pilotId +
                ", name='" + name + '\'' +
                '}';
    }
}
