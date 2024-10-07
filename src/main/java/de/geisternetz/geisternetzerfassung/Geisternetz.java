package de.geisternetz.geisternetzerfassung;

import jakarta.persistence.*;

@Entity
@Table(name = "Geisternetz")
public class Geisternetz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true)
    private Integer id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "PERSON_ID", nullable = false)
    private Person zugeordnetPerson;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "STANDORT_ID", nullable = false)
    private Standort standort;

    @Column(name = "GROESSE", nullable = false)
    private double groesse;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private Status status;

    public Geisternetz() {}

    public Geisternetz(Person zugeordnetPerson, Standort standort, double groesse, Status status) {
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

    public double getGroesse() {
        return groesse;
    }

    public void setGroesse(double groesse) {
        this.groesse = groesse;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
