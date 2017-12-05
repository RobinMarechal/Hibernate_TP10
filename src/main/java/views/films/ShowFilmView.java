package views.films;

import libs.Controller;
import libs.View;
import models.Film;

public class ShowFilmView extends View
{
    private Film film;

    public ShowFilmView (Controller controller, Film film)
    {
        super(controller);
    }

    @Override
    public void display ()
    {
        System.out.println(film);
    }
}
