package app.controllers;

import app.models.ExternalPlace;
import app.models.Place;
import app.models.Theatre;
import app.models.dao.PlaceDAO;
import app.views.places.AllPlacesView;
import app.views.places.CreatePlaceDialog;
import app.views.places.ShowPlaceView;
import fr.polytech.marechal.FormMap;
import fr.polytech.marechal.exceptions.ErrorType;
import fr.polytech.marechal.exceptions.FormException;
import libs.PlaceType;
import libs.mvc.controllers.Controller;
import libs.mvc.controllers.Home;
import libs.mvc.views.View;
import libs.ui.components.dialogs.DialogsManager;
import libs.ui.template.Template;
import libs.ui.template.nav.NavbarItem;

import java.util.List;

/**
 * Controls both Theatre and ExternalPlace classes
 */
public class PlaceController extends Controller<Place, Integer, PlaceDAO> implements Home
{
    public PlaceController ()
    {
        super();
    }

    /**
     * Get the controlled model's DAO instance
     *
     * @return the controlled model's DAO instance
     */
    @Override
    protected PlaceDAO getDao ()
    {
        return new PlaceDAO();
    }

    /**
     * Show the view that displays one model's instance
     *
     * @param id the primary key
     */
    @Override
    public void show (Integer id)
    {
        Place place = dao.find(id);

        View view = new ShowPlaceView(this, place);
        setTemplateView(view);
        selectNabarItem();
    }

    @Override
    public void showDetails (Place model)
    {

    }

    /**
     * Show the view that displays the list of data
     */
    @Override
    public void showAll ()
    {
        List<Place> places = dao.all();

        View view = new AllPlacesView(this, places);
        setTemplateView(view);
        selectNabarItem();
    }

    /**
     * Show the data's home
     */
    @Override
    public void home ()
    {
        showAll();
    }


    /**
     * Get the associated navbar item
     * @return the associated navbar item
     */
    @Override
    public NavbarItem getAssociatedNavbarItem ()
    {
        return Template.instance.placesNavbarItem;
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
    public void showUpdateDialog (Place model)
    {
        CreatePlaceDialog dialog = new CreatePlaceDialog(this, model);
        DialogsManager.instance.openDialog(dialog);
    }

    /**
     * Show the deletion dialog
     *
     * @param model the model to delete
     */
    @Override
    public void showDeleteDialog (Place model)
    {
        this.showDeleteDialogForClass(model, "place");
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
    public void update (Place model, FormMap form)
    {
        if (!form.hasKeys("name", "address", "type", "description")) {
            throw new FormException(ErrorType.MISSING_FIELD);
        }

        String    name        = ((String) form.get("name").getValue());
        String    address     = ((String) form.get("address").getValue());
        PlaceType type        = ((PlaceType) form.get("type").getValue());
        String    description = ((String) form.get("description").getValue());


        if (model == null) {
            if (type == PlaceType.THEATRE) {
                model = new Theatre(name, address, description);
            }
            else {
                model = new ExternalPlace(name, address, description);
            }

            dao.persist(model);
        }
        else {
            model.setName(name);
            model.setAddress(address);
            model.setDescription(description);
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
    protected void delete (Place model)
    {
        dao.remove(model);
        this.showAll();
    }

    public void showAllOfType (PlaceType item)
    {
        List<Place> places = dao.allOfType(item);

        View view = new AllPlacesView(this, places);
        setTemplateView(view);
    }
}
