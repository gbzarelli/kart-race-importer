package br.com.helpdev.race.infrastructure.logfile.entities;

public class PilotEntity {
    private final int number;
    private final String name;

    public PilotEntity(final int number,
                       final String name) {
        this.number = number;
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "PilotEntity{" +
                "number=" + number +
                ", name='" + name + '\'' +
                '}';
    }
}
