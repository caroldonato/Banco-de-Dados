package objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Filial {
    @Id
    @Column(length = 10)
    private String cod_filial;

    private String localizacao;

    // Getters & Setters
    public String getCod_filial() {
        return cod_filial;
    }

    public void setCod_filial(String cod_filial) {
        this.cod_filial = cod_filial;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    // Hash and Equals Override
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Filial filial = (Filial) o;
        return getCod_filial().equals(filial.getCod_filial());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCod_filial());
    }
}
