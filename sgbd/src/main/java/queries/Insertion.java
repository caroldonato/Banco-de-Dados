package queries;

import entities.*;
import queries.byentity.ClienteQueries;

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

    public void insertEntity(Object o) // Basic insertion of any persistent object
    {
        em.getTransaction().begin();
        em.persist(o);
        em.getTransaction().commit();
    }

    public void insertAllEntities(List<Object> objects) // Basic insertion of multiple objects
    {
        em.getTransaction().begin();
        for(Object o : objects) { em.persist(o); }
        em.getTransaction().commit();
    }

    // ========================================================================
    /* POPULATE DATABASE FUNCTION */
    // ========================================================================

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
    /* INSERTION QUERIES */
    // ========================================================================

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
