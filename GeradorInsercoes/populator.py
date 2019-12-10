import random
import time
import string

def str_time_prop(start, end, format, prop):
    """Get a time at a proportion of a range of two formatted times.

    start and end should be strings specifying times formated in the
    given format (strftime-style), giving an interval [start, end].
    prop specifies how a proportion of the interval to be taken after
    start.  The returned time will be in the specified format.
    """

    stime = time.mktime(time.strptime(start, format))
    etime = time.mktime(time.strptime(end, format))

    ptime = stime + prop * (etime - stime)

    return time.localtime(ptime)


def random_date(start, end, prop):
    return str_time_prop(start, end, '%m/%d/%Y %I:%M %p', prop)

def randInFile(file):
	file.seek(0)
	lines = file.readlines()
	line = lines[random.randrange(len(lines))]
	line = line[:-1]
	return line

def randCPF():
	return random.randrange(10000000000, 99999999999)

def randCNPJ():
	return random.randrange(100000000000, 999999999999)

def randGenericNum():
	return random.randrange(100000, 999999)

def randGender():
	return "M" if bool(random.getrandbits(1)) else "F"

def randBool():
	return "TRUE" if bool(random.getrandbits(1)) else "FALSE"

def randString(stringLength):
	return ''.join(random.choice(string.ascii_uppercase) for i in range(stringLength))

#Codigo gerador a partir daqui
num_insercao = int(input("Quantidade de insercoes em cada tabela: \n"))

print("ALTER SEQUENCE cliente_cod_cliente_seq RESTART;\n"+
	"ALTER SEQUENCE locacao_cod_locacao_seq RESTART;\n"+
	"ALTER SEQUENCE motorista_cod_motorista_seq RESTART;\n"+
	"ALTER SEQUENCE reserva_cod_reserva_seq RESTART;\n"+
	"ALTER SEQUENCE revisao_cod_revisao_seq RESTART;\n")

