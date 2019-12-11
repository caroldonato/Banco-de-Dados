package procedures;

import javax.persistence.*;

@NamedStoredProcedureQuery(
        name = "contar_pessoa_juridica",
        procedureName = "contar_pessoa_juridica",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.OUT, type = Integer.class, name = "total")
        }
)
@NamedStoredProcedureQuery(
        name = "contar_pessoa_fisica",
        procedureName = "contar_pessoa_fisica",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.OUT, type = Integer.class, name = "total")
        }
)
@NamedStoredProcedureQuery(
        name = "contar_clientes",
        procedureName = "contar_clientes",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.OUT, type = Integer.class, name = "total")
        }
)
@Entity
@Table(name = "cliente")
@SuppressWarnings("unused")
public class ClienteProcedures {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer cod_cliente;

    public Integer contarClientes(EntityManager em)
    {
        StoredProcedureQuery query = em.createNamedStoredProcedureQuery("contar_clientes");
        query.execute();
        return (Integer) query.getOutputParameterValue("total");
    }

    public Integer contarPessoasFisicas(EntityManager em)
    {
        StoredProcedureQuery query = em.createNamedStoredProcedureQuery("contar_pessoa_fisica");
        query.execute();
        return (Integer) query.getOutputParameterValue("total");
    }

    public Integer contarPessoasJuridicas(EntityManager em)
    {
        StoredProcedureQuery query = em.createNamedStoredProcedureQuery("contar_pessoa_juridica");
        query.execute();
        return (Integer) query.getOutputParameterValue("total");
    }
}
