package app.controllers;

import app.models.Place;
import app.views.places.AllPlacesView;
import libs.mvc.Controller;
import libs.mvc.Home;
import libs.mvc.View;

import java.util.List;

/**
 * Controls both Theatre and ExternalPlace classes
 */
public class PlaceController extends Controller<Integer> implements Home
{
    public PlaceController ()
    {
        super();
    }

    @Override
    public void show (Integer id)
    {

    }

    @Override
    public void showAll ()
    {
        List<Place> places = em.createQuery("FROM Place").getResultList();
        View view = new AllPlacesView(this, places);
        setTemplateView(view);
    }

    @Override
    public void home ()
    {
        showAll();
    }
}
