package sgbd.locadoraveiculos;

import javax.persistence.*;
import sgbd.locadoraveiculos.objects.Cliente;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("LocadoraVeiculos-PU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Testes
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Cliente").executeUpdate();
        entityManager.getTransaction().commit();

        Cliente cliente = new Cliente();
        cliente.setCod_cliente(1);
        cliente.setNome("Fulano de Tal");
        cliente.setEndereco("Rua Aquela Lá, 1212");

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();

        String jpql = "select c from Cliente c";
        TypedQuery<Cliente> typedQuery = entityManager.createQuery(jpql, Cliente.class);
        cliente = typedQuery.getSingleResult();
        System.out.println("Código: " + cliente.getCod_cliente() + " | Nome : " +
                            cliente.getNome() + " | Endereço: " + cliente.getEndereco());

        entityManager.close();
        entityManagerFactory.close();
    }
}
