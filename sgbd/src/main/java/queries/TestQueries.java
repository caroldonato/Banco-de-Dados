package queries;

import entities.Cliente;
import entities.Motorista;
import entities.Pessoa_Fisica;
import entities.Veiculo;
import queries.byentity.ClienteQueries;
import queries.byentity.MotoristaQueries;
import queries.byentity.VehicleQueries;
import queries.byentity.VehicleTypeQueries;

import javax.persistence.*;
import java.util.List;

public class TestQueries{
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("LocadoraVeiculos-PU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Insertion inserts = new Insertion(entityManager);
        Deletion deletions = new Deletion(entityManager);

        deletions.clearAllTables();
        inserts.populateTables();
        ClienteQueries clienteQueries = new ClienteQueries(entityManager);
        VehicleQueries vehicleQueries = new VehicleQueries(entityManager);
        VehicleTypeQueries vehicleTypeQueries = new VehicleTypeQueries(entityManager);
        MotoristaQueries motoristaQueries = new MotoristaQueries(entityManager);

        // Testes

        entityManager.close();
        entityManagerFactory.close();
    }
}
