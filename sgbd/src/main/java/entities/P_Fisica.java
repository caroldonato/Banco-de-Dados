package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class P_Fisica extends Cliente {
    @Column(length = 1)
    private String sexo;
    private LocalDate data_nasc;
    private Integer cpf;

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

    public Integer getCpf() {
        return cpf;
    }

    public void setCpf(Integer cpf) {
        this.cpf = cpf;
    }
}
