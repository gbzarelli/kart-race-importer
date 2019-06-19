package br.com.helpdev.race.application.importer.command;

import br.com.helpdev.race.shared.command.Command;

import java.time.LocalDate;

public class ImportRaceByDateCommand extends Command {

    private LocalDate localDate;

    public ImportRaceByDateCommand(LocalDate localDate) {
        this.localDate = localDate;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    @Override
    public String toString() {
        return "ImportRaceByDateCommand{" +
                "localDate=" + localDate +
                '}';
    }

}
