package queries;

import entities.Motorista;
import entities.P_Fisica;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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

        // Testes

        Motorista m = new Motorista();
        m.setCod_motorista(1);
        m.setNum_habili(1234567890);
        m.setVencimento_habili(LocalDate.of(2020,10,10));
        m.setIdent_motorista(1234567980);

        inserts.insertMotoristaForPFisicaUsingCpfAndName(m, "Fulano de Tal", 123142);

        entityManager.close();
        entityManagerFactory.close();
    }
}
