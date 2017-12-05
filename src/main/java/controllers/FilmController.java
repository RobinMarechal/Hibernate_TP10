package controllers;

import javafx.collections.ObservableList;
import libs.Controller;
import libs.View;
import models.Film;
import views.films.AllFilmView;
import views.films.ShowFilmView;

import javax.persistence.EntityManager;

public class FilmController extends Controller
{
    public FilmController (EntityManager em)
    {
        super(em);
    }

    @Override
    public void show (Object id)
    {
        Film film = em.find(Film.class, id);
        View view = new ShowFilmView(this, film);
        view.display();
    }

    @Override
    public void showAll ()
    {
        ObservableList<Film> films = (ObservableList<Film>) em.createQuery("FROM Film").getResultList();

        View view = new AllFilmView(this, films);
        view.display();
    }
}
