package de.geisternetz.geisternetzerfassung;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.transaction.Transactional;
import java.util.List;

@Named
@ApplicationScoped
public class GeisternetzDAO {
    private final EntityManager em;
    private final CriteriaBuilder builder;

    public GeisternetzDAO() {
        em = Persistence.createEntityManagerFactory("G2").createEntityManager();
        builder = em.getCriteriaBuilder();
    }


    public long getGeisternetzCount() {
        CriteriaQuery<Long> cq = builder.createQuery(Long.class);
        cq.select(builder.count(cq.from(Geisternetz.class)));
        return em.createQuery(cq).getSingleResult();
    }

    public void persist(Geisternetz gen) {
        EntityTransaction t = em.getTransaction();
        t.begin();
        try {
            em.persist(gen);
            t.commit();
        } catch (Exception e) {
            t.rollback();
            throw new RuntimeException("Fehler beim Persistieren eines Geisternetzes", e);
        }
    }

    public List<Geisternetz> getAllGeisternetze() {
        return em.createQuery("SELECT g FROM Geisternetz g ORDER BY g.id", Geisternetz.class).getResultList();
    }

    public List<Geisternetz> getGeisternetzeByStatus(Status status) {
        return em.createQuery("SELECT g FROM Geisternetz g WHERE g.status = :status", Geisternetz.class)
                .setParameter("status", status)
                .getResultList();
    }
    public List<Geisternetz> getGeisternetzeNotRecovered() {
        return em.createQuery("SELECT g FROM Geisternetz g WHERE g.status != :status1 AND g.status != :status2", Geisternetz.class)
                .setParameter("status1", Status.GEBORGEN)
                .setParameter("status2", Status.VERSCHOLLEN)
                .getResultList();

    }



    public void update(Geisternetz selectedGeisternetz) {
        if (selectedGeisternetz != null) {
            EntityTransaction t = em.getTransaction();
            t.begin();
            try {
                em.merge(selectedGeisternetz);
                t.commit();
            } catch (Exception e) {
                t.rollback();
                throw new RuntimeException("Fehler beim Aktualisieren eines Geisternetzes", e);
            }
        }
    }

    public static void main(String[] args) {
        GeisternetzDAO dao = new GeisternetzDAO();}

    @Transactional
    public void delete(Geisternetz geisternetz) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            if (geisternetz != null && geisternetz.getId() != null) {
                em.remove(geisternetz);
                em.flush();
            }
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }



}
