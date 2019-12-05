package queries;

import javax.persistence.EntityManager;

public class Deletion {
    private EntityManager em;
    private static Deletion instance = null;

    /* CONSTRUCTORS & SINGLETON IMPLEMENTATION */
    public Deletion(EntityManager entityManager) {
        this.em = entityManager;
    }

    public static Deletion getInstance(EntityManager entityManager) {
        if(instance == null) { instance = new Deletion(entityManager); }
        return instance;
    }

    /* DELETION QUERIES */

    public void deleteEntity(Object o) // Basic deletion of persistent object
    {
        em.getTransaction().begin();
        em.remove(o);
        em.getTransaction().commit();
    }

    /* DATABASE CLEANING */
    public void clearAllTables()
    {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Cliente").executeUpdate();
        em.createQuery("DELETE FROM P_Fisica").executeUpdate();
        em.createQuery("DELETE FROM P_Juridica").executeUpdate();
        em.createQuery("DELETE FROM Filial").executeUpdate();
        em.createQuery("DELETE FROM Locacao").executeUpdate();
        em.createQuery("DELETE FROM Motorista").executeUpdate();
        em.createQuery("DELETE FROM Reserva").executeUpdate();
        em.createQuery("DELETE FROM Revisao").executeUpdate();
        em.createQuery("DELETE FROM Tipo_Veiculo").executeUpdate();
        em.createQuery("DELETE FROM Tipo_Carga").executeUpdate();
        em.createQuery("DELETE FROM Tipo_Passageiro").executeUpdate();
        em.createQuery("DELETE FROM Veiculo").executeUpdate();
        em.getTransaction().commit();
    }


    /* GETTERS & SETTERS */
    public EntityManager getEm() {
        return em;
    }
    public void setEm(EntityManager em) {
        this.em = em;
    }
}
