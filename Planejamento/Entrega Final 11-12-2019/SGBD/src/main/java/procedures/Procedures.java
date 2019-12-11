package procedures;

import javax.persistence.EntityManager;

@SuppressWarnings("unused")
public class Procedures {
    private EntityManager em;

    private static ClienteProcedures clienteProcedures = new ClienteProcedures();
    private static FilialProcedures filialProcedures = new FilialProcedures();
    private static LocacaoProcedures locacaoProcedures = new LocacaoProcedures();
    private static MotoristaProcedures motoristaProcedures = new MotoristaProcedures();
    private static ReservaProcedures reservaProcedures = new ReservaProcedures();
    private static RevisaoProcedures revisaoProcedures = new RevisaoProcedures();
    private static VeiculoProcedures veiculoProcedures = new VeiculoProcedures();
    private static TipoVeiculoProcedures tipoVeiculoProcedures = new TipoVeiculoProcedures();

    private static Procedures instance = null;

    // ========================================================================
    /* CONSTRUCTORS & SINGLETON IMPLEMENTATION */
    // ========================================================================

    public Procedures(EntityManager entityManager) {
        this.em = entityManager;
    }

    public static Procedures getInstance(EntityManager entityManager) {
        if(instance == null) { instance = new Procedures(entityManager); }
        return instance;
    }

    // ========================================================================
    /* PROCEDURES ACCESS */
    // ========================================================================

    // CONTAGENS
    // ------------------------------------------------------------------------
    public Integer contarClientes()
    {
        return clienteProcedures.contarClientes(this.em);
    }
    public Integer contarPessoasFisicas() { return clienteProcedures.contarPessoasFisicas(this.em); }
    public Integer contarPessoasJuridicas() { return clienteProcedures.contarPessoasJuridicas(this.em); }
    public Integer contarFiliais() { return filialProcedures.contarFiliais(this.em); }
    public Integer contarLocacoes() { return locacaoProcedures.contarLocacoes(this.em); }
    public Integer contarMotoristas() { return motoristaProcedures.contarMotoristas(this.em); }
    public Integer contarReservas() { return reservaProcedures.contarReservas(this.em); }
    public Integer contarRevisoes() { return revisaoProcedures.contarRevisoes(this.em); }
    public Integer contarTipos() { return tipoVeiculoProcedures.contarTipos(this.em); }
    public Integer contarTiposPassageiro() { return tipoVeiculoProcedures.contarTiposPassageiro(this.em); }
    public Integer contarTiposCarga() { return tipoVeiculoProcedures.contarTiposCarga(this.em); }
    public Integer contarVeiculos() { return veiculoProcedures.contarVeiculos(this.em); }


}
