package queries.byentity;

import entities.Reserva;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

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

    /*
    ! Consulta todas as reservas registradas no banco de dados.
     */
    public List<Reserva> queryAllReservas()
    {
        String jpql = "SELECT r FROM Reserva r";
        TypedQuery<Reserva> typedQuery = em.createQuery(jpql, Reserva.class);
        return typedQuery.getResultList();
    }

    /*
    ! Consulta reserva de código de reserva especificado.
     */
    public Reserva queryReservaWithCodReserva(Integer cod_reserva)
    {
        String jpql = "SELECT r FROM Reserva r WHERE r.cod_reserva = :cod_reserva";
        TypedQuery<Reserva> typedQuery = em.createQuery(jpql, Reserva.class);
        typedQuery.setParameter("cod_reserva", cod_reserva);

        return typedQuery.getSingleResult();
    }

    /*
    ! Consulta de todas as reservas de veículos de carga que possuem a mesma
      filial como origem e destino.
     */
    public List<Reserva> queryReservaComMesmaOrigemEDestinoTipoCarga()
    {
        String jpql = "SELECT r FROM Reserva r JOIN Tipo_Carga c ON r.tipo.cod_tipo = c.tipo.cod_tipo" +
                      " WHERE r.cod_filial.orig = r.cod_filial.dest";
        TypedQuery<Reserva> typedQuery = em.createQuery(jpql, Reserva.class);
        return typedQuery.getResultList();
    }

    /*
    ! Consulta de reservas possíveis para uma determinada data e filial.
    */


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
