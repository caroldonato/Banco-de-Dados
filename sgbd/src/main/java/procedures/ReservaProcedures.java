package procedures;

import javax.persistence.*;

@NamedStoredProcedureQuery(
        name = "contar_reservas",
        procedureName = "contar_reservas",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.OUT, type = Integer.class, name = "total")
        }
)
@Entity
@Table(name = "reserva")
@SuppressWarnings("unused")
public class ReservaProcedures {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer cod_reserva;

    public Integer contarReservas(EntityManager em)
    {
        StoredProcedureQuery query = em.createNamedStoredProcedureQuery("contar_reservas");
        query.execute();
        return (Integer) query.getOutputParameterValue("total");
    }
}
