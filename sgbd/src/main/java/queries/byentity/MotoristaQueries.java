package queries.byentity;

import entities.Filial;
import entities.Motorista;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

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

    public List<Motorista> queryAllMotoristas()
    {
        String jpql = "SELECT m FROM Motorista m";
        TypedQuery<Motorista> typedQuery = em.createQuery(jpql, Motorista.class);
        return typedQuery.getResultList();
    }

    public Motorista queryMotoristaWithNumHab(Long num_hab)
    {
        String jpql = "SELECT m FROM Motorista m WHERE m.num_habili = :num_hab";
        TypedQuery<Motorista> typedQuery = em.createQuery(jpql, Motorista.class);
        typedQuery.setParameter("num_hab", num_hab);

        return typedQuery.getSingleResult();
    }

    public List<Motorista> queryMotoristasWithHabilitacaoVencida()
    {
        LocalDate data = LocalDate.now();
        String jpql = "SELECT m FROM Motorista m WHERE m.vencimento_habili <= :data";
        TypedQuery<Motorista> typedQuery = em.createQuery(jpql, Motorista.class);
        typedQuery.setParameter("data", data);
        return typedQuery.getResultList();
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
