package queries;

import entities.*;
import queries.byentity.ClienteQueries;

import javax.persistence.EntityManager;

@SuppressWarnings("unused")
public class Insertion {
    private EntityManager em;
    private static Insertion instance = null;

    /* CONSTRUCTORS & SINGLETON IMPLEMENTATION */
    public Insertion(EntityManager entityManager) {
        this.em = entityManager;
    }

    public static Insertion getInstance(EntityManager entityManager) {
        if(instance == null) { instance = new Insertion(entityManager); }
        return instance;
    }

    /* BASIC INSERTION */

    public void insertEntity(Object o) // Basic insertion of any persistent object
    {
        em.getTransaction().begin();
        em.persist(o);
        em.getTransaction().commit();
    }

    /* DATABASE INITIAL POPULATION */
    public void populateTables()
    {
        Pessoa_Fisica cliente = new Pessoa_Fisica();
        cliente.setNome("Fulano de Tal");
        cliente.setEndereco("Rua Aquela Lá, 1212");
        cliente.setSexo("X");
        cliente.setData_nasc(java.time.LocalDate.now());
        cliente.setCpf(123142L);

        Pessoa_Juridica cliente2 = new Pessoa_Juridica();
        cliente2.setNome("Deltrano de Tal");
        cliente2.setEndereco("Rua Aquela Outra, 1313");
        cliente2.setCnpj(1312412L);
        cliente2.setInscr_estado(12345643L);

        this.insertEntity(cliente);
        this.insertEntity(cliente2);
    }

    /* INSERTION QUERIES */

    public void insertMotoristaForPessoaFisicaUsingCpfAndName(Motorista m, String name, Long cpf)
    {
        try{
            ClienteQueries clienteQueries = new ClienteQueries(this.em);
            Pessoa_Fisica pfisica = clienteQueries.queryPFisicaWithNameAndCpf(name, cpf);
            m.setCliente(pfisica);
            this.insertEntity(m);
        }
        catch (Exception e) {
            Exception cause = (Exception) e.getCause();
            System.out.println("Não foi possível inserir motorista: " + cause.getMessage() + ".");
        }
    }


    /* GETTERS & SETTERS */
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
