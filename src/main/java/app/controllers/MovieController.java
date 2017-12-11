package app.controllers;

import app.models.Movie;
import app.views.movies.AllMoviesView;
import app.views.movies.ShowMovieView;
import libs.mvc.Controller;
import libs.mvc.Home;
import libs.mvc.View;

import java.io.Serializable;
import java.util.List;

public class MovieController extends Controller implements Home
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
    public void show (Serializable id)
    {
        Movie Movie = em.find(Movie.class, id);
        View  view = new ShowMovieView(this, Movie);
        this.setTemplateView(view);
    }

    @Override
    public void showAll ()
    {
        List<Movie> movies = (List<Movie>) em.createQuery("FROM Movie").getResultList();
        AllMoviesView view = new AllMoviesView(this, movies);
        this.setTemplateView(view);
    }
}
