package libs.mvc.models;

import libs.EntityManagerHolder;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;
import java.util.List;

public abstract class ModelDAO<MType extends Model, PKType extends Serializable>
{
    protected final EntityManager em;
    private final EntityTransaction transaction;

    public ModelDAO ()
    {
        this.em = EntityManagerHolder.getEntityManager();
        this.transaction = em.getTransaction();
}

    abstract public Class<MType> getModelClazz();

    public List<MType> all()
    {
        String modelClassName = getModelClazz().getSimpleName();
        return em.createQuery("FROM " + modelClassName).getResultList();
    }

    public MType find(PKType primaryKey)
    {
        return em.find(getModelClazz(), primaryKey);
    }

    public void remove(MType model)
    {
        transaction.begin();
        em.remove(model);
        transaction.commit();
    }

    public void persist(MType model)
    {
        transaction.begin();
        em.persist(model);
        transaction.commit();
    }
}
