package views.films;

import javafx.collections.ObservableList;
import libs.Controller;
import libs.View;
import models.Film;

public class AllFilmView extends View
{
    private ObservableList<Film> films;

    public AllFilmView (Controller controller, ObservableList<Film> films)
    {
        super(controller);
    }

    @Override
    public void display ()
    {
        films.forEach(System.out::println);
    }
}
