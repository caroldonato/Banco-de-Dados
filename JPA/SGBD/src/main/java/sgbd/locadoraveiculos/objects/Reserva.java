package sgbd.locadoraveiculos.objects;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Reserva {
    @Id
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

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="cod_cliente")
    private Cliente cliente;

    private java.time.LocalDate data_retirada;
    private java.time.LocalDate data_entrega;

    // Getters & Setters
    public Integer getCod_reserva() {
        return cod_reserva;
    }

    public void setCod_reserva(Integer cod_reserva) {
        this.cod_reserva = cod_reserva;
    }

    public Tipo_Veiculo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo_Veiculo tipo) {
        this.tipo = tipo;
    }

    public Filial getFilial_dest() {
        return filial_dest;
    }

    public void setFilial_dest(Filial filial_dest) {
        this.filial_dest = filial_dest;
    }

    public Filial getFilial_orig() {
        return filial_orig;
    }

    public void setFilial_orig(Filial filial_orig) {
        this.filial_orig = filial_orig;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getData_retirada() {
        return data_retirada;
    }

    public void setData_retirada(LocalDate data_retirada) {
        this.data_retirada = data_retirada;
    }

    public LocalDate getData_entrega() {
        return data_entrega;
    }

    public void setData_entrega(LocalDate data_entrega) {
        this.data_entrega = data_entrega;
    }

    // Hash and Equals Override
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reserva reserva = (Reserva) o;
        return getCod_reserva().equals(reserva.getCod_reserva());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCod_reserva());
    }
}
