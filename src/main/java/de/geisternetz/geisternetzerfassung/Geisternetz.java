package de.geisternetz.geisternetzerfassung;

import jakarta.persistence.*;

@Entity
@Table(name = "Geisternetz")
public class Geisternetz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private Integer id;

    @ManyToOne(cascade ={CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "PERSON_ID")
    private Person zugeordnetPerson;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "STANDORT_ID")
    private Standort standort;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "GROESSE")
    private Groesse groesse;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private Status status;

    public Geisternetz() {}

    public Geisternetz(Person zugeordnetPerson, Standort standort, Groesse groesse, Status status) {
        this.zugeordnetPerson = zugeordnetPerson;
        this.standort = standort;
        this.groesse = groesse;
        this.status = status;
    }


    public Integer getId() {
        return id;
    }

    public Person getZugeordnetPerson() {
        return zugeordnetPerson;
    }

    public void setZugeordnetPerson(Person zugeordnetPerson) {
        this.zugeordnetPerson = zugeordnetPerson;
    }

    public Standort getStandort() {
        return standort;
    }

    public void setStandort(Standort standort) {
        this.standort = standort;
    }

    public Groesse getGroesse() {
        return groesse;
    }

    public void setGroesse(Groesse groesse) {
        this.groesse = groesse;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
