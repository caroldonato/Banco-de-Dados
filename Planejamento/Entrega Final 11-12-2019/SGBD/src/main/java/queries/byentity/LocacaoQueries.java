package queries.byentity;

import entities.Locacao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
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

    /*
    ! Consulta todas as locações do banco de dados.
     */
    public List<Locacao> queryAllLocacoes()
    {
        try{
            String jpql = "SELECT l FROM Locacao l";
            TypedQuery<Locacao> typedQuery = em.createQuery(jpql, Locacao.class);
            return typedQuery.getResultList();
        }
        catch (Exception e)
        {
            Exception cause = (Exception) e.getCause();
            System.out.println("Erro na consulta queryAllLocacoes: " + cause.getMessage() + ".");
            return null;
        }
    }

    /*
    ! Consulta locação de código de locação especificado.
     */
    public Locacao queryLocacaoWithCodLocacao(Integer cod_locacao)
    {
        try{
            String jpql = "SELECT l from Locacao l WHERE l.cod_locacao = :cod_locacao";
            TypedQuery<Locacao> typedQuery = em.createQuery(jpql, Locacao.class);
            typedQuery.setParameter("cod_locacao", cod_locacao);

            return typedQuery.getSingleResult();
        }
        catch (Exception e)
        {
            Exception cause = (Exception) e.getCause();
            System.out.println("Erro na consulta queryLocacaoWithCodLocacao: " + cause.getMessage() + ".");
            return null;
        }
    }

    /*
    ! Consulta todos os nomes de clientes e placas de veículos de locações
      da filial especificada que tem quilometragem atual superior à especificada.
     */
    public List<Object[]> queryLocacaoOverKmWithCodFilialDest(String cod_filial, Integer km)
    {
        try{
            String jpql = "SELECT c.nome, v.cod_placa FROM Locacao l" +
                    " JOIN Veiculo v ON l.veiculo.cod_placa = v.cod_placa" +
                    " JOIN Motorista m ON l.motorista.cod_motorista = m.cod_motorista" +
                    " JOIN Cliente c ON m.cliente.cod_cliente = c.cod_cliente" +
                    " WHERE v.km_atual > :km AND l.filila_dest.cod_filial = :cod_filial";

            TypedQuery<Object[]> typedQuery = em.createQuery(jpql, Object[].class);
            typedQuery.setParameter("km", km);
            typedQuery.setParameter("cod_filial", cod_filial);
            return typedQuery.getResultList();
        }
        catch (Exception e)
        {
            Exception cause = (Exception) e.getCause();
            System.out.println("Erro na consulta queryLocacaoOverKmWithCodFilialDest: " + cause.getMessage() + ".");
            return null;
        }
    }

    /*
    ! Consulta todos os nomes de clientes e número de habilitação dos motoristas de
      locações feitas antes da data especificada.
     */
    public List<Object[]> queryLocacaoBeforeDataEntrega(LocalDate data)
    {
        try{
            String jpql = "SELECT c.nome, m.ident_motorista FROM Locacao l" +
                    " JOIN Motorista m ON l.motorista.cod_motorista = m.cod_motorista" +
                    " JOIN Cliente c ON m.cliente.cod_cliente = c.cod_cliente" +
                    " WHERE l.data_entrega = :data";

            TypedQuery<Object[]> typedQuery = em.createQuery(jpql, Object[].class);
            typedQuery.setParameter("data", data);
            return typedQuery.getResultList();
        }
        catch (Exception e)
        {
            Exception cause = (Exception) e.getCause();
            System.out.println("Erro na consulta queryLocacaoBeforeDataEntrega: " + cause.getMessage() + ".");
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
