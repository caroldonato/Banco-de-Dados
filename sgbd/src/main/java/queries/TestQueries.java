package queries;

import javax.persistence.*;

public class TestQueries{
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("LocadoraVeiculos-PU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Insertion inserts = new Insertion(entityManager);
        Deletion deletions = new Deletion(entityManager);
        ClientQueries clientQueries = new ClientQueries(entityManager);

        deletions.clearAllTables();
        inserts.populateTables();
        clientQueries.sampleQuery();

        entityManager.close();
        entityManagerFactory.close();
    }
}
