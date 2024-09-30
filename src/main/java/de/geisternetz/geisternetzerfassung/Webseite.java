package de.geisternetz.geisternetzerfassung;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Named
@ApplicationScoped
public class Webseite implements Serializable {
    @Inject
    GeisternetzDAO geisternetzDAO;
    @Inject PersonDAO personDAO;
    @Inject StandortDAO standortDAO;
    public Webseite(){

    }

    private static final Standort standort1 = new Standort(89.787877,56.68768676);
    private static final Person person1 = new Person("Lukas", "Arent", 22);

    private static final Standort standort2 = new Standort(83.787877,50.68768676);
    private static final Person person2 = new Person("Julia", "Arent", 22);
    private static final Person person3 = new Person("Leon", "Arent", 22);

    static final List<Geisternetz> baseGeisternetz = Arrays.asList(
            new Geisternetz(Status.GEMELDET,person1,standort1,"5"),
            new Geisternetz(Status.GEMELDET,person2,standort2,"2"),
            new Geisternetz(Status.GEBORGEN,person3,standort1,"10")
    );

    static final List<Person> basePerson = Arrays.asList(person1,person2,person3 );
    static final List<Standort> baseStandort = Arrays.asList(standort1,standort2 );
}
