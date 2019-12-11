package elements;

import entities.Cliente;

public class WCliente {
    private Integer cod_cliente;
    private String nome;
    private String endereco;
    public WCliente(Cliente cli) {
        this.cod_cliente = cli.getCod_cliente();
        this.nome = cli.getNome();
        this.endereco = cli.getEndereco();
    }

    public void setCod_cliente(Integer cod_cliente) {
        this.cod_cliente = cod_cliente;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCod_cliente() {
        return cod_cliente;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getNome() {
        return nome;
    }
}
