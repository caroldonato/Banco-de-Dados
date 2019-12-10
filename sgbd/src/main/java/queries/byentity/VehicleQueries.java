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

    /*
    ! Consulta todos os veículos registrados no banco de dados.
     */
    public List<Veiculo> queryAllVeiculos()
    {
        String jpql = "SELECT v FROM Veiculo v";
        TypedQuery<Veiculo> typedQuery = em.createQuery(jpql, Veiculo.class);
        return typedQuery.getResultList();
    }

    /*
    ! Consulta todos os veículos e dados dos seus tipos correspondentes.
     */
    public List<Object[]> queryAllVeiculosAndTipo()
    {
        String jpql = "SELECT v.cod_placa, v.tipo.cod_tipo, v.cod_filial_atual, v.num_chassi, v.num_motor, " +
                      "v.cor, v.km_atual, v.revisao_pendente, v.parado, t.horas_limpeza, t.horas_revisao " +
                      "FROM Veiculo v JOIN Tipo_Veiculo t ON v.tipo.cod_tipo = t.cod_tipo";
        TypedQuery<Object[]> typedQuery = em.createQuery(jpql, Object[].class);
        return typedQuery.getResultList();
    }

    /*
    ! Consulta veículo de código de placa especificado.
     */
    public Veiculo queryVeiculoWithCodPlaca(String cod_placa)
    {
        String jpql = "SELECT v FROM Veiculo v WHERE v.cod_placa = :cod_placa";
        TypedQuery<Veiculo> typedQuery = em.createQuery(jpql, Veiculo.class);
        typedQuery.setParameter("cod_placa", cod_placa);

        return typedQuery.getSingleResult();
    }

    /*
    ! Consulta tempo de limpeza e de revisão de veículo de código de placa especificado.
     */
    public Object[] queryVeiculoHorasLimpezaAndHorasRevisaoWithCodPlaca(String cod_placa)
    {
        String jpql = "SELECT v.tipo.horas_limpeza, v.tipo.horas_revisao FROM Veiculo v" +
                      " WHERE v.cod_placa = :cod_placa";
        TypedQuery<Object[]> typedQuery = em.createQuery(jpql, Object[].class);
        typedQuery.setParameter("cod_placa", cod_placa);
        return typedQuery.getSingleResult();
    }

    /*
    ! Consulta dados de veículo de placa especificada.
    */
    public Object[] queryVeiculoAndTipoWithCodPlaca(String cod_placa)
    {
        String jpql = "SELECT v.cod_placa, v.tipo.cod_tipo, v.cod_filial_atual, v.num_chassi, v.num_motor, " +
                "v.cor, v.km_atual, v.revisao_pendente, v.parado, t.horas_limpeza, t.horas_revisao " +
                "FROM Veiculo v JOIN Tipo_Veiculo t ON v.tipo.cod_tipo = t.cod_tipo " +
                "WHERE v.cod_placa = :cod_placa";

        TypedQuery<Object[]> typedQuery = em.createQuery(jpql, Object[].class);
        typedQuery.setParameter("cod_placa", cod_placa);
        return typedQuery.getSingleResult();
    }

    /*
    ! Consulta todos os veículos com a cor especificada.
    */
    public List<Veiculo> queryVeiculosWithCor(String cor)
    {
        String jpql = "SELECT v FROM Veiculo v WHERE v.cor = :cor";
        TypedQuery<Veiculo> typedQuery = em.createQuery(jpql, Veiculo.class);
        typedQuery.setParameter("cor", cor);
        return typedQuery.getResultList();
    }

    /*
    ! Consulta todos os veículos com quilometragem abaixo da especificada.
    */
    public List<Veiculo> queryVeiculosUnderKm(Integer km)
    {
        String jpql = "SELECT v FROM Veiculo v WHERE v.km_atual < :km";
        TypedQuery<Veiculo> typedQuery = em.createQuery(jpql, Veiculo.class);
        typedQuery.setParameter("km", km);
        return typedQuery.getResultList();
    }

    /*
    ! Consulta todos os veículos sem locações associadas.
     */
    public List<Veiculo> queryVeiculosDisponiveis()
    {
        String jpql = "SELECT v FROM Veiculo v LEFT JOIN Locacao l ON" +
                      " v.cod_placa = l.veiculo.cod_placa" +
                      " WHERE l.cod_locacao IS NULL";

        TypedQuery<Veiculo> typedQuery = em.createQuery(jpql, Veiculo.class);
        return typedQuery.getResultList();
    }

    /*
    ! Consulta os veículos disponíveis em determinada filial na data corrente.
     */
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

    /*
    ! Consulta os veículos presentemente alugados pela filial, o ponto de entrega
      (caso seja diferente do de locação) e data de entrega prevista.
    */
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
