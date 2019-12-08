package queries.byentity;

import entities.Filial;
import entities.Reserva;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class ReservaQueries {

    private EntityManager em;
    private static ReservaQueries instance = null;

    // ========================================================================
    /* CONSTRUCTORS & SINGLETON IMPLEMENTATION */
    // ========================================================================

    public ReservaQueries(EntityManager entityManager) {
        this.em = entityManager;
    }

    public static ReservaQueries getInstance(EntityManager entityManager) {
        if(instance == null) { instance = new ReservaQueries(entityManager); }
        return instance;
    }

    // ========================================================================
    /* RESERVA QUERIES */
    // ========================================================================

    public Reserva queryReservaWithCodReserva(Integer cod_reserva)
    {
        String jpql = "SELECT r FROM Reserva r WHERE r.cod_reserva = :cod_reserva";
        TypedQuery<Reserva> typedQuery = em.createQuery(jpql, Reserva.class);
        typedQuery.setParameter("cod_reserva", cod_reserva);

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
