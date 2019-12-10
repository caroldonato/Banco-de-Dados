package queries;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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

        entityManager.close();
        entityManagerFactory.close();
    }
}
