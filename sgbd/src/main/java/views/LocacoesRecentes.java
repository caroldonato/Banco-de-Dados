package views;

import entities.Filial;
import entities.Motorista;
import entities.Veiculo;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@SuppressWarnings("unused")
@Entity
@Immutable
@Table(name = "locacoes_recentes")
public class LocacoesRecentes {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer cod_locacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_placa")
    private Veiculo veiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_filial_dest")
    private Filial filial_dest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_motorista")
    private Motorista motorista;

    private LocalDate data_entrega;

    public Integer getCod_locacao() {
        return cod_locacao;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public Filial getFilial_dest() {
        return filial_dest;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public LocalDate getData_entrega() {
        return data_entrega;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocacoesRecentes that = (LocacoesRecentes) o;
        return getCod_locacao().equals(that.getCod_locacao());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCod_locacao());
    }
}
