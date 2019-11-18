package sgbd.locadoraveiculos.objects;

import javax.persistence.*;

@Entity
public class Tipo_Passageiro extends Tipo_Veiculo {
    @Column(length=1)
    private String tamanho;
    private Integer num_lugares;
    private Integer num_portas;
    @Column(length=1)
    private String ar_condicionado;
    @Column(length=1)
    private String radio;
    @Column(length=1)
    private String mp3;
    @Column(length=1)
    private String cd;
    @Column(length=1)
    private String dir_hidr;
    @Column(length=1)
    private String cambio_auto;

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

    public String getAr_condicionado() {
        return ar_condicionado;
    }

    public void setAr_condicionado(String ar_condicionado) {
        this.ar_condicionado = ar_condicionado;
    }

    public String getRadio() {
        return radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }

    public String getMp3() {
        return mp3;
    }

    public void setMp3(String mp3) {
        this.mp3 = mp3;
    }

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public String getDir_hidr() {
        return dir_hidr;
    }

    public void setDir_hidr(String dir_hidr) {
        this.dir_hidr = dir_hidr;
    }

    public String getCambio_auto() {
        return cambio_auto;
    }

    public void setCambio_auto(String cambio_auto) {
        this.cambio_auto = cambio_auto;
    }
}
