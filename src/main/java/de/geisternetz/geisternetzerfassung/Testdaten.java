package de.geisternetz.geisternetzerfassung;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@RequestScoped
@Named
public class Testdaten {
    @Inject
    private GeisternetzDAO geisternetzDAO;

    public Testdaten() {
    }

    public void run() {
        // [0] ist Größe und [1],[2] Lon,Lat
        double[][] daten = {
                {120.0, 0.0, 160.0},    // Pazifik
                {200.0, 5.0, -140.0},   // Zentralpazifik
                {180.0, -20.0, -100.0}, // Südlicher Pazifik
                {250.0, 25.0, -70.0},   // Atlantik
                {300.0, -30.0, 20.0},   // Südlicher Atlantik
                {90.0, 10.0, 80.0},     // Indischer Ozean
                {170.0, -15.0, 100.0},  // Indischer Ozean
                {140.0, 45.0, -30.0},   // Nordatlantik
                {220.0, -50.0, -150.0}, // Südlicher Pazifik
                {160.0, 60.0, 140.0}    // Nordpazifik
        };

        for (double[] datenpaar : daten) {
            Groesse g = new Groesse(datenpaar[0]);
            Standort s = new Standort(datenpaar[1],datenpaar[2]);

            Geisternetz gen = new Geisternetz();
            Person p = new Person();
            p.setRolle(Rolle.MELDEND);
            gen.setZugeordnetPerson(p);
            gen.setGroesse(g);
            gen.setStatus(Status.GEMELDET);
            gen.setStandort(s);
            geisternetzDAO.persist(gen);
        }
    }
}
