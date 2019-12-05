package queries;

import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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

    /* INSERTION QUERIES */

    public void insertEntity(Object o) // Basic insertion of any persistent object
    {
        em.getTransaction().begin();
        em.persist(o);
        em.getTransaction().commit();
    }

    public void insertMotoristaForPFisicaUsingCpfAndName(Motorista m, String name, Integer cpf)
    {
        ClientQueries clientQueries = new ClientQueries(this.em);
        P_Fisica pfisica = clientQueries.queryPFisicaWithNameAndCpf(name, cpf);
        m.setCliente(pfisica);
        this.insertEntity(m);
    }

    /* DATABASE INITIAL POPULATION */
    public void populateTables()
    {
        P_Fisica cliente = new P_Fisica();
        cliente.setCod_cliente(1);
        cliente.setNome("Fulano de Tal");
        cliente.setEndereco("Rua Aquela Lá, 1212");
        cliente.setSexo("X");
        cliente.setData_nasc(java.time.LocalDate.now());
        cliente.setCpf(123142);

        P_Juridica cliente2 = new P_Juridica();
        cliente2.setCod_cliente(2);
        cliente2.setNome("Deltrano de Tal");
        cliente2.setEndereco("Rua Aquela Outra, 1313");
        cliente2.setCnpj(1312412);
        cliente2.setInscr_estado("A123BACa_13");

        this.insertEntity(cliente);
        this.insertEntity(cliente2);
    }


    /* GETTERS & SETTERS */
    public EntityManager getEm() {
        return em;
    }
    public void setEm(EntityManager em) {
        this.em = em;
    }
}
