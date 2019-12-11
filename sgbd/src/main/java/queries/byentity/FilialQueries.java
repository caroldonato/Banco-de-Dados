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
        try{
            String jpql = "SELECT f FROM Filial f";
            TypedQuery<Filial> typedQuery = em.createQuery(jpql, Filial.class);
            return typedQuery.getResultList();
        }
        catch (Exception e)
        {
            Exception cause = (Exception) e.getCause();
            System.out.println("Erro na consulta queryAllFiliais: " + cause.getMessage() + ".");
            return null;
        }
    }

    /*
    ! Consulta filial de código de filial especificado.
     */
    public Filial queryFilialWithCodFilial(String cod_filial)
    {
        try{
            String jpql = "SELECT f FROM Filial f WHERE f.cod_filial = :cod_filial";
            TypedQuery<Filial> typedQuery = em.createQuery(jpql, Filial.class);
            typedQuery.setParameter("cod_filial", cod_filial);

            return typedQuery.getSingleResult();
        }
        catch (Exception e)
        {
            Exception cause = (Exception) e.getCause();
            System.out.println("Erro na consulta queryFilialWithCodFilial: " + cause.getMessage() + ".");
            return null;
        }
    }

    /*
    ! Consulta todas as filiais da localização especificada.
     */
    public List<Filial> queryFilialWithLocalizacao(String localizacao)
    {
        try{
            String jpql = "SELECT f FROM Filial WHERE f.localizacao = :localizacao";
            TypedQuery<Filial> typedQuery = em.createQuery(jpql, Filial.class);
            typedQuery.setParameter("localizacao", localizacao);

            return typedQuery.getResultList();
        }
        catch (Exception e)
        {
            Exception cause = (Exception) e.getCause();
            System.out.println("Erro na consulta queryFilialWithLocalizacao: " + cause.getMessage() + ".");
            return null;
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
