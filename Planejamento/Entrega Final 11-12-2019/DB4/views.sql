--------------------------------------------------------------------------------------
----------------------------------------Views-----------------------------------------
--------------------------------------------------------------------------------------
-- Locações mais novas para uma placa distinta
CREATE VIEW locacoes_recentes AS 
	SELECT l1.*
	FROM Locacao AS l1 LEFT JOIN Locacao AS l2
	ON (l1.cod_placa = l2.cod_placa AND l1.data_entrega < l2.data_entrega)
	WHERE l2.data_entrega IS NULL
GO;

-- Reservas ativas para data atual
CREATE VIEW reservas_recentes AS 
	SELECT *
	FROM Reserva
	WHERE data_entrega > SYSDATETIME()
GO;

