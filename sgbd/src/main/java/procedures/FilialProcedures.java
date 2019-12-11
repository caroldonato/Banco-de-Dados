package procedures;

import javax.persistence.*;

@NamedStoredProcedureQuery(
        name = "contar_filiais",
        procedureName = "contar_filiais",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.OUT, type = Integer.class, name = "total")
        }
)
@Entity
@Table(name = "filial")
@SuppressWarnings("unused")
public class FilialProcedures {
    @Id
    @Column(length = 10)
    private String cod_filial;

    public Integer contarFiliais(EntityManager em)
    {
        StoredProcedureQuery query = em.createNamedStoredProcedureQuery("contar_filiais");
        query.execute();
        return (Integer) query.getOutputParameterValue("total");
    }
}
