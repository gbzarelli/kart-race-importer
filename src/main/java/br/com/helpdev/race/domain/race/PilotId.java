package br.com.helpdev.race.domain.race;

public class PilotId {
    private int number;

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
    public String toString() {
        return String.valueOf(number);
    }
}
