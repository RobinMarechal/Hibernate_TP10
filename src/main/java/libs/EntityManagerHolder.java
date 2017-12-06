package libs;

import javax.persistence.EntityManager;

public class EntityManagerHolder
{
    private static EntityManager entityManager;

    public static EntityManager getEntityManager ()
    {
        return entityManager;
    }

    public static void setEntityManager (EntityManager entityManager)
    {
        EntityManagerHolder.entityManager = entityManager;
    }
}
