package libs.mvc.models;

import libs.EntityManagerHolder;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;
import java.util.List;

/**
 * DAO abstract class
 * @param <MType> The model's class
 * @param <PKType> the primary key's class
 */
public abstract class ModelDAO<MType extends Model, PKType extends Serializable>
{
    /** The entity manager */
    protected final EntityManager em;

    /** The transaction */
    private final EntityTransaction transaction;

    /**
     * Default constructor
     */
    public ModelDAO ()
    {
        this.em = EntityManagerHolder.getEntityManager();
        this.transaction = em.getTransaction();
}

    /**
     * Getter for the Model's class
     * @return the model's class
     */
    abstract public Class<MType> getModelClazz();

    /**
     * Retrieve all from the database
     * @return the record, or an empty list if the table is empty
     */
    public List<MType> all()
    {
        String modelClassName = getModelClazz().getSimpleName();
        return em.createQuery("FROM " + modelClassName).getResultList();
    }

    /**
     * Retrieve one record from the db based on the primary key
     * @param primaryKey the record's primary key
     * @return the record or null of not found
     */
    public MType find(PKType primaryKey)
    {
        return em.find(getModelClazz(), primaryKey);
    }

    /**
     * Remove an entity from the database
     * @param model the entity to remove
     */
    public void remove(MType model)
    {
        transaction.begin();
        em.remove(model);
        transaction.commit();
    }

    /**
     * Persist an entity to the database
     * @param model the entity to persist
     */
    public void persist(MType model)
    {
        transaction.begin();
        em.persist(model);
        transaction.commit();
    }
}
