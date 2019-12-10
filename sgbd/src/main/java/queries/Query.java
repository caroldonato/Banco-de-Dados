package queries;

import entities.*;
import queries.byentity.*;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

@SuppressWarnings("unused")
public class Query {
    private static EntityManager em;

    private static ClienteQueries clienteQueries;
    private static FilialQueries filialQueries;
    private static LocacaoQueries locacaoQueries;
    private static MotoristaQueries motoristaQueries;
    private static ReservaQueries reservaQueries;
    private static RevisaoQueries revisaoQueries;
    private static VehicleQueries vehicleQueries;
    private static VehicleTypeQueries vehicleTypeQueries;

    private static Query instance = null;

    // ========================================================================
    /* CONSTRUCTORS & SINGLETON IMPLEMENTATION */
    // ========================================================================

    public Query(EntityManager entityManager) {
        em = entityManager;
    }

    public static Query getInstance(EntityManager entityManager) {
        if(instance == null) {
            instance = new Query(entityManager);

            clienteQueries = new ClienteQueries(entityManager);
            filialQueries = new FilialQueries(entityManager);
            locacaoQueries = new LocacaoQueries(entityManager);
            motoristaQueries = new MotoristaQueries(entityManager);
            reservaQueries = new ReservaQueries(entityManager);
            revisaoQueries = new RevisaoQueries(entityManager);
            vehicleQueries = new VehicleQueries(entityManager);
            vehicleTypeQueries = new VehicleTypeQueries(entityManager);
        }
        return instance;
    }

    // ========================================================================
    /* QUERIES ACCESS */
    // ========================================================================

    // ------------------------------------------------------------------------
    // CLIENTE
    // ------------------------------------------------------------------------

    /*
    ! Consulta todos os clientes do banco de dados.
     */
    public List<Cliente> queryAllClientes() { return clienteQueries.queryAllClientes(); }

    /*
    ! Consulta cliente utilizando código de cliente especificado.
     */
    public Cliente queryClienteWithCodCliente(String cod_cliente) { return clienteQueries.queryClienteWithCodCliente(cod_cliente); }

    /*
    ! Consulta cliente de tipo pessoa física e seus dados utilizando código de cliente.
     */
    public Pessoa_Fisica queryPessoaFisicaWithCodCliente(String cod_cliente) { return clienteQueries.queryPessoaFisicaWithCodCliente(cod_cliente); }

    /*
    ! Consulta cliente de tipo pessoa jurídica e seus dados utilizando código de cliente.
     */
    public Pessoa_Juridica queryPessoaJuridicaWithCodCliente(String cod_cliente) { return clienteQueries.queryPessoaJuridicaWithCodCliente(cod_cliente); }

    /*
    ! Consulta todos os clientes que possuem locação de veículos de carga registradas.
     */
    public List<Cliente> queryClientesComLocacaoDeTipoCarga() { return clienteQueries.queryClientesComLocacaoDeTipoCarga(); }

    /*
    ! Consulta todos os clientes que possuem alguma reserva em seu nome.
     */
    public List<Cliente> queryClientesComReservas() { return clienteQueries.queryClientesComReservas(); }

    /*
    ! Consulta cliente que possui a locação do veículo de código de placa especificado.
     */
    public Cliente queryClienteComLocacaoWithCodPlaca(String cod_placa) { return clienteQueries.queryClienteComLocacaoWithCodPlaca(cod_placa); }

    /*
    ! Consulta cliente de tipo pessoa física utilizando nome e CPF.
     */
    public Pessoa_Fisica queryPessoaFisicaWithNameAndCpf(String name, Long cpf) { return clienteQueries.queryPessoaFisicaWithNameAndCpf(name, cpf); }

    /*
    ! Consulta cliente de tipo pessoa jurídica utilizando nome e CNPJ.
     */
    public Pessoa_Juridica queryPessoaJuridicaWithNameAndCnpj(String name, Long cnpj) { return clienteQueries.queryPessoaJuridicaWithNameAndCnpj(name, cnpj); }

    /*
    ! Consulta todas as pessoas físicas nascidas após ano especificado.
     */
    public List<Pessoa_Fisica> queryPessoaFisicaNascidaDepoisDeAno(Integer ano)
    { return clienteQueries.queryPessoaFisicaNascidaDepoisDeAno(ano); }

    // ------------------------------------------------------------------------
    // FILIAL
    // ------------------------------------------------------------------------


    /*
    ! Consulta todas as filiais do banco de dados.
     */
    public List<Filial> queryAllFiliais() { return filialQueries.queryAllFiliais(); }

    /*
    ! Consulta filial de código de filial especificado.
     */
    public Filial queryFilialWithCodFilial(String cod_filial) { return filialQueries.queryFilialWithCodFilial(cod_filial); }

    /*
    ! Consulta todas as filiais da localização especificada.
     */
    public List<Filial> queryFilialWithLocalizacao(String localizacao) { return filialQueries.queryFilialWithLocalizacao(localizacao); }


