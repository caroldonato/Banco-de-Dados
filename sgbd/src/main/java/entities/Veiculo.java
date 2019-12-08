package entities;

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
    private Filial cod_filial_atual;

    @Column(length = 10)
    private String num_chassi;
    @Column(length = 10)
    private String num_motor;
    @Column(length = 20)
    private String cor;
    private Integer km_atual = 0;
    private boolean revisao_pendente = false;
    private boolean parado = true;

    public Veiculo() {}

    public Veiculo(String cod_placa, Tipo_Veiculo tipo, Filial filial_atual, String num_chassi,
                   String num_motor, String cor, Integer km_atual, boolean revisao_pendente, boolean parado)
    {
        this.cod_placa = cod_placa;
        this.tipo = tipo;
        this.cod_filial_atual = filial_atual;
        this.num_chassi = num_chassi;
        this.num_motor = num_motor;
        this.cor = cor;
        this.km_atual = km_atual;
        this.revisao_pendente = revisao_pendente;
        this.parado = parado;
    }

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

    public Filial getCod_filial_atual() { return cod_filial_atual; }

    public void setCod_filial_atual(Filial cod_filial_atual) { this.cod_filial_atual = cod_filial_atual; }

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

    public boolean isParado() { return parado; }

    public void setParado(boolean parado) { this.parado = parado; }

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
