package sgbd.locadoraveiculos.objects;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Tipo_Veiculo {
    @Id
    @Column(length=5)
    private String cod_tipo;

    private Integer horas_limpeza;
    private Integer horas_revisao;

    // Getters & Setters
    public String getCod_tipo() {
        return cod_tipo;
    }

    public void setCod_tipo(String cod_tipo) {
        this.cod_tipo = cod_tipo;
    }

    public Integer getHoras_limpeza() {
        return horas_limpeza;
    }

    public void setHoras_limpeza(Integer horas_limpeza) {
        this.horas_limpeza = horas_limpeza;
    }

    public Integer getHoras_revisao() {
        return horas_revisao;
    }

    public void setHoras_revisao(Integer horas_revisao) {
        this.horas_revisao = horas_revisao;
    }

    // Hash and Equals Override
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tipo_Veiculo that = (Tipo_Veiculo) o;
        return getCod_tipo().equals(that.getCod_tipo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCod_tipo());
    }
}
