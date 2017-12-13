package libs.mvc;

import libs.EntityManagerHolder;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;

public abstract class Model<PKtype extends Serializable>
{
    abstract public PKtype getId ();

    public void persist ()
    {
        EntityManager em = EntityManagerHolder.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        em.persist(this);
        transaction.commit();
    }
}
