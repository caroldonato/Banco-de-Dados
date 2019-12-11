package elements;

import entities.Cliente;
import entities.Motorista;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

public class WMotorista {
    Motorista motorista;

    private Integer cod_motorista;
    private Integer cod_cliente;
    private Long num_habili;
    private Long ident_motorista;
    private LocalDate vencimento_habili;

    public WMotorista(Motorista nmoto) {
        this.cod_motorista = nmoto.getCod_motorista();
        this.cod_cliente = nmoto.getCod_Cliente();
        this.num_habili = nmoto.getNum_habili();
        this.ident_motorista = nmoto.getIdent_motorista();
        this.vencimento_habili = nmoto.getVencimento_habili();
    }


    public void setCod_cliente(Integer cod_cliente) {
        this.cod_cliente = cod_cliente;
    }

    public void setCod_motorista(Integer cod_motorista) {
        this.cod_motorista = cod_motorista;
    }

    public void setIdent_motorista(Long ident_motorista) {
        this.ident_motorista = ident_motorista;
    }

    public void setNum_habili(Long num_habili) {
        this.num_habili = num_habili;
    }

    public void setVencimento_habili(LocalDate vencimento_habili) {
        this.vencimento_habili = vencimento_habili;
    }

    public Integer getCod_cliente() {
        return cod_cliente;
    }

    public Integer getCod_motorista() {
        return cod_motorista;
    }

    public LocalDate getVencimento_habili() {
        return vencimento_habili;
    }

    public Long getIdent_motorista() {
        return ident_motorista;
    }

    public Long getNum_habili() {
        return num_habili;
    }

    public Motorista getMotorista() {
        return motorista;
    }
}
