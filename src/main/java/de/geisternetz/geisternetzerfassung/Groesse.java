package de.geisternetz.geisternetzerfassung;

import jakarta.persistence.*;

@Entity
@Table(name = "Groesse")
public class Groesse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private int id;

    @Column(name = "NETZGROESSE")
    private Double netzgroesse;


    public Groesse() {

    }
    public Groesse(Double netzgroesse) {
        this.netzgroesse = netzgroesse;
    }

    public Double getNetzgroesse() {
        return this.netzgroesse;
    }

    public void setNetzgroesse(Double netzgroesse) {
        this.netzgroesse = netzgroesse;
    }

    public int getId() {
        return id;
    }
}
