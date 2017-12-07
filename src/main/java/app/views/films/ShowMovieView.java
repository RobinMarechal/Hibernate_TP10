package app.views.films;

import libs.mvc.Controller;
import libs.mvc.View;

public class ShowMovieView extends View
{
    private Movie film;

    public ShowMovieView (Controller controller, Movie film)
    {
        super(controller);
    }

    @Override
    protected void setup ()
    {

    }

    @Override
    public void display ()
    {
        System.out.println(film);
    }
}
