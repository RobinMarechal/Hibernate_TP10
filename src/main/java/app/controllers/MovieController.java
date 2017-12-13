package app.controllers;

import app.models.Movie;
import app.models.Place;
import app.models.Producer;
import app.models.Scene;
import app.views.movies.AllMoviesView;
import app.views.movies.CreateMovieDialog;
import app.views.movies.ShowMovieView;
import fr.polytech.marechal.FormMap;
import libs.DayTime;
import libs.PlaceType;
import libs.mvc.View;
import libs.mvc.controllers.Controller;
import libs.mvc.controllers.Home;
import libs.mvc.controllers.ModelManager;
import libs.ui.components.dialogs.Dialog;
import libs.ui.components.dialogs.DialogsManager;

import java.util.ArrayList;
import java.util.List;

public class MovieController extends Controller<Integer> implements Home, ModelManager<Movie>
{
    public MovieController ()
    {
        super();
    }

    @Override
    public void home ()
    {
        this.showAll();
    }

    @Override
    public void show (Integer id)
    {
        Movie Movie = em.find(Movie.class, id);
        View  view  = new ShowMovieView(this, Movie, Movie.getScenes());
        this.setTemplateView(view);
    }

    @Override
    public void showAll ()
    {
        List<Movie>   movies = (List<Movie>) em.createQuery("FROM Movie").getResultList();
        AllMoviesView view   = new AllMoviesView(this, movies);
        this.setTemplateView(view);
    }

    /**
     * Show the dialog for the modification or the creation of the model
     *
     * @param model the model to update, or null for a creation
     */
    @Override
    public void showCreationDialog (Movie model)
    {
        List<Producer> producers = em.createQuery("FROM Producer").getResultList();

        Dialog dialog = new CreateMovieDialog(this, producers);
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

    }

    /**
     * Create an instance of the Model and persists it to the database
     *
     * @param form the form with the model's data
     */
    @Override
    public void create (FormMap form)
    {
        String   directorName = ((String) form.get("director").getValue());
        String   title        = ((String) form.get("title").getValue());
        Producer producer     = ((Producer) form.get("producer").getValue());

        Movie created = new Movie(title, directorName, producer);
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
    public void delete (Movie model)
    {
    }

    /**
     * Update a model and persists the changes to in the database
     *
     * @param model the model to update
     * @param form  the form with the model's data
     */
    @Override
    public void update (Movie model, FormMap form)
    {
    }

    public void showScenesOfMovieAtPlaceType (Movie movie, PlaceType placeType)
    {
        System.out.println(placeType);
        String hql;
        if (placeType == PlaceType.THEATRE) {
            hql = "FROM Scene s INNER JOIN s.movie ON s.movie.id = 2 INNER JOIN s.place p WHERE p.class = Theatre";
        }
        else {
            hql = "FROM Scene s INNER JOIN s.movie ON s.movie.id = 2 INNER JOIN s.place p WHERE p.class = ExternalPlace";
        }

        List<Object[]> results = em.createQuery(hql).getResultList();
        List<Scene> scenes = new ArrayList<>();

        for (Object[] result : results) {
            Scene scene = (Scene) result[0];
            Place place = (Place) result[2];

            scene.setPlace(place);
            scenes.add(scene);
        }

        for (Scene scene : scenes) {
            System.out.println(scene);
        }

        View view = new ShowMovieView(this, movie, scenes);
        setTemplateView(view);
    }

    public void showScenesOfMovieAtDayTime (Movie movie, DayTime dayTime)
    {
//        List<Scene> scenes = Query.forModel(Scene.class, em).whereEqual("id", movie.getId()).whereEqual("dayTime", dayTime).get();
        List<Scene> scenes = em.createQuery("FROM Scene s WHERE s.dayTime = :dayTime AND s.movie.id = :movieId")
                            .setParameter("dayTime", dayTime)
                            .setParameter("movieId", movie.getId())
                            .getResultList();
        View view = new ShowMovieView(this, movie, scenes);
        setTemplateView(view);
    }
}
