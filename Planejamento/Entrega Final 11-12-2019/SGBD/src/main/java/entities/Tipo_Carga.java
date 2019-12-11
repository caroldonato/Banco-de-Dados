package entities;

import javax.persistence.Entity;

@Entity
public class Tipo_Carga extends Tipo_Veiculo {
    private Integer capacidade;

    public Tipo_Carga() {}

    public Tipo_Carga(String cod_tipo, Integer horas_limpeza, Integer horas_revisao, Integer capacidade)
    {
        this.setCod_tipo(cod_tipo);
        this.setHoras_limpeza(horas_limpeza);
        this.setHoras_revisao(horas_revisao);
        this.capacidade = capacidade;
    }

    // Getters & Setters
    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }
}
