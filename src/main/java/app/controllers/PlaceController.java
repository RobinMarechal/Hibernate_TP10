package app.controllers;

import app.models.ExternalPlace;
import app.models.Place;
import app.models.Theatre;
import app.views.places.AllPlacesView;
import app.views.places.CreatePlaceDialog;
import app.views.places.ShowPlaceView;
import fr.polytech.marechal.FormMap;
import fr.polytech.marechal.exceptions.ErrorType;
import fr.polytech.marechal.exceptions.FormException;
import libs.PlaceType;
import libs.mvc.View;
import libs.mvc.controllers.Controller;
import libs.mvc.controllers.Home;
import libs.mvc.controllers.ModelManager;
import libs.ui.components.dialogs.DialogsManager;

import java.util.List;

/**
 * Controls both Theatre and ExternalPlace classes
 */
public class PlaceController extends Controller<Integer> implements Home, ModelManager<Place>
{
    public PlaceController ()
    {
        super();
    }

    @Override
    public void show (Integer id)
    {
        Place place = em.find(Place.class, id);
        View  view  = new ShowPlaceView(this, place);
        setTemplateView(view);
    }

    @Override
    public void showAll ()
    {
        List<Place> places = em.createQuery("FROM Place").getResultList();
        View        view   = new AllPlacesView(this, places);
        setTemplateView(view);
    }

    @Override
    public void home ()
    {
        showAll();
    }

    /**
     * Show the dialog for the modification or the creation of the model
     *
     * @param model the model to update, or null for a creation
     */
    @Override
    public void showCreationDialog (Place model)
    {
        CreatePlaceDialog dialog = new CreatePlaceDialog(this);
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

    }

    /**
     * Create an instance of the Model and persists it to the database
     *
     * @param form the form with the model's data
     */
    @Override
    public void create (FormMap form)
    {
        System.out.println(form);
        if (!form.hasKeys("name", "address", "type", "description")) {
            throw new FormException(ErrorType.MISSING_FIELD);
        }

        String    name        = ((String) form.get("name").getValue());
        String    address     = ((String) form.get("address").getValue());
        PlaceType type        = ((PlaceType) form.get("type").getValue());
        String    description = ((String) form.get("description").getValue());

        Place place;

        if (type == PlaceType.THEATRE) {
            place = new Theatre(name, address, description);
        }
        else {
            place = new ExternalPlace(name, address, description);
        }

        place.persist();
        DialogsManager.instance.closeLastOpened();

        this.show(place.getId());
    }

    /**
     * Delete a model instance and persists it to the database
     *
     * @param model the model instance to delete
     */
    @Override
    public void delete (Place model)
    {
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
    }

    public void showAllOfType (PlaceType item)
    {
        String query = "FROM ";

        if (item == PlaceType.THEATRE) {
            query += "Threatre";
        }
        else {
            query += "ExternalPlace";
        }

        List<Place> places = em.createQuery(query).getResultList();
        View        view   = new AllPlacesView(this, places);
        setTemplateView(view);
    }
}
