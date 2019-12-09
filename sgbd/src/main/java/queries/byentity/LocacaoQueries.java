package queries.byentity;

import entities.Locacao;
import views.LocacoesRecentes;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@SuppressWarnings("unused")
public class LocacaoQueries {

    private EntityManager em;
    private static LocacaoQueries instance = null;

    // ========================================================================
    /* CONSTRUCTORS & SINGLETON IMPLEMENTATION */
    // ========================================================================

    public LocacaoQueries(EntityManager entityManager) {
        this.em = entityManager;
    }

    public static LocacaoQueries getInstance(EntityManager entityManager) {
        if(instance == null) { instance = new LocacaoQueries(entityManager); }
        return instance;
    }

    // ========================================================================
    /* LOCACAO QUERIES */
    // ========================================================================

    public List<Locacao> queryAllLocacoes()
    {
        String jpql = "SELECT l FROM Locacao l";
        TypedQuery<Locacao> typedQuery = em.createQuery(jpql, Locacao.class);
        return typedQuery.getResultList();
    }

    public Locacao queryLocacaoWithCodLocacao(Integer cod_locacao)
    {
        String jpql = "SELECT l from Locacao l WHERE l.cod_locacao = :cod_locacao";
        TypedQuery<Locacao> typedQuery = em.createQuery(jpql, Locacao.class);
        typedQuery.setParameter("cod_locacao", cod_locacao);

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
