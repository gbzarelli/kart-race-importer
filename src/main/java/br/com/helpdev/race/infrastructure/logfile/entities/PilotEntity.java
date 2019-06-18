package br.com.helpdev.race.infrastructure.logfile.entities;

public class PilotEntity {
    private int number;
    private String name;

    public PilotEntity(int number, String name) {
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
