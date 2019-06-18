package br.com.helpdev.race.domain.race;

public abstract class Pilot {

    private int number;
    private String name;

    Pilot(int number, String name) {
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
        return "Pilot{" +
                "number=" + number +
                ", name='" + name + '\'' +
                '}';
    }
}
