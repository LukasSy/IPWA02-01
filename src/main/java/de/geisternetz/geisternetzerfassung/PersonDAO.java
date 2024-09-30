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
    EntityManager em;
    CriteriaBuilder builder;
    public PersonDAO() {
        try{
            em= Persistence.createEntityManagerFactory("geisterDB").createEntityManager();
            builder=em.getCriteriaBuilder();

                System.err.println("Initialisierung der Daten.");
                EntityTransaction t = getAndBeginTransaction();
                for(Person person : Webseite.basePerson) {
                    persist(person);
            }
            t.commit();
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public EntityTransaction getAndBeginTransaction() {
        EntityTransaction tran = em.getTransaction();
        tran.begin();
        return tran;
    }

    private void persist(Person person) {
        em.persist(person);
    }

    public Person getPersonAtIndex(int pos) {
        CriteriaQuery<Person> cq = builder.createQuery(Person.class);
        Root<Person> variableRoot = cq.from(Person.class);
        return em.createQuery(cq).setMaxResults(1).setFirstResult(pos).getSingleResult();
    }
}
