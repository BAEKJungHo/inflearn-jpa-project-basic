package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    private static final String persistenceUnitName = "hello";

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnitName);
        EntityManager em = emf.createEntityManager();

        // JPA 는 모두 Transaction 안에서 작동해야 한다.
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = em.find(Member.class, 1L);
            member.setName("HelloJPA");
            em.persist(member);

            tx.commit();
        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

}
