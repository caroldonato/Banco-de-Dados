package queries.byentity;

import entities.Cliente;
import entities.Pessoa_Fisica;
import entities.Pessoa_Juridica;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

@SuppressWarnings("unused")
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

    /*
    ! Consulta todos os clientes do banco de dados.
     */
    public List<Cliente> queryAllClientes()
    {
        try{
            String jpql = "SELECT c FROM Cliente c";
            TypedQuery<Cliente> typedQuery = em.createQuery(jpql, Cliente.class);
            return typedQuery.getResultList();
        }
        catch (Exception e) {
            Exception cause = (Exception) e.getCause();
            System.out.println("Erro na consulta queryAllClientes: " + cause.getMessage() + ".");
            return null;
        }
    }

    /*
    ! Consulta cliente utilizando código de cliente especificado.
     */
    public Cliente queryClienteWithCodCliente(String cod_cliente)
    {
        try {
            String jpql = "SELECT c FROM Cliente WHERE c.cod_cliente = :cod_cliente";
            TypedQuery<Cliente> typedQuery = em.createQuery(jpql, Cliente.class);
            typedQuery.setParameter("cod_cliente", cod_cliente);
            return typedQuery.getSingleResult();
        }
        catch (Exception e) {
            Exception cause = (Exception) e.getCause();
            System.out.println("Erro na consulta queryClienteWithCodCliente: " + cause.getMessage() + ".");
            return null;
        }
    }

    /*
    ! Consulta cliente de tipo pessoa física e seus dados utilizando código de cliente.
     */
    public Pessoa_Fisica queryPessoaFisicaWithCodCliente(String cod_cliente)
    {
        try{
            String jpql = "SELECT f FROM Pessoa_Fisica f, Cliente c WHERE f.cod_cliente = c.cod_cliente " +
                    "AND c.cod_cliente = :cod_cliente";

            TypedQuery<Pessoa_Fisica> typedQuery = em.createQuery(jpql, Pessoa_Fisica.class);
            typedQuery.setParameter("cod_cliente", cod_cliente);

            return typedQuery.getSingleResult();
        }
        catch (Exception e) {
            Exception cause = (Exception) e.getCause();
            System.out.println("Erro na consulta queryPessoaFisicaWithCodCliente: " + cause.getMessage() + ".");
            return null;
        }
    }

    /*
    ! Consulta cliente de tipo pessoa jurídica e seus dados utilizando código de cliente.
     */
    public Pessoa_Juridica queryPessoaJuridicaWithCodCliente(String cod_cliente)
    {
        try{
            String jpql = "SELECT j FROM Pessoa_Juridica j, Cliente c WHERE j.cod_cliente = c.cod_cliente " +
                    "AND c.cod_cliente = :cod_cliente";

            TypedQuery<Pessoa_Juridica> typedQuery = em.createQuery(jpql, Pessoa_Juridica.class);
            typedQuery.setParameter("cod_cliente", cod_cliente);

            return typedQuery.getSingleResult();
        }
        catch (Exception e) {
            Exception cause = (Exception) e.getCause();
            System.out.println("Erro na consulta queryPessoaJuridicaWithCodCliente: " + cause.getMessage() + ".");
            return null;
        }
    }

    /*
    ! Consulta todos os clientes que possuem locação de veículos de carga registradas.
     */
    public List<Cliente> queryClientesComLocacaoDeTipoCarga()
    {
        try{
            String jpql = "SELECT DISTINCT c FROM" +
                    " Locacao l JOIN Veiculo v ON l.veiculo.cod_placa = v.cod_placa" +
                    " JOIN Tipo_Carga t ON v.tipo.cod_tipo = t.cod_tipo" +
                    " JOIN Motorista m ON l.motorista.cod_motorista = m.cod_motorista" +
                    " JOIN Cliente c ON m.cliente.cod_cliente = c.cod_cliente";

            TypedQuery<Cliente> typedQuery = em.createQuery(jpql, Cliente.class);
            return typedQuery.getResultList();
        }
        catch (Exception e) {
            Exception cause = (Exception) e.getCause();
            System.out.println("Erro na consulta queryClientesComLocacaoDeTipoCarga: " + cause.getMessage() + ".");
            return null;
        }
    }

    /*
    ! Consulta todos os clientes que possuem alguma reserva em seu nome.
     */
    public List<Cliente> queryClientesComReservas()
    {
        try{
            String jpql = "SELECT DISTINCT c FROM Cliente c JOIN ReservasRecentes r" +
                    " ON c.cod_cliente = r.cliente.cod_cliente";
            TypedQuery<Cliente> typedQuery = em.createQuery(jpql, Cliente.class);
            return typedQuery.getResultList();
        }
        catch (Exception e)
        {
            Exception cause = (Exception) e.getCause();
            System.out.println("Erro na consulta queryClientesComReservas: " + cause.getMessage() + ".");
            return null;
        }
    }

    /*
    ! Consulta cliente que possui a locação do veículo de código de placa especificado.
     */
    public Cliente queryClienteComLocacaoWithCodPlaca(String cod_placa)
    {
        try{
            String jpql = "SELECT c FROM LocacoesRecentes l" +
                    " JOIN Veiculo v ON l.veiculo.cod_placa = v.cod_placa" +
                    " JOIN Motorista m ON l.motorista.cod_motorista = m.cod_motorista" +
                    " JOIN Cliente c ON m.cliente.cod_cliente = c.cod_cliente" +
                    " WHERE v.cod_placa = :cod_placa AND v.parado = false";

            TypedQuery<Cliente> typedQuery = em.createQuery(jpql, Cliente.class);
            typedQuery.setParameter("cod_placa", cod_placa);
            return typedQuery.getSingleResult();
        }
        catch (Exception e)
        {
            Exception cause = (Exception) e.getCause();
            System.out.println("Erro na consulta queryClienteComLocacaoWithCodPlaca: " + cause.getMessage() + ".");
            return null;
        }
    }

    /*
    ! Consulta cliente de tipo pessoa física utilizando nome e CPF.
     */
    public Pessoa_Fisica queryPessoaFisicaWithNameAndCpf(String name, Long cpf)
    {
        try{
            String jpql = "SELECT f FROM Pessoa_Fisica f, Cliente c WHERE f.cod_cliente = c.cod_cliente " +
                    "AND c.nome = :nome AND f.cpf = :cpf";

            TypedQuery<Pessoa_Fisica> typedQuery = em.createQuery(jpql, Pessoa_Fisica.class);
            typedQuery.setParameter("nome", name);
            typedQuery.setParameter("cpf", cpf);

            return typedQuery.getSingleResult();
        }
        catch (Exception e)
        {
            Exception cause = (Exception) e.getCause();
            System.out.println("Erro na consulta queryPessoaFisicaWithNameAndCpf: " + cause.getMessage() + ".");
            return null;
        }
    }

    /*
    ! Consulta cliente de tipo pessoa jurídica utilizando nome e CNPJ.
     */
    public Pessoa_Juridica queryPessoaJuridicaWithNameAndCnpj(String name, Long cnpj)
    {
        try{
            String jpql = "SELECT j FROM Pessoa_Juridica j, Cliente c WHERE j.cod_cliente = c.cod_cliente " +
                    "AND c.nome = :nome AND j.cnpj = :cnpj";

            TypedQuery<Pessoa_Juridica> typedQuery = em.createQuery(jpql, Pessoa_Juridica.class);
            typedQuery.setParameter("nome", name);
            typedQuery.setParameter("cnpj", cnpj);

            return typedQuery.getSingleResult();
        }
        catch (Exception e)
        {
            Exception cause = (Exception) e.getCause();
            System.out.println("Erro na consulta queryPessoaFisicaWithNameAndCpf: " + cause.getMessage() + ".");
            return null;
        }
    }

    /*
    ! Consulta todas as pessoas físicas nascidas após ano especificado.
     */
    public List<Pessoa_Fisica> queryPessoaFisicaNascidaDepoisDeAno(Integer ano)
    {
        try{
            LocalDate data = LocalDate.of(ano, 1, 1);
            String jpql = "SELECT f FROM Pessoa_Fisica f WHERE f.data_nasc > :data";
            TypedQuery<Pessoa_Fisica> typedQuery = em.createQuery(jpql, Pessoa_Fisica.class);
            typedQuery.setParameter("data", data);
            return typedQuery.getResultList();
        }
        catch (Exception e)
        {
            Exception cause = (Exception) e.getCause();
            System.out.println("Erro na consulta queryPessoaFisicaWithNameAndCpf: " + cause.getMessage() + ".");
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
