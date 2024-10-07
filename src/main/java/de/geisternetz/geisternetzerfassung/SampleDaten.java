package de.geisternetz.geisternetzerfassung;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class SampleDaten {

    @Inject
    private PersonDAO personDAO;

    @Inject
    private StandortDAO standortDAO;

    @Inject
    private GeisternetzDAO geisternetzDAO;

    @PostConstruct
    public void init() {
        try {
            if (personDAO.getPersonCount() == 0) {
                for (Person person : Webseite.basePersonen) {
                    personDAO.persist(person);
                }
                System.out.println("Personen erfolgreich hinzugefügt.------------------------------XX");
            }

            if (standortDAO.getStandortCount() == 0) {
                for (Standort standort : Webseite.baseStandort) {
                    standortDAO.persist(standort);
                }
                System.out.println("Standorte erfolgreich hinzugefügt.------------------------------XX");
            }

            if (geisternetzDAO.getGeisternetzCount() == 0) {
                for (Geisternetz geisternetz : Webseite.baseGeisternetz) {
                    geisternetzDAO.persist(geisternetz);
                }
                System.out.println("Geisternetze erfolgreich hinzugefügt.------------------------------XX");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Fehler bei der Initialisierung der Sample-Daten.XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", e);
        }
    }
}
