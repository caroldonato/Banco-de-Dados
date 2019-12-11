package queries.byentity;

import entities.Tipo_Carga;
import entities.Tipo_Passageiro;
import entities.Tipo_Veiculo;

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

    /*
    ! Consulta todos os tipos de veículo registrados no banco de dados.
     */
    public List<Tipo_Veiculo> queryAllTiposVeiculo()
    {
        try{
            String jpql = "SELECT t FROM Tipo_Veiculo t";
            TypedQuery<Tipo_Veiculo> typedQuery = em.createQuery(jpql, Tipo_Veiculo.class);
            return typedQuery.getResultList();
        }
        catch (Exception e)
        {
            Exception cause = (Exception) e.getCause();
            System.out.println("Erro na consulta queryAllTiposVeiculo: " + cause.getMessage() + ".");
            return null;
        }
    }

    /*
    ! Consulta veículos do tipo passageiro e seus dados.
    */
    public List<Object[]> queryAllVeiculosWithTipoPassageiro()
    {
        try{
            String jpql = "SELECT p.cod_tipo, p.horas_limpeza, p.horas_revisao, p.tamanho," +
                    " p.num_lugares, p.num_portas, p.ar_condicionado, p.radio, p.mp3," +
                    " p.cd, p.dir_hidr, p.cambio_auto, v.cod_placa, v.cod_filial_atual," +
                    " v.num_chassi, v.num_motor, v.cor, v.km_atual, v.revisao_pendente, v.parado" +
                    " FROM Veiculo v JOIN Tipo_Passageiro p ON" +
                    " v.tipo.cod_tipo = p.cod_tipo";

            TypedQuery<Object[]> typedQuery = em.createQuery(jpql, Object[].class);
            return typedQuery.getResultList();
        }
        catch (Exception e)
        {
            Exception cause = (Exception) e.getCause();
            System.out.println("Erro na consulta queryAllVeiculosWithTipoPassageiro: " + cause.getMessage() + ".");
            return null;
        }
    }

    /*
    ! Consulta veículos do tipo carga e seus dados.
    */
    public List<Object[]> queryAllVeiculosWithTipoCarga()
    {
        try{
            String jpql = "SELECT c.cod_tipo, c.horas_limpeza, c.horas_revisao, c.capacidade," +
                    " v.cod_placa, v.cod_filial_atual, v.num_chassi, v.num_motor, v.cor," +
                    " v.km_atual, v.revisao_pendente, v.parado " +
                    " FROM Veiculo v JOIN Tipo_Carga c ON" +
                    " v.tipo.cod_tipo = c.cod_tipo";

            TypedQuery<Object[]> typedQuery = em.createQuery(jpql, Object[].class);
            return typedQuery.getResultList();
        }
        catch (Exception e)
        {
            Exception cause = (Exception) e.getCause();
            System.out.println("Erro na consulta queryAllVeiculosWithTipoCarga: " + cause.getMessage() + ".");
            return null;
        }
    }

    /*
    ! Consulta tipo de veículo com código de tipo de veículo especificado.
     */
    public Tipo_Veiculo queryTipoVeiculoWithCodTipo(String cod_tipo)
    {
        try{
            String jpql = "SELECT t FROM Tipo_Veiculo t WHERE t.cod_tipo = :cod_tipo";
            TypedQuery<Tipo_Veiculo> typedQuery = em.createQuery(jpql, Tipo_Veiculo.class);
            typedQuery.setParameter("cod_tipo", cod_tipo);

            return typedQuery.getSingleResult();
        }
        catch (Exception e)
        {
            Exception cause = (Exception) e.getCause();
            System.out.println("Erro na consulta queryTipoVeiculoWithCodTipo: " + cause.getMessage() + ".");
            return null;
        }
    }

    /*
    ! Consulta tipo de veículo de passageiro com código de tipo de veículo especificado.
     */
    public Tipo_Passageiro queryTipoPassageiroWithCodTipo(String cod_tipo)
    {
        try{
            String jpql = "SELECT p FROM Tipo_Passageiro p WHERE p.cod_tipo = :cod_tipo";
            TypedQuery<Tipo_Passageiro> typedQuery = em.createQuery(jpql, Tipo_Passageiro.class);
            typedQuery.setParameter("cod_tipo", cod_tipo);

            return typedQuery.getSingleResult();
        }
        catch (Exception e)
        {
            Exception cause = (Exception) e.getCause();
            System.out.println("Erro na consulta queryTipoPassageiroWithCodTipo: " + cause.getMessage() + ".");
            return null;
        }
    }

    /*
    ! Consulta tipo de veículo de carga com código de tipo de veículo especificado.
     */
    public Tipo_Carga queryTipoCargaWithCodTipo(String cod_tipo)
    {
        try{
            String jpql = "SELECT c FROM Tipo_Carga c WHERE c.cod_tipo = :cod_tipo";
            TypedQuery<Tipo_Carga> typedQuery = em.createQuery(jpql, Tipo_Carga.class);
            typedQuery.setParameter("cod_tipo", cod_tipo);

            return typedQuery.getSingleResult();
        }
        catch (Exception e)
        {
            Exception cause = (Exception) e.getCause();
            System.out.println("Erro na consulta queryTipoCargaWithCodTipo: " + cause.getMessage() + ".");
            return null;
        }
    }

    /*
    ! Consulta veículos de tipo passageiro do tamanho especificado.
    */
    public List<Object[]> queryVeiculosTipoPassageiroOfTamanho(String tamanho)
    {
        try{
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
        catch (Exception e)
        {
            Exception cause = (Exception) e.getCause();
            System.out.println("Erro na consulta queryVeiculosTipoPassageiroOfTamanho: " + cause.getMessage() + ".");
            return null;
        }
    }

    /*
    ! Consulta veículos de tipo passageiro com o número de lugares especificados.
    */
    public List<Object[]> queryVeiculosTipoPassageiroOfLugares(Integer num_lugares)
    {
        try{
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
        catch (Exception e)
        {
            Exception cause = (Exception) e.getCause();
            System.out.println("Erro na consulta queryVeiculosTipoPassageiroOfLugares: " + cause.getMessage() + ".");
            return null;
        }
    }

    /*
    ! Consulta veículos de tipo passageiro com número de portas especificado.
     */
    public List<Object[]> queryVeiculosTipoPassageiroOfPortas(Integer num_portas)
    {
        try{
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
        catch (Exception e)
        {
            Exception cause = (Exception) e.getCause();
            System.out.println("Erro na consulta queryVeiculosTipoPassageiroOfPortas: " + cause.getMessage() + ".");
            return null;
        }
    }

    /*
    ! Consulta veículos de tipo passageiro com ar condicionado e mp3.
     */
    public List<Object[]> queryVeiculosTipoPassageiroWithArCondicionadoAndMp3()
    {
        try{
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
        catch (Exception e)
        {
            Exception cause = (Exception) e.getCause();
            System.out.println("Erro na consulta queryVeiculosTipoPassageiroWithArCondicionadoAndMp3: " + cause.getMessage() + ".");
            return null;
        }
    }

    /*
    ! Consulta veículos de tipo passageiro com direção hidráulica.
     */
    public List<Object[]> queryVeiculosTipoPassageiroWithDirHidraulica()
    {
        try{
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
        catch (Exception e)
        {
            Exception cause = (Exception) e.getCause();
            System.out.println("Erro na consulta queryVeiculosTipoPassageiroWithDirHidraulica: " + cause.getMessage() + ".");
            return null;
        }
    }

    /*
    ! Consulta veículos de tipo passageiro com câmbio automático e sem direção hidráulica.
     */
    public List<Object[]> queryVeiculosTipoPassageiroWithCambioAutomaticoWithoutDirHidraulica()
    {
        try{
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
        catch (Exception e)
        {
            Exception cause = (Exception) e.getCause();
            System.out.println("Erro na consulta queryVeiculosTipoPassageiroWithCambioAutomaticoWithoutDirHidraulica: "
                    + cause.getMessage() + ".");
            return null;
        }
    }

    /*
    ! Consulta veículos de tipo carga com capacidade acima da especificada.
     */
    public List<Object[]> queryVeiculosTipoCargaAboveCapacidade(String capacidade)
    {
        try{
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
        catch (Exception e)
        {
            Exception cause = (Exception) e.getCause();
            System.out.println("Erro na consulta queryVeiculosTipoCargaAboveCapacidade: " + cause.getMessage() + ".");
            return null;
        }
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
