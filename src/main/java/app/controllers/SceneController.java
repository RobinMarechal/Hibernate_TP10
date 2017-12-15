package app.controllers;

import app.models.*;
import app.models.dao.SceneDAO;
import app.views.scenes.CreateSceneView;
import app.views.scenes.SceneDetailsDialog;
import app.views.scenes.ShowSceneView;
import fr.polytech.marechal.FormMap;
import fr.polytech.marechal.exceptions.ErrorType;
import fr.polytech.marechal.exceptions.FormException;
import libs.DayTime;
import libs.mvc.controllers.Controller;
import libs.mvc.views.View;
import libs.ui.components.dialogs.Dialog;
import libs.ui.components.dialogs.DialogsManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SceneController extends Controller<Scene, Integer, SceneDAO>
{
    public SceneController ()
    {
        super();
    }

    @Override
    protected SceneDAO getDao ()
    {
        return new SceneDAO();
    }

    @Override
    public void show (Integer id)
    {
        Scene scene = dao.find(id);
        View  view  = new ShowSceneView(this, scene);
        this.setTemplateView(view);
    }

    @Override
    public void showDetails (Scene scene)
    {
        List<Setup> setups;
        List<Clap>  claps;

        long nbSetups;
        long nbClaps;
        long totalClapDuration;

        setups = scene.getSetups();
        nbSetups = setups.size();

        claps = setups.stream().map(Setup::getClaps).collect(ArrayList::new, List::addAll, List::addAll);
        nbClaps = claps.size();

        totalClapDuration = claps.stream().map(Clap::getDuration).reduce((d1, d2) -> d1 + d2).get();

        HashMap<String, Long> details = new HashMap<>();
        details.put("nbSetups", nbSetups);
        details.put("nbClaps", nbClaps);
        details.put("totalClapDuration", totalClapDuration);

        Dialog dialog = new SceneDetailsDialog(this, scene, details);
        DialogsManager.instance.openDialog(dialog);
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
        update(null, form);
    }

    @Override
    public void update (Scene model, FormMap form)
    {
        if (!form.hasKeys("dayTime", "place", "description", "movie")) {
            throw new FormException(ErrorType.MISSING_FIELD);
        }

        DayTime dayTime     = (DayTime) form.get("dayTime").getValue();
        Place   place       = ((CreateSceneView.PlaceItem) form.get("place").getValue()).getPlace();
        String  description = (String) form.get("description").getValue();
        Movie   movie       = (Movie) form.get("movie").getValue();

        if (model == null) {
            model = new Scene(movie, place, dayTime, description);
            dao.persist(model);
        }
        else {
            model.setDescription(description);
            model.setPlace(place, dayTime);
            movie.addScenes(model);
        }

        DialogsManager.instance.closeLastOpened();
        this.show(model.getId());
    }

    @Override
    public void delete (Scene model)
    {

    }
}
