package libs.mvc.controllers;

import fr.polytech.marechal.FormMap;
import javafx.scene.control.Alert;
import libs.EntityManagerHolder;
import libs.mvc.models.Model;
import libs.mvc.models.ModelDAO;
import libs.mvc.views.View;
import libs.ui.template.Template;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * Abstract model controller
 * @param <ModelPKType> the type of the model's primary key
 */
public abstract class Controller<MType extends Model, ModelPKType extends Serializable, DaoType extends ModelDAO>
{
    /** The entity manager for the persistence and the database access */
    protected EntityManager em;

    /** The dao instance */
    protected DaoType dao;

    /**
     * Default constructor
     */
    public Controller()
    {
        this.em = EntityManagerHolder.getEntityManager();
        this.dao = prepareDAO();
    }

    abstract protected DaoType prepareDAO ();

    /**
     * Show the view that displays one model's instance
     * @param id the primary key
     */
    public abstract void show (ModelPKType id);

    /**
     * Show the details of a model instance
     * @param model the model instance
     */
    public abstract void showDetails (MType model);

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
    /**
     * Show the dialog for the modification or the creation of the model
     */
    abstract public void showCreationDialog ();

    /**
     * Show the dialog for the modification or the creation of the model
     *
     * @param model the model to update, or null for a creation
     */
    abstract public void showUpdateDialog (MType model);

    /**
     * Show the deletion dialog
     *
     * @param model the model to delete
     */
    abstract public void showDeleteDialog (MType model);

    /**
     * Show the deletion dialog for a data type
     * @param model the model to delete
     * @param dataName the name of the data
     */
    protected void showDeleteDialogForClass(MType model, String dataName)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setHeaderText("Are you sure?");
        alert.setContentText("This can't be undone.");
        alert.setTitle("Delete a " + dataName);

        if (alert.getResult().getButtonData().isDefaultButton()) {
            delete(model);
        }
    }

    /**
     * Create an instance of the Model and persists it to the database
     *
     * @param form the form with the model's data
     */
    abstract public void create (FormMap form);

    /**
     * Update a model and persists the changes to in the database
     *
     * @param model the model to update. If model is null, it will create a new instance and persist it
     * @param form  the form with the model's data
     */
    abstract public void update (MType model, FormMap form);

    /**
     * Delete a model instance and persists it to the database
     *
     * @param model the model instance to delete
     */
    abstract public void delete (MType model);
}
