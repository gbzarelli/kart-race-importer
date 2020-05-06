![Issues](https://img.shields.io/github/issues/gbzarelli/kart-race-importer.svg) ![Forks](https://img.shields.io/github/forks/gbzarelli/kart-race-importer.svg) ![Stars](https://img.shields.io/github/stars/gbzarelli/kart-race-importer.svg) ![Release Version](https://img.shields.io/github/release/gbzarelli/kart-race-importer.svg)

# Kart Race Importer [![CircleCI](https://circleci.com/gh/gbzarelli/kart-race-importer.svg?style=svg)](https://circleci.com/gh/gbzarelli/kart-race-importer)

<p align="center">
    <img src="./images/kart-logo.png" height="450">
</p>

The 'Kart Race Importer' is a project to import data held
in log files. These files contain information about a race
of kart, kept in a structural format of 'laps', where each lap
contains pilot information, time, lap and speed. The idea of the 
project is to transcribe this information into complex data and 
statistical information about a race. Java has been used as the 
programming language with as few external dependencies as possible.

## Data for import

The data folder to be imported is configured in the
`application.properties` that is stored in the project resources folder
`(src / main / resources)`, every Race file must follow a pattern
name so that the import recognizes the files, starting with year, month
and day all together.

Here's an example of a file to import:

```text
Hour                               Pilot               No. Lap  Lap Time             Average lap speed
23:49:08.277      038 – F.MASSA                           1		1:02.852                        44,275
23:49:10.858      033 – R.BARRICHELLO                     1		1:04.352                        43,243
23:49:11.075      002 – K.RAIKKONEN                       1		1:04.108                        43,408
23:49:12.667      023 – M.WEBBER                          1		1:04.414                        43,202
23:49:30.976      015 – F.ALONSO                          1		1:18.456                        35,47
23:50:11.447      038 – F.MASSA                           2		1:03.170                        44,053
23:50:14.860      033 – R.BARRICHELLO                     2		1:04.002                        43,48
23:50:15.057      002 – K.RAIKKONEN                       2		1:03.982                        43,493
23:50:17.472      023 – M.WEBBER                          2		1:04.805                        42,941
23:50:37.987      015 – F.ALONSO                          2		1:07.011                        41,528
23:51:14.216      038 – F.MASSA                           3		1:02.769                        44,334
23:51:18.576      033 – R.BARRICHELLO                     3		1:03.716                        43,675
23:51:19.044      002 – K.RAIKKONEN                       3		1:03.987                        43,49
23:51:21.759      023 – M.WEBBER                          3		1:04.287                        43,287
23:51:46.691      015 – F.ALONSO                          3		1:08.704                        40,504
23:52:01.796      011 – S.VETTEL                          1		3:31.315                        13,169
23:52:17.003      038 – F.MASS                            4		1:02.787                        44,321
23:52:22.586      033 – R.BARRICHELLO                     4		1:04.010                        43,474
23:52:22.120      002 – K.RAIKKONEN                       4		1:03.076                        44,118
23:52:25.975      023 – M.WEBBER                          4		1:04.216                        43,335
23:53:06.741      015 – F.ALONSO                          4		1:20.050                        34,763
23:53:39.660      011 – S.VETTEL                          2		1:37.864                        28,435
23:54:57.757      011 – S.VETTEL                          3		1:18.097                        35,633
```

## Import

In the current import model, we can pass a specific date (Day, Month, Year) 
and the system will fetch the edited folder the races of that date and 
return a list of imported races.

## Import Model

When importing the file, a race model will be generated, through this 
model we can extract various information such as:

- Classification by driver:
    - Best lap.
    - Average speed.
    - Time for the first.

- List of laps of the race:
    - Number of the lap.
    - Classification of the lap by pilot:
        - Placement on lap
        - Lap Information:
            - Time.
            - Lap time.
            - Average speed.
        - List the time difference for each rider ahead of you on the lap.
            - Pilot
            - Placement on lap
            - Time
    - Faster lap pilot:
        - Placement on lap
        - Time
        - Average speed.

## Entry Point

Currently I have not created a clear entry point for this project, 
such as a REST or something like that. But to test execution, 
I have created a `Main.java` in the root of the `package ('br.com.helpdev.race')`
of the project so that execution can be done.

The `Main` class creates an instance of `ImporterService` and 
executes `importRaces(date)`, the result is converted to JSON to verify it as an API.

```java
public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.showRacesFrom(LocalDate.of(2019, Month.JUNE, 18));
    }

    private final ImporterFacade importer = new ImporterService();

    private void showRacesFrom(LocalDate localDate) {
        RacesImported races = importer.importRaces(new ImportRaceByDate(localDate));
        System.out.println(new Gson().toJson(races));
        System.out.println(races);
    }
}
```

## Notes

The project has several points that could use resources such as
injection of dependencies and libraries that however, the project was developed with
the idea of using the language's own resources with the **minimum possible 
external dependencies**.

## Licence

[MIT](https://choosealicense.com/licenses/mit/)
