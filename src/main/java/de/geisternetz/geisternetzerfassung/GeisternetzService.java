package de.geisternetz.geisternetzerfassung;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.Id;

import java.util.List;

@ApplicationScoped
public class GeisternetzService {
    @Inject
    private GeisternetzDAO geisternetzDAO;

    public List<Geisternetz> filterGeisternetzeByStatus(String selectedStatus) {
        if (selectedStatus == null || selectedStatus.isEmpty()) {
            return geisternetzDAO.getAllGeisternetze();
        } else {
            return geisternetzDAO.getGeisternetzeByStatus(Status.valueOf(selectedStatus));
        }
    }
    public void updateGeisternetzStandortToGeborgen(Geisternetz geisternetz, Standort geborgenStandort) {
        geisternetz.setStatus(Status.GEBORGEN);
        geisternetz.setStandort(geborgenStandort);
        geisternetzDAO.update(geisternetz);
    }

    public void updateVerschollen(Geisternetz selectedGeisternetz) {
        selectedGeisternetz.setStatus(Status.VERSCHOLLEN);
        geisternetzDAO.update(selectedGeisternetz);
    }

    public void updateGeisternetzPersonSelected(Geisternetz selectedGeisternetz, Person selectedPerson) {
        selectedPerson.setRolle(Rolle.BERGEND);
        selectedGeisternetz.setZugeordnetPerson(selectedPerson);
        selectedGeisternetz.setStatus(Status.BERGUNG_BEVORSTEHEND);
        geisternetzDAO.update(selectedGeisternetz);
    }
}
