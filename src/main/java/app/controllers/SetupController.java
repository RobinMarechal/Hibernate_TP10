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

    @Override
    protected SetupDAO getDao ()
    {
        return new SetupDAO();
    }

    @Override
    public void show (Integer id)
    {
        System.out.println("Showing setup n°" + id + "...");
    }

    @Override
    public void showDetails (Setup model)
    {

    }

    @Override
    public void showAll ()
    {

    }

    @Override
    public void showCreationDialog ()
    {

    }

    @Override
    public void showUpdateDialog (Setup model)
    {

    }

    @Override
    public void showDeleteDialog (Setup model)
    {

    }

    @Override
    public void create (FormMap form)
    {

    }

    @Override
    public void update (Setup model, FormMap form)
    {

    }

    @Override
    public void delete (Setup model)
    {

    }
}
