package app.views.movies;

import app.controllers.MovieController;
import app.models.Movie;
import libs.mvc.View;

public class ShowMovieView extends View
{
    private final Movie movie;

    public ShowMovieView (MovieController controller, Movie movie)
    {
        super(controller);
        this.movie = movie;
    }

    @Override
    protected void setup ()
    {

    }

    @Override
    protected void display ()
    {
        System.out.println(movie);
    }
}
