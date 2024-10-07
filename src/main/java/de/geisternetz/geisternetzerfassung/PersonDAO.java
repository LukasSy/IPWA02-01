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
public class PersonDAO {
    private EntityManager em;
    private CriteriaBuilder builder;

    public PersonDAO() {
        try {
            em = Persistence.createEntityManagerFactory("G1").createEntityManager();
            builder = em.getCriteriaBuilder();

            long count = getPersonCount();
            System.err.println("Wir haben "+ count +" Personen ");
            if(count == 0) {
                System.err.println("Initialisierung der Daten.");
                EntityTransaction t = getAndBeginTransaction();
                for (Person person : Webseite.basePersonen) {
                    persist(person);
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

    public void persist(Person person) {
        em.persist(person);
    }

    public static void main(String[] args) {
        PersonDAO dao = new PersonDAO();
        System.err.println("Wir haben " + dao.getPersonCount() + " Personen.");
    }

    public int getPersonCount() {
        CriteriaQuery<Long> cq = builder.createQuery(Long.class);
        Root<Person> root = cq.from(Person.class);
        cq.select(builder.count(root));
        return em.createQuery(cq).getSingleResult().intValue();
    }
}
