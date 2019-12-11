package procedures;

import javax.persistence.*;

@NamedStoredProcedureQuery(
        name = "contar_tipos",
        procedureName = "contar_tipos",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.OUT, type = Integer.class, name = "total")
        }
)
@NamedStoredProcedureQuery(
        name = "contar_tipo_passageiro",
        procedureName = "contar_tipo_passageiro",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.OUT, type = Integer.class, name = "total")
        }
)
@NamedStoredProcedureQuery(
        name = "contar_tipo_carga",
        procedureName = "contar_tipo_carga",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.OUT, type = Integer.class, name = "total")
        }
)
@Entity
@Table(name = "tipo_veiculo")
@SuppressWarnings("unused")
public class TipoVeiculoProcedures {
    @Id
    @Column(length = 5)
    private String cod_tipo;

    public Integer contarTipos(EntityManager em)
    {
        StoredProcedureQuery query = em.createNamedStoredProcedureQuery("contar_tipos");
        query.execute();
        return (Integer) query.getOutputParameterValue("total");
    }

    public Integer contarTiposPassageiro(EntityManager em)
    {
        StoredProcedureQuery query = em.createNamedStoredProcedureQuery("contar_tipo_passageiro");
        query.execute();
        return (Integer) query.getOutputParameterValue("total");
    }

    public Integer contarTiposCarga(EntityManager em)
    {
        StoredProcedureQuery query = em.createNamedStoredProcedureQuery("contar_tipo_carga");
        query.execute();
        return (Integer) query.getOutputParameterValue("total");
    }
}
