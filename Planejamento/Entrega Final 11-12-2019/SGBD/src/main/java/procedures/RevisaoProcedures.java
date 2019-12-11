package procedures;

import javax.persistence.*;

@NamedStoredProcedureQuery(
        name = "contar_revisoes",
        procedureName = "contar_revisoes",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.OUT, type = Integer.class, name = "total")
        }
)
@Entity
@Table(name = "revisao")
@SuppressWarnings("unused")
public class RevisaoProcedures {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer cod_revisao;

    public Integer contarRevisoes(EntityManager em)
    {
        StoredProcedureQuery query = em.createNamedStoredProcedureQuery("contar_revisoes");
        query.execute();
        return (Integer) query.getOutputParameterValue("total");
    }
}
