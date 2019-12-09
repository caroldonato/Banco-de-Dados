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

    public List<Cliente> queryAllClientes()
    {
        String jpql = "SELECT c FROM Cliente";
        TypedQuery<Cliente> typedQuery = em.createQuery(jpql, Cliente.class);
        return typedQuery.getResultList();
    }

    public Cliente queryClienteWithCodCliente(String cod_cliente)
    {
        String jpql = "SELECT c FROM Cliente WHERE c.cod_cliente = :cod_cliente";
        TypedQuery<Cliente> typedQuery = em.createQuery(jpql, Cliente.class);
        typedQuery.setParameter("cod_cliente", cod_cliente);

        return typedQuery.getSingleResult();
    }

    public Pessoa_Fisica queryPessoaFisicaWithCodCliente(String cod_cliente)
    {
        String jpql = "SELECT f FROM Pessoa_Fisica f, Cliente c WHERE f.cod_cliente = c.cod_cliente " +
                      "AND c.cod_cliente = :cod_cliente";

        TypedQuery<Pessoa_Fisica> typedQuery = em.createQuery(jpql, Pessoa_Fisica.class);
        typedQuery.setParameter("cod_cliente", cod_cliente);

        return typedQuery.getSingleResult();
    }

    public Pessoa_Juridica queryPessoaJuridicaWithCodCliente(String cod_cliente)
    {
        String jpql = "SELECT j FROM Pessoa_Juridica j, Cliente c WHERE j.cod_cliente = c.cod_cliente " +
                      "AND c.cod_cliente = :cod_cliente";

        TypedQuery<Pessoa_Juridica> typedQuery = em.createQuery(jpql, Pessoa_Juridica.class);
        typedQuery.setParameter("cod_cliente", cod_cliente);

        return typedQuery.getSingleResult();
    }

    public List<Cliente> queryClientesComLocacaoDeTipoCarga()
    {
        String jpql = "SELECT DISTINCT c FROM" +
                      " Locacao l JOIN Veiculo v ON l.veiculo.cod_placa = v.cod_placa" +
                      " JOIN Tipo_Carga t ON v.tipo.cod_tipo = t.cod_tipo" +
                      " JOIN Motorista m ON l.motorista.cod_motorista = m.cod_motorista" +
                      " JOIN Cliente c ON m.cliente.cod_cliente = c.cod_cliente";

        TypedQuery<Cliente> typedQuery = em.createQuery(jpql, Cliente.class);
        return typedQuery.getResultList();
    }

    public List<Cliente> queryClientesComReservas()
    {
        String jpql = "SELECT DISTINCT c FROM Cliente c JOIN ReservasRecentes r" +
                      " ON c.cod_cliente = r.cliente.cod_cliente";
        TypedQuery<Cliente> typedQuery = em.createQuery(jpql, Cliente.class);
        return typedQuery.getResultList();
    }

    public Cliente queryClienteComLocacaoWithCodPlaca(String cod_placa)
    {
        String jpql = "SELECT c FROM LocacoesRecentes l" +
                      " JOIN Veiculo v ON l.veiculo.cod_placa = v.cod_placa" +
                      " JOIN Motorista m ON l.motorista.cod_motorista = m.cod_motorista" +
                      " JOIN Cliente c ON m.cliente.cod_cliente = c.cod_cliente" +
                      " WHERE v.cod_placa = :cod_placa AND v.parado = false";

        TypedQuery<Cliente> typedQuery = em.createQuery(jpql, Cliente.class);
        typedQuery.setParameter("cod_placa", cod_placa);
        return typedQuery.getSingleResult();
    }

    public Pessoa_Fisica queryPessoaFisicaWithNameAndCpf(String name, Long cpf)
    {
        String jpql = "SELECT f FROM Pessoa_Fisica f, Cliente c WHERE f.cod_cliente = c.cod_cliente " +
                      "AND c.nome = :nome AND f.cpf = :cpf";

        TypedQuery<Pessoa_Fisica> typedQuery = em.createQuery(jpql, Pessoa_Fisica.class);
        typedQuery.setParameter("nome", name);
        typedQuery.setParameter("cpf", cpf);

        return typedQuery.getSingleResult();
    }

    public Pessoa_Juridica queryPessoaJuridicaWithNameAndCnpj(String name, Long cnpj)
    {
        String jpql = "SELECT j FROM Pessoa_Juridica j, Cliente c WHERE j.cod_cliente = c.cod_cliente " +
                "AND c.nome = :nome AND j.cnpj = :cnpj";

        TypedQuery<Pessoa_Juridica> typedQuery = em.createQuery(jpql, Pessoa_Juridica.class);
        typedQuery.setParameter("nome", name);
        typedQuery.setParameter("cnpj", cnpj);

        return typedQuery.getSingleResult();
    }

    public List<Pessoa_Fisica> queryPessoaFisicaNascidaDepoisDeAno(Integer ano)
    {
        LocalDate data = LocalDate.of(ano, 1, 1);
        String jpql = "SELECT f FROM Pessoa_Fisica f WHERE f.data_nasc > :data";
        TypedQuery<Pessoa_Fisica> typedQuery = em.createQuery(jpql, Pessoa_Fisica.class);
        typedQuery.setParameter("data", data);
        return typedQuery.getResultList();
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
