package libs.mvc.models;

import libs.EntityManagerHolder;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public abstract class ModelDAO<MType extends Model, PKType extends Serializable>
{
    protected final EntityManager em;

    public ModelDAO ()
    {
        this.em = EntityManagerHolder.getEntityManager();
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
        em.remove(model);
    }

    public void persist(MType model)
    {
        em.persist(model);
    }
}
