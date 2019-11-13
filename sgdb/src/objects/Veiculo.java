package objects;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Veiculo {
    @Id
    @Column(length = 10)
    private String cod_placa;

    @ManyToOne
    @JoinColumn(name = "cod_tipo")
    private Tipo_Veiculo tipo;

    @ManyToOne
    @JoinColumn(name = "cod_filial_atual")
    private Filial filial_atual;

    @Column(length = 10)
    private String num_chassi;
    @Column(length = 10)
    private String num_motor;
    @Column(length = 20)
    private String cor;
    private Integer km_atual;
    @Type(type = "numeric_boolean")
    private boolean revisao_pendente;

    // Getters & Setters
    public String getCod_placa() {
        return cod_placa;
    }

    public void setCod_placa(String cod_placa) {
        this.cod_placa = cod_placa;
    }

    public Tipo_Veiculo getTipo() {
        return tipo;
    }

    public String getCod_tipo() {
        return tipo.getCod_tipo();
    }

    public void setTipo(Tipo_Veiculo tipo) {
        this.tipo = tipo;
    }

    public Filial getFilial_atual() {
        return filial_atual;
    }

    public String getCod_filial_atual() {
        return filial_atual.getCod_filial();
    }

    public void setFilial_atual(Filial filial_atual) {
        this.filial_atual = filial_atual;
    }

    public String getNum_chassi() {
        return num_chassi;
    }

    public void setNum_chassi(String num_chassi) {
        this.num_chassi = num_chassi;
    }

    public String getNum_motor() {
        return num_motor;
    }

    public void setNum_motor(String num_motor) {
        this.num_motor = num_motor;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Integer getKm_atual() {
        return km_atual;
    }

    public void setKm_atual(Integer km_atual) {
        this.km_atual = km_atual;
    }

    public boolean isRevisao_pendente() {
        return revisao_pendente;
    }

    public void setRevisao_pendente(boolean revisao_pendente) {
        this.revisao_pendente = revisao_pendente;
    }

    // Hash and Equals Override
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Veiculo veiculo = (Veiculo) o;
        return getCod_placa().equals(veiculo.getCod_placa());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCod_placa());
    }
}
