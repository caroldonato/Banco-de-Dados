CREATE DATABASE Locadora_Veiculos;

USE Locadora_Veiculos;

CREATE TABLE P_Fisica(
    cod_cliente INTEGER PRIMARY KEY,
    nome VARCHAR(255),
    endereco VARCHAR(255),
    sexo VARCHAR(1),
    data_nasc DATE,
    cpf INTEGER,
);


CREATE TABLE P_Juridica(
    cod_cliente INTEGER PRIMARY KEY,
    nome VARCHAR(255),
    endereco VARCHAR(255),
    cnpj INTEGER,
    inscr_estado VARCHAR(20),
);


CREATE TABLE Motorista(
    cod_motorista INTEGER PRIMARY KEY,
    cod_cliente INTEGER,
    num_habili INTEGER,
    vencimento_habili DATE,
    ident_motorista INTEGER,

    FOREIGN KEY (cod_cliente) 
        REFERENCES Cliente(cod_cliente)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);


CREATE TABLE Tipo_Passageiro(
    cod_tipo VARCHAR(5) PRIMARY KEY,
    horas_limpeza INTEGER,
    horas_revisao INTEGER,
    tamanho VARCHAR(1),
    num_lugares INTEGER,
    num_portas INTEGER,
    ar_condicionado VARCHAR(1),
    radio VARCHAR(1),
    mp3 VARCHAR(1),
    cd VARCHAR(1),
    dir_hidr VARCHAR(1),
    cambio_auto VARCHAR(1),
);


CREATE TABLE Tipo_Carga(
    cod_tipo VARCHAR(5) PRIMARY KEY,
    horas_limpeza INTEGER,
    horas_revisao INTEGER,
    capacidade INTEGER,
);


CREATE TABLE Filial(
    cod_filial VARCHAR(10) PRIMARY KEY,
    localizacao VARCHAR(255)
);


CREATE TABLE Veiculo(
    cod_placa VARCHAR(10) PRIMARY KEY,
    cod_tipo VARCHAR(5),
    cod_filial_atual VARCHAR(10),
    num_chassi VARCHAR(10),
    num_motor VARCHAR(10),
    cor VARCHAR(20),
    km_atual INTEGER,
    revisao_pendente BIT,
    
    FOREIGN KEY (cod_tipo) 
        REFERENCES Tipo_Veiculo(cod_tipo) 
        ON UPDATE CASCADE
        ON DELETE CASCADE,

    FOREIGN KEY (cod_filial_atual) 
        REFERENCES Filial(cod_filial) 
        ON UPDATE CASCADE
        ON DELETE CASCADE
);


CREATE TABLE Revisao(
    cod_tipo VARCHAR(5),
    cod_revisao INTEGER,
    km_media INTEGER,
    

    PRIMARY KEY (cod_tipo, cod_revisao),
    FOREIGN KEY (cod_tipo) 
        REFERENCES Tipo_Veiculo(cod_tipo)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);


CREATE TABLE Reserva(
    cod_reserva INTEGER PRIMARY KEY,
    cod_tipo VARCHAR(5),
    cod_filial_dest VARCHAR(10),
    cod_filial_orig VARCHAR(10),
    cod_cliente INTEGER,
    data_retirada DATE,
    data_entrega DATE,

    FOREIGN KEY (cod_tipo) 
        REFERENCES Tipo_Veiculo(cod_tipo)
        ON UPDATE CASCADE
        ON DELETE NO ACTION,

    FOREIGN KEY (cod_filial_dest) 
        REFERENCES Filial(cod_filial)
        ON UPDATE CASCADE
        ON DELETE NO ACTION,

    FOREIGN KEY (cod_filial_orig) 
        REFERENCES Filial(cod_filial)
        ON UPDATE CASCADE
        ON DELETE NO ACTION,

    FOREIGN KEY (cod_cliente) 
        REFERENCES Cliente(cod_cliente)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);


CREATE TABLE Locacao(
    cod_locacao INTEGER PRIMARY KEY,
    cod_placa VARCHAR(10),
    cod_filial_dest VARCHAR(10),
    cod_motorista INTEGER,
    data_entrega DATE,

    FOREIGN KEY (cod_placa) 
        REFERENCES Veiculo(cod_placa)
        ON UPDATE NO ACTION
        ON DELETE CASCADE,

    FOREIGN KEY (cod_filial_dest) 
        REFERENCES Filial(cod_filial)
        ON UPDATE CASCADE
        ON DELETE NO ACTION,

    FOREIGN KEY (cod_motorista) 
        REFERENCES Motorista(cod_motorista)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);