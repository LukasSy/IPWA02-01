package de.geisternetz.geisternetzerfassung;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class GeisternetzBean implements Serializable {

    @Inject
    private GeisternetzDAO geisternetzDAO;

    private String selectedStatus;
    private List<Geisternetz> filteredGeisternetze;

    @PostConstruct
    public void init() {
        filteredGeisternetze = geisternetzDAO.getAllGeisternetze();
    }

    public String getSelectedStatus() {
        return selectedStatus;
    }

    public void setSelectedStatus(String selectedStatus) {
        this.selectedStatus = selectedStatus;
    }

    public void filterByStatus() {
        if (selectedStatus == null || selectedStatus.isEmpty()) {
            filteredGeisternetze = geisternetzDAO.getAllGeisternetze();
        } else {
            filteredGeisternetze = geisternetzDAO.getGeisternetzeByStatus(Status.valueOf(selectedStatus));
        }
    }
    public List<Geisternetz> getFilteredGeisternetze() {
        return filteredGeisternetze;
    }

    public Status[] getAllStatuses() {
        return Status.values();
    }

    public void saveGeisternetz(Geisternetz geisternetz) {
        geisternetzDAO.persist(geisternetz);
    }
}
