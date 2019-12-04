package queries;

import javax.persistence.*;
import entities.*;
import java.util.List;

public class TestQueries{
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("LocadoraVeiculos-PU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Testes
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Cliente").executeUpdate();
        entityManager.createQuery("DELETE FROM P_Fisica").executeUpdate();
        entityManager.createQuery("DELETE FROM P_Juridica").executeUpdate();
        entityManager.getTransaction().commit();

        P_Fisica cliente = new P_Fisica();
        cliente.setCod_cliente(1);
        cliente.setNome("Fulano de Tal");
        cliente.setEndereco("Rua Aquela Lá, 1212");
        cliente.setSexo("X");
        cliente.setData_nasc(java.time.LocalDate.now());
        cliente.setCpf(123142);

        P_Juridica cliente2 = new P_Juridica();
        cliente2.setCod_cliente(2);
        cliente2.setNome("Deltrano de Tal");
        cliente2.setEndereco("Rua Aquela Outra, 1313");
        cliente2.setCnpj(1312412);
        cliente2.setInscr_estado("A123BACa_13");

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();
        entityManager.persist(cliente2);
        entityManager.getTransaction().commit();

        String jpql = "select f from P_Fisica f";
        TypedQuery<P_Fisica> typedQuery = entityManager.createQuery(jpql, P_Fisica.class);
        List<P_Fisica> result = typedQuery.getResultList();
        result.forEach(f -> System.out.println("Código: " + f.getCod_cliente() + " | Nome : " +
                f.getNome() + " | Endereço: " + f.getEndereco()));

        entityManager.close();
        entityManagerFactory.close();
    }
}
