package de.geisternetz.geisternetzerfassung;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Named
@ApplicationScoped
public class StandortDAO {
    private EntityManager em;
    private CriteriaBuilder builder;

    public StandortDAO() {
        try {
            em = Persistence.createEntityManagerFactory("geisterDB").createEntityManager();
            builder = em.getCriteriaBuilder();

            System.err.println("Initialisierung der Daten.");
            EntityTransaction t = getAndBeginTransaction();
            for (Standort standort : Webseite.baseStandort) {
                persist(standort);
            }
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public EntityTransaction getAndBeginTransaction() {
        EntityTransaction tran = em.getTransaction();
        tran.begin();
        return tran;
    }

    public void persist(Standort standort) {
        em.persist(standort);
    }

    public Standort getStandortAtIndex(int pos) {
        CriteriaQuery<Standort> cq = builder.createQuery(Standort.class);
        Root<Standort> variableRoot = cq.from(Standort.class);
        return em.createQuery(cq).setMaxResults(1).setFirstResult(pos).getSingleResult();
    }
}
