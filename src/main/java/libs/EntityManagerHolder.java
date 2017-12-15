package libs;

import javax.persistence.EntityManager;

/**
 * Program EntityManager's holder
 */
public class EntityManagerHolder
{
    /** the held entity manager */
    private static EntityManager entityManager;

    /**
     * Get the entity manager
     * @return the entity manager
     */
    public static EntityManager getEntityManager ()
    {
        return entityManager;
    }

    /**
     * set the entity manager
     * @param entityManager the entity manager
     */
    public static void setEntityManager (EntityManager entityManager)
    {
        EntityManagerHolder.entityManager = entityManager;
    }
}
