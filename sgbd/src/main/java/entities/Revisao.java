package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
public class Revisao {
    @Id
    private Integer cod_revisao;

    @ManyToOne
    @JoinColumn(name = "cod_tipo")
    private Tipo_Veiculo tipo;

    private Integer km_media;

    // Getters & Setters
    public Integer getCod_revisao() {
        return cod_revisao;
    }

    public void setCod_revisao(Integer cod_revisao) {
        this.cod_revisao = cod_revisao;
    }

    public Tipo_Veiculo get_tipo() {
        return tipo;
    }

    public void set_tipo(Tipo_Veiculo tipo) {
        this.tipo = tipo;
    }

    public Integer getKm_media() {
        return km_media;
    }

    public void setKm_media(Integer km_media) {
        this.km_media = km_media;
    }

    // Hash and Equals Override
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Revisao revisao = (Revisao) o;
        return getCod_revisao().equals(revisao.getCod_revisao());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCod_revisao());
    }
}
