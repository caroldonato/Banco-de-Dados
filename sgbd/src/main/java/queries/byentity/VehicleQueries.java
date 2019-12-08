package queries.byentity;

import entities.Veiculo;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@SuppressWarnings("unused")
public class VehicleQueries {

    private EntityManager em;
    private static VehicleQueries instance = null;

    // ========================================================================
    /* CONSTRUCTORS & SINGLETON IMPLEMENTATION */
    // ========================================================================

    public VehicleQueries(EntityManager entityManager) {
        this.em = entityManager;
    }

    public static VehicleQueries getInstance(EntityManager entityManager) {
        if(instance == null) { instance = new VehicleQueries(entityManager); }
        return instance;
    }

    // ========================================================================
    /* VEHICLE QUERIES */
    // ========================================================================

    public Veiculo queryVeiculoWithCodPlaca(String cod_placa)
    {
        String jpql = "SELECT v FROM Veiculo v WHERE v.cod_placa = :cod_placa";
        TypedQuery<Veiculo> typedQuery = em.createQuery(jpql, Veiculo.class);
        typedQuery.setParameter("cod_placa", cod_placa);

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
