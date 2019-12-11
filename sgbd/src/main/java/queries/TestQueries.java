package queries;

import entities.Cliente;
import queries.byentity.ClienteQueries;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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

        // Testes

        ClienteQueries clienteQueries = new ClienteQueries(entityManager);
        List<Cliente> c = clienteQueries.queryAllClientes();

        c.forEach(cliente -> System.out.println(cliente.getNome()));

        Query query = new Query(entityManager);
        query.setQueries();
        query.queryAllClientes();

        entityManager.close();
        entityManagerFactory.close();
    }
}
