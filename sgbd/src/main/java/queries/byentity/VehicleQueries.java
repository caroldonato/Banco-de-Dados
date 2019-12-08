package queries.byentity;

import entities.Veiculo;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

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

    public List<Veiculo> queryVeiculosDisponiveisWithCodFilial(String cod_filial)
    {
        String jpql = "SELECT v FROM Veiculo v, Filial f WHERE " +
                      "v.cod_filial_atual = f.cod_filial AND " +
                      "f.cod_filial = :cod_filial AND " +
                      "v.parado = true";
        TypedQuery<Veiculo> typedQuery = em.createQuery(jpql, Veiculo.class);
        typedQuery.setParameter("cod_filial", cod_filial);

        return typedQuery.getResultList();
    }

    public List<Object[]> queryVeiculosAlugadosAndEntregaWithCodFilial(String cod_filial)
    {
        String jpql = "SELECT v.cod_placa, v.cod_filial_atual, l.filial_dest, l.data_entrega FROM " +
                      "Veiculo v JOIN " +
                      "LocacoesRecentes l ON v.cod_placa = l.veiculo.cod_placa WHERE " +
                      "l.data_entrega < :data_atual AND v.cod_filial_atual.cod_filial = :cod_filial";

        TypedQuery<Object[]> typedQuery = em.createQuery(jpql, Object[].class);
        typedQuery.setParameter("data_atual", LocalDate.now());
        typedQuery.setParameter("cod_filial", cod_filial);

        return typedQuery.getResultList();

        // Exemplo de como acessar resultados
/*        List<Object[]> vehicles = vehicleQueries.queryVeiculosAlugadosAndEntregaWithCodFilial("Filial1");
        vehicles.forEach(v -> System.out.println("Cod placa: " + v[0] +
                " | Filial atual: " + v[1] +
                " | Filial destino: " + v[2] +
                " | Data entrega: " + v[3]));*/
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
