package sgbd.locadoraveiculos.objects;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
public class Locacao {
    @Id
    private Integer cod_locacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_placa")
    private Veiculo veiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_filial_dest")
    private Filial filial_dest;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_motorista")
    private List<Motorista> motorista;

    private java.time.LocalDate data_entrega;

    // Getters & Setters
    public Integer getCod_locacao() {
        return cod_locacao;
    }

    public void setCod_locacao(Integer cod_locacao) {
        this.cod_locacao = cod_locacao;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Filial getFilial_dest() {
        return filial_dest;
    }

    public void setFilial_dest(Filial filial_dest) {
        this.filial_dest = filial_dest;
    }

    public List<Motorista> getMotorista() {
        return motorista;
    }

    public void setMotorista(List<Motorista> motorista) {
        this.motorista = motorista;
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
        Locacao locacao = (Locacao) o;
        return getCod_locacao().equals(locacao.getCod_locacao());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCod_locacao());
    }
}
