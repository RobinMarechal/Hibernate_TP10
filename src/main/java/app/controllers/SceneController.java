package app.controllers;

import app.models.Scene;
import app.models.dao.SceneDAO;
import fr.polytech.marechal.FormMap;
import libs.mvc.controllers.Controller;

public class SceneController extends Controller<Scene, Integer, SceneDAO>
{
    public SceneController ()
    {
        super();
    }

    @Override
    protected SceneDAO prepareDAO ()
    {
        return new SceneDAO();
    }

    @Override
    public void show (Integer id)
    {

    }

    @Override
    public void showDetails (Scene model)
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
    public void showUpdateDialog (Scene model)
    {

    }

    @Override
    public void showDeleteDialog (Scene model)
    {

    }

    @Override
    public void create (FormMap form)
    {

    }

    @Override
    public void update (Scene model, FormMap form)
    {

    }

    @Override
    public void delete (Scene model)
    {

    }
}
