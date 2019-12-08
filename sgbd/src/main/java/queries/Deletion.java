package queries;

import entities.Pessoa_Fisica;
import entities.Pessoa_Juridica;
import entities.Veiculo;
import org.postgresql.util.PSQLException;
import queries.byentity.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.lang.reflect.Type;

@SuppressWarnings("unused")
public class Deletion {

    private EntityManager em;
    private static Deletion instance = null;

    // ========================================================================
    /* CONSTRUCTOR & SINGLETON IMPLEMENTATION */
    // ========================================================================

    public Deletion(EntityManager entityManager) { this.em = entityManager; }

    public static Deletion getInstance(EntityManager entityManager) {
        if(instance == null) { instance = new Deletion(entityManager); }
        return instance;
    }

    // ========================================================================
    /* BASIC DELETION */
    // ========================================================================

    public void deleteEntity(Object o)// Basic deletion of persistent object
    {
        em.getTransaction().begin();
        em.remove(o);
        em.getTransaction().commit();
    }

    // ========================================================================
    /* CLEAN DATABASE */
    // ========================================================================

    public void clearAllTables()
    {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Cliente").executeUpdate();
        em.createQuery("DELETE FROM Pessoa_Fisica").executeUpdate();
        em.createQuery("DELETE FROM Pessoa_Juridica").executeUpdate();
        em.createQuery("DELETE FROM Filial").executeUpdate();
        em.createQuery("DELETE FROM Locacao").executeUpdate();
        em.createQuery("DELETE FROM Motorista").executeUpdate();
        em.createQuery("DELETE FROM Reserva").executeUpdate();
        em.createQuery("DELETE FROM Revisao").executeUpdate();
        em.createQuery("DELETE FROM Tipo_Veiculo").executeUpdate();
        em.createQuery("DELETE FROM Tipo_Carga").executeUpdate();
        em.createQuery("DELETE FROM Tipo_Passageiro").executeUpdate();
        em.createQuery("DELETE FROM Veiculo").executeUpdate();
        em.getTransaction().commit();
    }

    // ========================================================================
    /* DELETION QUERIES */
    // ========================================================================

    public void removePessoaFisicaWithNomeAndCpf(String name, Long cpf)
    {
        ClienteQueries clienteQueries = new ClienteQueries(this.em);
        try {
            this.deleteEntity(clienteQueries.queryPessoaFisicaWithNameAndCpf(name, cpf));
        }
        catch (Exception e)
        {
            Exception cause = (Exception) e.getCause();
            System.out.println("Não foi possível remover pessoa física de nome " + name +
                               " e CPF " + cpf + ": " + cause.getMessage() + ".");
        }
    }

    public void removePessoaJuridicaWithNomeAndCnpj(String name, Long cnpj)
    {
        ClienteQueries clienteQueries = new ClienteQueries(this.em);
        try{
            this.deleteEntity(clienteQueries.queryPessoaJuridicaWithNameAndCnpj(name, cnpj));
        }
        catch (Exception e)
        {
            Exception cause = (Exception) e.getCause();
            System.out.println("Não foi possível remover pessoa jurídica de nome " +
                                name + " e CNPJ " + cnpj + ": " + cause.getMessage() + ".");
        }
    }

    public void removeVeiculoWithCodPlaca(String cod_placa)
    {
        VehicleQueries vehicleQueries = new VehicleQueries(this.em);
        try
        {
            this.deleteEntity(vehicleQueries.queryVeiculoWithCodPlaca(cod_placa));
        }
        catch (Exception e)
        {
            Exception cause = (Exception) e.getCause();
            System.out.println("Não foi possível remover veículo de placa " +
                    cod_placa + ": " + cause.getMessage() + ".");
        }
    }

    public void removeFilialWithCodFilial(String cod_filial)
    {
        FilialQueries filialQueries = new FilialQueries(this.em);
        try
        {
            this.deleteEntity(filialQueries.queryFilialWithCodFilial(cod_filial));
        }
        catch (Exception e)
        {
            Exception cause = (Exception) e.getCause();
            System.out.println("Não foi possível remover filial de código " + cod_filial +
                               ": " + cause.getMessage() + ".");
        }
    }

    public void removeMotoristaWithNumHab(Long num_hab)
    {
        MotoristaQueries motoristaQueries = new MotoristaQueries(this.em);
        try
        {
            this.deleteEntity(motoristaQueries.queryMotoristaWithNumHab(num_hab));
        }
        catch (Exception e)
        {
            Exception cause = (Exception) e.getCause();
            System.out.println("Não foi possível remover motorista de habilitação " + num_hab +
                               ": " + cause.getMessage() + ".");
        }
    }

    public void removeTipoVeiculoWithCodTipo(String cod_tipo)
    {
        VehicleTypeQueries vehicleTypeQueries = new VehicleTypeQueries(this.em);
        try
        {
            this.deleteEntity(vehicleTypeQueries.queryTipoVeiculoWithCodTipo(cod_tipo));
        }
        catch (Exception e)
        {
            Exception cause = (Exception) e.getCause();
            System.out.println("Não foi possível remover tipo de veículo de código" + cod_tipo +
                               ": " + cause.getMessage() + ".");
        }
    }

    public void removeRevisaoWithCodTipoAndCodRevisao(String cod_tipo, Integer cod_revisao)
    {
        RevisaoQueries revisaoQueries = new RevisaoQueries(this.em);
        try {
            this.deleteEntity(revisaoQueries.queryRevisaoWithCodTipoAndCodRevisao(cod_tipo, cod_revisao));
        }
        catch (Exception e)
        {
            Exception cause = (Exception) e.getCause();
            System.out.println("Não foi possível remover revisão de código de tipo " +
                    cod_tipo + " e código de revisão " + cod_revisao + ": " + cause.getMessage() + ".");
        }
    }

    public void removeReservaWithCodReserva(Integer cod_reserva)
    {
        ReservaQueries reservaQueries = new ReservaQueries(this.em);
        try {
            this.deleteEntity(reservaQueries.queryReservaWithCodReserva(cod_reserva));
        }
        catch (Exception e)
        {
            Exception cause = (Exception) e.getCause();
            System.out.println("Não foi possível remover reserva de código" + cod_reserva +
                    ": " + cause.getMessage() + ".");
        }
    }

    public void removeLocacaoWithCodLocacao(Integer cod_locacao)
    {
        LocacaoQueries locacaoQueries = new LocacaoQueries(this.em);
        try {
            this.deleteEntity(locacaoQueries.queryLocacaoWithCodLocacao(cod_locacao));
        }
        catch (Exception e)
        {
            Exception cause = (Exception) e.getCause();
            System.out.println("Não foi possível remover locação de códio" + cod_locacao +
                    ": " + cause.getMessage() + ".");
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
