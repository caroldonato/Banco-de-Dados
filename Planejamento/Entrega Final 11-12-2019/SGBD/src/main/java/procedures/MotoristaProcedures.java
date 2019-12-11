package procedures;

import javax.persistence.*;

@NamedStoredProcedureQuery(
        name = "contar_motoristas",
        procedureName = "contar_motoristas",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.OUT, type = Integer.class, name = "total")
        }
)
@Entity
@Table(name = "motorista")
@SuppressWarnings("unused")
public class MotoristaProcedures {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer cod_motorista;

    public Integer contarMotoristas(EntityManager em)
    {
        StoredProcedureQuery query = em.createNamedStoredProcedureQuery("contar_motoristas");
        query.execute();
        return (Integer) query.getOutputParameterValue("total");
    }
}
