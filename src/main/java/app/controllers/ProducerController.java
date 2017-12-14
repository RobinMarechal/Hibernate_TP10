package app.controllers;

import app.models.Producer;
import app.models.dao.ProducerDAO;
import app.views.producers.AllProducersView;
import app.views.producers.CreateProducerDialog;
import app.views.producers.ShowProducerView;
import fr.polytech.marechal.FormMap;
import fr.polytech.marechal.exceptions.ErrorType;
import fr.polytech.marechal.exceptions.FormException;
import libs.mvc.controllers.Controller;
import libs.mvc.controllers.Home;
import libs.mvc.views.View;
import libs.ui.components.dialogs.Dialog;
import libs.ui.components.dialogs.DialogsManager;
import libs.ui.template.Template;
import libs.ui.template.nav.NavbarItem;

import java.util.List;

public class ProducerController extends Controller<Producer, Integer, ProducerDAO> implements Home
{

    public ProducerController ()
    {
        super();
    }

    @Override
    protected ProducerDAO prepareDAO ()
    {
        return new ProducerDAO();
    }

    @Override
    public void home ()
    {
        showAll();
    }

    @Override
    public NavbarItem getAssociatedNavbarItem ()
    {
        return Template.instance.producersNavbarItem;
    }

    @Override
    public void show (Integer id)
    {
        Producer producer = dao.find(id);

        View view = new ShowProducerView(this, producer);
        this.setTemplateView(view);
        selectNabarItem();

    }

    @Override
    public void showDetails (Producer model)
    {

    }

    @Override
    public void showAll ()
    {
        List<Producer>   producers = dao.all();
        AllProducersView view      = new AllProducersView(this, producers);
        this.setTemplateView(view);
        selectNabarItem();
    }

    /**
     * Show the dialog for the modification or the creation of the model
     */
    @Override
    public void showCreationDialog ()
    {
        showUpdateDialog(null);
    }

    @Override
    public void showUpdateDialog (Producer model)
    {
        Dialog dialog = new CreateProducerDialog(this, model);
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
        this.showDeleteDialogForClass(model, "producer");
    }

    /**
     * Create an instance of the Model and persists it to the database
     *
     * @param form the form with the model's data
     */
    @Override
    public void create (FormMap form)
    {
        this.update(null, form);
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
        if(!form.hasKeys("name"))
            throw new FormException(ErrorType.MISSING_FIELD);

        String name = ((String) form.get("name").getValue());

        if (model == null) {
            model = new Producer(name);
            dao.persist(model);
        }
        else {
            model.setName(name);
        }

        DialogsManager.instance.closeLastOpened();

        this.show(model.getId());
    }

    /**
     * Delete a model instance and persists it to the database
     *
     * @param model the model instance to delete
     */
    @Override
    public void delete (Producer model)
    {
        dao.remove(model);
        this.showAll();
    }
}
