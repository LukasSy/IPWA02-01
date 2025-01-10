package de.geisternetz.geisternetzerfassung;

import jakarta.persistence.*;

@Entity
@Table(name = "Person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private Integer id;

    @Column(name = "NAME", nullable = true)
    private String name;

    @Column(name = "TELEFONNUMMER", nullable = true)
    private String telefonnummer;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLLE")
    private Rolle rolle;

    public Person() {}

    public Person(String name, String telefonnummer, Rolle rolle) {
        this.name = name;
        this.telefonnummer = telefonnummer;
        this.rolle = rolle;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRolle(Rolle rolle) {
        this.rolle = rolle;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Rolle getRolle() {
        return rolle;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }
}
