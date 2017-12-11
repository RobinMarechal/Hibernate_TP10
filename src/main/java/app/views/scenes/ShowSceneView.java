package app.views.scenes;

import app.models.Scene;
import libs.mvc.Controller;
import libs.mvc.View;

public class ShowSceneView extends View
{
    private final Scene scene;

    public ShowSceneView (Controller controller, Scene scene)
    {
        super(controller);
        this.scene = scene;
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
