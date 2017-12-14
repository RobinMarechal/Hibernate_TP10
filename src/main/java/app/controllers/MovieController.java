package app.controllers;

import app.models.Movie;
import app.models.Producer;
import app.models.Scene;
import app.models.dao.MovieDAO;
import app.models.dao.ProducerDAO;
import app.views.movies.AllMoviesView;
import app.views.movies.CreateMovieDialog;
import app.views.movies.ShowMovieView;
import fr.polytech.marechal.FormMap;
import fr.polytech.marechal.exceptions.ErrorType;
import fr.polytech.marechal.exceptions.FormException;
import libs.DayTime;
import libs.PlaceType;
import libs.mvc.controllers.Controller;
import libs.mvc.controllers.Home;
import libs.mvc.views.View;
import libs.ui.components.dialogs.Dialog;
import libs.ui.components.dialogs.DialogsManager;

import java.util.List;

public class MovieController extends Controller<Movie, Integer, MovieDAO> implements Home
{
    private final MovieDAO dao = new MovieDAO();

    public MovieController ()
    {
        super();
    }

    @Override
    protected MovieDAO prepareDAO ()
    {
        return new MovieDAO();
    }

    @Override
    public void home ()
    {
        this.showAll();
    }

    @Override
    public void show (Integer id)
    {
        Movie Movie = dao.find(id);
        View  view  = new ShowMovieView(this, Movie, Movie.getScenes());
        this.setTemplateView(view);
    }

    @Override
    public void showDetails (Movie model)
    {

    }

    @Override
    public void showAll ()
    {
        List<Movie>   movies = dao.all();
        AllMoviesView view   = new AllMoviesView(this, movies);
        this.setTemplateView(view);
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
    public void showUpdateDialog (Movie movie)
    {
        List<Producer> producers = new ProducerDAO().all();

        Dialog dialog = new CreateMovieDialog(this, movie, producers);
        DialogsManager.instance.openDialog(dialog);
    }

    /**
     * Show the deletion dialog
     *
     * @param model the model to delete
     */
    @Override
    public void showDeleteDialog (Movie model)
    {
        this.showDeleteDialogForClass(model, "movie");
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
     * @param model the model to update. If model is null, it will create a new instance and persist it
     * @param form  the form with the model's data
     */
    @Override
    public void update (Movie model, FormMap form)
    {
        if(!form.hasKeys("title", "director", "producer"))
            throw new FormException(ErrorType.MISSING_FIELD);

        String   directorName = ((String) form.get("director").getValue());
        String   title        = ((String) form.get("title").getValue());
        Producer producer     = ((Producer) form.get("producer").getValue());

        if (model == null) {
            model = new Movie(title, directorName, producer);
            dao.persist(model);
        }
        else {
            model.setTitle(title);
            model.setDirector(directorName);
            model.setProducer(producer);
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
    public void delete (Movie model)
    {
        dao.remove(model);
        this.showAll();
    }

    public void showScenesOfMovieAtPlaceType (Movie movie, PlaceType placeType)
    {
        List<Scene> scenes = dao.getScenesAtPlaceType(movie.getId(), placeType);

        View view = new ShowMovieView(this, movie, scenes);
        setTemplateView(view);
    }

    public void showScenesOfMovieAtDayTime (Movie movie, DayTime dayTime)
    {
        List<Scene> scenes = dao.getScenesAtDayTime(movie.getId(), dayTime);

        View view = new ShowMovieView(this, movie, scenes);
        setTemplateView(view);
    }
}
