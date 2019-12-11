package elements;

import entities.Cliente;
import entities.Filial;
import entities.Reserva;
import entities.Tipo_Veiculo;

import java.time.LocalDate;

public class WReserva {
    private Integer cod_reserva;
    private String tipo;
    private String filial_dest;
    private String filial_orig;
    private Integer cod_cliente;
    private LocalDate data_retirada;
    private LocalDate data_entrega;


    public WReserva(Reserva res) {
        this.cod_reserva = res.getCod_reserva();
        this.tipo = res.getTipo().getCod_tipo();
        this.filial_dest = res.getFilial_dest().getCod_filial();
        this.filial_orig = res.getFilial_orig().getCod_filial();
        this.cod_cliente = res.getCliente().getCod_cliente();
        this.data_entrega = res.getData_entrega();
        this.data_retirada = res.getData_retirada();
    }

    public void setCod_cliente(Integer cod_cliente) {
        this.cod_cliente = cod_cliente;
    }

    public void setCod_reserva(Integer cod_reserva) {
        this.cod_reserva = cod_reserva;
    }

    public void setData_entrega(LocalDate data_entrega) {
        this.data_entrega = data_entrega;
    }

    public void setData_retirada(LocalDate data_retirada) {
        this.data_retirada = data_retirada;
    }

    public void setFilial_dest(String filial_dest) {
        this.filial_dest = filial_dest;
    }

    public void setFilial_orig(String filial_orig) {
        this.filial_orig = filial_orig;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getCod_cliente() {
        return cod_cliente;
    }

    public Integer getCod_reserva() {
        return cod_reserva;
    }

    public LocalDate getData_entrega() {
        return data_entrega;
    }

    public LocalDate getData_retirada() {
        return data_retirada;
    }

    public String getFilial_dest() {
        return filial_dest;
    }

    public String getFilial_orig() {
        return filial_orig;
    }

    public String getTipo() {
        return tipo;
    }
}

