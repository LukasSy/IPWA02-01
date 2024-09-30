package de.geisternetz.geisternetzerfassung;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Person {
    @Id
    @GeneratedValue
    private int id;
    private String nachname;
    private String vorname;
    private int age;
    public Person() {

    }
    public Person(String name, String surname, int age) {
        this.nachname = name;
        this.vorname = surname;
        this.age = age;

    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return nachname;
    }
    public void setName(String name) {
        this.nachname = name;
    }
    public String getSurname() {
        return vorname;
    }
    public void setSurname(String surname) {this.vorname = surname;}
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getNameVoll() {
        return vorname +" " + nachname;
    }
}
