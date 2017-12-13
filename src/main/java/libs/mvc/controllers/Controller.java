package libs.mvc.controllers;

import libs.EntityManagerHolder;
import libs.mvc.View;
import libs.ui.template.Template;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * Abstract model controller
 * @param <ModelPKType> the type of the model's primary key
 */
public abstract class Controller<ModelPKType extends Serializable>
{
    /** The entity manager for the persistence and the database access */
    protected EntityManager em;

    /**
     * Default constructor
     */
    public Controller()
    {
        this.em = EntityManagerHolder.getEntityManager();
    }

    /**
     * Show the view that displays one model's instance
     * @param id
     */
    public abstract void show (ModelPKType id);

    /**
     * Show the view that displays the list of data
     */
    public abstract void showAll();

    /**
     * Display a view in the template
     * @param view the view to display in the template
     */
    public void setTemplateView (View view)
    {
        Template.getInstance().setView(view);
    }
}
