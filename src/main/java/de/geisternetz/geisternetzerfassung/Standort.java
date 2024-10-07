package de.geisternetz.geisternetzerfassung;

import jakarta.persistence.*;

@Entity
public class Standort {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private int id;
    @Column(name = "LONGITUDE", nullable = false)
    private double longitude;
    @Column(name = "LATITUDE", nullable = false)
    private double latitude;


    public Standort() {

    }
    public Standort(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }
}
