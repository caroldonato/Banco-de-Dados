package procedures;

import javax.persistence.*;

@NamedStoredProcedureQuery(
        name = "contar_locacoes",
        procedureName = "contar_locacoes",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.OUT, type = Integer.class, name = "total")
        }
)
@Entity
@Table(name = "locacao")
@SuppressWarnings("unused")
public class LocacaoProcedures {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer cod_locacao;

    public Integer contarLocacoes(EntityManager em)
    {
        StoredProcedureQuery query = em.createNamedStoredProcedureQuery("contar_locacoes");
        query.execute();
        return (Integer) query.getOutputParameterValue("total");
    }
}
