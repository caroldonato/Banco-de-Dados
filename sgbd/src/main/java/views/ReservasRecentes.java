package views;

import entities.Cliente;
import entities.Filial;
import entities.Tipo_Veiculo;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@SuppressWarnings("unused")
@Entity
@Immutable
@Table(name = "reservas_recentes")
public class ReservasRecentes {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer cod_reserva;

    @ManyToOne
    @JoinColumn(name = "cod_tipo")
    private Tipo_Veiculo tipo;

    @ManyToOne
    @JoinColumn(name = "cod_filial_dest")
    private Filial filial_dest;

    @ManyToOne
    @JoinColumn(name = "cod_filial_orig")
    private Filial filial_orig;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_cliente")
    private Cliente cliente;

    private LocalDate data_retirada;
    private LocalDate data_entrega;


    public Integer getCod_reserva() {
        return cod_reserva;
    }

    public Tipo_Veiculo getTipo() {
        return tipo;
    }

    public Filial getFilial_dest() {
        return filial_dest;
    }

    public Filial getFilial_orig() {
        return filial_orig;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getData_retirada() {
        return data_retirada;
    }

    public LocalDate getData_entrega() {
        return data_entrega;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservasRecentes that = (ReservasRecentes) o;
        return getCod_reserva().equals(that.getCod_reserva());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCod_reserva());
    }
}
