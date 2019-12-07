package queries.byentity;

import entities.Cliente;
import entities.Pessoa_Fisica;
import entities.Pessoa_Juridica;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ClienteQueries {
    private EntityManager em;
    private static ClienteQueries instance = null;

    // ========================================================================
    /* CONSTRUCTORS & SINGLETON IMPLEMENTATION */
    // ========================================================================

    public ClienteQueries(EntityManager entityManager) {
        this.em = entityManager;
    }

    public static ClienteQueries getInstance(EntityManager entityManager) {
        if(instance == null) { instance = new ClienteQueries(entityManager); }
        return instance;
    }

    // ========================================================================
    /* CLIENT QUERIES */
    // ========================================================================

    public Cliente queryClienteWithCodCliente(String cod_cliente)
    {
        String jpql = "SELECT c FROM Cliente WHERE c.cod_cliente = :cod_cliente";
        TypedQuery<Cliente> typedQuery = em.createQuery(jpql, Cliente.class);
        typedQuery.setParameter("cod_cliente", cod_cliente);

        return typedQuery.getSingleResult();
    }

    public Pessoa_Fisica queryPFisicaWithCodCliente(String cod_cliente)
    {
        String jpql = "SELECT f FROM Pessoa_Fisica f, Cliente c WHERE f.cod_cliente = c.cod_cliente " +
                      "AND c.cod_cliente = :cod_cliente";

        TypedQuery<Pessoa_Fisica> typedQuery = em.createQuery(jpql, Pessoa_Fisica.class);
        typedQuery.setParameter("cod_cliente", cod_cliente);

        return typedQuery.getSingleResult();
    }

    public Pessoa_Juridica queryPJuridicaWithCodCliente(String cod_cliente)
    {
        String jpql = "SELECT j FROM Pessoa_Juridica j, Cliente c WHERE j.cod_cliente = c.cod_cliente " +
                      "AND c.cod_cliente = :cod_cliente";

        TypedQuery<Pessoa_Juridica> typedQuery = em.createQuery(jpql, Pessoa_Juridica.class);
        typedQuery.setParameter("cod_cliente", cod_cliente);

        return typedQuery.getSingleResult();
    }

   // ---------------------------------------------------------------------

    public Pessoa_Fisica queryPFisicaWithNameAndCpf(String name, Long cpf)
    {
        String jpql = "SELECT f FROM Pessoa_Fisica f, Cliente c WHERE f.cod_cliente = c.cod_cliente " +
                      "AND c.nome = :nome AND f.cpf = :cpf";

        TypedQuery<Pessoa_Fisica> typedQuery = em.createQuery(jpql, Pessoa_Fisica.class);
        typedQuery.setParameter("nome", name);
        typedQuery.setParameter("cpf", cpf);

        return typedQuery.getSingleResult();
    }

    public Pessoa_Juridica queryPJuridicaWithNameAndCnpj(String name, Long cnpj)
    {
        String jpql = "SELECT j FROM Pessoa_Juridica j, Cliente c WHERE j.cod_cliente = c.cod_cliente " +
                "AND c.nome = :nome AND j.cnpj = :cnpj";

        TypedQuery<Pessoa_Juridica> typedQuery = em.createQuery(jpql, Pessoa_Juridica.class);
        typedQuery.setParameter("nome", name);
        typedQuery.setParameter("cnpj", cnpj);

        return typedQuery.getSingleResult();
    }

    // ========================================================================
    public void sampleQuery()
    {
        String jpql = "select f from Pessoa_Fisica f";
        TypedQuery<Pessoa_Fisica> typedQuery = em.createQuery(jpql, Pessoa_Fisica.class);
        List<Pessoa_Fisica> result = typedQuery.getResultList();
        result.forEach(f -> System.out.println("Código: " + f.getCod_cliente() + " | Nome : " +
                f.getNome() + " | Endereço: " + f.getEndereco()));
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
