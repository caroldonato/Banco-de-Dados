package objects;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class P_Juridica extends Cliente {
    private Integer cnpj;
    @Column(length = 20)
    private String inscr_estado;

    // Getters & Setters
    public Integer getCnpj() {
        return cnpj;
    }

    public void setCnpj(Integer cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscr_estado() {
        return inscr_estado;
    }

    public void setInscr_estado(String inscr_estado) {
        this.inscr_estado = inscr_estado;
    }
}
