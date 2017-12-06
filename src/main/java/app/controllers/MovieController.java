package app.controllers;

import app.models.Movie;
import app.views.films.AllMoviesView;
import app.views.films.ShowMovieView;
import libs.mvc.Controller;
import libs.mvc.View;
import libs.ui.template.Template;

import java.util.List;

public class MovieController extends Controller
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
    public void show (int id)
    {
        Movie film = em.find(Movie.class, id);
        View  view = new ShowMovieView(this, film);
        view.display();
    }

    @Override
    public void showAll ()
    {
        List<Movie> movies = (List<Movie>) em.createQuery("FROM Movie").getResultList();

        //        for (Movie movie : movies) {
        //            System.out.println(movie);
        //        }

        Template.getInstance().setView(new AllMoviesView(this, movies));
    }
}
