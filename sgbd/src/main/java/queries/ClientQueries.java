package queries;

import entities.P_Fisica;

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
    public P_Fisica queryPFisicaWithNameAndCpf(String name, Integer cpf)
    {
        String jpql = "SELECT f FROM P_Fisica f, Cliente c WHERE f.cod_cliente = c.cod_cliente " +
                      "AND c.nome = :nome AND f.cpf = :cpf";

        TypedQuery<P_Fisica> typedQuery = em.createQuery(jpql, P_Fisica.class);
        typedQuery.setParameter("nome", name);
        typedQuery.setParameter("cpf", cpf);

        return typedQuery.getSingleResult();
    }

    public void sampleQuery()
    {
        String jpql = "select f from P_Fisica f";
        TypedQuery<P_Fisica> typedQuery = em.createQuery(jpql, P_Fisica.class);
        List<P_Fisica> result = typedQuery.getResultList();
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
