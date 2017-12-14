package app.controllers;

import app.models.Clap;
import app.models.dao.ClapDAO;
import app.models.pk.ClapPrimaryKey;
import fr.polytech.marechal.FormMap;
import libs.mvc.controllers.Controller;

public class ClapController extends Controller<Clap, ClapPrimaryKey, ClapDAO>
{
    public ClapController ()
    {
        super();
    }

    @Override
    protected ClapDAO prepareDAO ()
    {
        return new ClapDAO();
    }

    @Override
    public void show (ClapPrimaryKey id)
    {

    }

    @Override
    public void showDetails (Clap model)
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
    public void showUpdateDialog (Clap model)
    {

    }

    @Override
    public void showDeleteDialog (Clap model)
    {

    }

    @Override
    public void create (FormMap form)
    {

    }

    @Override
    public void update (Clap model, FormMap form)
    {

    }

    @Override
    public void delete (Clap model)
    {

    }
}