with open("G:\\UEL\\terceiroano\\TrabalhosEmGrupo\\BancoDeDados\\locadora_veiculo\\GeradorInsercoes\\list_name_city.txt", encoding="utf8") as city, open("G:\\UEL\\terceiroano\\TrabalhosEmGrupo\\BancoDeDados\\locadora_veiculo\\GeradorInsercoes\\list_name_person.txt", encoding="utf8") as person:
	list_filial = []
	list_tipo = []
	list_placa = []
	#Pessoa fisica
	for _ in range(num_insercao):
		birth = random_date("1/1/1971 12:00 AM", "1/1/2020 12:00 AM", random.random())
		print("WITH client AS (INSERT INTO Cliente(nome, endereco) VALUES (\'"+randInFile(person)+"\', \'"+randInFile(city)+"\') RETURNING cod_cliente)\n"+
		"INSERT INTO Pessoa_Fisica(cod_cliente, cpf, sexo, data_nasc) VALUES ((SELECT cod_cliente FROM client), "+str(randCPF())+", \'"+randGender()+"\', \'"+str(birth.tm_year)+"-"+str(birth.tm_mon)+"-"+str(birth.tm_mday)+"\');")
	print('')
	#Pessoa juridica
	for _ in range(num_insercao):
		print("WITH client AS (INSERT INTO Cliente(nome, endereco) VALUES (\'"+randInFile(person)+" Inc\', \'"+randInFile(city)+"\') RETURNING cod_cliente)\n"+
		"INSERT INTO Pessoa_Juridica(cod_cliente, cnpj, inscr_estado) VALUES ((SELECT cod_cliente FROM client), "+str(randCNPJ())+", "+str(randCNPJ())+");")
	print('')
	#Motorista
	for _ in range(num_insercao):
		venci = random_date("1/1/2020 12:00 AM", "1/1/2030 12:00 AM", random.random())
		print("INSERT INTO Motorista(cod_cliente, num_habili, vencimento_habili, ident_motorista) VALUES ("+str(random.randint(1, num_insercao*2))+", "+str(randCPF())+", \'"+str(venci.tm_year)+"-"+str(venci.tm_mon)+"-"+str(venci.tm_mday)+"\', "+str(randCPF())+");")
	print('')
	#Tipo_Passageiro
	for _ in range(num_insercao):
		list_tipo.append(randString(5))
		print("WITH tipo AS (INSERT INTO Tipo_Veiculo(cod_tipo, horas_limpeza, horas_revisao) VALUES (\'"+list_tipo[-1]+"\', "+str(random.randint(1,20))+"1, "+str(random.randint(1,20))+") RETURNING cod_tipo)\n"+
		"INSERT INTO Tipo_Passageiro(cod_tipo, tamanho, num_lugares, num_portas, ar_condicionado, radio, mp3, cd, dir_hidr, cambio_auto) "+
		"VALUES ((SELECT cod_tipo FROM tipo), \'"+randString(1)+"\', "+str(random.randint(1,7))+", "+str(random.randint(1,4))+", "+randBool()+", "+randBool()+", "+randBool()+", "+randBool()+", "+randBool()+", "+randBool()+");")
	print('')
	#Tipo_Carga
	for _ in range(num_insercao):
		list_tipo.append(randString(5))
		print("WITH tipo AS (INSERT INTO Tipo_Veiculo(cod_tipo, horas_limpeza, horas_revisao) VALUES (\'"+list_tipo[-1]+"\', "+str(random.randint(1,20))+", "+str(random.randint(1,20))+") RETURNING cod_tipo)\n"+
		"INSERT INTO Tipo_Carga(cod_tipo, capacidade) VALUES ((SELECT cod_tipo FROM tipo), "+str(random.randint(100, 10000))+");")
	print('')
	#Filial
	for _ in range(num_insercao):
		list_filial.append(randString(10))
		print("INSERT INTO Filial(cod_filial, localizacao) VALUES (\'"+list_filial[-1]+"\', \'"+randInFile(city)+"\');")
	print('')
	#Veiculo
	for _ in range(num_insercao):
		list_placa.append(randString(10))
		print("INSERT INTO Veiculo(cod_placa, cod_tipo, cod_filial_atual, num_chassi, num_motor, cor, km_atual) VALUES "+
		"(\'"+list_placa[-1]+"\', \'"+list_tipo[random.randint(0,(num_insercao*2)-1)]+"\', \'"+list_filial[random.randint(0, num_insercao-1)]+"\', \'"+str(randGenericNum())+"\', \'"+str(randGenericNum())+"\', \'"+randString(5)+"\', "+str(random.randint(0,100000))+");")
	print('')
	#Revisao
	for _ in range(num_insercao):
		print("INSERT INTO Revisao(cod_tipo, km_media) VALUES (\'"+list_tipo[random.randint(0,(num_insercao*2)-1)]+"\', "+str(random.randint(0,100000))+");")
	print('')
	#Reserva
	for _ in range(num_insercao):
		date1 = random_date("1/1/2020 12:00 AM", "1/1/2040 12:00 AM", random.random())
		date2 = random_date(time.strftime('%m/%d/%Y %I:%M %p', date1), "1/1/2040 12:00 AM", random.random())
		print("INSERT INTO Reserva(cod_tipo, cod_filial_dest, cod_filial_orig, cod_cliente, data_retirada, data_entrega) VALUES "+
		"(\'"+list_tipo[random.randint(0,(num_insercao*2)-1)]+"\', \'"+list_filial[random.randint(0, num_insercao-1)]+"\', \'"+list_filial[random.randint(0, num_insercao-1)]+"\', "+str(random.randint(1, num_insercao*2))+", \'"+
		str(date1.tm_year)+"-"+str(date1.tm_mon)+"-"+str(date1.tm_mday)+"\', \'"+str(date2.tm_year)+"-"+str(date2.tm_mon)+"-"+str(date2.tm_mday)+"\');")
	print('')
	#Locacao
	for _ in range(num_insercao):
		date1 = random_date("1/1/2020 12:00 AM", "1/1/2040 12:00 AM", random.random())
		print("INSERT INTO Locacao(cod_placa, cod_filial_dest, cod_motorista, data_entrega) VALUES "+
		"(\'"+list_placa[random.randint(0,num_insercao-1)]+"\', \'"+list_filial[random.randint(0, num_insercao-1)]+"\', "+str(random.randint(1, num_insercao))+", \'"+str(date1.tm_year)+"-"+str(date1.tm_mon)+"-"+str(date1.tm_mday)+"\');")

	
	
