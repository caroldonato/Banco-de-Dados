package queries.byentity;

import entities.*;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class VehicleTypeQueries {
    private EntityManager em;
    private static VehicleTypeQueries instance = null;

    // ========================================================================
    /* CONSTRUCTORS & SINGLETON IMPLEMENTATION */
    // ========================================================================

    public VehicleTypeQueries(EntityManager entityManager) {
        this.em = entityManager;
    }

    public static VehicleTypeQueries getInstance(EntityManager entityManager) {
        if(instance == null) { instance = new VehicleTypeQueries(entityManager); }
        return instance;
    }

    // ========================================================================
    /* VEHICLE TYPE QUERIES */
    // ========================================================================

    public Tipo_Veiculo queryTipoVeiculoWithCodTipo(String cod_tipo)
    {
        String jpql = "SELECT t FROM Tipo_Veiculo t WHERE t.cod_tipo = :cod_tipo";
        TypedQuery<Tipo_Veiculo> typedQuery = em.createQuery(jpql, Tipo_Veiculo.class);
        typedQuery.setParameter("cod_tipo", cod_tipo);

        return typedQuery.getSingleResult();
    }

    public Tipo_Passageiro queryTipoPassageiroWithCodTipo(String cod_tipo)
    {
        String jpql = "SELECT p FROM Tipo_Passageiro p WHERE p.cod_tipo = :cod_tipo";
        TypedQuery<Tipo_Passageiro> typedQuery = em.createQuery(jpql, Tipo_Passageiro.class);
        typedQuery.setParameter("cod_tipo", cod_tipo);

        return typedQuery.getSingleResult();
    }

    public Tipo_Carga queryTipoCargaWithCodTipo(String cod_tipo)
    {
        String jpql = "SELECT c FROM Tipo_Carga c WHERE c.cod_tipo = :cod_tipo";
        TypedQuery<Tipo_Carga> typedQuery = em.createQuery(jpql, Tipo_Carga.class);
        typedQuery.setParameter("cod_tipo", cod_tipo);

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
