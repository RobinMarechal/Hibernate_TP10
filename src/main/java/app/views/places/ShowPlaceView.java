package app.views.places;

import app.models.Place;
import libs.mvc.Controller;
import libs.mvc.View;

public class ShowPlaceView extends View
{
    private final Place place;

    public ShowPlaceView (Controller controller, Place place)
    {
        super(controller);
        this.place = place;
    }

    @Override
    protected void setup ()
    {

    }

    @Override
    protected void display ()
    {

    }
}
