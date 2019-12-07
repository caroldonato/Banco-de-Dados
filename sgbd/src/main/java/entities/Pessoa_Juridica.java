package entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Pessoa_Juridica extends Cliente {
    private Long cnpj;
    @Column(length = 20)
    private Long inscr_estado;

    public Pessoa_Juridica() {}

    public Pessoa_Juridica(String nome, String endereco, Long cnpj, Long inscr_estado)
    {
        this.setNome(nome);
        this.setEndereco(endereco);
        this.cnpj = cnpj;
        this.inscr_estado = inscr_estado;
    }

    // Getters & Setters
    public Long getCnpj() {
        return cnpj;
    }

    public void setCnpj(Long cnpj) {
        this.cnpj = cnpj;
    }

    public Long getInscr_estado() {
        return inscr_estado;
    }

    public void setInscr_estado(Long inscr_estado) {
        this.inscr_estado = inscr_estado;
    }
}
