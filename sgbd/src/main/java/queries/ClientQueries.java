package queries;

import entities.Pessoa_Fisica;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ClientQueries {
    private EntityManager em;
    private static ClientQueries instance = null;

    /* CONSTRUCTORS & SINGLETON IMPLEMENTATION */
    public ClientQueries(EntityManager entityManager) {
        this.em = entityManager;
    }

    public static ClientQueries getInstance(EntityManager entityManager) {
        if(instance == null) { instance = new ClientQueries(entityManager); }
        return instance;
    }

    /* CLIENT QUERIES */
    public Pessoa_Fisica queryPFisicaWithNameAndCpf(String name, Long cpf)
    {
        String jpql = "SELECT f FROM Pessoa_Fisica f, Cliente c WHERE f.cod_cliente = c.cod_cliente " +
                      "AND c.nome = :nome AND f.cpf = :cpf";

        TypedQuery<Pessoa_Fisica> typedQuery = em.createQuery(jpql, Pessoa_Fisica.class);
        typedQuery.setParameter("nome", name);
        typedQuery.setParameter("cpf", cpf);

        return typedQuery.getSingleResult();
    }

    public void sampleQuery()
    {
        String jpql = "select f from Pessoa_Fisica f";
        TypedQuery<Pessoa_Fisica> typedQuery = em.createQuery(jpql, Pessoa_Fisica.class);
        List<Pessoa_Fisica> result = typedQuery.getResultList();
        result.forEach(f -> System.out.println("Código: " + f.getCod_cliente() + " | Nome : " +
                f.getNome() + " | Endereço: " + f.getEndereco()));
    }

    /* GETTERS & SETTERS */
    public EntityManager getEm() {
        return em;
    }
    public void setEm(EntityManager em) {
        this.em = em;
    }
}
