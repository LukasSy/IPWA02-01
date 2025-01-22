package de.geisternetz.geisternetzerfassung;

import com.sun.faces.util.Json;
import jakarta.annotation.PostConstruct;
import jakarta.el.MethodExpression;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

@Named
@ApplicationScoped
public class GeisternetzBean implements Serializable {

    @Inject
    private GeisternetzDAO geisternetzDAO;
    @Inject
    private GeisternetzService geisternetzService;

    private String selectedStatus;
    private List<Geisternetz> filteredGeisternetze;

    private List<Geisternetz> filteredGeisternetzeNotRecovered;
    private Geisternetz selectedGeisternetz;
    private Person selectedPerson;
    private Standort geborgenStandort;

    private Geisternetz geisternetz;
    private Person person;
    private Standort standort;
    private Groesse groesse;


    @PostConstruct
    public void init() {
        filteredGeisternetze = geisternetzDAO.getAllGeisternetze(); // Initiale Daten abrufen
        filteredGeisternetzeNotRecovered = geisternetzDAO.getGeisternetzeNotRecovered();
    }

    public GeisternetzBean(){
        selectedPerson = new Person();
        this.groesse = new Groesse();
        this.geisternetz = new Geisternetz();
        this.person = new Person();
        this.standort = new Standort();
    }
    public void updateNotRecovered(){
        filteredGeisternetzeNotRecovered = geisternetzDAO.getGeisternetzeNotRecovered();
    }

    public Person getSelectedPerson() {
        return selectedPerson;
    }

    public void setSelectedPerson(Person selectedPerson) {
        this.selectedPerson = selectedPerson;
    }

    public String getSelectedStatus() {
        return selectedStatus;
    }

    public void setSelectedStatus(String selectedStatus) {
        this.selectedStatus = selectedStatus;
    }

    public List<Geisternetz> getFilteredGeisternetze() {
        return filteredGeisternetze;
    }

    public void setFilteredGeisternetze(List<Geisternetz> filteredGeisternetze) {
        this.filteredGeisternetze = filteredGeisternetze;
    }

    public Status[] getAllStatuses() {
        return Status.values();
    }

    public void filterByStatus() {
        filteredGeisternetze = geisternetzService.filterGeisternetzeByStatus(selectedStatus);

    }
    public void saveGeisternetz() {
        geisternetz.setGroesse(groesse);
        person.setRolle(Rolle.MELDEND);
        geisternetz.setZugeordnetPerson(person);
        geisternetz.setStandort(standort);
        geisternetz.setStatus(Status.GEMELDET);
        geisternetzDAO.persist(geisternetz);

        this.groesse = new Groesse();
        this.geisternetz = new Geisternetz();
        this.person = new Person();
        this.standort = new Standort();
    }

    public Geisternetz getSelectedGeisternetz() {
        return selectedGeisternetz;
    }

    public void setSelectedGeisternetz(Geisternetz selectedGeisternetz) {
        this.selectedGeisternetz = selectedGeisternetz;
    }
    public void updatePersonSelectedGeisternetz() {
        if (selectedGeisternetz != null && selectedPerson != null) {
            geisternetzService.updateGeisternetzPersonSelected(selectedGeisternetz,selectedPerson);
        }
        selectedPerson = new Person();
    }

    public void updateStandortToGeborgen() {
        if (selectedGeisternetz != null && geborgenStandort != null) {
            geisternetzService.updateGeisternetzStandortToGeborgen(selectedGeisternetz, geborgenStandort);
        }
    }

    public void setGeborgenStandort(Standort geborgenStandort) {
        this.geborgenStandort = geborgenStandort;
    }

    public Standort getGeborgenStandort() {
        return geborgenStandort;
    }

    public void verschollen(){
        geisternetzService.updateVerschollen(selectedGeisternetz);
    }

    public String viewDetails(Geisternetz geisternetz) {
        if (Status.GEMELDET.equals(geisternetz.getStatus())) {
            this.selectedGeisternetz = geisternetz;
            return "bergendePerson?faces-redirect=true";
        } else if(Status.BERGUNG_BEVORSTEHEND.equals(geisternetz.getStatus())){
            this.selectedGeisternetz = geisternetz;
            geborgenStandort = new Standort();
            return "bergungEintragen?faces-redirect=true";
        } else if(Status.GEBORGEN.equals(geisternetz.getStatus())){
            this.selectedGeisternetz = geisternetz;
            return "geisternetzLöschen?faces-redirect=true";
        }else if(Status.VERSCHOLLEN.equals(geisternetz.getStatus())){
            this.selectedGeisternetz = geisternetz;
            return "geisternetzLöschen?faces-redirect=true";
        }
        else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Status nicht vergeben. Fehler aufgetreten",
                            "Details können nur angezeigt werden, wenn der Status vergeben ist."));
            return null;
        }
    }

    public void deleteGeisternetz() {
        if (selectedGeisternetz != null) {
            geisternetzDAO.delete(selectedGeisternetz);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Erfolg", "Das Geisternetz wurde erfolgreich gelöscht."));
        }
    }
    public String GeisternetzeListJson(){
        Gson gson = new Gson();
        return gson.toJson(filteredGeisternetzeNotRecovered);
        //return gson.toJson(filteredGeisternetze);
    }

    public Geisternetz getGeisternetz() {
        return geisternetz;
    }
    public void setGeisternetz(Geisternetz geisternetz) {
        this.geisternetz = geisternetz;
    }
    public Person getPerson() {
        return person;
    }
    public void setPerson(Person person) {
        this.person = person;
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

}
