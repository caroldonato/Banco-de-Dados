--------------------------------------------------------------------------------------
-----------------------------------Popular tabelas------------------------------------
--------------------------------------------------------------------------------------
-- Inserir filial
INSERT INTO Filial(cod_filial, localizacao) VALUES
('Filial1', 'Pindamonhangaba');

-- Inserir tipo de veículo de passageiro
INSERT INTO Tipo_veiculo(cod_tipo, horas_limpeza, horas_revisao, tamanho, num_lugares, num_portas, ar_condicionado, radio, mp3, cd, dir_hidr, cambio_auto) VALUES
('M2', 5, 10, 'M', 5, 4, 'S', 'N', 'N', 'N', 'S', 'S');

-- Inserir tipo de veículo de carga
INSERT INTO Tipo_veiculo(cod_tipo, horas_limpeza, horas_revisao, capacidade) VALUES
('G2', 5, 10, 500);
-- Inserir veículo
INSERT INTO Veiculo(cod_placa, cod_tipo, cod_filial_atual, num_chassi, num_motor, cor, km_atual) VALUES
('ABC-1234', 'M2', 'Filial1', '123123', '321321', 'Vermelho', 12485);

-- Inserir revisão
INSERT INTO Revisao(cod_tipo, km_media) VALUES
('M2', 50000);

--------------------------------------------------------------------------------------
---------------------------------Adicionar clientes-----------------------------------
--------------------------------------------------------------------------------------
-- Inserir cliente como pessoa física
INSERT INTO Cliente(nome, endereco, sexo, data_nasc, cpf) VALUES
('Joao Silva', 'Rua Sete 820', 'M', 1999-5-18, 11122233344);

-- Inserir cliente como pessoa jurídica
INSERT INTO Cliente(nome, endereco, cnpj, inscr_estado) VALUES
('Joao S. Imobiliaria', 'Rua Sete 820', 18853410000140, 0223233556);

-- Inserir motorista para cliente como pessoa física com nome e CPF fornecidos
INSERT INTO Motorista(cod_cliente, num_habili, vencimento_habili, ident_motorista) VALUES
((SELECT cod_cliente FROM Cliente WHERE nome = "Joao Silva" AND cpf = 11122233344), 12345678900, 2020-10-10, 1234567980);

-- Inserir motorista para cliente como pessoa jurídica com nome e CNPJ fornecedios
INSERT INTO Motorista(cod_cliente, num_habili, vencimento_habili, ident_motorista) VALUES
((SELECT cod_cliente FROM Cliente WHERE nome = "Joao S. Imobiliaria" AND cnpj = 18853410000140), 12345678900, 2020-10-10, 1234567980);

--------------------------------------------------------------------------------------
---------------------------------Ações de clientes------------------------------------
--------------------------------------------------------------------------------------
-- Consultar os veículos disponíveis em determinada filial na data corrente.
-- Ou seja, os veiculos no dia atual que estão parados.
SELECT cod_placa
FROM Veiculo
WHERE cod_filial_atual = 'Filial' AND parado = 1;

-- Consultar as reservas possíveis para veículos em uma filial, com previsão de que veículos estarão disponíveis em uma data futura;
-- Veiculos em todos os estados em uma filial em uma data futura que podem ser locados
    -- Veículo com locação com destino à filial e com data de entrega mais cedo que a data analisada
	-- Veiculo parado na filial
	-- Uniao das duas tabelas de veículos disponíveis
	-- Reservas marcadas para a data procurada para o tipo de carro procurado na filial procurada
	-- Contar quantos veículos do tipo desejado estão disponíveis na filial, ou seja, parado ou chegando à filial de acordo com o subitem anterior
    -- Ver se essa contagem é maior que a quantidade de reservas desse tipo de veiculo pro dia analisado

	-- Consulta final:
	WITH veic_loc_date AS (
        SELECT Veiculo.*
        FROM Veiculo JOIN Locacao ON (Veiculo.cod_placa = Locacao.cod_placa)
        WHERE
            (cod_filial_atual = 'Filial_desejada' 	AND
			cod_filial_dest IS NULL				) 	OR
			cod_filial_dest = 'Filial_desejada'		AND
			data_entrega < 'Data_desejada'
		GROUP BY cod_placa
		HAVING data_entrega = MAX(data_entrega)
	),
    veic_parado AS (
		SELECT *
		FROM Veiculo
		WHERE parado = 1 AND cod_filial_atual = 'Filial_desejada'
	),
	query_table AS (
		SELECT * FROM veic_loc_date
		UNION
		SELECT * FROM veic_parado
	),
	reserv_filial_date AS (
		SELECT *
		FROM Reserva
		WHERE data_retirada < 'Data_desejada' AND data_entrega > 'Data_desejada' AND cod_filial_dest = 'Filial_desejada'
	)
	SELECT
		COUNT(query_table.cod_placa) AS qntd_disponiveis,
		cod_tipo
	FROM (query_table JOIN reserv_filial_date ON query_table.cod_tipo = reserv_filial_date.cod_tipo)
	GROUP BY (cod_tipo)
	HAVING COUNT(query_table.cod_placa) > COUNT(reserv_filial_date.cod_placa)
	

    

    
-- Fazer reserva 