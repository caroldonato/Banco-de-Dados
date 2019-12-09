package queries.byentity;

import entities.Filial;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class FilialQueries {

    private EntityManager em;
    private static FilialQueries instance = null;

    // ========================================================================
    /* CONSTRUCTORS & SINGLETON IMPLEMENTATION */
    // ========================================================================

    public FilialQueries(EntityManager entityManager) {
        this.em = entityManager;
    }

    public static FilialQueries getInstance(EntityManager entityManager) {
        if(instance == null) { instance = new FilialQueries(entityManager); }
        return instance;
    }

    // ========================================================================
    /* FILIAL QUERIES */
    // ========================================================================

    /*
    ! Consulta todas as filiais do banco de dados.
     */
    public List<Filial> queryAllFiliais()
    {
        String jpql = "SELECT f FROM Filial";
        TypedQuery<Filial> typedQuery = em.createQuery(jpql, Filial.class);
        return typedQuery.getResultList();
    }

    /*
    ! Consulta filial de código de filial especificado.
     */
    public Filial queryFilialWithCodFilial(String cod_filial)
    {
        String jpql = "SELECT f FROM Filial f WHERE f.cod_filial = :cod_filial";
        TypedQuery<Filial> typedQuery = em.createQuery(jpql, Filial.class);
        typedQuery.setParameter("cod_filial", cod_filial);

        return typedQuery.getSingleResult();
    }

    /*
    ! Consulta todas as filiais da localização especificada.
     */
    public List<Filial> queryFilialWithLocalizacao(String localizacao)
    {
        String jpql = "SELECT f FROM Filial WHERE f.localizacao = :localizacao";
        TypedQuery<Filial> typedQuery = em.createQuery(jpql, Filial.class);
        typedQuery.setParameter("localizacao", localizacao);

        return typedQuery.getResultList();
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
