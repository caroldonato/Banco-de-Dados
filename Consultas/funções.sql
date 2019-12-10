-- Contar número total de clientes registrados.

CREATE OR REPLACE FUNCTION contar_clientes(OUT total integer)
  RETURNS integer AS
$BODY$
BEGIN
	total = COUNT(cod_cliente) FROM cliente;
END;
$BODY$
  LANGUAGE plpgsql;

-- Contar número total de filiais registradas.

CREATE OR REPLACE FUNCTION contar_filiais(OUT total integer)
  RETURNS integer AS
$BODY$
BEGIN
	total = COUNT(cod_filial) FROM filial;
END;
$BODY$
  LANGUAGE plpgsql;

-- Contar número total de locações registradas.

CREATE OR REPLACE FUNCTION contar_locacao(OUT total integer)
  RETURNS integer AS
$BODY$
BEGIN
	total = COUNT(cod_locacao) FROM locacao;
END;
$BODY$
  LANGUAGE plpgsql;

-- Contar número total de motoristas registrados.

CREATE OR REPLACE FUNCTION contar_motoristas(OUT total integer)
  RETURNS integer AS
$BODY$
BEGIN
	total = COUNT(cod_motoristas) FROM motorista;
END;
$BODY$
  LANGUAGE plpgsql;

-- Contar número total de clientes de tipo pessoa física registrados.

CREATE OR REPLACE FUNCTION contar_pessoa_fisica(OUT total integer)
  RETURNS integer AS
$BODY$
BEGIN
	total = COUNT(cod_cliente) FROM pessoa_fisica;
END;
$BODY$
  LANGUAGE plpgsql;

-- Contar número total de clientes de tipo pessoa jurídica registrados.

CREATE OR REPLACE FUNCTION contar_pessoa_juridica(OUT total integer)
  RETURNS integer AS
$BODY$
BEGIN
	total = COUNT(cod_cliente) FROM pessoa_juridica;
END;
$BODY$
  LANGUAGE plpgsql;

-- Contar número total de reservas registradas.

CREATE OR REPLACE FUNCTION contar_reservas(OUT total integer)
  RETURNS integer AS
$BODY$
BEGIN
	total = COUNT(cod_reserva) FROM reserva;
END;
$BODY$
  LANGUAGE plpgsql;

-- Contar número total de revisões registradas.

CREATE OR REPLACE FUNCTION contar_revisoes(OUT total integer)
  RETURNS integer AS
$BODY$
BEGIN
	total = COUNT(cod_revisao) FROM revisao;
END;
$BODY$
  LANGUAGE plpgsql;

-- Contar número total de veículos de tipo carga registrados.

CREATE OR REPLACE FUNCTION contar_tipo_carga(OUT total integer)
  RETURNS integer AS
$BODY$
BEGIN
	total = COUNT(cod_tipo) FROM tipo_carga;
END;
$BODY$
  LANGUAGE plpgsql;

-- Contar número total de veículos de tipo passageiro registrados.

CREATE OR REPLACE FUNCTION contar_tipo_passageiro(OUT total integer)
  RETURNS integer AS
$BODY$
BEGIN
	total = COUNT(cod_tipo) FROM tipo_passageiro;
END;
$BODY$
  LANGUAGE plpgsql;

-- Contar número total de tipos de veículos registrados.

CREATE OR REPLACE FUNCTION contar_tipos(OUT total integer)
  RETURNS integer AS
$BODY$
BEGIN
	total = COUNT(cod_tipo) FROM tipo_veiculo;
END;
$BODY$
  LANGUAGE plpgsql;

-- Contar número total de veículos registrados.

CREATE OR REPLACE FUNCTION contar_veiculos(OUT total integer)
  RETURNS integer AS
$BODY$
BEGIN
	total = COUNT(cod_placa) FROM veiculo;
END;
$BODY$
  LANGUAGE plpgsql;

