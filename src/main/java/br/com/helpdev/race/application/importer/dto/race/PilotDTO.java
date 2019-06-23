package br.com.helpdev.race.application.importer.dto.race;

public class PilotDTO {
    private int number;
    private String name;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PilotDTO{" +
                "number=" + number +
                ", name='" + name + '\'' +
                '}';
    }
}
