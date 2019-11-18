-- Inserir cliente como pessoa física
INSERT INTO Cliente(nome, endereco, sexo, data_nasc, cpf) VALUES
('Joao Silva', 'Rua Sete 820', 'M', 1999-5-18, 11122233344);

-- Inserir cliente como pessoa jurídica
INSERT INTO Cliente(nome, endereco, cnpj, inscr_estado) VALUES
('Joao S. Imobiliaria', 'Rua Sete 820', 18853410000140, 0223233556);

-- Inserir motorista para cliente como pessoa física com nome e CPF fornecidos
INSERT INTO Motorista(cod_cliente, num_habili, vencimento_habili, ident_motorista) VALUES
((SELECT cod_cliente FROM Cliente WHERE nome = "Joao Silva" AND cpf = 11122233344), 12345678900, 2020-10-10, 1234567980);

-- Inserir tipo de veículo de passageiro
INSERT INTO Tipo_veiculo(cod_tipo, horas_limpeza, horas_revisao, tamanho, num_lugares, num_portas, ar_condicionado, radio, mp3, cd, dir_hidr, cambio_auto) VALUES
('M2', 5, 10, 'M', 5, 4, 'S', 'N', 'N', 'N', 'S', 'S');

-- Inserir tipo de veículo de carga
INSERT INTO Tipo_veiculo(cod_tipo, horas_limpeza, horas_revisao, capacidade) VALUES
('G2', 5, 10, 500);

-- Inserir filial
INSERT INTO Filial(cod_filial, localizacao) VALUES
('Filial1', 'Pindamonhangaba');

-- Inserir veículo
INSERT INTO Veiculo(cod_placa, cod_tipo, cod_filial_atual, num_chassi, num_motor, cor, km_atual, revisao_pendente) VALUES
('ABC-1234', 'M2', 'Filial1', '123123', '321321', 'Vermelho', 12485, 1);

-- Inserir revisão
INSERT INTO Revisao(cod_tipo, km_media) VALUES
('M2', 50000);

-- Inserir reserva para cliente como pessoa jurídica por nome e CNPJ
INSERT INTO Reserva(cod_tipo, cod_filial_dest, cod_filial_orig, cod_cliente, data_retirada, data_entrega) VALUES
('M2', 'Filial1', 'Filial2', (
    SELECT cod_cliente
    FROM Cliente
    WHERE nome = 'Joao S. Imobiliaria' AND cnpj = 18853410000140
), 2020-10-10, 2020-11-15);

-- Inserir locação de motorista por numero de habilitação
INSERT INTO Locacao(cod_placa, cod_filial_dest, cod_motorista, data_entrega) VALUES
('ABC-1234', 'Filial2', 12345678900, 2020-10-20);