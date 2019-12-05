package entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Tipo_Passageiro extends Tipo_Veiculo {
    @Column(length = 1)
    private String tamanho;
    private Integer num_lugares;
    private Integer num_portas;
    @Column(length = 1)
    private boolean ar_condicionado;
    @Column(length = 1)
    private boolean radio;
    @Column(length = 1)
    private boolean mp3;
    @Column(length = 1)
    private boolean cd;
    @Column(length = 1)
    private boolean dir_hidr;
    @Column(length = 1)
    private boolean cambio_auto;

    // Getters & Setters
    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public Integer getNum_lugares() {
        return num_lugares;
    }

    public void setNum_lugares(Integer num_lugares) {
        this.num_lugares = num_lugares;
    }

    public Integer getNum_portas() {
        return num_portas;
    }

    public void setNum_portas(Integer num_portas) {
        this.num_portas = num_portas;
    }

    public boolean getAr_condicionado() {
        return ar_condicionado;
    }

    public void setAr_condicionado(boolean ar_condicionado) {
        this.ar_condicionado = ar_condicionado;
    }

    public boolean getRadio() {
        return radio;
    }

    public void setRadio(boolean radio) {
        this.radio = radio;
    }

    public boolean getMp3() {
        return mp3;
    }

    public void setMp3(boolean mp3) {
        this.mp3 = mp3;
    }

    public boolean getCd() {
        return cd;
    }

    public void setCd(boolean cd) {
        this.cd = cd;
    }

    public boolean getDir_hidr() {
        return dir_hidr;
    }

    public void setDir_hidr(boolean dir_hidr) {
        this.dir_hidr = dir_hidr;
    }

    public boolean getCambio_auto() {
        return cambio_auto;
    }

    public void setCambio_auto(boolean cambio_auto) {
        this.cambio_auto = cambio_auto;
    }
}
