package queries;

import entities.*;
import queries.byentity.*;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class Insertion {

    private EntityManager em;
    private static Insertion instance = null;

    // ========================================================================
    /* CONSTRUCTORS & SINGLETON IMPLEMENTATION */
    // ========================================================================

    public Insertion(EntityManager entityManager) {
        this.em = entityManager;
    }

    public static Insertion getInstance(EntityManager entityManager) {
        if(instance == null) { instance = new Insertion(entityManager); }
        return instance;
    }

    // ========================================================================
    /* BASIC INSERTION */
    // ========================================================================

    /*
    ! Insere qualquer objeto persistente no banco de dados.
     */
    public void insertEntity(Object o) // Basic insertion of any persistent object
    {
        em.getTransaction().begin();
        em.persist(o);
        em.getTransaction().commit();
    }

    /*
    ! Insere lista de quaisquer objetos persistentes no banco de dados.
     */
    public void insertAllEntities(List<Object> objects) // Basic insertion of multiple objects
    {
        em.getTransaction().begin();
        for(Object o : objects) { em.persist(o); }
        em.getTransaction().commit();
    }

    // ========================================================================
    /* POPULATE DATABASE FUNCTION */
    // ========================================================================

    /*
    ! Popula o banco de dados com dados iniciais padronizados.
     */
    public void populateTables()
    {
        List<Object> objects = new ArrayList<>();

        // Clientes

        Pessoa_Fisica pessoa_fisica = new Pessoa_Fisica("Fulano de Tal", "Rua Aquela Lá, 1212",
                "X", LocalDate.of(2000, 5, 21), 123142L);
        Pessoa_Juridica pessoa_juridica = new Pessoa_Juridica("Deltrano de Tal",
                "Rua Aquela Outra, 1313",1312412L,12345643L);

        // Filiais

        Filial filial = new Filial("Filial1", "Pindamonhangaba");
        Filial filial2 = new Filial("Filial2", "São Paulo");

        // Tipo_Veículo
        Tipo_Passageiro tipo_passageiro = new Tipo_Passageiro("M2", 5, 10,
                "M", 5, 4, true, false, false, false,
                true, true);

        Tipo_Carga tipo_carga = new Tipo_Carga("G2", 5, 10, 500);

        // Veículo
        Veiculo veiculo = new Veiculo("ABC-1234", tipo_passageiro, filial, "123123",
                "321321", "Vermelho", 12485, true, false);

        Veiculo veiculo2 = new Veiculo("DBE-5678", tipo_carga, filial2, "123435",
                "3453161", "Preto", 100000, true, false);

        // Revisão
        Revisao revisao = new Revisao(tipo_passageiro, 50000);

        // Reserva
        Reserva reserva = new Reserva(tipo_passageiro, filial, filial2, pessoa_juridica,
                LocalDate.of(2020, 11, 15), LocalDate.of(2020, 11, 15));

        // Motorista
        Motorista motorista = new Motorista(pessoa_fisica, 837594875L, 83398483L,
                LocalDate.of(2030, 12, 31));

        // Locação
        Locacao locacao = new Locacao(veiculo, filial2, motorista, LocalDate.of(2020, 1, 15));

        // Inserção
        objects.add(pessoa_fisica);
        objects.add(pessoa_juridica);
        objects.add(filial);
        objects.add(filial2);
        objects.add(tipo_carga);
        objects.add(tipo_passageiro);
        objects.add(veiculo);
        objects.add(veiculo2);
        objects.add(revisao);
        objects.add(reserva);
        objects.add(motorista);
        objects.add(locacao);

        this.insertAllEntities(objects);
    }

    // ========================================================================
    /* INSERTION QUERIES - INSERÇÕES DE ENTIDADE SIMPLES */
    // ========================================================================

    /*
    ! Insere novo cliente como pessoa física no banco de dados.
     */
    public void insertPessoaFisica(String nome, String endereco, String sexo, LocalDate data_nasc, Long cpf)
    {
        try{
            Pessoa_Fisica pessoa_fisica = new Pessoa_Fisica(nome, endereco, sexo, data_nasc, cpf);
            this.insertEntity(pessoa_fisica);
        }
        catch (Exception e)
        {
            Exception cause = (Exception) e.getCause();
            System.out.println("Não foi possível inserir pessoa física de nome " + nome +
                    " e CPF " + cpf + ": " + cause.getMessage() + ".");
        }
    }

    /*
    ! Insere novo cliente como pessoa jurídica no banco de dados.
     */
    public void insertPessoaJuridica(String nome, String endereco, Long cnpj, Long inscr_estado)
    {
        try{
            Pessoa_Juridica pessoa_juridica = new Pessoa_Juridica(nome, endereco, cnpj, inscr_estado);
            this.insertEntity(pessoa_juridica);
        }
        catch (Exception e)
        {
            Exception cause = (Exception) e.getCause();
            System.out.println("Não foi possível inserir pessoa jurídica de nome " +
                    nome + " e CNPJ " + cnpj + ": " + cause.getMessage() + ".");
        }
    }

    /*
    ! Insere novo tipo de veículo de passageiro no banco de dados.
     */
    public void insertTipoPassageiro(String cod_tipo, Integer horas_limpeza, Integer horas_revisao,
                                     String tamanho, Integer num_lugares, Integer num_portas,
                                     boolean ar_condicionado, boolean radio, boolean mp3, boolean cd,
                                     boolean dir_hidr, boolean cambio_auto)
    {
        try{
            Tipo_Passageiro tipo_passageiro = new Tipo_Passageiro(cod_tipo, horas_limpeza, horas_revisao,
                    tamanho, num_lugares, num_portas, ar_condicionado, radio, mp3, cd, dir_hidr, cambio_auto);

            this.insertEntity(tipo_passageiro);
        }
        catch (Exception e)
        {
            Exception cause = (Exception) e.getCause();
            System.out.println("Não foi possível inserir tipo de veículo de código" + cod_tipo +
                    ": " + cause.getMessage() + ".");
        }
    }

    /*
    ! Insere novo tipo de veículo de carga no banco de dados.
     */
    public void insertTipoCarga(String cod_tipo, Integer horas_limpeza, Integer horas_revisao, Integer capacidade)
    {
        try{
            Tipo_Carga tipo_carga = new Tipo_Carga(cod_tipo, horas_limpeza, horas_revisao, capacidade);
            this.insertEntity(tipo_carga);
        }
        catch (Exception e)
        {
            Exception cause = (Exception) e.getCause();
            System.out.println("Não foi possível inserir tipo de veículo de código" + cod_tipo +
                    ": " + cause.getMessage() + ".");
        }
    }

    /*
    ! Insere nova filial no banco de dados.
     */
    public void insertFilial(String cod_filial, String localizacao)
    {
        try{
            Filial filial = new Filial(cod_filial, localizacao);
            this.insertEntity(filial);
        }
        catch (Exception e)
        {
            Exception cause = (Exception) e.getCause();
            System.out.println("Não foi possível inserir filial de código " + cod_filial +
                    ": " + cause.getMessage() + ".");
        }
    }

    /*
    ! Insere novo veículo no banco de dados.
     */
    public void insertVeiculo(String cod_placa, Tipo_Veiculo tipo, Filial filial_atual, String num_chassi,
                              String num_motor, String cor, Integer km_atual, boolean revisao_pendente, boolean parado)
    {
        try{
            Veiculo veiculo = new Veiculo(cod_placa, tipo, filial_atual, num_chassi, num_motor, cor,
                    km_atual, revisao_pendente, parado);
            this.insertEntity(veiculo);
        }
        catch (Exception e)
        {
            Exception cause = (Exception) e.getCause();
            System.out.println("Não foi possível inserir veículo de placa " +
                    cod_placa + ": " + cause.getMessage() + ".");
        }
    }

    /*
    ! Insere nova revisão no banco de dados.
     */
    public void insertRevisao(Tipo_Veiculo tipo, Integer km_media)
    {
        try{
            Revisao revisao = new Revisao(tipo, km_media);
            this.insertEntity(revisao);
        }
        catch (Exception e)
        {
            Exception cause = (Exception) e.getCause();
            System.out.println("Não foi possível remover revisão de código de tipo " +
                     tipo.getCod_tipo() + ": " + cause.getMessage() + ".");
        }
    }

    /*
    ! Insere nova locação no banco de dados.
     */
    public void insertLocacao(String cod_placa, String cod_filial_dest, Integer cod_motorista, LocalDate data_entrega)
    {
        try{
            VehicleQueries vehicleQueries = new VehicleQueries(this.em);
            FilialQueries filialQueries = new FilialQueries(this.em);
            MotoristaQueries motoristaQueries = new MotoristaQueries(this.em);

            Veiculo veiculo = vehicleQueries.queryVeiculoWithCodPlaca(cod_placa);
            Filial filial = filialQueries.queryFilialWithCodFilial(cod_filial_dest);
            Motorista motorista = motoristaQueries.queryMotoristaWithCodMotorista(cod_motorista);

            Locacao locacao = new Locacao(veiculo, filial, motorista, data_entrega);
        }
        catch (Exception e)
        {
            Exception cause = (Exception) e.getCause();
            System.out.println("Não foi possível remover locação de código de placa " + cod_placa +
                    ": " + cause.getMessage() + ".");
        }
    }

    /*
    ! Insere nova reserva no banco de dados.
     */
    public void insertReserva(String cod_tipo, String cod_filial_dest, String cod_filial_orig, String cod_cliente,
                              LocalDate data_entrega, LocalDate data_retirada)
    {
        try{
            VehicleTypeQueries vehicleTypeQueries = new VehicleTypeQueries(this.em);
            FilialQueries filialQueries = new FilialQueries(this.em);
            ClienteQueries clienteQueries = new ClienteQueries(this.em);

            Tipo_Veiculo tipo_veiculo = vehicleTypeQueries.queryTipoVeiculoWithCodTipo(cod_tipo);
            Filial filial_dest = filialQueries.queryFilialWithCodFilial(cod_filial_dest);
            Filial filial_orig = filialQueries.queryFilialWithCodFilial(cod_filial_orig);
            Cliente cliente = clienteQueries.queryClienteWithCodCliente(cod_cliente);

            Reserva reserva = new Reserva(tipo_veiculo, filial_dest, filial_orig, cliente, data_entrega, data_retirada);
            this.insertEntity(reserva);
        }
        catch (Exception e)
        {
            Exception cause = (Exception) e.getCause();
            System.out.println("Não foi possível remover reserva de código de tipo " + cod_tipo +
                    ": " + cause.getMessage() + ".");
        }

    }


    // ========================================================================
    /* INSERTION QUERIES - INSERÇÕES DE ENTIDADE COM CRITÉRIOS */
    // ========================================================================

    /*
    ! Insere novo motorista para cliente pessoa física com nome e CPF fornecidos.
     */
    public void insertMotoristaForPessoaFisicaUsingNomeAndCpf(Motorista m, String name, Long cpf)
    {
        try{
            ClienteQueries clienteQueries = new ClienteQueries(this.em);
            Pessoa_Fisica pfisica = clienteQueries.queryPessoaFisicaWithNameAndCpf(name, cpf);
            m.setCliente(pfisica);
            this.insertEntity(m);
        }
        catch (Exception e) {
            Exception cause = (Exception) e.getCause();
            System.out.println("Não foi possível inserir motorista: " + cause.getMessage() + ".");
        }
    }

    /*
    ! Insere nova reserva para cliente pessoa jurídica com nome e CNPJ fornecidos.
     */
    public void insertReservaForPessoaJuridicaUsingNomeAndCnpj(Reserva r, String name, Long cnpj)
    {
        try{
            ClienteQueries clienteQueries = new ClienteQueries(this.em);
            Pessoa_Juridica pjuridica = clienteQueries.queryPessoaJuridicaWithNameAndCnpj(name, cnpj);
            r.setCliente(pjuridica);
            this.insertEntity(r);
        }
        catch (Exception e){
            Exception cause = (Exception) e.getCause();
            System.out.println("Não foi possível inserir reserva: " + cause.getMessage() + ".");
        }
    }

    /*
    ! Insere locação de motorista por número de habilitação.
     */
    public void insertLocacaoForMotoristaWithNumHabiliAndCodPlacaAndFilial(String cod_placa, String cod_filial_dest,
                                                                            Long num_habili, LocalDate data_entrega)
    {
        try{
            VehicleQueries vehicleQueries = new VehicleQueries(this.em);
            FilialQueries filialQueries = new FilialQueries(this.em);
            MotoristaQueries motoristaQueries = new MotoristaQueries(this.em);

            Veiculo veiculo = vehicleQueries.queryVeiculoWithCodPlaca(cod_placa);
            Filial filial = filialQueries.queryFilialWithCodFilial(cod_filial_dest);
            Motorista motorista = motoristaQueries.queryMotoristaWithNumHab(num_habili);

            Locacao locacao = new Locacao(veiculo, filial, motorista, data_entrega);
            this.insertEntity(locacao);
        }
        catch (Exception e)
        {
            Exception cause = (Exception) e.getCause();
            System.out.println("Não foi possível inserir locação para motorista de habilitação " + num_habili +
                    ": " + cause.getMessage() + ".");
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
