package app.controllers;

import app.models.Producer;
import app.views.producers.AllProducersView;
import app.views.producers.CreateProducerDialog;
import app.views.producers.ShowProducerView;
import fr.polytech.marechal.FormMap;
import libs.mvc.View;
import libs.mvc.controllers.Controller;
import libs.mvc.controllers.Home;
import libs.mvc.controllers.ModelManager;
import libs.ui.components.dialogs.Dialog;
import libs.ui.components.dialogs.DialogsManager;

import java.util.List;

public class ProducerController extends Controller<Integer> implements Home, ModelManager<Producer>
{

    public ProducerController ()
    {
        super();
    }

    @Override
    public void home ()
    {
        showAll();
    }

    @Override
    public void show (Integer id)
    {
        Producer producer = em.find(Producer.class, id);
        View     view     = new ShowProducerView(this, producer);
        this.setTemplateView(view);
    }

    @Override
    public void showAll ()
    {
        List<Producer>   producers = (List<Producer>) em.createQuery("FROM Producer").getResultList();
        AllProducersView view      = new AllProducersView(this, producers);
        this.setTemplateView(view);
    }

    /**
     * Show the dialog for the modification or the creation of the model
     *
     * @param model the model to update, or null for a creation
     */
    @Override
    public void showCreationDialog (Producer model)
    {
        Dialog dialog = new CreateProducerDialog(this);
        DialogsManager.instance.openDialog(dialog);
    }

    /**
     * Show the deletion dialog
     *
     * @param model the model to delete
     */
    @Override
    public void showDeleteDialog (Producer model)
    {

    }

    /**
     * Create an instance of the Model and persists it to the database
     *
     * @param form the form with the model's data
     */
    @Override
    public void create (FormMap form)
    {
        String   name = ((String) form.get("name").getValue());

        Producer created = new Producer(name);
        created.persist();

        DialogsManager.instance.closeLastOpened();

        this.show(created.getId());
    }

    /**
     * Delete a model instance and persists it to the database
     *
     * @param model the model instance to delete
     */
    @Override
    public void delete (Producer model)
    {
    }

    /**
     * Update a model and persists the changes to in the database
     *
     * @param model the model to update
     * @param form  the form with the model's data
     */
    @Override
    public void update (Producer model, FormMap form)
    {
    }
}
