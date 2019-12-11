package elements;

import entities.Locacao;

import java.time.LocalDate;

public class WLocacao {
    private Integer cod_locacao;
    private String cod_placa;
    private String cod_filial_dest;
    private Integer cod_motorista;
    private LocalDate data_entrega;

    public WLocacao(Locacao loc) {
        this.cod_locacao = loc.getCod_locacao();
        this.cod_placa = loc.getVeiculo().getCod_placa();
        this.cod_filial_dest = loc.getFilial_dest().getCod_filial();
        this.cod_motorista = loc.getMotorista().getCod_motorista();
        this.data_entrega = loc.getData_entrega();
    }

    public Integer getCod_locacao() {
        return cod_locacao;
    }

    public void setCod_locacao(Integer cod_locacao) {
        this.cod_locacao = cod_locacao;
    }

    public String getCod_placa() {
        return cod_placa;
    }

    public void setCod_placa(String cod_placa) {
        this.cod_placa = cod_placa;
    }

    public String getCod_filial_dest() {
        return cod_filial_dest;
    }

    public void setCod_filial_dest(String cod_filial_dest) {
        this.cod_filial_dest = cod_filial_dest;
    }

    public Integer getCod_motorista() {
        return cod_motorista;
    }

    public void setCod_motorista(Integer cod_motorista) {
        this.cod_motorista = cod_motorista;
    }

    public LocalDate getData_entrega() {
        return data_entrega;
    }

    public void setData_entrega(LocalDate data_entrega) {
        this.data_entrega = data_entrega;
    }
}
