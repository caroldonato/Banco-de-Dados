package entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Motorista {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer cod_motorista;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_cliente")
    private Cliente cliente;

    private Long num_habili;
    private Long ident_motorista;
    private LocalDate vencimento_habili;

    public Motorista() { }

    public Motorista(Cliente cliente, Long num_habili, Long ident_motorista, LocalDate vencimento_habili)
    {
        this.cliente = cliente;
        this.num_habili = num_habili;
        this.ident_motorista = ident_motorista;
        this.vencimento_habili = vencimento_habili;
    }


    // Getters & Setters
    public Integer getCod_motorista() {
        return cod_motorista;
    }

    public void setCod_motorista(Integer cod_motorista) {
        this.cod_motorista = cod_motorista;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Integer getCod_Cliente() {
        return this.cliente.getCod_cliente();
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Long getNum_habili() {
        return num_habili;
    }

    public void setNum_habili(Long num_habili) {
        this.num_habili = num_habili;
    }

    public Long getIdent_motorista() {
        return ident_motorista;
    }

    public void setIdent_motorista(Long ident_motorista) {
        this.ident_motorista = ident_motorista;
    }

    public LocalDate getVencimento_habili() {
        return vencimento_habili;
    }

    public void setVencimento_habili(LocalDate vencimento_habili) {
        this.vencimento_habili = vencimento_habili;
    }

    // Hash and Equals Override
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Motorista motorista = (Motorista) o;
        return getCod_motorista().equals(motorista.getCod_motorista()) &&
                getCliente().equals(motorista.getCliente());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCod_motorista(), getCliente());
    }
}
