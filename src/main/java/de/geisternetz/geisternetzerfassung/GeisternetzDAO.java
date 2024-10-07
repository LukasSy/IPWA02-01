package de.geisternetz.geisternetzerfassung;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import java.util.List;

@Named
@ApplicationScoped
public class GeisternetzDAO {
    EntityManager em;
    CriteriaBuilder builder;

    public GeisternetzDAO() {
        try{
            em= Persistence.createEntityManagerFactory("G1").createEntityManager();
            builder=em.getCriteriaBuilder();

            long count = getGeisternetzCount();
            System.err.println("Wir haben "+ count +" Geisternetzeee ");

            if(count == 0){
                System.err.println("Initialisierung der Daten.");
                EntityTransaction t = getAndBeginTransaction();
                for(Geisternetz gen : Webseite.baseGeisternetz) {
                    persist(gen);
                }
                t.commit();
            }
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public long getGeisternetzCount() {
        CriteriaQuery<Long> cq = builder.createQuery(Long.class);
        cq.select(builder.count(cq.from(Geisternetz.class)));
        return em.createQuery(cq).getSingleResult();
    }

    public EntityTransaction getAndBeginTransaction() {
        EntityTransaction tran = em.getTransaction();
        tran.begin();
        return tran;
    }

    public void persist(Geisternetz gen) {
        em.persist(gen);
    }

    public List<Geisternetz> getAllGeisternetze() {
        return em.createQuery("SELECT g FROM Geisternetz g ORDER BY g.id",Geisternetz.class).getResultList();
    }

    public static void main(String[] args) {
        GeisternetzDAO dao = new GeisternetzDAO();
        System.err.println("Wir haben " + dao.getGeisternetzCount() + " Geisternetz.");
    }

    public List<Geisternetz> getGeisternetzeByStatus(Status status) {
        return em.createQuery("SELECT g FROM Geisternetz g WHERE g.status = :status", Geisternetz.class)
                .setParameter("status", status)
                .getResultList();
    }


}
