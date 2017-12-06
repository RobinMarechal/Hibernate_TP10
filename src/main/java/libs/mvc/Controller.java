package libs.mvc;

import libs.EntityManagerHolder;

import javax.persistence.EntityManager;

public abstract class Controller
{

    protected EntityManager em;

    public Controller()
    {
        this.em = EntityManagerHolder.getEntityManager();
    }

    public abstract void home();

    public abstract void show(int id);

    public abstract void showAll();
}
