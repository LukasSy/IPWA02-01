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
            em = Persistence.createEntityManagerFactory("G1").createEntityManager();
            builder = em.getCriteriaBuilder();

            long count = getStandortCount();
            System.err.println("Wir haben "+ count +" Standorte ");
            if(count == 0){
                System.err.println("Initialisierung der Daten.");
                EntityTransaction t = getAndBeginTransaction();
                for (Standort standort : Webseite.baseStandort) {
                    persist(standort);
                }
                t.commit();
            }

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

    public static void main(String[] args) {
        StandortDAO dao = new StandortDAO();
        System.err.println("Wir haben " + dao.getStandortCount() + " Standort.");
    }


    public int getStandortCount() {
        CriteriaQuery<Long> cq = builder.createQuery(Long.class);
        Root<Standort> root = cq.from(Standort.class);
        cq.select(builder.count(root));
        return em.createQuery(cq).getSingleResult().intValue();
    }
}
