-- Remover pessoa física por nome e CPF
DELETE FROM Cliente
WHERE nome = 'Joao Silva' AND cpf = 11122233344;

-- Remover pessoa jurídica por nome e CNPJ
DELETE FROM Cliente
WHERE nome = 'Joao S. Imobiliaria' AND cpf = 18853410000140;

-- Remover motorista por numero de habilitação
DELETE FROM Motorista 
WHERE num_habili = 12345678900;

-- Remover tipo de veículo
DELETE FROM Tipo_Veiculo
WHERE cod_tipo = 'M2';

-- Remover filial
DELETE FROM Filial
WHERE cod_filial = 'Filial2';

-- Remover veiculo
DELETE FROM Veiculo
WHERE cod_placa = 'ABC-1234';

-- Remover revisao por codigo de tipo e codigo de revisao
DELETE FROM Revisao
WHERE cod_tipo = 'M2' AND cod_revisao = 6;

-- Remover reserva
DELETE FROM Reserva
WHERE cod_reserva = 42;

-- Remover locação
DELETE FROM Locacao
WHERE cod_locacao = 42;