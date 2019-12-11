package elements;

import entities.Filial;
import entities.Veiculo;


public class WVeiculo {
    private String cod_placa;
    private String cod_tipo;
    private String cod_filial_atual;
    private String num_chassi;
    private String num_motor;
    private String cor;
    private Integer km_atual = 0;
    private String revisao_pendente;
    private String parado;


    public WVeiculo(Veiculo veic) {
        this.cod_placa = veic.getCod_placa();
        this.cod_tipo = veic.getTipo().getCod_tipo();
        this.cod_filial_atual = veic.getCod_filial_atual().getCod_filial();
        this.num_chassi = veic.getNum_chassi();
        this.num_motor = veic.getNum_motor();
        this.cor = veic.getCor();
        this.km_atual = veic.getKm_atual();
        this.revisao_pendente = veic.isRevisao_pendente() ? "Sim" : "Não";
        this.parado = veic.isParado() ? "Sim" : "Não";
    }

    public void setCod_filial_atual(String cod_filial_atual) {
        this.cod_filial_atual = cod_filial_atual;
    }

    public void setCod_placa(String cod_placa) {
        this.cod_placa = cod_placa;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public void setKm_atual(Integer km_atual) {
        this.km_atual = km_atual;
    }

    public void setNum_chassi(String num_chassi) {
        this.num_chassi = num_chassi;
    }

    public void setNum_motor(String num_motor) {
        this.num_motor = num_motor;
    }

    public void setParado(String parado) {
        this.parado = parado;
    }

    public void setRevisao_pendente(String revisao_pendente) {
        this.revisao_pendente = revisao_pendente;
    }

    public String getCod_tipo() {
        return cod_tipo;
    }

    public void setCod_tipo(String cod_tipo) {
        this.cod_tipo = cod_tipo;
    }

    public String getCod_placa() {
        return cod_placa;
    }

    public String getCod_filial_atual() {
        return cod_filial_atual;
    }

    public String getNum_chassi() {
        return num_chassi;
    }

    public String getNum_motor() {
        return num_motor;
    }

    public String getCor() {
        return cor;
    }

    public Integer getKm_atual() {
        return km_atual;
    }

    public String getRevisao_pendente() {
        return revisao_pendente;
    }

    public String getParado() {
        return parado;
    }
}
