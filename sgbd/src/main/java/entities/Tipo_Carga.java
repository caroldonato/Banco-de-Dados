package entities;

import javax.persistence.Entity;

@Entity
public class Tipo_Carga extends Tipo_Veiculo {
    private Integer capacidade;

    // Getters & Setters
    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }
}
