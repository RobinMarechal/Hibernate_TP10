package app.views.setups;

import app.models.Setup;
import libs.mvc.Controller;
import libs.mvc.View;

public class ShowSetupView extends View
{
    private final Setup setups;

    public ShowSetupView (Controller controller, Setup setups)
    {
        super(controller);
        this.setups = setups;
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