    // ------------------------------------------------------------------------
    // LOCAÇÃO
    // ------------------------------------------------------------------------

    /*
     ! Consulta todas as locações do banco de dados.
    */
    public List<Locacao> queryAllLocacoes() { return locacaoQueries.queryAllLocacoes(); }

    /*
    ! Consulta locação de código de locação especificado.
     */
    public Locacao queryLocacaoWithCodLocacao(Integer cod_locacao) { return locacaoQueries.queryLocacaoWithCodLocacao(cod_locacao); }

    /*
    ! Consulta todos os nomes de clientes e placas de veículos de locações
      da filial especificada que tem quilometragem atual superior à especificada.
     */
    public List<Object[]> queryLocacaoOverKmWithCodFilialDest(String cod_filial, Integer km) { return locacaoQueries.queryLocacaoOverKmWithCodFilialDest(cod_filial, km); }

    /*
    ! Consulta todos os nomes de clientes e número de habilitação dos motoristas de
      locações feitas antes da data especificada.
     */
    public List<Object[]> queryLocacaoBeforeDataEntrega(LocalDate data) { return locacaoQueries.queryLocacaoBeforeDataEntrega(data); }


    // ------------------------------------------------------------------------
    // MOTORISTA
    // ------------------------------------------------------------------------


    /*
    ! Consulta todos os motoristas do banco de dados.
     */
    public List<Motorista> queryAllMotoristas() { return motoristaQueries.queryAllMotoristas(); }

    /*
    ! Consulta motorista de código de motorista especificado.
     */
    public Motorista queryMotoristaWithCodMotorista(Integer cod_motorista) { return motoristaQueries.queryMotoristaWithCodMotorista(cod_motorista); }

    /*
    ! Consulta motorista de número de habilitação especificado.
     */
    public Motorista queryMotoristaWithNumHab(Long num_hab) { return motoristaQueries.queryMotoristaWithNumHab(num_hab); }

    /*
    ! Consulta de todos os motoristas com carteira de habilitação vencida.
     */
    public List<Motorista> queryMotoristasWithHabilitacaoVencida() { return motoristaQueries.queryMotoristasWithHabilitacaoVencida(); }


    // ------------------------------------------------------------------------
    // RESERVA
    // ------------------------------------------------------------------------


    /*
    ! Consulta todas as reservas registradas no banco de dados.
    */
    public List<Reserva> queryAllReservas() { return reservaQueries.queryAllReservas(); }

    /*
    ! Consulta reserva de código de reserva especificado.
     */
    public Reserva queryReservaWithCodReserva(Integer cod_reserva) { return reservaQueries.queryReservaWithCodReserva(cod_reserva); }

    /*
    ! Consulta de todas as reservas de veículos de carga que possuem a mesma
      filial como origem e destino.
     */
    public List<Reserva> queryReservaComMesmaOrigemEDestinoTipoCarga() { return reservaQueries.queryReservaComMesmaOrigemEDestinoTipoCarga(); }


    // ------------------------------------------------------------------------
    // REVISÃO
    // ------------------------------------------------------------------------


    /*
    ! Consulta todas as revisões registradas no banco de dados.
    */
    public List<Revisao> queryAllRevisoes() { return revisaoQueries.queryAllRevisoes(); }

    /*
    ! Consulta revisão de código de revisão e código de tipo especificados.
     */
    public Revisao queryRevisaoWithCodTipoAndCodRevisao(String cod_tipo, Integer cod_revisao) { return revisaoQueries.queryRevisaoWithCodTipoAndCodRevisao(cod_tipo, cod_revisao); }


    // ------------------------------------------------------------------------
    // VEÍCULO
    // ------------------------------------------------------------------------


    /*
    ! Consulta todos os veículos registrados no banco de dados.
     */
    public List<Veiculo> queryAllVeiculos() { return vehicleQueries.queryAllVeiculos(); }

    /*
    ! Consulta todos os veículos e dados dos seus tipos correspondentes.
     */
    public List<Object[]> queryAllVeiculosAndTipo() { return vehicleQueries.queryAllVeiculosAndTipo(); }

    /*
    ! Consulta veículo de código de placa especificado.
     */
    public Veiculo queryVeiculoWithCodPlaca(String cod_placa) { return vehicleQueries.queryVeiculoWithCodPlaca(cod_placa); }

    /*
    ! Consulta tempo de limpeza e de revisão de veículo de código de placa especificado.
     */
    public Object[] queryVeiculoHorasLimpezaAndHorasRevisaoWithCodPlaca(String cod_placa) { return vehicleQueries.queryVeiculoHorasLimpezaAndHorasRevisaoWithCodPlaca(cod_placa); }

    /*
    ! Consulta dados de veículo de placa especificada.
    */
    public Object[] queryVeiculoAndTipoWithCodPlaca(String cod_placa) { return vehicleQueries.queryVeiculoAndTipoWithCodPlaca(cod_placa); }

