package queries;

import procedures.Procedures;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

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

        Procedures procedure = new Procedures(entityManager);
        Integer num_clientes = procedure.contarClientes();
        System.out.println("Número de clientes: " + num_clientes);

        Insertion insertion = new Insertion(entityManager);
        insertion.insertPessoaFisica("Oi", "Rua Aquela Lá, 1212",
                "X", LocalDate.of(2000, 5, 21), 12354352L);

        num_clientes = procedure.contarClientes();
        System.out.println("Número de clientes depois: " + num_clientes);


        entityManager.close();
        entityManagerFactory.close();
    }
}
