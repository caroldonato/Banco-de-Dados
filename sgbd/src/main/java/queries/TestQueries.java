package queries;

import entities.*;
import queries.byentity.ClienteQueries;
import queries.byentity.FilialQueries;
import queries.byentity.VehicleTypeQueries;

import javax.persistence.*;
import java.time.LocalDate;

public class TestQueries{
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("LocadoraVeiculos-PU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Insertion inserts = new Insertion(entityManager);
        Deletion deletions = new Deletion(entityManager);
        ClienteQueries clienteQueries = new ClienteQueries(entityManager);
        FilialQueries filialQueries = new FilialQueries(entityManager);
        VehicleTypeQueries vehicleTypeQueries = new VehicleTypeQueries(entityManager);

        deletions.clearAllTables();
        inserts.populateTables();
        clienteQueries.sampleQuery();

        // Testes

        // INSERT INTO Veiculo(cod_placa, cod_tipo, cod_filial_atual, num_chassi, num_motor, cor, km_atual, revisao_pendente)
        // VALUES
        //('ABC-1234', 'M2', 'Filial1', '123123', '321321', 'Vermelho', 12485, 1);

        // Criando coisas que precisa para veiculo
        Filial filial = new Filial("Filial1", "Pindamonhangaba");
        inserts.insertEntity(filial);
        Filial filial2 = new Filial("Filial2", "São Paulo");
        inserts.insertEntity(filial2);

        Tipo_Passageiro tipo_passageiro = new Tipo_Passageiro("M2", 5, 10,
                "M", 5, 4, true, false, false, false,
                true, true);
        inserts.insertEntity(tipo_passageiro);

        Pessoa_Juridica pessoa_juridica = new Pessoa_Juridica("Joao S. Imobiliaria", "Endereço do João",
                18853410000140L, 43243253252L);

        inserts.insertEntity(pessoa_juridica);

        // Obtendo entidades de query para teste

        Filial filialGot = filialQueries.queryFilialWithCodFilial("Filial1");
        System.out.println("Resultado Filial: " + filialGot.getLocalizacao());

        Filial filialGot2 = filialQueries.queryFilialWithCodFilial("Filial2");
        System.out.println("Resultado Filial2: " + filialGot2.getLocalizacao());

        Tipo_Veiculo tipoGot = vehicleTypeQueries.queryTipoVeiculoWithCodTipo("M2");
        System.out.println("Resultado Tipo: " + tipoGot.getCod_tipo());

        Cliente clienteGot = clienteQueries.queryPJuridicaWithNameAndCnpj("Joao S. Imobiliaria", 18853410000140L);

        // Criando veiculo
        Veiculo veiculo = new Veiculo("ABC-1234", tipoGot, filialGot, "123123",
                "321321", "Vermelho", 12485, true, false);

        inserts.insertEntity(veiculo);

        // Criando revisao
        Revisao revisao = new Revisao(tipoGot, 50000);
        inserts.insertEntity(revisao);

        // Criando reserva para cliente como pessoa juridica por nome e cnpj

        Reserva reserva = new Reserva(tipoGot, filialGot, filialGot2, clienteGot,
                LocalDate.of(2020, 11, 15), LocalDate.of(2020, 11, 15));

        inserts.insertEntity(reserva);

        // FALHANDO POR ERRO DE CONSTRAINT
        Motorista m = new Motorista(clienteGot, 3294324792L, 321312931L, LocalDate.of(2030, 10, 01));
        inserts.insertEntity(m);

        //inserts.insertMotoristaForPessoaFisicaUsingCpfAndName(m, "Fulano de Tal", 123142L);

        entityManager.close();
        entityManagerFactory.close();
    }
}
