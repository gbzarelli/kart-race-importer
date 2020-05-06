package br.com.helpdev.race.domain.race;

import java.util.Objects;

public class PilotId {
    private final int number;

    public PilotId(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public boolean equals(Object obj) {
        return obj instanceof PilotId && ((PilotId) obj).number == number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
