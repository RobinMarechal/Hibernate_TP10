import models.Owner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main
{
    public static void main (String[] args)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate_tp10");
        EntityManager        em  = emf.createEntityManager();


        Owner o1 = new Owner("Robin", "Ici");

        em.getTransaction().begin();
        em.persist(o1);

        o1.addPhoneNumber("0662119806");

        em.getTransaction().commit();

        System.out.println(o1);
        System.out.println(o2);
    }
}
