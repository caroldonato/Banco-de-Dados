package queries.byentity;

import entities.Filial;
import entities.Motorista;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class MotoristaQueries {
    private EntityManager em;
    private static MotoristaQueries instance = null;

    // ========================================================================
    /* CONSTRUCTORS & SINGLETON IMPLEMENTATION */
    // ========================================================================

    public MotoristaQueries(EntityManager entityManager) {
        this.em = entityManager;
    }

    public static MotoristaQueries getInstance(EntityManager entityManager) {
        if(instance == null) { instance = new MotoristaQueries(entityManager); }
        return instance;
    }

    // ========================================================================
    /* MOTORISTA QUERIES */
    // ========================================================================

    public Motorista queryMotoristaWithNumHab(Long num_hab)
    {
        String jpql = "SELECT m FROM Motorista m WHERE m.num_habili = :num_hab";
        TypedQuery<Motorista> typedQuery = em.createQuery(jpql, Motorista.class);
        typedQuery.setParameter("num_hab", num_hab);

        return typedQuery.getSingleResult();
    }

    // ========================================================================
    /* GETTERS & SETTERS */
    // ========================================================================

    public EntityManager getEm() {
        return em;
    }
    public void setEm(EntityManager em) {
        this.em = em;
    }
}
