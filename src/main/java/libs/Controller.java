package libs;

import javax.persistence.EntityManager;

public abstract class Controller
{
    protected final EntityManager em;

    public Controller (EntityManager em)
    {
        this.em = em;
    }

    public abstract void show(Object id);

    public abstract void showAll();
}
