package queries.byentity;

import entities.*;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@SuppressWarnings("unused")
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

    public List<Tipo_Veiculo> queryAllTiposVeiculo()
    {
        String jpql = "SELECT t FROM Tipo_Veiculo t";
        TypedQuery<Tipo_Veiculo> typedQuery = em.createQuery(jpql, Tipo_Veiculo.class);
        return typedQuery.getResultList();
    }

    public List<Object[]> queryAllVeiculosWithTipoPassageiro()
    {
        String jpql = "SELECT p.cod_tipo, p.horas_limpeza, p.horas_revisao, p.tamanho," +
                      " p.num_lugares, p.num_portas, p.ar_condicionado, p.radio, p.mp3," +
                      " p.cd, p.dir_hidr, p.cambio_auto, v.cod_placa, v.cod_filial_atual," +
                      " v.num_chassi, v.num_motor, v.cor, v.km_atual, v.revisao_pendente, v.parado" +
                      " FROM Veiculo v JOIN Tipo_Passageiro p ON" +
                      " v.tipo.cod_tipo = p.cod_tipo";

        TypedQuery<Object[]> typedQuery = em.createQuery(jpql, Object[].class);
        return typedQuery.getResultList();
    }

    public List<Object[]> queryAllVeiculosWithTipoCarga()
    {
        String jpql = "SELECT c.cod_tipo, c.horas_limpeza, c.horas_revisao, c.capacidade," +
                      " v.cod_placa, v.cod_filial_atual, v.num_chassi, v.num_motor, v.cor," +
                      " v.km_atual, v.revisao_pendente, v.parado " +
                      " FROM Veiculo v JOIN Tipo_Carga c ON" +
                      " v.tipo.cod_tipo = c.cod_tipo";

        TypedQuery<Object[]> typedQuery = em.createQuery(jpql, Object[].class);
        return typedQuery.getResultList();
    }

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

    public List<Object[]> queryVeiculosTipoPassageiroOfTamanho(String tamanho)
    {
        String jpql = "SELECT p.cod_tipo, p.horas_limpeza, p.horas_revisao, p.tamanho," +
                " p.num_lugares, p.num_portas, p.ar_condicionado, p.radio, p.mp3," +
                " p.cd, p.dir_hidr, p.cambio_auto, v.cod_placa, v.cod_filial_atual," +
                " v.num_chassi, v.num_motor, v.cor, v.km_atual, v.revisao_pendente, v.parado" +
                " FROM Veiculo v JOIN Tipo_Passageiro p ON" +
                " v.tipo.cod_tipo = p.cod_tipo" +
                " AND p.tamanho = :tamanho";

        TypedQuery<Object[]> typedQuery = em.createQuery(jpql, Object[].class);
        typedQuery.setParameter("tamanho", tamanho);
        return typedQuery.getResultList();
    }

    public List<Object[]> queryVeiculosTipoPassageiroOfLugares(Integer num_lugares)
    {
        String jpql = "SELECT p.cod_tipo, p.horas_limpeza, p.horas_revisao, p.tamanho," +
                " p.num_lugares, p.num_portas, p.ar_condicionado, p.radio, p.mp3," +
                " p.cd, p.dir_hidr, p.cambio_auto, v.cod_placa, v.cod_filial_atual," +
                " v.num_chassi, v.num_motor, v.cor, v.km_atual, v.revisao_pendente, v.parado" +
                " FROM Veiculo v JOIN Tipo_Passageiro p ON" +
                " v.tipo.cod_tipo = p.cod_tipo" +
                " AND p.num_lugares = :num_lugares";

        TypedQuery<Object[]> typedQuery = em.createQuery(jpql, Object[].class);
        typedQuery.setParameter("num_lugares", num_lugares);
        return typedQuery.getResultList();
    }

    public List<Object[]> queryVeiculosTipoPassageiroOfPortas(Integer num_portas)
    {
        String jpql = "SELECT p.cod_tipo, p.horas_limpeza, p.horas_revisao, p.tamanho," +
                " p.num_lugares, p.num_portas, p.ar_condicionado, p.radio, p.mp3," +
                " p.cd, p.dir_hidr, p.cambio_auto, v.cod_placa, v.cod_filial_atual," +
                " v.num_chassi, v.num_motor, v.cor, v.km_atual, v.revisao_pendente, v.parado" +
                " FROM Veiculo v JOIN Tipo_Passageiro p ON" +
                " v.tipo.cod_tipo = p.cod_tipo" +
                " AND p.num_portas = :num_portas";

        TypedQuery<Object[]> typedQuery = em.createQuery(jpql, Object[].class);
        typedQuery.setParameter("num_portas", num_portas);
        return typedQuery.getResultList();
    }

    public List<Object[]> queryVeiculosTipoPassageiroWithArCondicionadoAndMp3()
    {
        String jpql = "SELECT p.cod_tipo, p.horas_limpeza, p.horas_revisao, p.tamanho," +
                " p.num_lugares, p.num_portas, p.ar_condicionado, p.radio, p.mp3," +
                " p.cd, p.dir_hidr, p.cambio_auto, v.cod_placa, v.cod_filial_atual," +
                " v.num_chassi, v.num_motor, v.cor, v.km_atual, v.revisao_pendente, v.parado" +
                " FROM Veiculo v JOIN Tipo_Passageiro p ON" +
                " v.tipo.cod_tipo = p.cod_tipo" +
                " AND p.ar_condicionado = true AND p.mp3 = true";

        TypedQuery<Object[]> typedQuery = em.createQuery(jpql, Object[].class);
        return typedQuery.getResultList();
    }

    public List<Object[]> queryVeiculosTipoPassageiroWithDirHidraulica()
    {
        String jpql = "SELECT p.cod_tipo, p.horas_limpeza, p.horas_revisao, p.tamanho," +
                " p.num_lugares, p.num_portas, p.ar_condicionado, p.radio, p.mp3," +
                " p.cd, p.dir_hidr, p.cambio_auto, v.cod_placa, v.cod_filial_atual," +
                " v.num_chassi, v.num_motor, v.cor, v.km_atual, v.revisao_pendente, v.parado" +
                " FROM Veiculo v JOIN Tipo_Passageiro p ON" +
                " v.tipo.cod_tipo = p.cod_tipo" +
                " AND p.dir_hidr = true";

        TypedQuery<Object[]> typedQuery = em.createQuery(jpql, Object[].class);
        return typedQuery.getResultList();
    }

    public List<Object[]> queryVeiculosTipoPassageiroWithCambioAutomaticoWithoutDirHidraulica()
    {
        String jpql = "SELECT p.cod_tipo, p.horas_limpeza, p.horas_revisao, p.tamanho," +
                " p.num_lugares, p.num_portas, p.ar_condicionado, p.radio, p.mp3," +
                " p.cd, p.dir_hidr, p.cambio_auto, v.cod_placa, v.cod_filial_atual," +
                " v.num_chassi, v.num_motor, v.cor, v.km_atual, v.revisao_pendente, v.parado" +
                " FROM Veiculo v JOIN Tipo_Passageiro p ON" +
                " v.tipo.cod_tipo = p.cod_tipo" +
                " AND p.dir_hidr = false AND p.cambio_auto = true";

        TypedQuery<Object[]> typedQuery = em.createQuery(jpql, Object[].class);
        return typedQuery.getResultList();
    }

    public List<Object[]> queryVeiculosTipoCargaAboveCapacidade(String capacidade)
    {
        String jpql = "SELECT c.cod_tipo, c.horas_limpeza, c.horas_revisao, c.capacidade," +
                " v.cod_placa, v.cod_filial_atual, v.num_chassi, v.num_motor, v.cor, v.km_atual," +
                " v.revisao_pendente, v.parado" +
                " FROM Veiculo v JOIN Tipo_Carga c ON" +
                " v.tipo.cod_tipo = c.cod_tipo" +
                " AND c.capacidade = :capacidade";

        TypedQuery<Object[]> typedQuery = em.createQuery(jpql, Object[].class);
        typedQuery.setParameter("capacidade", capacidade);
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
