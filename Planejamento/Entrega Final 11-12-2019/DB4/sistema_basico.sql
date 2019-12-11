--------------------------------------------------------------------------------------
-----------------------------------Popular tabelas------------------------------------
--------------------------------------------------------------------------------------
-- Inserir filial
INSERT INTO Filial(cod_filial, localizacao) VALUES
('Filial1', 'Pindamonhangaba');

-- Inserir tipo de veículo de passageiro
INSERT INTO Tipo_Veiculo(cod_tipo, horas_limpeza, horas_revisao, tamanho, num_lugares, num_portas, ar_condicionado, radio, mp3, cd, dir_hidr, cambio_auto) VALUES
('M2', 5, 10, 'M', 5, 4, 1, 0, 0, 0, 1, 1);

-- Inserir tipo de veículo de carga
INSERT INTO Tipo_Veiculo(cod_tipo, horas_limpeza, horas_revisao, capacidade) VALUES
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
	
-- Consultar os veículos presentemente alugados pela filial, o ponto de entrega (caso seja diferente do de locação) e data de entrega prevista.
	SELECT cod_placa, cod_filial_atual, cod_filial_dest, data_entrega
	FROM Veiculo JOIN locacoes_recentes ON Veiculo.cod_placa = locacoes_recentes.cod_placa
	WHERE data_entrega < 'Dia atual' AND cod_filial_atual = 'Filial analisada'

-- Fazer reserva
	INSERT INTO Reserva(cod_tipo, cod_filial_dest, cod_filial_orig, cod_cliente, data_retirada, data_entrega) VALUES
	('M2', 'Filial1', 'Filial2', 163, '2020-10-10', '2020-10-20');

-- Fazer locação
	INSERT INTO Locacao(cod_placa, cod_filial_dest, cod_motorista, data_entrega) VALUES
	('ABC-1234', 'Filial2', 53, '2020-10-20');

--------------------------------------------------------------------------------------
---------------------------------Consultas do grupo A---------------------------------
--------------------------------------------------------------------------------------

-- Consultar todos os veiculos e seus dados da locadora
	SELECT *
	FROM
		Veiculo JOIN Tipo_Veiculo
		ON Veiculo.cod_tipo = Tipo_Veiculo.cod_tipo
	
-- Todos os dados do veículo da placa especificada
	SELECT *
	FROM
		Veiculo JOIN Tipo_Veiculo
		ON Veiculo.cod_tipo = Tipo_Veiculo.cod_tipo
	WHERE cod_placa = 'ABC-1234'

-- Todas as identificações dos veículos com a cor fornecida
	SELECT *
	FROM Veiculo
	WHERE cod = 'Preto'

-- Todas as identificações dos veóuclos com quilometragem abaixo de uma marca fornecida
	SELECT *
	FROM Veiculo
	WHERE km_atual < 30000

-- Veiculos do tipo passageiro e suas caracteristicas
	SELECT
		cod_tipo,
		horas_limpeza,
		horas_revisao,
		tamanho,
		num_lugares,
		num_portas,
		ar_condicionado,
		radio,
		mp3,
		cd,
		dir_hidr,
		cambio_auto,
		cod_placa,
		cod_filial_atual,
		num_chassi,
		num_motor,
		cor,
		km_atual,
		revisao_pendente,
		parado
	FROM
		Tipo_Veiculo JOIN Veiculo
		ON Tipo_Veiculo.cod_tipo = Veiculo.cod_tipo
	WHERE capacidade IS NULL

-- Veiculos do tipo de carga e suas caracteristicas
	SELECT
		cod_tipo,
		horas_limpeza,
		horas_revisao,
		capacidade,
		cod_placa,
		cod_filial_atual,
		num_chassi,
		num_motor,
		cor,
		km_atual,
		revisao_pendente,
		parado
	FROM
		Tipo_Veiculo JOIN Veiculo
		ON Tipo_Veiculo.cod_tipo = Veiculo.cod_tipo
	WHERE capacidade IS NULL

-- Veiculos de passageiro do tamanho especificado
	SELECT *
	FROM Veiculo JOIN Tipo_Veiculo ON Tipo_Veiculo.cod_tipo = Veiculo.cod_tipo
	WHERE tamanha = 'G'

-- Veiculos de passsageiro com quantidade de tamanhos especificada
	SELECT *
	FROM Veiculo JOIN Tipo_Veiculo ON Tipo_Veiculo.cod_tipo = Veiculo.cod_tipo
	WHERE num_lugares >= 4

-- Veiculo de passageiros com numero de portas especificada
	SELECT *
	FROM Veiculo JOIN Tipo_Veiculo ON Tipo_Veiculo.cod_tipo = Veiculo.cod_tipo
	WHERE num_portas = 4

