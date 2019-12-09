package queries.byentity;

import entities.Motorista;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class MotoristaQueries {
    private EntityManager em;
    private static MotoristaQueries instance = null;

    // ========================================================================
    /* CONSTRUCTORS & SINGLETON IMPLEMENTATION */
    // ========================================================================

    public MotoristaQueries(EntityManager entityManager) {
        this.em = entityManager;
    }

    public static MotoristaQueries getInstance(EntityManager entityManager) {
        if(instance == null) { instance = new MotoristaQueries(entityManager); }
        return instance;
    }

    // ========================================================================
    /* MOTORISTA QUERIES */
    // ========================================================================

    /*
    ! Consulta todos os motoristas do banco de dados.
     */
    public List<Motorista> queryAllMotoristas()
    {
        String jpql = "SELECT m FROM Motorista m";
        TypedQuery<Motorista> typedQuery = em.createQuery(jpql, Motorista.class);
        return typedQuery.getResultList();
    }

    /*
    ! Consulta motorista de código de motorista especificado.
     */
    public Motorista queryMotoristaWithCodMotorista(Integer cod_motorista)
    {
        String jpql = "SELECT m FROM Motorista m WHERE m.cod_motorista = :cod_motorista";
        TypedQuery<Motorista> typedQuery = em.createQuery(jpql, Motorista.class);
        typedQuery.setParameter("cod_motorista", cod_motorista);
        return typedQuery.getSingleResult();
    }

    /*
    ! Consulta motorista de número de habilitação especificado.
     */
    public Motorista queryMotoristaWithNumHab(Long num_hab)
    {
        String jpql = "SELECT m FROM Motorista m WHERE m.num_habili = :num_hab";
        TypedQuery<Motorista> typedQuery = em.createQuery(jpql, Motorista.class);
        typedQuery.setParameter("num_hab", num_hab);

        return typedQuery.getSingleResult();
    }

    /*
    ! Consulta de todos os motoristas com carteira de habilitação vencida.
     */
    public List<Motorista> queryMotoristasWithHabilitacaoVencida()
    {
        LocalDate data = LocalDate.now();
        String jpql = "SELECT m FROM Motorista m WHERE m.vencimento_habili <= :data";
        TypedQuery<Motorista> typedQuery = em.createQuery(jpql, Motorista.class);
        typedQuery.setParameter("data", data);
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
