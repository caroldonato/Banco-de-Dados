package procedures;

import javax.persistence.*;

@NamedStoredProcedureQuery(
        name = "contar_veiculos",
        procedureName = "contar_veiculos",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.OUT, type = Integer.class, name = "total")
        }
)
@Entity
@Table(name = "veiculo")
@SuppressWarnings("unused")
public class VeiculoProcedures {
        @Id
        @Column(length = 10)
        private String cod_placa;

        public Integer contarVeiculos(EntityManager em)
        {
                StoredProcedureQuery query = em.createNamedStoredProcedureQuery("contar_veiculos");
                query.execute();
                return (Integer) query.getOutputParameterValue("total");
        }
}