-- Veiculo de passageiro com ar condicionado e MP3
	SELECT *
	FROM Veiculo JOIN Tipo_Veiculo ON Tipo_Veiculo.cod_tipo = Veiculo.cod_tipo
	WHERE ar_condicionado = 1 AND mp3 = 1

-- Veiculo de passageiro com direção hidráulica
	SELECT *
	FROM Veiculo JOIN Tipo_Veiculo ON Tipo_Veiculo.cod_tipo = Veiculo.cod_tipo
	WHERE dir_hidr = 1

-- Veiculo de passageiro com cambio automatico e sem direção hidráulica
	SELECT *
	FROM Veiculo JOIN Tipo_Veiculo ON Tipo_Veiculo.cod_tipo = Veiculo.cod_tipo
	WHERE dir_hidr = 0 AND cambio_auto = 1

-- Veiculo de carga com capacidade acima da especificada
	SELECT *
	FROM Veiculo JOIN Tipo_Veiculo ON Tipo_Veiculo.cod_tipo = Veiculo.cod_tipo
	WHERE capacidade > 3000

-- Veiculos sem locação associada
	SELECT Veiculo.*
	FROM
		Veiculo LEFT JOIN Locacao
		ON Veiculo.cod_placa = Locacao.cod_placa
	WHERE cod_locacao IS NULL

-- Todos os clientes com locação de veiculos de carga registrados
	SELECT DISTINCT Cliente.*
	FROM
		Locacao
		JOIN Veiculo ON Locacao.cod_placa = Veiculo.cod_placa
		JOIN Tipo_Veiculo ON Veiculo.cod_tipo = Tipo_Veiculo.cod_tipo
		JOIN Motorista ON Locacao.cod_motorista = Motorista.cod_motorista
		JOIN Cliente ON Motorista.cod_cliente = Cliente.cod_cliente
	WHERE
		capacidade IS NOT NULL

-- Pessoa que está alugando determinado veículo
	SELECT cod_cliente, nome, endereco
	FROM 
		locacoes_recentes
		JOIN Veiculo ON locacoes_recentes.cod_placa = Veiculo.cod_placa
		JOIN Motorista ON locacoes_recentes.cod_motorista = Motorista.cod_motorista
		JOIN Cliente ON Motorista.cod_cliente = Cliente.cod_cliente
	WHERE
		cod_placa = 'ABC-1234' AND parado = 0

-- Tempo de revisão e limpeza de um determinado veículo
	SELECT horas_limpeza, horas_revisao
	FROM Tipo_Veiculo JOIN Veiculo ON Tipo_Veiculo.cod_tipo = Veiculo.cod_tipo
	WHERE cod_placa = 'ABC-1234'

-- Nome e CPF de pessoas fisicas nascidas depois de 2000
	SELECT nome, cpf
	FROM Cliente
	WHERE data_nasc > '2000-01-01'

-- Motoristas com habilitação vencida
	SELECT *
	FROM Motorista
	WHERE vencimento_habili <= 'Data atual aqui'

-- Clientes com alguma reserva
	SELECT DISTINCT Cliente.*
	FROM Cliente JOIN reservas_recentes ON Cliente.cod_cliente = reservas_recentes.cod_cliente

-- Código das reservas que originam e destinam para a mesma filial com tipo de veículo de carga
	SELECT cod_reserva
	FROM Reserva JOIN Tipo_Veiculo ON Reserva.cod_tipo = Tipo_Veiculo.cod_tipo
	WHERE cod_filial_orig = cod_filial_dest AND capacidade IS NOT NULL

-- Nome do cliente e placa de veículo de toda locação da filial destinataria informada onde a quilometragem atual do veiculo exceda 100.000 quilometros
	SELECT nome, cod_placa
	FROM
		Locacao
		JOIN Veiculo ON Locacao.cod_placa = Veiculo.cod_placa
		JOIN Motorista ON Locacao.cod_motorista = Motorista.cod_motorista
		JOIN Cliente ON Motorista.cod_cliente = Cliente.cod_cliente
	WHERE km_atual > 100000 AND cod_filial_dest = 'Filial2'

-- Nome do cliente e o numero de habilitação do motorista das locações feitas antes do dia 10 outubro de 2012
	SELECT nome, ident_motorista
	FROM
		Locacao
		JOIN Motorista ON Locacao.cod_motorista = Motorista.cod_motorista
		JOIN Cliente ON Motorista.cod_cliente = Cliente.cod_cliente
	WHERE data_entrega < '2012-10-10'