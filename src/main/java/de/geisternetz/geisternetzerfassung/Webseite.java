package de.geisternetz.geisternetzerfassung;

import java.util.ArrayList;
import java.util.List;

public class Webseite {
    public static List<Geisternetz> baseGeisternetz = new ArrayList<>();
    public static List<Standort> baseStandort = new ArrayList<>();
    public static List<Person> basePersonen = new ArrayList<>();

    static {
        try {
            basePersonen.add(new Person("Lukas Mustermann", "1234567890", Rolle.BERGEND));
            basePersonen.add(new Person("Lukas Testmann", "0987654321", Rolle.MELDEND));

            baseStandort.add(new Standort(48.137154, 11.576124));
            baseStandort.add(new Standort(52.520008, 13.404954));

            baseGeisternetz.add(new Geisternetz(basePersonen.get(0),baseStandort.get(0), 100.0, Status.GEMELDET));
            baseGeisternetz.add(new Geisternetz( basePersonen.get(1), baseStandort.get(1), 150.5, Status.GEBORGEN));
            baseGeisternetz.add(new Geisternetz( basePersonen.get(1), baseStandort.get(1), 150.5, Status.BERGUNG_BEVORSTEHEND));
            baseGeisternetz.add(new Geisternetz(basePersonen.get(1), baseStandort.get(1), 150.5, Status.BERGUNG_BEVORSTEHEND));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Fehler bei der Initialisierung der Sample Daten",e);
        }
        }
}
