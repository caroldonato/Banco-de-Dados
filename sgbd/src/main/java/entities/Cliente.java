package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "cliente")
public class Cliente {
    @Id
    private Integer cod_cliente;
    private String nome;
    private String endereco;

    // Getters & Setters
    public Integer getCod_cliente() {
        return cod_cliente;
    }

    public void setCod_cliente(Integer cod_cliente) {
        this.cod_cliente = cod_cliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    // Hash e Equals override
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return getCod_cliente().equals(cliente.getCod_cliente());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCod_cliente());
    }
}
