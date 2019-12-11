package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Pessoa_Fisica extends Cliente {
    @Column(length = 1)
    private String sexo;
    private LocalDate data_nasc;
    private Long cpf;

    public Pessoa_Fisica() {}

    public Pessoa_Fisica(String nome, String endereco, String sexo, LocalDate data_nasc, Long cpf)
    {
        this.setNome(nome);
        this.setEndereco(endereco);
        this.sexo = sexo;
        this.data_nasc = data_nasc;
        this.cpf = cpf;
    }

    // Getters & Setters
    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public LocalDate getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(LocalDate data_nasc) {
        this.data_nasc = data_nasc;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }
}
