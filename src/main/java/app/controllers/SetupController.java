package app.controllers;

import app.models.Setup;
import app.models.dao.SetupDAO;
import fr.polytech.marechal.FormMap;
import libs.mvc.controllers.Controller;

public class SetupController extends Controller<Setup, Integer, SetupDAO>
{
    public SetupController ()
    {
        super();
    }

    /**
     * Get the controlled model's DAO instance
     *
     * @return the controlled model's DAO instance
     */
    @Override
    protected SetupDAO getDao ()
    {
        return new SetupDAO();
    }

    /**
     * Show the view that displays one model's instance
     *
     * @param id the primary key
     */
    @Override
    public void show (Integer id)
    {
        System.out.println("Showing setup of id " + id);
    }

    /**
     * Show the details of a model instance
     *
     * @param model the model instance
     */
    @Override
    public void showDetails (Setup model)
    {
        
    }

    /**
     * Show the view that displays the list of data
     */
    @Override
    public void showAll ()
    {

    }

    /**
     * Show the dialog for the modification or the creation of the model
     */
    @Override
    public void showCreationDialog ()
    {

    }

    /**
     * Show the dialog for the modification or the creation of the model
     *
     * @param model the model to update, or null for a creation
     */
    @Override
    public void showUpdateDialog (Setup model)
    {

    }

    /**
     * Show the deletion dialog
     *
     * @param model the model to delete
     */
    @Override
    public void showDeleteDialog (Setup model)
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

    }

    /**
     * Update a model and persists the changes to in the database
     *
     * @param model the model to update. If model is null, it will create a new instance and persist it
     * @param form  the form with the model's data
     */
    @Override
    public void update (Setup model, FormMap form)
    {

    }

    /**
     * Delete a model instance and persists it to the database
     *
     * @param model the model instance to delete
     */
    @Override
    protected void delete (Setup model)
    {

    }

}