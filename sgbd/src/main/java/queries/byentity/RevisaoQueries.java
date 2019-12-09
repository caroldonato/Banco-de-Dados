package queries.byentity;

import entities.Revisao;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class RevisaoQueries {

    private EntityManager em;
    private static RevisaoQueries instance = null;

    // ========================================================================
    /* CONSTRUCTORS & SINGLETON IMPLEMENTATION */
    // ========================================================================

    public RevisaoQueries(EntityManager entityManager) {
        this.em = entityManager;
    }

    public static RevisaoQueries getInstance(EntityManager entityManager) {
        if(instance == null) { instance = new RevisaoQueries(entityManager); }
        return instance;
    }

    // ========================================================================
    /* REVISAO QUERIES */
    // ========================================================================

    public List<Revisao> queryAllRevisoes()
    {
        String jpql = "SELECT r FROM Revisao r";
        TypedQuery<Revisao> typedQuery = em.createQuery(jpql, Revisao.class);
        return typedQuery.getResultList();
    }

    public Revisao queryRevisaoWithCodTipoAndCodRevisao(String cod_tipo, Integer cod_revisao)
    {
        String jpql = "SELECT r FROM Revisao r, Tipo_Veiculo t WHERE " +
                      "r.cod_tipo = t.cod_tipo AND " +
                      "t.cod_tipo = :cod_tipo AND " +
                      "r.cod_revisao = :cod_revisao";
        TypedQuery<Revisao> typedQuery = em.createQuery(jpql, Revisao.class);
        typedQuery.setParameter("cod_tipo", cod_tipo);
        typedQuery.setParameter("cod_revisao", cod_revisao);

        return typedQuery.getSingleResult();
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