    /*
    ! Consulta todos os veículos com a cor especificada.
    */
    public List<Veiculo> queryVeiculosWithCor(String cor) { return vehicleQueries.queryVeiculosWithCor(cor); }

    /*
    ! Consulta todos os veículos com quilometragem abaixo da especificada.
    */
    public List<Veiculo> queryVeiculosUnderKm(Integer km) { return vehicleQueries.queryVeiculosUnderKm(km); }

    /*
    ! Consulta todos os veículos sem locações associadas.
     */
    public List<Veiculo> queryVeiculosDisponiveis() { return vehicleQueries.queryVeiculosDisponiveis(); }

    /*
    ! Consulta os veículos disponíveis em determinada filial na data corrente.
     */
    public List<Veiculo> queryVeiculosDisponiveisWithCodFilial(String cod_filial) { return vehicleQueries.queryVeiculosDisponiveisWithCodFilial(cod_filial); }

    /*
    ! Consulta os veículos presentemente alugados pela filial, o ponto de entrega
      (caso seja diferente do de locação) e data de entrega prevista.
    */
    public List<Object[]> queryVeiculosAlugadosAndEntregaWithCodFilial(String cod_filial) { return vehicleQueries.queryVeiculosAlugadosAndEntregaWithCodFilial(cod_filial); }


    // ------------------------------------------------------------------------
    // TIPO DE VEÍCULO
    // ------------------------------------------------------------------------


    /*
    ! Consulta todos os tipos de veículo registrados no banco de dados.
     */
    public List<Tipo_Veiculo> queryAllTiposVeiculo() { return vehicleTypeQueries.queryAllTiposVeiculo(); }

    /*
    ! Consulta veículos do tipo passageiro e seus dados.
    */
    public List<Object[]> queryAllVeiculosWithTipoPassageiro() { return vehicleTypeQueries.queryAllVeiculosWithTipoPassageiro(); }

    /*
    ! Consulta veículos do tipo carga e seus dados.
    */
    public List<Object[]> queryAllVeiculosWithTipoCarga() { return vehicleTypeQueries.queryAllVeiculosWithTipoCarga(); }

    /*
    ! Consulta tipo de veículo com código de tipo de veículo especificado.
     */
    public Tipo_Veiculo queryTipoVeiculoWithCodTipo(String cod_tipo) { return vehicleTypeQueries.queryTipoVeiculoWithCodTipo(cod_tipo); }

    /*
    ! Consulta tipo de veículo de passageiro com código de tipo de veículo especificado.
     */
    public Tipo_Passageiro queryTipoPassageiroWithCodTipo(String cod_tipo) { return vehicleTypeQueries.queryTipoPassageiroWithCodTipo(cod_tipo); }

    /*
    ! Consulta tipo de veículo de carga com código de tipo de veículo especificado.
     */
    public Tipo_Carga queryTipoCargaWithCodTipo(String cod_tipo) { return vehicleTypeQueries.queryTipoCargaWithCodTipo(cod_tipo); }

    /*
    ! Consulta veículos de tipo passageiro do tamanho especificado.
    */
    public List<Object[]> queryVeiculosTipoPassageiroOfTamanho(String tamanho) { return vehicleTypeQueries.queryVeiculosTipoPassageiroOfTamanho(tamanho); }

    /*
    ! Consulta veículos de tipo passageiro com o número de lugares especificados.
    */
    public List<Object[]> queryVeiculosTipoPassageiroOfLugares(Integer num_lugares) { return vehicleTypeQueries.queryVeiculosTipoPassageiroOfLugares(num_lugares); }

    /*
    ! Consulta veículos de tipo passageiro com número de portas especificado.
     */
    public List<Object[]> queryVeiculosTipoPassageiroOfPortas(Integer num_portas) { return vehicleTypeQueries.queryVeiculosTipoPassageiroOfPortas(num_portas); }

    /*
    ! Consulta veículos de tipo passageiro com ar condicionado e mp3.
     */
    public List<Object[]> queryVeiculosTipoPassageiroWithArCondicionadoAndMp3() { return vehicleTypeQueries.queryVeiculosTipoPassageiroWithArCondicionadoAndMp3(); }

    /*
    ! Consulta veículos de tipo passageiro com direção hidráulica.
     */
    public List<Object[]> queryVeiculosTipoPassageiroWithDirHidraulica() { return vehicleTypeQueries.queryVeiculosTipoPassageiroWithDirHidraulica(); }

    /*
    ! Consulta veículos de tipo passageiro com câmbio automático e sem direção hidráulica.
     */
    public List<Object[]> queryVeiculosTipoPassageiroWithCambioAutomaticoWithoutDirHidraulica() { return vehicleTypeQueries.queryVeiculosTipoPassageiroWithCambioAutomaticoWithoutDirHidraulica(); }

    /*
    ! Consulta veículos de tipo carga com capacidade acima da especificada.
     */
    public List<Object[]> queryVeiculosTipoCargaAboveCapacidade(String capacidade) { return vehicleTypeQueries.queryVeiculosTipoCargaAboveCapacidade(capacidade); }

}
